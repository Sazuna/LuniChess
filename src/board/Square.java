package board;

import java.util.ArrayList;

import piece.Piece;

public class Square {
    private Coord m_coord;
    private Piece m_piece;
    
    public Square(int range, int column) {
        m_coord = new Coord(range, column);
        m_piece = null;
    }
    
    public Square (char column, char range) {
        m_coord = new Coord(column, range);
        m_piece = null;
    }
    
    public Square (String coord) {
        m_coord = new Coord(coord);
        m_piece = null;
    }
    
    public void setPiece(Piece piece) {
        m_piece = piece;
    }
    
    public Piece getPiece() {
        return m_piece;
    }
    
    /**
     * @return true if the piece is not null
     */
    public boolean hasPiece() {
        return m_piece != null;
    }
    
    public int getRange() {
        return m_coord.getRange();
    }
    
    public int getColumn() {
        return m_coord.getColumn();
    }
    
    public Coord getCoord() {
        return m_coord;
    }
    
    public void clear() {
        m_piece = null;
    }
    
    public String toString() {
        return m_coord.toString();
    }
    
    public ArrayList<Coord> getPossibleSquares() {
        return m_piece.getPossibleSquares(this);
    }
}
