import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class CustomLineTest {

    private CustomLine testLine;

    @BeforeEach
    void setUp() {
        testLine = new CustomLine(1,1,5,5, Color.black);
    }

    @Test
    void getX1() {
        assertEquals(testLine.getX1(), 1);
    }

    @Test
    void getY1() {
        assertEquals(testLine.getY1(), 1);
    }


    @Test
    void getX2() {
        assertEquals(testLine.getX2(), 5);
    }

    @Test
    void getY2() {
        assertEquals(testLine.getY2(), 5);
    }

    @Test
    void setShapePenColour() {
        testLine.setShapePenColour(Color.pink);
        assertEquals(testLine.getShapePenColour(), Color.pink);
    }

    @Test
    void getShapeType() {
        assertEquals(testLine.GetShapeType(), GUI.ShapeType.LINE );
    }

    @Test
    void getShape() {
        assertEquals(testLine.GetShape(), testLine);
    }

    @Test
    void getShapePenColour() {
        testLine.setShapePenColour(Color.pink);
        assertEquals(testLine.getShapePenColour(), Color.pink);
    }

    @Test
    void getShapeFillColour() {
        testLine.setShapeFillColour(Color.pink);
    }
}