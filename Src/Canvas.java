import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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

    private String shape;
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

        if(origin != null && destination != null){
            int x1 = origin.x;
            int y1 = origin.y;
            int x2 = destination.x;
            int y2 = destination.y;

            if(shape == GUI.ShapeType.Line.toString()){
                draw.drawLine(x1,y1,x2,y2);
            }else if(shape == GUI.ShapeType.Rectangle.toString()){
//                draw.drawRect(x1,y1, abs(x1 - x2), abs(y1 - y2));
                ShapesDrawn.add(new Rectangle(origin.x, origin.y, abs(origin.x - destination.x), abs(origin.y - destination.y)));
            }else if(shape == GUI.ShapeType.Polygon.toString()){
                draw.drawPolygon(new Polygon());
            }else if (shape == GUI.ShapeType.Ellipse.toString()){
                draw.drawOval(x1,y1, abs(x1 - x2), abs(y1 - y2));
            }else if(shape == GUI.ShapeType.Plot.toString()){
                draw.fillOval(x1 - 2, y1 - 2, 4, 4);
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

    public void setShape(String shape) {
        this.shape = shape;
    }







}
