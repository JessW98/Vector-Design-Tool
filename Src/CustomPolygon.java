import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPolygon extends Polygon implements ShapeControl{
    /**
     * <h1>CustomPolygon</h1>
     * <p>
     *  Describes a polygon object by it's coordinates, fillColour and penColour
     * </p>
     *  @author Jessica Williams, William Daley, Jacob Kraut
     */
    private static final GUI.ShapeType SHAPE_TYPE = GUI.ShapeType.POLYGON;

    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomPolygon(List<double[]> coordinates, Color penColour, Color fillColour) {
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);

        this.fillColour = fillColour;
        this.penColour = penColour;
    }

    public CustomPolygon(List<double[]> coordinates, Color penColour) {
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);
        this.penColour = penColour;
    }

    public CustomPolygon(List<double[]> coordinates) {
        for (int i = 0; i < coordinates.size(); i++)
            this.addPoint((int) coordinates.get(i)[0], (int) coordinates.get(i)[1]);
    }

    public int[] getXCoordinates()
    {
        return xpoints;
    }

    public int[] getYCoordinates()
    {
        return ypoints;
    }

    public void setXCoordinates(int[] xCoordinates)
    {
        xpoints = xCoordinates;
    }

    public void setYCoordinates(int[] yCoordinates)
    {
        ypoints = yCoordinates;
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
