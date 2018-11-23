package piece;

import java.util.ArrayList;

import board.Coord;

public class Empty extends Piece {

    public Empty(ChessColor color, Coord coord) {
        super(color, coord);
        // TODO Auto-generated constructor stub
    }

    @Override
    public ArrayList<Coord> getPossibleSquares() {
        // TODO Auto-generated method stub
        return null;
    }


}
