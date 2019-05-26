import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomEllipse extends Ellipse2D.Double implements ShapeControl {

    /**
     *  <h1>CustomEllipse</h1>
     *  <p>Describes the customEllipse object by x, y, width, height,
     *  fillColour, penColour.
     *  </p>
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.ELLIPSE;

    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColour;
    private Color penColour;

    public CustomEllipse(double x, double y, double width, double height,
                         Color penColour, Color fillColour) {
        /**
         * Constructs a custom ellipse object with x, y, width, height,
         * penColour, fillColour.
         * @params double x, double y, double width, double height,
         * Color penColour, Color fillColour
         */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.penColour = penColour;
        this.fillColour = fillColour;
    }

    @Override
    public double getX() {
        /**
         * Returns the x coordinate for the CustomEllipse object.
         * @return x
         */
        return this.x;
    }

    @Override
    public double getY() {
        /**
         * Returns the y coordinate for the CustomEllipse object.
         * @return y
         */
        return this.y;
    }

    @Override
    public double getWidth() {
        /**
         * Returns the width of the CustomEllipse object.
         * @return width
         */
        return this.width;
    }

    @Override
    public double getHeight() {
        /**
         * Returns the height of the CustomEllipse object.
         * @return height
         */
        return this.height;
    }

    @Override
    public void setShapePenColour(Color colour) {
        /**
         * Set the penColour for the CustomEllipse object.
         * @param Color Colour
         */
        this.penColour = colour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        /**
         * Set the fillColour for the CustomEllipse object.
         * @param Color Colour
         */
        this.fillColour = colour;
    }

    @Override
    public GUI.ShapeType GetShapeType() {
        /**
         * Returns the SHAPE_TYPE for the CustomEllipse object.
         * @return SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    @Override
    public Shape GetShape() {
        /**
         * Returns the CustomEllipse object.
         * @return this
         */
        return this;
    }

    @Override
    public Color getShapePenColour() {
        /**
         * Returns the penColour for the CustomEllipse object.
         * @return penColour
         */
        return penColour;
    }

    @Override
    public Color getShapeFillColour() {
        /**
         * Return the fillColour for the CustomEllipse Object.
         * @return fillColour
         */
        return fillColour;
    }
}

