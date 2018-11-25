package board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static piece.ChessColor.WHITE;
import static piece.ChessColor.BLACK;

import piece.Bishop;
import piece.King;

class TestBoard {

    @Test
    void testKingPosition() {
        Board b = new Board();
        b.setPiece(new King(BLACK), new Coord("b8"));
        b.setPiece(new King(WHITE), new Coord("d3"));
        Square kingPosition = b.getKing(WHITE);
        assertEquals(kingPosition.toString(), "d3");
    }
    
    @Test
    void testCheck() {
        Board b = new Board();
        b.setPiece(new Bishop(BLACK), new Coord("a8"));
        b.setPiece(new King(WHITE), new Coord("h1"));
        Square kingPosition = b.getKing(WHITE);
        assertTrue(b.isCheck(kingPosition));
    }

}
