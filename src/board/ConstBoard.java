package board;

import java.util.ArrayList;

import piece.ChessColor;

public interface ConstBoard {
    
    public Square getSquare(int range, int column);

    public Square getSquare(Coord coord);

    public boolean getWhiteCastle();

    public boolean getBlackCastle();

    public boolean getWhiteLongCastle();

    public boolean getBlackLongCastle();

    public ArrayList<Coord> getPossibleMoves(Square square);
    
    public boolean isCheck(Square kingPosition);
    
    public Square getKing(ChessColor color);
}
