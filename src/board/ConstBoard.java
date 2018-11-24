package board;

import java.util.ArrayList;

public interface ConstBoard {
    
    public Square getSquare(int range, int column);

    public Square getSquare(Coord coord);

    public boolean getWhiteCastle();

    public boolean getBlackCastle();

    public boolean getWhiteLongCastle();

    public boolean getBlackLongCastle();

    public ArrayList<Coord> getPossibleMoves(Square square);
}
