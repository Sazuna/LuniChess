package game;

import board.Square;
import piece.Piece;

public class Move {
    
    private Piece m_piece;
    
    private Square m_from;
    
    private Square m_to;
    
    public Move(Square from, Square to) {
        if (from != null && from.hasPiece())
            m_piece = from.getPiece();
        m_from = from;
        m_to = to;
        
    }

    public Square getSquareFrom() {
        return m_from;
    }

    public void setSquareFrom(Square from) {
        this.m_from = from;
    }

    public Square getSquareTo() {
        return m_to;
    }

    public void setSquareTo(Square to) {
        this.m_to = to;
    }

    public Piece getPiece() {
        return m_piece;
    }

    public void setPiece(Piece piece) {
        this.m_piece = piece;
    }
    
    public String toString(boolean range, boolean column) {
        String res = m_piece.toString();
        if (m_to.hasPiece()) {
            res += 'x';
        }
        return res + m_to.toString();
    }
    
    public String toString() {
        if (m_piece == null ) return "";
        return m_piece.toString() + m_to.toString();
    }
    
}
