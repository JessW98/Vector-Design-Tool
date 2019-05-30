import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;

/**
 * <h1>Canvas</h1>
 * <p>
 * The Canvas class controls the drawing space for the
 * vector design tool.
 * </p>
 * @author Jessica Williams, William Daley, Jacob Kraut
 * @version 1.0
 * @since 2019-05-03
 */
public class Canvas extends JPanel {
    private static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;

    private Image captureCanvas;

    private Graphics2D drawController;

    private Color penColor = Color.BLACK;
    private Color fillColor = null;

    private Point origin;
    private Point destination;

    private GUI.ShapeType currentSelectedShape = GUI.ShapeType.LINE;

    private List<ShapeControl> shapesDrawn = new ArrayList<>();

    public List<ShapeControl> getShapesDrawn() {
        return shapesDrawn;
    }

    /**
     * Nested class that handles mouse inputs.<br>
     * Delivers 2 points that pass on x and y co-ordinates
     * to the shapes to be drawn.
     *
     */
    private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            origin = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentSelectedShape != GUI.ShapeType.POLYGON) {
                if (!shapesDrawn.isEmpty() &&
                        shapesDrawn.get(shapesDrawn.size() - 1).
                                GetShapeType() == currentSelectedShape)
                    RemoveLastShape();
                DrawShapeAt(e);
                repaint();
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            DrawShapeAt(e);
            repaint();
        }
    }

    /**
     * Draws a shape where the mouse coordinates are.
     *
     * @param e This is the event passed from the mouse
     * @return Nothing.
     */
    private void DrawShapeAt(MouseEvent e)
    {
        destination = e.getPoint();

        if(origin != null && destination != null){

            int x1 = origin.x;
            int y1 = origin.y;
            int x2 = destination.x;
            int y2 = destination.y;


            switch(currentSelectedShape){
                case RECTANGLE:
                    if (y1 > y2){
                        int ytemp = y1;
                        y1 = y2;
                        y2 = ytemp;
                    }
                    if (x1 > x2){
                        int xtemp = x1;
                        x1 = x2;
                        x2 = xtemp;
                    }
                    CustomRectangle Rectangle = new CustomRectangle(x1, y1, x2, y2, penColor, fillColor);
                    shapesDrawn.add(Rectangle);
                    break;
                case LINE:
                    CustomLine Line = new CustomLine(x1, y1, x2, y2, penColor);
                    shapesDrawn.add(Line);
                    break;
                case ELLIPSE:
                    if (y1 > y2){
                        int ytemp = y1;
                        y1 = y2;
                        y2 = ytemp;
                    }
                    if (x1 > x2){
                        int xtemp = x1;
                        x1 = x2;
                        x2 = xtemp;
                    }
                    CustomEllipse Ellipse = new CustomEllipse(x1, y1, x2, y2, penColor,fillColor);
                    shapesDrawn.add(Ellipse);
                    break;
                case PLOT:
                    CustomPlot Plot = new CustomPlot(x2 - 2, y2 - 2, Color.BLACK, 4, 4);
                    shapesDrawn.add(Plot);
                    break;
                case POLYGON:
                        // If there is already a polygon started, add to it, otherwise, create one
                        if (!shapesDrawn.isEmpty() && shapesDrawn.get(
                                shapesDrawn.size() - 1).GetShapeType() ==
                                GUI.ShapeType.POLYGON) {
                            CustomPolygon polyInProgress = (CustomPolygon)
                                    shapesDrawn.get(shapesDrawn.size() - 1).
                                            GetShape();
                            polyInProgress.addPoint(x2, y2);
                            RemoveLastShape();
                            shapesDrawn.add(polyInProgress);
                        }
                        else {
                            //Creating a list of coordinates for the polygon
                            double[] firstPoint = {x2,y2};
                            List<double[]> polygonCoordinates = new ArrayList<>();
                            polygonCoordinates.add(firstPoint);

                            CustomPolygon polygon = new CustomPolygon(
                                    polygonCoordinates, penColor, fillColor);
                            shapesDrawn.add(polygon);
                        }
                    break;
            }
        }
    }

    public void OverrideCanvas(List<Shape> newShapeList)
    {
        shapesDrawn.clear();
        for (int i = 0; i < newShapeList.size(); i++)
        {
            shapesDrawn.add((ShapeControl) newShapeList.get(i));
        }
        repaint();
    }

    /**
     * Default Constructor.
     * Sets up the drawing area and enables mouse inputs.
     */
    public Canvas(){
        setDoubleBuffered(false);
        setBackground(DEFAULT_BACKGROUND_COLOUR);
        Mouse minnie = new Mouse();
        addMouseListener(minnie);
        addMouseMotionListener(minnie);
    }

    /**
     * Clears the canvas. Removing any drawn shapes on the
     * canvas.
     */
    public void clear(){
        shapesDrawn.clear();
        repaint();
    }

    //creates image
    public void saveImage(Graphics g){
        if(captureCanvas == null){
            captureCanvas = createImage(getSize().width, getSize().height);
            drawController = (Graphics2D) captureCanvas.getGraphics();
        }
        g.drawImage(captureCanvas, 0, 0, null);
    }

    /**
     *  Paints the ShapeControl objects onto the canvas.
     */
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawController = (Graphics2D) g;

        for(ShapeControl i : shapesDrawn){

            if(i.getShapeFillColour() != null){
                drawController.setPaint(i.getShapeFillColour());
                drawController.fill(i);
            }

            drawController.draw(i);

            if(i.getShapePenColour() != null){
                drawController.setPaint(i.getShapePenColour());
            }

            drawController.draw(i);
        }
    }

    /**
     * Removes the Last Shape drawn on the canvas.
     */
    public void RemoveLastShape() {
        shapesDrawn.remove(shapesDrawn.size() - 1);
    }

    /**
     * Checks the Shape in shapesDrawn related to the index
     *  against the shapeType.
     * @param index, GUi.ShapeType shapeType
     */
    public boolean checkShapeAt(int index, GUI.ShapeType shapeType) {
        GUI.ShapeType shapeBeingChecked = shapesDrawn.get(index).GetShapeType();
        if (shapeBeingChecked == shapeType)
            return true;
        return false;
    }

    /**
     * Returns the current pen colour in use by the canvas.
     * @return Color penColor
     */
    public Color getPenColor() {
        return penColor;
    }

    /**
     * Sets the Pen Color in use by the canvas to change shape border color.
     * @param penColor
     */
    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     * Sets the currently selected shape.
     * @param currentSelectedShape
     */
    public void setCurrentSelectedShape(GUI.ShapeType currentSelectedShape) {
        this.currentSelectedShape = currentSelectedShape;
    }

    /**
     * Returns the currentSelected Shape.
     * @return currentSelectedShape
     */
    public GUI.ShapeType getCurrentSelectedShape() {
        return currentSelectedShape;
    }

    /**
     * Returns the current fill color being used by the canvas to fill shapes.
     * @return fillColor
     */
    public Color getFillColor() {
        return fillColor;
    }

    /**
     * Sets the current fill Color for shapes being drawn on the canvas.
     * @return fillColor
     */
    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

}
