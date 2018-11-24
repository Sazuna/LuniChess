package app;

import board.Square;
import piece.ChessColor;

public class BoardActions{
    
    private Square m_pressed;
    
    private Square m_released;
    
    private ChessColor m_toMove;
    
    private static BoardPainter m_boardPainter;
    
    public BoardActions(BoardPainter boardPainter, ChessColor toMove) {
        m_boardPainter = boardPainter;
        m_toMove = toMove;
    }
    
    public void setToMove(ChessColor toMove) {
        m_toMove = toMove;
    }
    
    public Square getFrom() {
        return m_pressed;
    }
    
    public Square getTo() {
        return m_released;
    }
    
    public void setPressed(Square square) {
        m_pressed = square;
        m_released = null;
        executePressed();
    }
    
    /**
     * 
     * @param coord
     * @return true if different squares.
     */
    public boolean setReleased(Square square) {
        m_released = square;
        return executeReleased();
    }
    
    private void executePressed() {
        
    }
    
    private boolean executeReleased() {
        if (m_pressed.equals(m_released))
            return false;
        if (! m_pressed.getPiece().getColor().equals(m_toMove))
            return false;
        return true;
    }
}
