import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

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

    public List<ShapeControl> GetShapesDrawn() {
        return shapesDrawn;
    }

    /**
     * Nested class that handles mouse inputs.<br>
     * Delivers 2 points that pass on x and y co-ordinates
     * to the shapes to be drawn.
     */
    private class Mouse extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            origin = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if (currentSelectedShape != GUI.ShapeType.POLYGON) {
                if (!shapesDrawn.isEmpty() && CheckLastShape(currentSelectedShape))
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
     * This method updates the coordinates of all the shapes based off how much
     * they need to expand/contract and in which direction.
     * @param widthFactor The new width as a decimal of the current width.
     *                    for example:
     *                                1   : no change
     *                                1.1 : expand width 10%
     *                                0.9 : shrink width 10%
     * @param heightFactor The new height as a decimal of the current height.
     *                     for example:
     *                                1   : no change
     *                                1.1 : expand height 10%
     *                                0.9 : shrink height 10%
     * @return Nothing.
     */
    public void ResizeShapes(double widthFactor, double heightFactor)
    {
        for (int i = 0; i < shapesDrawn.size(); i++)
        {
            ShapeControl shape = shapesDrawn.get(i);
            switch (shapesDrawn.get(i).GetShapeType())
            {
                case PLOT:
                    double x =((CustomPlot)shape).getX();
                    double y =((CustomPlot)shape).getY();

                    x *= widthFactor;
                    y *= heightFactor;

                    ((CustomPlot)shape).setX(x);
                    ((CustomPlot)shape).setY(y);
                    break;
                case LINE:
                    double lx1 =((CustomLine)shape).getX1();
                    double ly1 =((CustomLine)shape).getY1();
                    double lx2 =((CustomLine)shape).getX2();
                    double ly2 =((CustomLine)shape).getY2();

                    lx1 *= widthFactor;
                    ly1 *= heightFactor;
                    lx2 *= widthFactor;
                    ly2 *= heightFactor;

                    ((CustomLine)shape).SetX1(lx1);
                    ((CustomLine)shape).SetY1(ly1);
                    ((CustomLine)shape).SetX2(lx2);
                    ((CustomLine)shape).SetY2(ly2);
                    break;
                case RECTANGLE:
                    double rx1 =((CustomRectangle)shape).getX();
                    double ry1 =((CustomRectangle)shape).getY();
                    double rx2 =((CustomRectangle)shape).getX2();
                    double ry2 =((CustomRectangle)shape).getY2();

                    rx1 *= widthFactor;
                    ry1 *= heightFactor;
                    rx2 *= widthFactor;
                    ry2 *= heightFactor;

                    ((CustomRectangle)shape).setX1(rx1);
                    ((CustomRectangle)shape).setY1(ry1);
                    ((CustomRectangle)shape).setX2(rx2);
                    ((CustomRectangle)shape).setY2(ry2);
                    break;
                case ELLIPSE:
                    double ex1 =((CustomEllipse)shape).getX();
                    double ey1 =((CustomEllipse)shape).getY();
                    double ex2 =((CustomEllipse)shape).getX2();
                    double ey2 =((CustomEllipse)shape).getY2();

                    ex1 *= widthFactor;
                    ey1 *= heightFactor;
                    ex2 *= widthFactor;
                    ey2 *= heightFactor;

                    ((CustomEllipse)shape).setX1(ex1);
                    ((CustomEllipse)shape).setY1(ey1);
                    ((CustomEllipse)shape).setX2(ex2);
                    ((CustomEllipse)shape).setY2(ey2);
                    break;
                case POLYGON:
                    int[] XCoords = ((CustomPolygon)shape).getXCoordinates();
                    int[] YCoords = ((CustomPolygon)shape).getYCoordinates();

                    for (int z = 0; z < XCoords.length; z++)
                    {
                        XCoords[z] *= widthFactor;
                        YCoords[z] *= heightFactor;
                    }
                    ((CustomPolygon) shape).setXCoordinates(XCoords);
                    ((CustomPolygon) shape).setYCoordinates(YCoords);
                    break;
            }
        }
        repaint();
    }

    /**
     * Draws the currently selected shape where the mouse coordinates are.
     * @param e This is the event passed from the mouse.
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
                    CustomPlot plot = new CustomPlot(x2 - 2, y2 - 2, penColor, 4, 4);
                    shapesDrawn.add(plot);
                    break;
                case POLYGON:
                        // If there is already a polygon started, add to it, otherwise, create one
                        if (!shapesDrawn.isEmpty() && shapesDrawn.get(
                                shapesDrawn.size() - 1).GetShapeType() == GUI.ShapeType.POLYGON) {
                            CustomPolygon polyInProgress =
                                    (CustomPolygon) shapesDrawn.get(shapesDrawn.size() - 1).GetShape();
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

    /**
     * This method erases the shapes stored in <i>shapesDrawn</i> and replaces it with the a new list of <i>Shape</i> objects.
     * @param newShapeList The list of <i>Shapes</i> that will override the current list.
     * @return Nothing.
     */
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
     * @return Nothing.
     */
    public void Clear(){
        shapesDrawn.clear();
        repaint();
    }

    private void saveImage(Graphics g){
        if(captureCanvas == null){
            captureCanvas = createImage(getSize().width, getSize().height);
            drawController = (Graphics2D) captureCanvas.getGraphics();
        }
        g.drawImage(captureCanvas, 0, 0, null);
    }

    /**
     *  Paints the ShapeControl objects onto the canvas.
     * @param g The graphics object that will draw te shapes.
     * @return Nothing.
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
     * @return Nothing.
     */
    public void RemoveLastShape() {
        shapesDrawn.remove(shapesDrawn.size() - 1);
    }

    /**
     * Checks if the last shape drawn is of the specified type.
     * @param shapeType The shape type being checked for.
     * @return True if the last shape is the specified type or not.
     */
    public boolean CheckLastShape(GUI.ShapeType shapeType) {
        ShapeControl shapeBeingChecked = shapesDrawn.get(shapesDrawn.size()-1);
        GUI.ShapeType typeOfShapeBeingChecked = shapeBeingChecked.GetShapeType();

        if (typeOfShapeBeingChecked == GUI.ShapeType.POLYGON ||
                typeOfShapeBeingChecked == GUI.ShapeType.RECTANGLE ||
                typeOfShapeBeingChecked == GUI.ShapeType.ELLIPSE)
        {
            if (typeOfShapeBeingChecked == shapeType && shapeBeingChecked.getShapeFillColour() == fillColor &&
                    shapeBeingChecked.getShapePenColour() == penColor)
            {
                return true;
            }
        }
        else if (typeOfShapeBeingChecked == GUI.ShapeType.LINE ||
                typeOfShapeBeingChecked == GUI.ShapeType.PLOT)
        {
            if (typeOfShapeBeingChecked == shapeType && shapeBeingChecked.getShapePenColour() == penColor)
            {
                return true;
            }
        }

        return false;
    }

    /**
     * Returns the current pen colour in use by the canvas as a <i>Color</i> object.
     * @return The current pen colour in use by the canvas as a <i>Color</i> object.
     */
    public Color GetPenColor() {
        return penColor;
    }

    /**
     * Sets the Pen Color in use by the canvas to change shape border color.
     * @param penColor The desired <i>Color</i>.
     */
    public void SetPenColor(Color penColor) {
        this.penColor = penColor;
    }

    /**
     * Sets the currently selected shape.
     * @param currentSelectedShape The shape to be selected.
     * @return Nothing.
     */
    public void SetCurrentSelectedShape(GUI.ShapeType currentSelectedShape) {
        this.currentSelectedShape = currentSelectedShape;
    }

    /**
     * Returns the shape that is currently selected.
     * @return The shape that is currently selected.
     */
    public GUI.ShapeType GetCurrentSelectedShape() {
        return currentSelectedShape;
    }

    /**
     * Returns the current fill color being used by the canvas to fill shapes.
     * @return The current fill color.
     */
    public Color GetFillColor() {
        return fillColor;
    }

    /**
     * Sets the current fill Color for shapes being drawn on the canvas.
     * @param fillColor The desired <i>Color</i> for the fill.
     * @return Nothing.
     */
    public void SetFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }
}
