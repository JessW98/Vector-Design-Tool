import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomRectangle extends Rectangle2D.Double implements ShapeControl {
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.RECTANGLE;

    private double x1;
    private double y1;
    private double x2;
    private double y2;
    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomRectangle(double x1, double y1, double x2, double y2) {
        x = x1;
        y = y1;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.height = y2 - y1;
        this.width = x2 - x1;
    }

    public CustomRectangle(double x1, double y1, double x2, double y2, Color penColour) {
        x = x1;
        y = y1;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.height = y2 - y1;
        this.width = x2 - x1;
        this.penColour = penColour;
    }

    public CustomRectangle(double x1, double y1, double x2, double y2, Color penColour, Color fillColour) {
        x = x1;
        y = y1;

        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.height = y2 - y1;
        this.width = x2 - x1;
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
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    @Override
    public GUI.ShapeType GetShapeType() {
        return SHAPE_TYPE;
    }

    @Override
    public Shape GetShape() {
        return this;
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

