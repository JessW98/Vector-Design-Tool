import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CustomPolygon extends Polygon implements ShapeControl{

    private List<double[]> coordinates = new ArrayList<>();
    private Color fillColour = null;
    private Color penColour = Color.BLACK;

    public CustomPolygon(List<double[]> coordinates, Color penColour, Color fillColour) {
        this.coordinates = coordinates;
        this.fillColour = fillColour;
        this.penColour = penColour;
    }

    public CustomPolygon(List<double[]> coordinates, Color penColour) {
        this.coordinates = coordinates;
        this.penColour = penColour;
    }

    public CustomPolygon(List<double[]> coordinates) {
        this.coordinates = coordinates;
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
    public Color getShapePenColour() {
        return penColour;
    }

    @Override
    public Color getShapeFillColour() {
        return fillColour;
    }
}
