package piece;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;

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
        for (int i = 1; i <= 7; i++ ) {
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
        return allPossibleSquares;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Coord coord, ConstBoard board, Move lastMove) {
        int range = coord.getRange();
        int column = coord.getColumn();
        ArrayList<Square> possibleMoves = new ArrayList<>();
        boolean n,s,e,w;
        n = true;
        s = true;
        e = true;
        w = true;
        Square sq;
        Piece p;
        for (int i = 1; i <= 7; i++ ) {
            if (range + i >= 0 && range + i < 8 && n) {
                sq = board.getSquare(range + i, column);
                p = sq.getPiece();
                if (sq.hasPiece()) {
                    n = false;
                    if (! p.getColor().equals(m_color)) {
                        possibleMoves.add(sq);
                    }
                }
                else {
                    possibleMoves.add(sq);
                }
            }
            if (column - i >= 0 && column - i < 8 && w) {
                sq = board.getSquare(range, column - i);
                p = sq.getPiece();
                if (sq.hasPiece()) {
                    w = false;
                    if (! p.getColor().equals(m_color)) {
                        possibleMoves.add(sq);
                    }
                }
                else {
                    possibleMoves.add(sq);
                }
            }
            if (range - i >= 0 && range - i < 8 && s) {
                sq = board.getSquare(range - i, column);
                p = sq.getPiece();
                if (sq.hasPiece()) {
                    s = false;
                    if (! p.getColor().equals(m_color)) {
                        possibleMoves.add(sq);
                    }
                }
                else {
                    possibleMoves.add(sq);
                }
                if (column + i >= 0 && column + i < 8 && e) {
                    sq = board.getSquare(range, column + i);
                    p = sq.getPiece();
                    if (sq.hasPiece()) {
                        e = false;
                        if (! p.getColor().equals(m_color)) {
                            possibleMoves.add(sq);
                        }
                    }
                    else {
                        possibleMoves.add(sq);
                    }
                }
            }
        }
        return possibleMoves;
    }
}
