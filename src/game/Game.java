package game;

import board.Board;
import board.ConstBoard;
import board.Square;
import pgn.PgnTree;
import piece.ChessColor;
import piece.Piece;

public class Game {

    private Board m_board;
    
    private PgnTree m_tree;
    
    public ConstBoard getBoard() {
        return (ConstBoard)m_board;
    }
    
    public void initBoard() {
        m_board = new Board();
        m_board.init();
    }
    
    public void initTree() {
        m_tree = new PgnTree();
    }
    
    public void setToMove(ChessColor color) {
        m_board.setToMove(color);
    }
    
    public ChessColor getToMove() {
        return m_board.getToMove();
    }
    
    public Move getActualMove() {
        return m_tree.getActualMove();
    }
    
    public int getMovesNumber() {
        return m_tree.getGameFromBeginning().size();
    }
    
    public void play(Piece piece, Square from, Square to) {
        m_tree.addVariation(piece, from, to);
        if (!to.getPiece().equals(null))
            m_board.setCaptured(to.getPiece());
    }
}
