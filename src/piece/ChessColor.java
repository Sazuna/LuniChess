package piece;

public enum ChessColor {
	BLACK, WHITE;
	public String toString() {
		if (this == BLACK)
			return "b";
		return "w";
	}

	/**
	 * @return 1 for WHITE and -1 for BLACK
	 */
	public int getSense() {
		if (this == WHITE)
			return 1;
		return -1;
	}
	
	/**
	 * @return 1 for WHITE and 6 for BLACK
	 */
	public int getPawnFirstRange() {
	    if (this == WHITE)
	        return 1;
	    return 6;
	}
	
	public char getName() {
	    if (this == WHITE)
	        return 'w';
	    return 'b';
	}
	
	public ChessColor otherColor() {
	    if (this == WHITE)
	        return BLACK;
	    return WHITE;
	}
	
	/**
	 * 
	 * @param piece
	 * @return WHITE if uppercase, BLACK if lowercase.
	 */
	public static ChessColor getFenColorOf(char piece) {
	    if (Character.isUpperCase(piece))
	        return WHITE;
	    return BLACK;
	}
}
