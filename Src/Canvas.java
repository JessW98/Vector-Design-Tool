import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Canvas extends JPanel {
    //PROPERTIES
    private static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;
    //Used to save drawing
    private Image captureCanvas;
    //The graphics 2d object that user will draw on
    private Graphics2D graphic;

    private Point origin;
    private Point destination;

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
            clear();
        }
        g.drawImage(captureCanvas, 0, 0, null);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D line = (Graphics2D) g;

        if(origin != null && destination != null){
            int x1 = origin.x;
            int y1 = origin.y;
            int x2 = destination.x;
            int y2 = destination.y;

            line.drawLine(x1, y1, x2, y2);
        }
    }


}
