import java.awt.*;

/**
 * Interface for all Custom Shapes, Extends Shape
 * @author Jacob Kraut
 * @since 2019-05-03
 */
public interface ShapeControl extends Shape {
    void setShapePenColour(Color colour);
    void setShapeFillColour(Color colour);
    GUI.ShapeType GetShapeType();
    Shape GetShape();
    Color getShapePenColour();
    Color getShapeFillColour();

}