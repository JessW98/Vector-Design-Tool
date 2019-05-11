import javax.swing.*;
import java.awt.*;

public class Canvas extends JPanel {
    //PROPERTIES
    private static final Color DEFAULT_BACKGROUND_COLOUR = Color.WHITE;
    //Used to save drawing
    private Image captureCanvas;
    //The graphics 2d object that user will draw on
    private Graphics2D graphic;

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
}
