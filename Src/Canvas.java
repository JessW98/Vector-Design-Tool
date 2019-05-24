import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;


public class Canvas extends JPanel {
    //Permanent canvas color
    private static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;

    //Used to save a snapshot of the canvas
    private Image captureCanvas;

    //The graphics 2d object that is used by the paintComponent method to draw
    private Graphics2D drawController;

    //Colors used for shape outlines and fills
    private Color penColor = Color.BLACK;
    private Color fillColor = null;

    //Point objects used to track users mouse movements
    private Point origin;
    private Point destination;

    //ShapeType object used to track users shape selection
    private GUI.ShapeType currentSelectedShape = GUI.ShapeType.LINE;

    //List used to keep a track of which shapes have been drawn
    private List<ShapeControl> ShapesDrawn = new ArrayList<>();

    private class Mouse extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            origin = e.getPoint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            if(!ShapesDrawn.isEmpty())
                ShapesDrawn.remove(ShapesDrawn.size() - 1);
            DrawShapeAt(e);
            repaint();

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            DrawShapeAt(e);
            repaint();
        }
    }

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
                        y1=y2;
                        y2=ytemp;
                    }
                    if (x1 > x2){
                        int xtemp = x1;
                        x1=x2;
                        x2=xtemp;
                    }
                    CustomRectangle Rectangle = new CustomRectangle(x1, y1,
                            abs(x1 - x2), abs(y1 - y2), penColor,
                            fillColor);
                    ShapesDrawn.add(Rectangle);
                    break;
                case LINE:
                    CustomLine Line = new CustomLine(x1, y1, x2, y2, penColor);
                    ShapesDrawn.add(Line);
                    break;
                case ELLIPSE:
                    CustomEllipse Ellipse = new CustomEllipse(x1, y1, abs(x1 - x2),
                            abs(y1 - y2), penColor,fillColor);
                    ShapesDrawn.add(Ellipse);
                    break;
                case PLOT:
                    CustomPlot Plot = new CustomPlot(x1 - 2, y1 - 2, Color.BLACK, 4, 4);
                    ShapesDrawn.add(Plot);
                    break;
            }
        }
    }

    //setup the drawing area, enable mouse input when pressed and dragged.
    public Canvas(){
        setDoubleBuffered(false);
        setBackground(DEFAULT_BACKGROUND_COLOUR);
        Mouse minnie = new Mouse();
        addMouseListener(minnie);
        addMouseMotionListener(minnie);
    }

    //clears the canvas
    public void clear(){
        ShapesDrawn.clear();
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

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawController = (Graphics2D) g;

        for(ShapeControl i : ShapesDrawn){

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


    public Color getPenColor() {
        return penColor;
    }

    public void setPenColor(Color penColor) {
        this.penColor = penColor;
    }

    public void setCurrentSelectedShape(GUI.ShapeType currentSelectedShape) {
        this.currentSelectedShape = currentSelectedShape;
    }

    public GUI.ShapeType getCurrentSelectedShape() {
        return currentSelectedShape;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

}
