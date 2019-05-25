import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomLine extends Line2D.Double implements ShapeControl{
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.LINE;

    private double x1, x2;
    private double y1, y2;
    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomLine(double x, double y, double x2, double y2, Color penColour){
        this.x1 = x;
        this.y1 = y;
        this.x2 = x2;
        this.y2 = y2;
        this.penColour = penColour;
    }
    @Override
    public double getX1() {
        return this.x1;
    }

    @Override
    public double getY1() {
        return this.y1;
    }

    @Override
    public double getX2() {
        return this.x2;
    }

    @Override
    public double getY2() {
        return this.y2;
    }

    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = null;
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
        return this.penColour;
    }

    @Override
    public Color getShapeFillColour() {
        return null;
    }
}
