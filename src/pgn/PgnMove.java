package pgn;

import static piece.ChessColor.WHITE;
import static piece.ChessColor.BLACK;

import piece.ChessColor;

import java.util.ArrayList;

import board.Square;
import game.Move;

public class PgnMove extends Move{
    
    private String m_move;
    
    private ChessColor m_color;
    
    private PgnMove m_previous;
    
    private ArrayList<PgnMove> m_nexts;
    
    /**
     * The current next move
     */
    private int m_currentVariation;
    
    /**
     * The position in the game
     */
    private int m_index;
    private String m_comment = "";
    
    public PgnMove(Square from, Square to, PgnMove previous) {
        super(from, to);
        this.m_nexts = new ArrayList<PgnMove>();
        String move = super.toString();
        this.m_move = move;
        this.m_previous = previous;
        this.m_currentVariation = 0;
        previous.addNext(this);
        m_color = previous.m_color.otherColor();
        m_index = previous.getIndex();
        if (m_color.equals(WHITE))
            m_index++;
    }

    public PgnMove(Square from, Square to) {
        super(from, to);
        m_nexts = new ArrayList<PgnMove>();
        this.m_move = super.toString();
        m_previous = null;
        m_currentVariation = 0;
        m_index = 0;
        m_color = BLACK;
    }
    
    public void addNext(PgnMove move) {
        m_nexts.add(move);
        move.setPrevious(this);
        move.setColor(m_color.otherColor());
        if (move.getColor().equals(WHITE))
            move.setIndex(m_index + 1);
        else
            move.setIndex(m_index);
    }
    
    public void delVariation(int variation) {
        m_nexts.remove(variation);
    }
    
    public PgnMove next() {
        if (m_nexts.isEmpty())
            return this;
        return m_nexts.get(m_currentVariation);
    }
    
    public ArrayList<PgnMove> getNexts() {
        return m_nexts;
    }
    
    public void setPrevious (PgnMove previous) {
        this.m_previous = previous;
    }
    
    public PgnMove getPrevious() {
        return m_previous;
    }
    
    public ChessColor getColor() {
        return m_color;
    }
    
    public void setColor(ChessColor color) {
        this.m_color = color;
    }
    
    /**
     * in existing variations (do not use if you want to create one)
     * @param variation
     * @return
     */
    public boolean setCurrentVariation(int variation) {
        if (m_nexts.size() > variation) {
            m_currentVariation = variation;
            return true;
        }
        return false;
    }
    
    public int getCurrentVariation() {
        return m_currentVariation;
    }
    
    public boolean isMainVariation() {
        if (m_previous.m_nexts.get(0).equals(this)) {
            if (m_previous.isFirstMove()) {
                return true;
            }
            return m_previous.isMainVariation();
        }
        return false;
    }
    
    public boolean isFirstMove() {
        return m_previous == null;
    }
    
    public boolean hasNext() {
        return (m_nexts.size() > 0 && m_nexts.get(m_currentVariation) != null);
    }
    
    public boolean hasVariations() {
        return (m_nexts.size() > 1);
    }
    
    public boolean hasParallelVariations() {
        if (m_previous == null)
            return false;
        return (m_previous.m_nexts.size() > 1);
    }
    
    public boolean hasNextParallelVariation() {
        if (m_previous == null)
            return false;
        return (m_previous.m_nexts.size() - 1 > getVariationNumber());
    }
    
    public PgnMove getNextParallelVariation() {
        if (!hasNextParallelVariation())
            return null;
        return m_previous.m_nexts.get(getVariationNumber() + 1);
    }
    
    public boolean isFirstVariation() {
        if (m_previous == null)
            return true;
        return (m_previous.m_nexts.get(0).equals(this));
    }
    
    public int getVariationNumber() {
        if (m_previous == null)
            return 0;
        return m_previous.m_nexts.indexOf(this);
    }
    
    public int getIndex() {
        /*int res = 1;
        PgnMove actual = this;
        while (!actual.isFirstMove()) {
            actual = actual.previous;
            res ++;
        }
        return res;*/
        return m_index;
    }
    
    public void setIndex(int index) {
        this.m_index = index;
    }
    
    
    public String getComment() {
        return m_comment;
    }
    
    public void setComment(String comment) {
        this.m_comment = comment;
    }
    
    public boolean hasComment() {
        return !this.m_comment.equals("");
    }

    public String toString() {
        if (this.isFirstMove()) return "";
        String res = m_move;
        if (m_comment.length() != 0) {
            res = res + " { " + m_comment + " }";
        }
        return res;
    }
    
    public void setAsCurrentVariation() {
        if (this.isFirstMove()) {
            return;
        }
        m_previous.setCurrentVariation(getVariationNumber());
        m_previous.setAsCurrentVariation();
    }
}