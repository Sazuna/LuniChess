package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestKing {

    @Test
    void test() {
        Piece p = new King(ChessColor.BLACK);
        ArrayList<Coord> possibleSquares = p.getPossibleSquares(new Coord("d5"));
        assertTrue(possibleSquares.contains(new Coord("e4")));
        assertTrue(possibleSquares.contains(new Coord("c6")));
        assertTrue(possibleSquares.contains(new Coord("d6")));
    }

}
