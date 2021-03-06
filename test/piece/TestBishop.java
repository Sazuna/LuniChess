package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestBishop {

    @Test
    void test() {
        Piece p = new Bishop(ChessColor.WHITE);
        ArrayList<Coord> possibleSquares = p.getPossibleSquares(new Coord("a4"));
        assertTrue(possibleSquares.contains(new Coord("b5")));
        assertTrue(possibleSquares.contains(new Coord("c6")));
        assertTrue(possibleSquares.contains(new Coord("d7")));
    }

}
