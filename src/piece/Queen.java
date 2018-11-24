package piece;

import java.util.ArrayList;

import board.Coord;

public class Queen extends Piece {

	public Queen(ChessColor color) {
		super(color);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'q';
		}
		else {
			super.m_name = 'Q';
		}
	}

    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        ArrayList<Coord> possibleSquares = new Bishop(super.getColor()).getPossibleSquares(coord);
        possibleSquares.addAll(new Rook(super.getColor()).getPossibleSquares(coord));
        return possibleSquares;
    }
}
