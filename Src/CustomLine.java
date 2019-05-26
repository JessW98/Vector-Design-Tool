import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomLine extends Line2D.Double implements ShapeControl{
    /**
     * <h1>CustomLine</h1>
     * <p>Describes the CustomLine object by x1, x2, y1, y2, fillColour, penColour</p>
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.LINE;

    private double x1, x2;
    private double y1, y2;
    private Color fillColour;
    private Color penColour = Color.BLACK;

    public CustomLine(double x, double y, double x2, double y2, Color penColour){
        /**
         * Constructs a CustomLine object with x, y, x2, y2, penColour
         */
        this.x1 = x;
        this.y1 = y;
        this.x2 = x2;
        this.y2 = y2;
        this.penColour = penColour;
    }
    @Override
    public double getX1() {
        /**
         * Returns the x1 coordinate of the line
         * @return x1
         */
        return this.x1;
    }

    @Override
    public double getY1() {
        /**
         * Returns the y1 coordinate of the line
         * @return y1
         */
        return this.y1;
    }

    @Override
    public double getX2() {
        /**
         * Returns the x2 coordinate of the line
         * @return x2
         */
        return this.x2;
    }

    @Override
    public double getY2() {
        /**
         * Returns the y2 coordinate of the line
         * @return y2
         */
        return this.y2;
    }

    @Override
    public void setShapePenColour(Color colour) {
        /**
         * Sets the pen colour for the line object
         * @return penColour
         */
        this.penColour = colour;
    }

    @Override
    public GUI.ShapeType GetShapeType() {
        /**
         * Returns the SHAPE_TYPE for the line object
         * @return SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    @Override
    public Shape GetShape() {
        /**
         * Returns the CustomLine object
         * @return this
         */
        return this;
    }

    @Override
    public Color getShapePenColour() {
        /**
         * Returns the penColour of the CustomLine object
         * @return penColour
         */
        return this.penColour;
    }

    @Override
    public Color getShapeFillColour() {
        /**
         * Returns the fillColour for the plot object
         * @return fillColour
         */
        return this.fillColour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        /**
         * Sets the fillColour for the plot object shape
         * @return fillColour
         */
        this.fillColour = colour;
    }

}
