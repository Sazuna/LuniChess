package board;

public interface ConstBoard {
    
    public Square getSquare(int range, int column);

    public boolean getWhiteCastle();
    
    public boolean getBlackCastle();
    
    public boolean getWhiteLongCastle();
    
    public boolean getBlackLongCastle();
}
