package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestKnight {

    @Test
    void test() {
        Piece p = new Knight(ChessColor.WHITE, new Coord("f4"));
        ArrayList<Coord> possibleSquares = p.getPossibleSquares();
        assertTrue(possibleSquares.contains(new Coord("e6")));
        assertTrue(possibleSquares.contains(new Coord("g6")));
        assertTrue(possibleSquares.contains(new Coord("d3")));
    }
}
