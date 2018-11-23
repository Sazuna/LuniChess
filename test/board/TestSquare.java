package board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestSquare {

    @Test
    void testToString() {
        Square s = new Square(5,2);
        assertEquals(s.toString(), "c6");
        s = new Square('e','3');
    }

}
