package piece;

import java.util.ArrayList;

import board.Coord;
import board.Square;

public class Rook extends Piece {

	public Rook(ChessColor color, Coord coord) {
		super(color, coord);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'r';
		}
		else {
			super.m_name = 'R';
		}
	}
	
    public Rook(ChessColor color, Square square) {
        super(color, new Coord(square.getRange(), square.getColumn()));
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'r';
        }
        else {
            super.m_name = 'R';
        }
    }
	   @Override
	    public ArrayList<Coord> getPossibleSquares() {
	       int range = super.getRange();
	       int column = super.getColumn();
	        ArrayList<Coord> allPossibleSquares = new ArrayList<>();
	        for (int i = 0; i <= 7; i++ ) {
	            if (i != 0) {
	                if (range + i >= 0 && range + i < 8) {
	                    allPossibleSquares.add(new Coord(range + i, column));
	                }
	                if (column - i >= 0 && column - i < 8) {
	                    allPossibleSquares.add(new Coord(range , column - i));

	                }
	                if (range - i >= 0 && range - i < 8) {
	                    allPossibleSquares.add(new Coord(range - i, column ));
	                }
	                if (column + i >= 0 && column + i < 8) {
	                    allPossibleSquares.add(new Coord(range , column + i));
	                }
	            }

	        }
	        return allPossibleSquares;
	    }
}
