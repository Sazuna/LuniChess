package app;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;
import piece.ChessColor;
import piece.Piece;

public class BoardActions{
    
    private Square m_pressed;
    
    private Square m_released;
    
    private ChessColor m_toMove;
    
    private static BoardPainter m_boardPainter;
    
    private boolean m_highlighted;
    
    private ArrayList<Coord> m_possibleSquares;
    
    private Move m_lastMove;
    
    public BoardActions(BoardPainter boardPainter, ChessColor toMove) {
        m_boardPainter = boardPainter;
        m_toMove = toMove;
        m_highlighted = false;
        m_possibleSquares = new ArrayList<>();
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
    
    public void setPressed(Square square, ConstBoard board) {
        m_pressed = square;
        m_released = null;
        executePressed(board);
    }
    
    /**
     * 
     * @param coord
     * @return true if different squares.
     */
    public boolean setReleased(Square square, ConstBoard board) {
        m_released = square;
        return executeReleased(board);
    }
    
    private void executePressed(ConstBoard board) {
        if (! m_pressed.hasPiece()) {
            if (m_highlighted)
                m_boardPainter.paint();
            return;
        }
        Piece piece = m_pressed.getPiece();
        ChessColor color = piece.getColor();
        if (color.equals(m_toMove)) {
            ArrayList<Square> possibleMoves = m_pressed.getPiece().getPossibleMoves(m_pressed.getCoord(), board, m_lastMove);
        //    m_possibleSquares = board.getPossibleMoves(m_pressed);
            m_possibleSquares.clear();
            for (Square s : possibleMoves) {
                m_possibleSquares.add(s.getCoord());
            }
            m_boardPainter.highlight(m_possibleSquares, m_toMove.otherColor());
            m_highlighted = true;
        }
    }
    
    /**
     * @return true if a move has to be done.
     */
    private boolean executeReleased(ConstBoard board) {
        if (m_pressed.equals(m_released)) {
            if (m_highlighted)
                m_boardPainter.paint();
            return false;
        }
        if (! m_pressed.hasPiece()) {
            return false;
        }
        if (! m_pressed.getPiece().getColor().equals(m_toMove))
            return false;
        for (Coord coord : m_possibleSquares)
        m_boardPainter.drawPiece(coord);
        return true;
    }
}
