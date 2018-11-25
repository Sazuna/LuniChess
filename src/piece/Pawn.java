package piece;

import java.util.ArrayList;

import board.BoardUtil;
import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Move;

public class Pawn extends Piece {

    public Pawn(ChessColor color) {
        super(color);
        if (color.equals(ChessColor.BLACK)) {
            super.m_name = 'p';
        }
        else {
            super.m_name = 'P';
        }
    }

    @Override
    public ArrayList<Coord> getPossibleSquares(Coord coord) {
        ArrayList<Coord> allPossibleSquares = new ArrayList<>();
        int sense = super.getColor().getSense();
        int firstRange = super.getColor().getPawnFirstRange();
        int column = coord.getColumn();
        int range = coord.getRange();
        for (int i = -1; i <= 1; i++ )
            if (column + i >= 0 && column + i < 8)
                allPossibleSquares.add(new Coord(range + sense, column + i));
        if (range == firstRange)
            allPossibleSquares.add(new Coord(range + 2 * sense, column));
        return allPossibleSquares;
    }

    @Override
    public ArrayList<Square> getPossibleMoves(Coord coord, ConstBoard board, Move lastMove) {
        ArrayList<Square> possibleMoves = new ArrayList<>();
        int range = coord.getRange();
        int column = coord.getColumn();
        int sense = m_color.getSense();
        int firstRange = m_color.getPawnFirstRange();
        //En passant
        if (lastMove != null) {
            if (lastMove.getPiece().toString().equals(" ")) {
                Square lastFrom = lastMove.getSquareFrom();
                Square lastTo = lastMove.getSquareTo();
                if (lastFrom.getRange() == range + 2 * sense) {
                    if (lastTo.getRange() == range) {
                        if (lastFrom.getColumn() == column + 1) {
                            possibleMoves.add(board.getSquare(range + sense, column + 1));
                        } else if (lastFrom.getColumn() == column - 1) {
                            possibleMoves.add(board.getSquare(range + sense, column - 1));
                        }

                    }
                }
            }
        }
        //Forward
        Square oneForward = board.getSquare(range + sense, column);
        if (! oneForward.hasPiece()) {
            possibleMoves.add(oneForward);
            if (range == firstRange) {
                Square twoForward = board.getSquare(range + 2 * sense, column);
                if (! twoForward.hasPiece())
                    possibleMoves.add(twoForward);
            }
        }
        //Capture
        for (int i = - 1 ; i < 2; i += 2) {
            if (BoardUtil.isOnBoard(column + i)) {
                Square capture = board.getSquare(range + sense, column + i);
                if (capture.hasPiece()) {
                    if (capture.getPiece().getColor().equals(m_color.otherColor()))
                        possibleMoves.add(capture);
                }
            }
        }
        return possibleMoves;

    }
}
