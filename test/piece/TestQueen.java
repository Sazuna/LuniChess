package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestQueen {

    @Test
    void test() {
        Piece p = new Queen(ChessColor.BLACK);
        ArrayList<Coord> possibleSquares = p.getPossibleSquares(new Coord("d5"));
        assertTrue(possibleSquares.contains(new Coord("d1")));
        assertTrue(possibleSquares.contains(new Coord("h5")));
        assertTrue(possibleSquares.contains(new Coord("a8")));
    }

}
