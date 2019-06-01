import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * <h1>CustomPlot</h1>
 * <p>
 *  Describes a plot object by x, y, width, height, fillColour, penColour
 * </p>
 * @author Jessica Williams, William Daley, Jacob Kraut
 */
public class CustomPlot extends Ellipse2D.Double implements ShapeControl {
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.PLOT;

    private double x;
    private double y;
    private double width;
    private double height;
    private Color fillColour;
    private Color penColour;

    /**
     * Constructs a plot object with x, y, fillColour, width, height
     * @param x The desired x location on the canvas.
     * @param y The desired y location on the canvas.
     * @param penColour
     */
    public CustomPlot(double x, double y, Color penColour, double width, double height) {
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

    /**
     * Sets the x to a new value.
     * @param x The new value to be assigned.
     * @return Nothing.
     */
    public void setX(double x)
    {
        this.x = x;
    }

    /**
     * Sets the y to a new value.
     * @param y The new value to be assigned.
     * @return Nothing.
     */
    public void setY(double y)
    {
        this.y = y;
    }

    /**
     * Returns the y coordinate for the plot object
     * @return y
     */
    @Override
    public double getY() {
        return this.y;
    }

    /**
     * Returns the width of the plot object
     * @return width
     */
    @Override
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the plot object
     * @return height
     */
    @Override
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the penColour for the plot object shape
     * @return penColour
     */
    @Override
    public void setShapePenColour(Color colour) {
        this.penColour = colour;
    }

    /**
     * Sets the fillColour for the plot object shape
     * @return fillColour
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

    /**
     * Returns the SHAPE_TYPE of the plot object shape
     * @return SHAPE_TYPE
     */
    @Override
    public GUI.ShapeType GetShapeType() {
        return SHAPE_TYPE;
    }

    /**
     * Returns the plot object
     * @return this
     */
    @Override
    public Shape GetShape() {
        return this;
    }

    /**
     * Returns the penColour for the plot object
     * @return penColour
     */
    @Override
    public Color getShapePenColour() {
        return penColour;
    }

    /**
     * Returns the fillColour for the plot object
     * @return fillColour
     */
    @Override
    public Color getShapeFillColour() {
        return penColour;
    }
}

