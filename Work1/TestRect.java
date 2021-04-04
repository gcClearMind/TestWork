import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.math.*;

public class TestRect {
    @Test
    public void TestGetArea() {
        assertEquals(new BigDecimal("-1"), new Rect(null, 2).getArea());
        assertEquals(new BigDecimal("-1"), new Rect(2, null).getArea());
        assertEquals(new BigDecimal("-1"), new Rect("a", 2).getArea());
        assertEquals(new BigDecimal("-1"), new Rect(2, "a").getArea());
        assertEquals(new BigDecimal("-1"), new Rect(-1, 2).getArea());
        assertEquals(new BigDecimal("-1"), new Rect(2, -1).getArea());
        assertEquals(new BigDecimal("-1"), new Rect(0, 2).getArea());
        assertEquals(new BigDecimal("-1"), new Rect(2, 0).getArea());
        assertEquals(new BigDecimal("10000000000"), new Rect(100000, 100000).getArea());
        assertEquals(new BigDecimal("12"), new Rect(3, 4).getArea());
        assertEquals(new BigDecimal("2.42"), new Rect(1.1, 2.2).getArea());
    }

    @Test
    public void TestGetPerimeter() {
        assertEquals(new BigDecimal("-1"), new Rect(null, 2).getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect(2, null).getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect("a", 2).getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect(2, "a").getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect(-1, 2).getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect(2, -1).getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect(0, 2).getPerimeter());
        assertEquals(new BigDecimal("-1"), new Rect(2, 0).getPerimeter());
        assertEquals(new BigDecimal("4000000000"), new Rect(1000000000, 1000000000).getPerimeter());
        assertEquals(new BigDecimal("14"), new Rect(3, 4).getPerimeter());
        assertEquals(new BigDecimal("6.6"), new Rect(1.1, 2.2).getPerimeter());
        System.out.println("ALL PASS");
    }

    @Test
    public void TestGetMaxByArea() {
        assertEquals("No legal rectangle",  Rect.findMax(new Rect[]{},
                new Rect.areaCompare()).getObject());

        assertEquals("(2,65)",Rect.findMax(new Rect[]{
                        new Rect("a", 2), new Rect(2, 65),
                        new Rect(3, 10), new Rect(6, 20)},
                new Rect.areaCompare()).getObject());
        assertEquals("(2,65)",Rect.findMax(new Rect[]{
                        new Rect(2, 65), new Rect(2, "aq"),
                        new Rect(3, 10), new Rect(6, 20)},
                new Rect.areaCompare()).getObject());
        assertEquals("No legal rectangle",Rect.findMax(new Rect[]{
                        new Rect("a", 5), new Rect(0, 2),
                        new Rect(-1, 10), new Rect(2, 0)},
                new Rect.areaCompare()).getObject());
        assertEquals("(10,20)", Rect.findMax(new Rect[]{
                        new Rect(10, 20), new Rect(2, 65),
                        new Rect(3, 10), new Rect(6, 20)},
                new Rect.areaCompare()).getObject());
        System.out.println("ALL PASS");
    }
    @Test
    public void TestGetMaxByPerimeter() {
        assertEquals("No legal rectangle",  Rect.findMax(new Rect[]{},
                new Rect.perimeterCompare()).getObject());

        assertEquals("(2,65)",Rect.findMax(new Rect[]{
                        new Rect("a", 2), new Rect(2, 65),
                        new Rect(3, 10), new Rect(6, 20)},
                new Rect.perimeterCompare()).getObject());
        assertEquals("(2,65)",Rect.findMax(new Rect[]{
                        new Rect(2, 65), new Rect(2, "aq"),
                        new Rect(3, 10), new Rect(6, 20)},
                new Rect.perimeterCompare()).getObject());
        assertEquals("No legal rectangle",Rect.findMax(new Rect[]{
                        new Rect("a", 5), new Rect(0, 2),
                        new Rect(-1, 10), new Rect(2, 0)},
                new Rect.perimeterCompare()).getObject());
        assertEquals("(2,65)", Rect.findMax(new Rect[]{
                        new Rect(10, 20), new Rect(2, 65),
                        new Rect(3, 10), new Rect(6.5, 20)},
                new Rect.perimeterCompare()).getObject());
        System.out.println("ALL PASS");
    }
}
