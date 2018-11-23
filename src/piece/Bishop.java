package piece;

import java.util.ArrayList;

import board.Coord;
import board.Square;

public class Bishop extends Piece {

	public Bishop(ChessColor color, Coord coord) {
		super(color, coord);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'b';
		}
		else {
			super.m_name = 'B';
		}
	}

    public Bishop(ChessColor color, Square square) {
        super(color, new Coord(square.getRange(), square.getColumn()));
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'b';
        }
        else {
            super.m_name = 'B';
        }
    }

    @Override
    public ArrayList<Coord> getPossibleSquares() {
        ArrayList<Coord> allPossibleSquares = new ArrayList<>();
        int range = super.getRange();
        int column = super.getColumn();
        for (int i = 0; i <= 7; i++ ) {
            if (i != 0) {
                if (range + i >= 0 && range + i < 8) {
                    if ( column + i >= 0 && column + i < 8 ) {
                        allPossibleSquares.add(new Coord(range + i, column + i));
                    }
                    if (column - i >= 0 && column - i < 8) {
                        allPossibleSquares.add(new Coord(range + i, column - i));
                    }
                }
                if (range - i >= 0 && range - i < 8) {
                    if (column + i >= 0 && column + i < 8) {
                        allPossibleSquares.add(new Coord(range - i, column + i));
                    }
                    if (column - i >= 0 && column - i < 8) {
                        allPossibleSquares.add(new Coord(range - i, column - i));
                    }
                }
            }
        }
        return allPossibleSquares;
    }
}
