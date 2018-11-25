package piece;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;

public class King extends Piece {
	
    public King(ChessColor color) {
        super(color);
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'k';
        }
        else {
            super.m_name = 'K';
        }
    }
    
    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        ArrayList<Coord> possibleSquares = new ArrayList<>();
        int column = coord.getColumn();
        int range = coord.getRange();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (i != 0 || j != 0)
                    if (range + i >= 0 && range + i < 8
                    && column + j >= 0 && column + j < 8)
                        possibleSquares.add(new Coord(range + i, column + j));
        return possibleSquares;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Coord coord, ConstBoard board, Move lastMove) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        // TODO Auto-generated method stub
        return possibleMoves;
    }
}
