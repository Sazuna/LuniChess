package piece;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;

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
        ArrayList<Coord> possibleSquares = new Bishop(m_color).getPossibleSquares(coord);
        possibleSquares.addAll(new Rook(m_color).getPossibleSquares(coord));
        return possibleSquares;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Coord coord, ConstBoard board, Move lastMove) {
        ArrayList<Square> possibleMoves = new Bishop(m_color).getPossibleMoves(coord, board, lastMove);
        possibleMoves.addAll(new Rook(m_color).getPossibleMoves(coord, board, lastMove));
        return possibleMoves;
    }
}
