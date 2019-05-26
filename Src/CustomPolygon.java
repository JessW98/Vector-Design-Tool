import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPolygon extends Polygon implements ShapeControl{
    /**
     * <h1>CustomPolygon</h1>
     * <p>
     *  Describes a polygon object by it's coordinates, fillColour and penColour
     * </p>
     *  .
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.POLYGON;

    private List<double[]> coordinates = new ArrayList<>();
    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomPolygon(List<double[]> coordinates, Color penColour, Color fillColour) {
        /**
         * Constructs a CustomPolygon with a list of coordinates, penColour and fillColour.
         */
        this.coordinates = coordinates;
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);

        this.fillColour = fillColour;
        this.penColour = penColour;
    }

    public CustomPolygon(List<double[]> coordinates, Color penColour) {
        /**
         * Constructs a polygon object with a list of coordinates and penColour.
         */
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);
        this.coordinates = coordinates;
        this.penColour = penColour;
    }

    public CustomPolygon(List<double[]> coordinates) {
        /**
         * Constructs a polygon object with a list of coordinates.
         */
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);
        this.coordinates = coordinates;
    }

    @Override
    public void setShapePenColour(Color colour) {
        /**
         * Sets the pen colour for the polygon object
         * @params Color Colour
         */
        this.penColour = colour;
    }

    @Override
    public void setShapeFillColour(Color colour) {
        /**
         * Sets the fill colour for the polygon object
         * @params Color Colour
         */
        this.fillColour = colour;
    }

    @Override
    public GUI.ShapeType GetShapeType() {
        /**
         * Returns the SHAPE_TYPE of the polygon.
         * @return SHAPE_TYPE
         */
        return SHAPE_TYPE;
    }

    @Override
    public Shape GetShape() {
        /**
         * Returns the polygon object
         * @return this
         */
        return this;
    }

    @Override
    public Color getShapePenColour() {
        /**
         * Returns the penColour for the polygon shape
         * @return penColour
         */
        return penColour;
    }

    @Override
    public Color getShapeFillColour() {
        /**
         * Returns the fillColour for the polygon shape
         * @return fillColour
         */
        return fillColour;
    }
}
