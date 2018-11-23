package piece;

import java.util.ArrayList;

import board.Coord;

public abstract class Piece{
    
    protected char m_name;
    protected ChessColor m_color;
    protected Coord m_coord;
    
    protected Piece(ChessColor color, Coord coord) {
        m_color = color;
        m_coord = coord;
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
    
    public Coord getCoord() {
        return m_coord;
    }

    public int getColumn() {
        return m_coord.getColumn();
    }
    
    public int getRange() {
        return m_coord.getRange();
    }
    
    public void setCoord(Coord coord) {
        m_coord = coord;
    }
    
    public abstract ArrayList<Coord> getPossibleSquares();
}