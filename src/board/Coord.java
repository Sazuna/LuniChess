package board;

/**
 * Class for storing square names as indexes.
 * Coords are stored as array indexes (A1 is 0,0)
 * @author frete
 *
 */
public class Coord {

    private int m_range;
    private int m_column;
    
    /**
     * First range, then column.
     * @param range
     * @param column
     */
    public Coord(int range, int column) {
        setCoordRange(range);
        setCoordColumn(column);
    }
    
    /**
     * First column, then range.
     * @param column
     * @param range
     */
    public Coord(char column, char range) {
        setChessRange(range);
        setChessColumn(column);
    }
    
    public Coord(String coord) {
        coord = coord.trim();
        setChessRange(coord.charAt(1));
        setChessColumn(coord.charAt(0));
    }
    
    
    public int getRange() {
        return m_range;
    }
    
    public int getColumn() {
        return m_column;
    }
    
    /**
     * @param range must be between char 1 and char 8.
     * @see setCoordRange(int range) to set the range from an index.
     */
    public void setChessRange(char range) {
        if (range < '1' || range > '8') {
            System.err.println("Chess range must be between 1 and 8");
            m_range = -1;
            return;
        }
        m_range = range - '1';
    }
    
    /**
     * @param column must be between char a and char h
     * @see setCoordColumn(int column) to set the range from an index.
     */
    public void setChessColumn(char column) {
        if (column < 'a' || column > 'h') {
            System.err.println("Chess column must be between a and h");
            m_column = -1;
            return;
        }
        m_column = column - 'a';
    }
    
    public void setCoordRange(int range) {
        if (!BoardUtil.isOnBoard(range)) {
            System.err.println("Coord range must be between 0 and 7");
            m_range = -1;
            return;
        }
        m_range = range;
    }
    
    public void setCoordColumn(int column) {
        if (!BoardUtil.isOnBoard(column)) {
            System.err.println("Coord range must be between 0 and 7");
            m_column = -1;
            return;
        }
        m_column = column;
    }
    
    public String toString() {
        int a = 'a' + m_column;
        int b = '1' + m_range;
        char ac = (char) a;
        char bc = (char) b;
        return ("" + ac + "" + bc);
    }
    
    @Override
    public boolean equals(Object coord) {
        return ((Coord)coord).m_column == m_column && ((Coord)coord).m_range == m_range;
    }
}
