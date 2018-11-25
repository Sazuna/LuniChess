package piece;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;

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
        for (int i = 1; i <= 7; i++ ) {
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
        return allPossibleSquares;
    }
    
    @Override
    public ArrayList<Square> getPossibleMoves(Coord coord, ConstBoard board, Move lastMove) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        int range = coord.getRange();
        int column = coord.getColumn();
        boolean pp, pm, mp, mm;
        pp = true;
        pm = true;
        mp = true;
        mm = true;
        Square s;
        Piece p;
        for (int i = 1; i <= 7; i++ ) {
            if (range + i >= 0 && range + i < 8) {
                if ( column + i >= 0 && column + i < 8 && pp) {
                    s = board.getSquare(range + i, column + i);
                    p = s.getPiece();
                    if (s.hasPiece()) {
                        pp = false;
                        if (! p.getColor().equals(m_color)) {
                            possibleMoves.add(s);
                        }
                    }
                    else {
                        possibleMoves.add(s);
                    }
                }
                if (column - i >= 0 && column - i < 8 && pm) {
                    s = board.getSquare(range + i, column - i);
                    p = s.getPiece();
                    if (s.hasPiece()) {
                        pm = false;
                        if (! p.getColor().equals(m_color)) {
                            possibleMoves.add(s);
                        }
                    }
                    else {
                        possibleMoves.add(s);
                    }
                }
            }
            if (range - i >= 0 && range - i < 8) {
                if (column + i >= 0 && column + i < 8 && mp) {
                    s = board.getSquare(range - i, column + i);
                    p = s.getPiece();
                    if (s.hasPiece()) {
                        mp = false;
                        if (! p.getColor().equals(m_color)) {
                            possibleMoves.add(s);
                        }
                    }
                    else {
                        possibleMoves.add(s);
                    }
                }
                if (column - i >= 0 && column - i < 8 && mm) {
                    s = board.getSquare(range - i, column - i);
                    p = s.getPiece();
                    if (s.hasPiece()) {
                        mm = false;
                        if (! p.getColor().equals(m_color)) {
                            possibleMoves.add(s);
                        }
                    }
                    else {
                        possibleMoves.add(s);
                    }
                }
            }
        }
        return possibleMoves;
    }
}
