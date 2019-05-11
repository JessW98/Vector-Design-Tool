import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * The Drawing Canvas
 *
 */

public class Canvas extends JComponent {
    //PROPERTIES
    //Used to save drawing
    private Image pic;
    //The graphics 2d object that user will draw on
    private Graphics2D g2d;
    //coords of mouse
    private int x,y,oldX, oldY;

    //setup the drawing area, enable mouse input when pressed and dragged.
    // Updates coordinate properties
    public void drawArea(){
        setDoubleBuffered(false);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                //save the coordinates when the mouse is pressed
                oldX = e.getX();
                oldY = e.getY();
            }
        });

        //enable mouse motion input, store in current x,y
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                x = e.getX();
                y = e.getY();

                if (g2d != null){
                    g2d.drawLine(oldX, oldY, x, y);
                    repaint();

                    oldX = x;
                    oldY = y;
                }

            }
        });
    }
    //clears the canvas
    public void clear(){
        g2d.setPaint(Color.WHITE);
        g2d.fillRect(0,0,getSize().width, getSize().height);
        g2d.setPaint(Color.black);
        repaint();
    }

    //creates image
    protected void paintComponent(Graphics g){
        if(pic == null){
            pic = createImage(getSize().width, getSize().height);
            g2d = (Graphics2D) pic.getGraphics();
            clear();
        }
        g.drawImage(pic, 0, 0, null);
    }

    public void red() {
        // apply red color on g2 context
        g2d.setPaint(Color.red);
    }

    public void black() {
        g2d.setPaint(Color.black);
    }

    public void pink() {
        g2d.setPaint(Color.pink);
    }

    public void green() {
        g2d.setPaint(Color.green);
    }

    public void blue() {
        g2d.setPaint(Color.blue);
    }

    public void cyan() {
        g2d.setPaint(Color.cyan);
    }


}
