import java.awt.*;
import java.awt.geom.Rectangle2D;

public class CustomRectangle extends Rectangle2D.Double implements ShapeControl {
    /**
     * <h1>CustomRectangle</h1>
     * <p>
     * Describes a rectangle shape object by its location,
     * width, height, fillColour and penColour.
     * </p>
     */

    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.RECTANGLE;

    private double x;
    private double y;
    private double width;
    private double height;

    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomRectangle(double x, double y, double width, double height) {
        /**
         * Constructor for custom rectangle with inputs x, y, width and height.
         */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public CustomRectangle(double x, double y, double width, double height, Color penColour) {
        /**
         * Constructor for a custom rectangle with inputs x, y, width, height and penColour.
         */
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.penColour = penColour;
    }

    public CustomRectangle(double x, double y, double width, double height, Color penColour, Color fillColour) {
        /**
         * Constructor for a custom rectangle with inputs x, y, width, height, penColour and fillColour
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
        return this.x;
    }

    @Override
    public double getY() {
        /**
         * Returns the Y coordinate for the rectangle object
         * @returns y
         */
        return this.y;
    }

    @Override
    public double getWidth() {
        /**
         * Returns the width for the rectangle object
         * @returns width
         */
        return this.width;
    }

    @Override
    public double getHeight() {
        /**
         * Returns the height for the rectangle object
         * @returns height
         */
        return this.height;
    }

    @Override
    public void setShapePenColour(Color colour) {
        /**
         * Sets the penColour for this Rectangle object
         * @params Color Colour
         */
        this.penColour = colour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        /**
         * Sets the fill colour for the shape object
         */
        this.fillColour = colour;
    }

    @Override
    public GUI.ShapeType GetShapeType() {
        /**
         * Returns the SHAPE_TYPE of the Rectangle object
         * @returns SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    @Override
    public Shape GetShape() {
        /**
         * Returns the Rectangle Object
         * @returns this
         */
        return this;
    }

    @Override
    public Color getShapePenColour() {
        /**
         * Returns the penColour of the Rectangle Object
         * @returns peColour
         */
        return penColour;
    }

    @Override
    public Color getShapeFillColour() {
        /**
         * Returns the shapeFillColour of the Rectangle Object
         * @returns fillColour
         */
        return fillColour;
    }
}

