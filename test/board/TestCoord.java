package board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestCoord {

    @Test
    void testInt() {
        Coord c = new Coord(0,0);
        assertEquals(c.getRange(), 0);
        assertEquals(c.getColumn(), 0);
    }
    
    @Test
    void testIntNotOnBoard() {
        Coord c = new Coord(-1,8);
        assertEquals(c.getRange(), -1);
        assertEquals(c.getColumn(), -1);
    }
    
    @Test
    void testChar() {
        Coord c = new Coord('a','2');
        assertEquals(c.getRange(), 1);
        assertEquals(c.getColumn(), 0);
    }
    
    @Test
    void testCharNotOnBoard() {
        Coord c = new Coord('i','0');
        assertEquals(c.getRange(), -1);
        assertEquals(c.getColumn(), -1);
    }
    
    @Test
    void testToString() {
        Coord c = new Coord('d','6');
        assertEquals(c.toString(), "d6");
    }
    
    @Test
    void testEquals() {
        Coord c1 = new Coord("a8");
        Coord c2 = new Coord("a8");
        assertEquals(c1,c2);
    }
}
