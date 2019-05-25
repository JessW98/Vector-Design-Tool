import java.awt.*;

public interface ShapeControl extends Shape {

    void setShapePenColour(Color colour);
    void setShapeFillColour(Color colour);
    GUI.ShapeType GetShapeType();
    Shape GetShape();
    Color getShapePenColour();
    Color getShapeFillColour();
}
