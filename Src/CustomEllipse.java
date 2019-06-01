import java.awt.*;
import java.awt.geom.Ellipse2D;

public class CustomEllipse extends Ellipse2D.Double implements ShapeControl {

    /**
     *  <h1>CustomEllipse</h1>
     *  <p>Describes the customEllipse object by x1, y1, x2, y2,
     *  fillColour, penColour.
     *  </p>
     *  @author Jessica Williams, William Daley, Jacob Kraut
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.ELLIPSE;

    private double x1;
    private double y1;
    private double x2;
    private double y2;

    private Color fillColour;
    private Color penColour;

    public CustomEllipse(double x1, double y1, double x2, double y2, Color penColour, Color fillColour) {
        /**
         * Constructs a custom ellipse object with x, y, width, height,
         * penColour, fillColour.
         * @params double x, double y, double width, double height,
         * Color penColour, Color fillColour
         */

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

    public double getX2()
    {
        return x2;
    }

    public double getY2()
    {
        return y2;
    }

    public void setX1(double x1)
    {
        this.x1 = x1;
        this.x = x1;
    }

    public void setY1(double y1)
    {
        this.y1 = y1;
        this.y = y1;
    }

    public void setX2(double x2)
    {
        this.x2 = x2;
        this.width = this.x2 - this.x1;
    }

    public void setY2(double y2)
    {
        this.y2 = y2;
        this.height = this.y2 - this.y1;
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

