import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Mouse extends MouseAdapter{

    private Point origin = null;
    private Point destination = null;

    @Override
    public void mousePressed(MouseEvent e) {
        origin = e.getPoint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        destination = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        destination = e.getPoint();
    }

    public Point getOrigin() {
        return origin;
    }

    public void setOrigin(Point origin) {
        this.origin = origin;
    }

    public Point getDestination() {
        return destination;
    }

    public void setDestination(Point destination) {
        this.destination = destination;
    }
}
