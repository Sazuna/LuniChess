package piece;

import java.util.ArrayList;

import board.Coord;
import board.Square;

public class Queen extends Piece {

	public Queen(ChessColor color, Coord coord) {
		super(color, coord);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'q';
		}
		else {
			super.m_name = 'Q';
		}
	}

    public Queen(ChessColor color, Square square) {
        super(color, new Coord(square.getRange(), square.getColumn()));
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'q';
        }
        else {
            super.m_name = 'Q';
        }
    }

    @Override
    public ArrayList<Coord> getPossibleSquares() {
        ArrayList<Coord> possibleSquares = new Bishop(super.getColor(), super.getCoord()).getPossibleSquares();
        possibleSquares.addAll(new Rook(super.getColor(), super.getCoord()).getPossibleSquares());
        return possibleSquares;
    }
}
