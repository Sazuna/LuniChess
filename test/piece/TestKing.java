package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestKing {

    @Test
    void test() {
        Piece p = new King(ChessColor.BLACK, new Coord("d5"));
        ArrayList<Coord> possibleSquares = p.getPossibleSquares();
        assertTrue(possibleSquares.contains(new Coord("e4")));
        assertTrue(possibleSquares.contains(new Coord("c6")));
        assertTrue(possibleSquares.contains(new Coord("d6")));
    }

}
