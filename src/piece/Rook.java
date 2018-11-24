package piece;

import java.util.ArrayList;

import board.Coord;

public class Rook extends Piece {

    public Rook(ChessColor color) {
        super(color);
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'r';
        }
        else {
            super.m_name = 'R';
        }
    }

    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        int range = coord.getRange();
        int column = coord.getColumn();
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
