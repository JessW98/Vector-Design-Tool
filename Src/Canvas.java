import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;
import static java.lang.Math.abs;


public class Canvas extends JPanel {
    //PROPERTIES
    private static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;
    //Used to save drawing
    private Image captureCanvas;
    //The graphics 2d object that user will draw on
    private Graphics2D graphic;
    private Graphics2D draw;
    private Color drawColour = Color.BLACK;

    private Shape shapeToBeAdded;

    private GUI.ShapeType currentSelectedShape;
    private Point origin;
    private Point destination;
    private List<Shape> ShapesDrawn = new ArrayList<>();


    private class Mouse extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            origin = e.getPoint();
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            destination = e.getPoint();
            repaint();
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            destination = e.getPoint();
            repaint();
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

//    clears the canvas
    public void clear(){
        graphic.setPaint(Color.WHITE);
        graphic.fillRect(0,0,getSize().width, getSize().height);
        graphic.setPaint(Color.black);
        repaint();
    }

    //creates image
    public void saveImage(Graphics g){
        if(captureCanvas == null){
            captureCanvas = createImage(getSize().width, getSize().height);
            graphic = (Graphics2D) captureCanvas.getGraphics();
        }
        g.drawImage(captureCanvas, 0, 0, null);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw = (Graphics2D) g;
        draw.setPaint(drawColour);

        if(origin != null && destination != null) {
            int x1 = origin.x;
            int y1 = origin.y;
            int x2 = destination.x;
            int y2 = destination.y;
            int plotRadius = 4;

            GUI.ShapeType shape = getCurrentSelectedShape();

            switch (shape) {
                case RECTANGLE:
                    Shape Rectangle = new Rectangle2D.Float(x1, y1, abs(x1 - x2),
                            abs(y1 - y2));
                    ShapesDrawn.add(Rectangle);
                    break;
                case ELLIPSE:
                    ShapesDrawn.add(new Ellipse2D.Float(x1, y1, abs(x1 - x2),
                            abs(y1 - y2)));
                    break;
                case LINE:
                    Shape Line = new Line2D.Float(x1, y1, x2, y2);
                    ShapesDrawn.add(Line);
                    break;
                case PLOT:
                    Shape EllipsePlot =
                            new Ellipse2D.Float(x1 - (plotRadius / 2),
                                    y1 - (plotRadius / 2), plotRadius, plotRadius);
                    draw.fill(EllipsePlot);
                    ShapesDrawn.add(EllipsePlot);
                    break;
                case POLYGON:
                    break;
            }
        }

        for(Shape i : ShapesDrawn){
            draw.draw(i);
        }
    }


    public Color getDrawColour() {
        return drawColour;
    }

    public void setDrawColour(Color drawColour) {
        this.drawColour = drawColour;
    }

    public void setCurrentSelectedShape(GUI.ShapeType currentSelectedShape) {
        this.currentSelectedShape = currentSelectedShape;
    }

    public GUI.ShapeType getCurrentSelectedShape() {
        return currentSelectedShape;
    }

    public Shape getShapeToBeAdded() {
        return shapeToBeAdded;
    }

    public void setShapeToBeAdded(Shape shapeToBeAdded) {
        this.shapeToBeAdded = shapeToBeAdded;
    }







}
