import java.awt.*;

public interface ShapeControl extends Shape {

    void setShapePenColour(Color colour);
    void setShapeFillColour(Color colour);
    Color getShapePenColour();
    Color getShapeFillColour();
}
