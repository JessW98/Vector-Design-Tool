import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


class CanvasTest {

    private Canvas testCanvas;
    private List<ShapeControl> testShapes;
    private CustomRectangle testTangle;

    @BeforeEach
    void setUp() {
        testCanvas = new Canvas();
        testShapes = new ArrayList<>();
        testTangle = new CustomRectangle(10, 10, 10, 10,
                Color.BLACK,
                Color.WHITE);
    }

    @Test
    void arrayGrowsDynamicallyTest(){
        testShapes.add(testTangle);

        assertEquals(1, testShapes.size());

        testShapes.add(testTangle);

        assertEquals(2, testShapes.size());
    }

    @Test
    void clearTest() {
        for(int i = 0; i < 5; i++){
            testShapes.add(testTangle);
        }

        assertEquals(5, testShapes.size());

        testShapes.clear();

        assertEquals(0, testShapes.size());
    }

    @Test
    void paintComponent() {

    }

    @Test
    void getPenColor() {
        assertEquals(Color.BLACK, testCanvas.GetPenColor());
    }

    @Test
    void setPenColor() {
        testCanvas.SetPenColor(Color.GREEN);
        assertEquals(Color.GREEN, testCanvas.GetPenColor());
    }

    @Test
    void setCurrentSelectedShape() {
        testCanvas.SetCurrentSelectedShape(GUI.ShapeType.RECTANGLE);
        assertEquals(GUI.ShapeType.RECTANGLE, testCanvas.GetCurrentSelectedShape());
    }

    @Test
    void getCurrentSelectedShape() {
        assertEquals(GUI.ShapeType.LINE, testCanvas.GetCurrentSelectedShape());
    }

    @Test
    void getFillColor() {
        assertEquals(null, testCanvas.GetFillColor());
    }

    @Test
    void setFillColor() {
        testCanvas.SetFillColor(Color.GREEN);
        assertEquals(Color.GREEN, testCanvas.GetFillColor());
    }
}