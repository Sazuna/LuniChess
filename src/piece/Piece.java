package piece;

import java.util.ArrayList;

import board.Coord;
import board.Square;

public abstract class Piece{
    
    protected char m_name;
    
    protected ChessColor m_color;
    
    protected Piece(ChessColor color) {
        m_color = color;
    }
    
    /**
     * @return the FEN char name (B for white bishop, b for white bishop)
     */
    public char getName() {
        return m_name;
    }
    
    public String toString() {
        if (Character.toUpperCase(m_name) == 'P')
            return "";
        return ""+Character.toUpperCase(m_name);
    }
    
    public ChessColor getColor() {
        return m_color;
    }
    
    /**
     * 
     * @param from The square where the piece is
     * @return
     */
    public abstract ArrayList<Coord> getPossibleSquares(Coord from);
    
    public ArrayList<Coord> getPossibleSquares(Square from) {
        return getPossibleSquares(from.getCoord());
    }
}