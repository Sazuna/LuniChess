package pgn;

import static piece.ChessColor.WHITE;
import static piece.ChessColor.BLACK;

import java.util.ArrayList;

import board.Square;
import game.Move;
import piece.ChessColor;
import piece.Piece;

public class PgnTree{

    private PgnMove m_root;

    /**
     * A field that is useful in case of a diagram
     */
    private ChessColor m_firstToMove;

    private PgnMove m_actualMove;

    public PgnMove getRoot() {
        return m_root;
    }
    
    public void setFirstToMove(ChessColor color) {
        m_firstToMove = color;
    }
    
    public ChessColor getFirstToMove() {
        return m_firstToMove;
    }

    public PgnMove getActualMove() {
        return m_actualMove;
    }
    
    public PgnTree() {
        m_root = new PgnMove(null, null, null);
        m_actualMove = m_root;
    }

    /**
     * Creates a new PgnTree with a root and add the first move to the root's children
     * @param firstMove
     */
    public PgnTree(PgnMove firstMove) {
        m_root = new PgnMove(null, null, null);
        m_root.addNext(firstMove);
        m_actualMove = firstMove;
        m_firstToMove = BLACK;
    }

    public PgnTree(PgnMove firstMove, ChessColor toMove) {
        m_root = new PgnMove(null, null, null);
        m_root.addNext(firstMove);
        m_actualMove = firstMove;
        m_firstToMove = toMove.otherColor();
    }

    public void next() {
        m_actualMove = m_actualMove.next();
    }

    public void setCurrentVariation(int variation) {
        m_actualMove.setCurrentVariation(variation);
    }

    /**
     * if no variation, this will be the first move
     * changes the actual tree move into the parameter move
     * @param move
     */
    public void addVariation(PgnMove move) {
        m_actualMove.addNext(move);
        m_actualMove = move;
        move.setAsCurrentVariation();
    }
    /**
     * Creates the move from the piece and its move squares
     * @param move
     */
    public void addVariation(Piece piece, Square from, Square to) {
        PgnMove m = new PgnMove(piece, from, to);
        m_actualMove.addNext(m);
        m_actualMove = m;
    }

    public void changeVariation(int variation) {
        m_actualMove.setCurrentVariation(variation);
    }

    public void goToRoot() {
        m_actualMove = m_root;
    }

    public void goToLastMove() {
        while (m_actualMove.hasNext())
            next();
        m_actualMove.setAsCurrentVariation();
    }

    /**
     * sets the actual move as index move in the current branch
     * @param move
     */
    public void goToMove(int move) {
        m_actualMove = m_root;
        for (int i = 0; i < move; i++) {
            m_actualMove = m_actualMove.next();
        }
    }

    /**
     * Moves in the tree from the root to the next move, setting the right path index in the moves
     * @param path must never contain more integers than the branch
     */
    public void goToMove(ArrayList<Integer> path) {
        m_actualMove = m_root;
        for (int i = 0 ; i < path.size(); i++) {
            m_actualMove.setCurrentVariation(path.get(i));
            m_actualMove = m_actualMove.next();
        }
    }

    public PgnMove getMove(int move) {
        PgnMove actual = m_root;
        for (int i = 0; i < move; i++) {
            actual = m_actualMove.next();
        }
        return actual;
    }

    public ArrayList<PgnMove> getGameFromBeginning() {
        ArrayList<PgnMove> moves = new ArrayList<PgnMove>();
        PgnMove currentMove = m_root;
        moves.add(currentMove);
        while (currentMove.hasNext()) {
            currentMove = currentMove.next();
            moves.add(currentMove);
        }
        return moves;
    }

    public String toString() {
        if (!m_root.hasNext()) {
            return "Start a new game";
        }
        return toString(m_root.getNexts().get(0), false);
    }


    /**
     * 
     * @param s is useful to know if we need to specify 9... before a black move after a variation or comment.
     * @param actualMove
     * @return
     */
    private String toString(PgnMove actualMove, boolean lastHasCommentOrIsVar) {
        String move = "";
        if (lastHasCommentOrIsVar && actualMove.getColor().equals(BLACK)) {
            move += (actualMove.getIndex() + "... " + actualMove.toString());
        }
        else {
            if (actualMove.getColor().equals(WHITE)) {
                move += actualMove.getIndex() + ". " + actualMove.toString();
            }
            else {
                move = actualMove.toString();
            }
        }
        if (! actualMove.hasNext() && ! actualMove.hasNextParallelVariation()) {
            return move;
        }
        else {
            if (actualMove.hasNextParallelVariation()) {
                PgnMove parallel = actualMove.getNextParallelVariation();
                move += " (" + toString(parallel, actualMove.hasComment() || actualMove.hasParallelVariations()) + ")";
            }
            if (actualMove.hasNext())
                move += ' ' + toString(actualMove.getNexts().get(0), actualMove.hasComment());
        }
        return move;
    }
}
