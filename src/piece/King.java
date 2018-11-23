package piece;

import java.util.ArrayList;

import board.Coord;
import board.Square;

public class King extends Piece {
	
    public King(ChessColor color, Coord coord) {
        super(color, coord);
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'k';
        }
        else {
            super.m_name = 'K';
        }
    }

    public King(ChessColor color, Square square) {
        super(color, new Coord(square.getRange(), square.getColumn()));
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'k';
        }
        else {
            super.m_name = 'K';
        }
    }
    
    @Override
    public ArrayList<Coord> getPossibleSquares() {
        ArrayList<Coord> possibleSquares = new ArrayList<>();
        int column = super.getColumn();
        int range = super.getRange();
        for (int i = -1; i <= 1; i++)
            for (int j = -1; j <= 1; j++)
                if (i != 0 || j != 0)
                    if (range + i >= 0 && range + i < 8
                    && column + j >= 0 && column + j < 8)
                        possibleSquares.add(new Coord(range + j, column + i));
        return possibleSquares;
    }
}
