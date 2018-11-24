package piece;

import java.util.ArrayList;

import board.Coord;

public class Pawn extends Piece {

	public Pawn(ChessColor color) {
		super(color);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'p';
		}
		else {
			super.m_name = 'P';
		}
	}

    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        ArrayList<Coord> allPossibleSquares = new ArrayList<>();
        int sense = super.getColor().getSense();
        int firstRange = super.getColor().getPawnFirstRange();
        int column = coord.getColumn();
        int range = coord.getRange();
        for (int i = -1; i <= 1; i++ )
            if (column + i >= 0 && range + i < 8)
                allPossibleSquares.add(new Coord(range + sense, column + i));
        if (range == firstRange)
            allPossibleSquares.add(new Coord(range + 2*sense, column));
        return allPossibleSquares;
    }
}
