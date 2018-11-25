package piece;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;

public class Knight extends Piece {

	public Knight(ChessColor color) {
		super(color);
		if (color.equals(ChessColor.BLACK)) {
			super.m_name = 'n';
		}
		else {
			super.m_name = 'N';
		}
	}

    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        ArrayList<Coord> allPossibleSquares = new ArrayList<>();
        int column = coord.getColumn();
        int range = coord.getRange();
        for (int i = -1; i <= 1; i+=2 ) {
            for (int j = -2; j <= 2; j+=4) {
                if (column + i >= 0 && column + i < 8) 
                    if (range + j >= 0 && range + j < 8)
                        allPossibleSquares.add(new Coord(range + j, column + i));
                if (range + i >= 0 && range + i < 8) 
                    if (column + j >= 0 && column + j < 8) 
                        allPossibleSquares.add(new Coord(range + i, column + j));
            }
        }
        return allPossibleSquares;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Coord coord, ConstBoard board, Move lastMove) {
        ArrayList<Coord> possibleSquares = getPossibleSquares(coord);
        ArrayList<Square> possibleMoves = new ArrayList<>();
        Square s;
        for (Coord c : possibleSquares) {
            s = board.getSquare(c);
            if (! s.hasPiece() || s.hasPiece() && s.getPiece().getColor().equals(m_color.otherColor()))
                possibleMoves.add(s);
        }
        return possibleMoves;
    }
}
