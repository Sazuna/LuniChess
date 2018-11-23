package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestRook {

    @Test
    void test() {
        Piece p = new Rook(ChessColor.WHITE, new Coord("h7"));
        ArrayList<Coord> possibleSquares = p.getPossibleSquares();
        assertTrue(possibleSquares.contains(new Coord("a7")));
        assertTrue(possibleSquares.contains(new Coord("h8")));
        assertTrue(possibleSquares.contains(new Coord("h2")));
    }

}
