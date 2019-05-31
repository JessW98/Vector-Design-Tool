import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomPlot extends Ellipse2D.Double implements ShapeControl {
    /**
     * <h1>CustomPlot</h1>
     * <p>
     *  Describes a plot object by x, y, width, height, fillColour, penColour
     * </p>
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.PLOT;

    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColour;
    private Color penColour;

    public CustomPlot(double x, double y, Color penColour, double width, double height) {
        /**
         * Constructs a plot object with x, y, fillColour, width, height
         * @params double x, double y, Color fillColour, double width, double height
         */
        this.x = x;
        this.y = y;
        this.penColour = penColour;
        this.width = width;
        this.height = height;
    }

    @Override
    public double getX() {
        /**
         * Return the x coordinate for the plot object
         * @return x
         */
        return this.x;
    }

    public void setX(double x)
    {
        this.x = x;
    }

    public void setY(double y)
    {
        this.y = y;
    }

    @Override
    public double getY() {
        /**
         * Returns the y coordinate for the plot object
         * @return y
         */
        return this.y;
    }

    @Override
    public double getWidth() {
        /**
         * Returns the width of the plot object
         * @return width
         */
        return this.width;
    }

    @Override
    public double getHeight() {
        /**
         * Returns the height of the plot object
         * @return height
         */
        return this.height;
    }

    @Override
    public void setShapePenColour(Color colour) {
        /**
         * Sets the penColour for the plot object shape
         * @return penColour
         */
        this.penColour = colour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        /**
         * Sets the fillColour for the plot object shape
         * @return fillColour
         */
        this.fillColour = colour;
    }

    @Override
    public GUI.ShapeType GetShapeType() {
        /**
         * Returns the SHAPE_TYPE of the plot object shape
         * @return SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    @Override
    public Shape GetShape() {
        /**
         * Returns the plot object
         * @return this
         */
        return this;
    }

    @Override
    public Color getShapePenColour() {
        /**
         * Returns the penColour for the plot object
         * @return penColour
         */
        return penColour;
    }

    @Override
    public Color getShapeFillColour() {
        /**
         * Returns the fillColour for the plot object
         * @return fillColour
         */
        return penColour;
    }
}

