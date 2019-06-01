import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CustomLine extends Line2D.Double implements ShapeControl{
    /**
     * <h1>CustomLine</h1>
     * <p>Describes the CustomLine object by x1, x2, y1, y2, fillColour, penColour</p>
     * @author Jessica Williams, William Daley, Jacob Kraut
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.LINE;

    private double x1, x2;
    private double y1, y2;
    private Color fillColour;
    private Color penColour = Color.BLACK;

    /**
     * Constructs a CustomLine object with x, y, x2, y2, penColour
     */
    public CustomLine(double x1, double y1, double x2, double y2, Color penColour){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.penColour = penColour;
    }

    /**
     * Returns the x1 coordinate of the line
     * @return x1
     */
    @Override
    public double getX1() {
        return this.x1;
    }

    /**
     * Returns the y1 coordinate of the line
     * @return y1
     */
    @Override
    public double getY1() {
        return this.y1;
    }

    /**
     * Returns the x2 coordinate of the line
     * @return x2
     */
    @Override
    public double getX2() {
        return this.x2;
    }

    /**
     * Returns the y2 coordinate of the line
     * @return y2
     */
    @Override
    public double getY2() {
        return this.y2;
    }

    /**
     * Sets the x1 to a new value.
     * @param x1 The new value to be assigned.
     * @return Nothing.
     */
    public void SetX1(double x1)
    {
        this.x1 = x1;
    }

    /**
     * Sets the y1 to a new value.
     * @param y1 The new value to be assigned.
     * @return Nothing.
     */
    public void SetY1(double y1)
    {
        this.y1 = y1;
    }

    /**
     * Sets the x2 to a new value.
     * @param x2 The new value to be assigned.
     * @return Nothing.
     */
    public void SetX2(double x2)
    {
        this.x2 = x2;
    }

    /**
     * Sets the y2 to a new value.
     * @param y2 The new value to be assigned.
     * @return Nothing.
     */
    public void SetY2(double y2)
    {
        this.y2 = y2;
    }

    /**
     * Sets the colour of the pen for the outline.
     * @param colour the desired <i>Color</i>.
     */
    @Override
    public void setShapePenColour(Color colour) {
        /**
         * Sets the pen colour for the line object
         * @return penColour
         */
        this.penColour = colour;
    }

    /**
     * Returns the type of shape as a <i>GUI.ShapeType</i>
     * @return The type of shape as a <i>GUI.ShapeType</i>
     */
    @Override
    public GUI.ShapeType GetShapeType() {
        /**
         * Returns the SHAPE_TYPE for the line object
         * @return SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    /**
     * Returns an instance of itself at a <i>Shape</i> object.
     * @return An instance of itself at a <i>Shape</i> object.
     */
    @Override
    public Shape GetShape() {
        /**
         * Returns the CustomLine object
         * @return this
         */
        return this;
    }

    /**
     * Returns the penColour of the CustomLine object
     * @return penColour
     */
    @Override
    public Color getShapePenColour() {
        return this.penColour;
    }

    /**
     * Returns the fillColour for the plot object
     * @return fillColour
     */
    @Override
    public Color getShapeFillColour() {
        return this.fillColour;
    }

    /**
     * Sets the fillColour for the Line object.
     * @return The desired <i>Color</i>.
     */
    @Override
    public void setShapeFillColour(Color colour) {
        this.fillColour = colour;
    }

}
