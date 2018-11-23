package board;

import static piece.ChessColor.WHITE;

import piece.Bishop;
import piece.ChessColor;
import piece.King;

import static piece.ChessColor.BLACK;

import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public final class BoardUtil {

    public static int SIZE = 8;
    
    public static boolean isOnBoard(int range, int column) {
        return (BoardUtil.isOnBoard(range) && BoardUtil.isOnBoard(column));
    }
    
    public static boolean isOnBoard(int index) {
        return (index < SIZE && index >= 0);
    }
}
