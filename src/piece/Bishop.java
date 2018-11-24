package piece;

import java.util.ArrayList;

import board.Coord;

public class Bishop extends Piece {

	public Bishop(ChessColor color) {
		super(color);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'b';
		}
		else {
			super.m_name = 'B';
		}
	}

    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        ArrayList<Coord> allPossibleSquares = new ArrayList<>();
        int range = coord.getRange();
        int column = coord.getColumn();
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
