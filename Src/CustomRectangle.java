import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomRectangle extends Rectangle2D.Double implements ShapeControl {

    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomRectangle(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public CustomRectangle(double x, double y, double width, double height, Color penColour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.penColour = penColour;
    }

    public CustomRectangle(double x, double y, double width, double height, Color penColour, Color fillColour) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.penColour = penColour;
        this.fillColour = fillColour;
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
        this.penColour = colour;
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

