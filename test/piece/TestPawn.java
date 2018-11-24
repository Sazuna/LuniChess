package piece;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import board.Coord;

class TestPawn {

    @Test
    void testBlack() {
        Piece p = new Pawn(ChessColor.BLACK);
        ArrayList<Coord> possibleSquares = p.getPossibleSquares(new Coord("d7"));
        assertTrue(possibleSquares.contains(new Coord("d5")));
        assertTrue(possibleSquares.contains(new Coord("d6")));
        assertTrue(possibleSquares.contains(new Coord("e6")));
    }
    
    void testWhite() {
        Piece p = new Pawn(ChessColor.WHITE);
        ArrayList<Coord> possibleSquares = p.getPossibleSquares(new Coord("h2"));
        assertTrue(possibleSquares.contains(new Coord("h3")));
        assertTrue(possibleSquares.contains(new Coord("g3")));
        assertTrue(possibleSquares.contains(new Coord("h4")));
    }

}
