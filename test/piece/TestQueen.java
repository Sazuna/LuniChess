package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestQueen {

    @Test
    void test() {
        Piece p = new Queen(ChessColor.BLACK, new Coord("d5"));
        ArrayList<Coord> possibleSquares = p.getPossibleSquares();
        assertTrue(possibleSquares.contains(new Coord("d1")));
        assertTrue(possibleSquares.contains(new Coord("h5")));
        assertTrue(possibleSquares.contains(new Coord("a8")));
    }

}
