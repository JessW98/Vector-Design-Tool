import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomPlot extends Ellipse2D.Double implements ShapeControl {

    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColour;
    private Color penColour;

    public CustomPlot(double x, double y, Color fillColour, double width, double height) {
        this.x = x;
        this.y = y;
        this.fillColour = fillColour;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getX() {
        return this.x;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getWidth() {
        return this.width;
    }

    @Override
    public double getHeight() {
        return this.height;
    }

    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = null;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    @Override
    public Color getShapePenColour() {
        return penColour;
    }

    @Override
    public Color getShapeFillColour() {
        return fillColour;
    }
}

