package board;

import static piece.ChessColor.WHITE;
import static piece.ChessColor.BLACK;

import java.util.ArrayList;

import piece.Bishop;
import piece.ChessColor;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class Board implements ConstBoard {
    
    private Square[][] m_board;
    
    private ChessColor m_toMove;
    
    private boolean m_whiteCastle;
    
    private boolean m_whiteLongCastle;
    
    private boolean m_blackCastle;
    
    private boolean m_blackLongCastle;
    
    private ArrayList<Piece> m_whiteCaptures;
    
    private ArrayList<Piece> m_blackCaptures;
    
    public Board() {
        m_whiteCaptures = new ArrayList<Piece>();
        m_blackCaptures = new ArrayList<Piece>();
        m_board = new Square[8][8];
        for (int i = 0; i < m_board.length ; i ++)
            for (int j = 0; j < m_board[0].length ; j++)
                m_board[i][j] = new Square(i,j);
        m_toMove = ChessColor.WHITE;
    }
    
    
    public Square[][] getBoard() {
        return m_board;
    }
    
    /**
     * @param range
     * @param column
     */
    public Square getSquare(int range, int column) {
        assert BoardUtil.isOnBoard(range, column);
        return m_board[range][column];
    }

    /**
     * @param column
     * @param range
     */
    public Square getSquare(char column, char range) {
        assert BoardUtil.isOnBoard(range, column);
        return m_board[range - '1'][column - 'a'];
    }
    
    /**
     * @param square
     * @return true if the address of the square is in m_board.
     */
    public boolean hasSquare(Square square) {
        for (int i = 0; i < m_board.length ; i ++)
            for (int j = 0; j < m_board[0].length ; j++)
                if (m_board[i][j].equals(square))
                    return true;
        return false;
    }
    
    /**
     * Delete all pieces on the board.
     */
    public void clear() {
        for (int i = 0; i < m_board.length ; i ++)
            for (int j = 0; j < m_board[0].length ; j++)
                m_board[i][j].clear();
        m_toMove = WHITE;
        m_whiteCastle = false;
        m_blackCastle = false;
        m_whiteLongCastle = false;
        m_blackLongCastle = false;
        m_whiteCaptures.clear();
        m_blackCaptures.clear();
    }
    
    /**
     * Clear the board and set initial position
     */
    public void init() {
        clear();
        m_board[0][0].setPiece(new Rook(WHITE));
        m_board[0][1].setPiece(new Knight(WHITE));
        m_board[0][2].setPiece(new Bishop(WHITE));
        m_board[0][3].setPiece(new Queen(WHITE));
        m_board[0][4].setPiece(new King(WHITE));
        m_board[0][5].setPiece(new Bishop(WHITE));
        m_board[0][6].setPiece(new Knight(WHITE));
        m_board[0][7].setPiece(new Rook(WHITE));
        m_board[7][0].setPiece(new Rook(BLACK));
        m_board[7][1].setPiece(new Knight(BLACK));
        m_board[7][2].setPiece(new Bishop(BLACK));
        m_board[7][3].setPiece(new Queen(BLACK));
        m_board[7][4].setPiece(new King(BLACK));
        m_board[7][5].setPiece(new Bishop(BLACK));
        m_board[7][6].setPiece(new Knight(BLACK));
        m_board[7][7].setPiece(new Rook(BLACK));
        for (int i = 0 ; i < 8; i++) {
            m_board[1][i].setPiece(new Pawn(WHITE));
            m_board[6][i].setPiece(new Pawn(BLACK));
        }
        m_whiteCastle = true;
        m_blackCastle = true;
        m_whiteLongCastle = true;
        m_blackLongCastle = true;
    }
    
    public void setPiece(Piece piece, Coord coord) {
        m_board[coord.getRange()][coord.getColumn()].setPiece(piece);
    }
    
    public void clearPiece(Coord coord) {
        m_board[coord.getRange()][coord.getColumn()].clear();
    }
    
    /**
     * Must call setToMove(ChessColor color).
     * If a piece is on the coord square, will add it to the
     * list of captured of the piece's color.
     * This method will force the piece to the square,
     * ignoring the legal moves.
     * @param piece
     * @param from the square the piece comes from.
     * @param to the destination of the piece.
     * @see setToMove(ChessColor color)
     */
    public void move(Piece piece, Coord from, Coord to) {
        Square sfrom = getSquare(from);
        Square sto = getSquare(to);
        if (sto.hasPiece())
            setCaptured(sto.getPiece());
        sfrom.clear();
        sto.setPiece(piece);
    }
    
    public ArrayList<Square> getSquaresBetween(Square from, Square to) {
        assert hasSquare(from);
        assert hasSquare(to);
        ArrayList<Square> between = new ArrayList<>();
        int incrementI = - 1;
        int incrementJ = - 1;
        if (to.getRange() > from.getRange())
            incrementI = 1;
        if (to.getColumn() > from.getColumn())
            incrementJ = 1;
        for (int j = from.getColumn(), i = from.getRange(); j == to.getColumn() || i == to.getRange(); j+= incrementJ, i+= incrementI)
            between.add(m_board[i][j]);
        return between;
    }
    
    public void setWhiteCastle(boolean whiteCastle) {
        m_whiteCastle = whiteCastle;
    }
    
    public void setBlackCastle(boolean blackCastle) {
        m_blackCastle = blackCastle;
    }
    
    public void setWhiteLongCastle(boolean whiteLongCastle) {
        m_whiteLongCastle = whiteLongCastle;
    }
    
    public void setBlackLongCastle(boolean blackLongCastle) {
        m_blackLongCastle = blackLongCastle;
    }
    
    @Override
    public boolean getWhiteCastle() {
        return m_whiteCastle;
    }
    
    @Override
    public boolean getBlackCastle() {
        return m_blackCastle;
    }
    
    @Override
    public boolean getWhiteLongCastle() {
        return m_whiteLongCastle;
    }
    
    @Override
    public boolean getBlackLongCastle() {
        return m_blackLongCastle;
    }
    
    public void setToMove(ChessColor color) {
        m_toMove = color;
    }
    
    public ChessColor getToMove() {
        return m_toMove;
    }
    
    /**
     * @param color
     * @return the list of captured piece for the specified color.
     * Black captures white pieces.
     */
    public ArrayList<Piece> getCaptured(ChessColor color) {
        if (color == WHITE)
            return m_whiteCaptures;
        return m_blackCaptures;
    }
    
    public void setCaptured(Piece piece) {
        if (piece.getColor() == WHITE)
            m_blackCaptures.add(piece);
        else
            m_whiteCaptures.add(piece);
    }

    public Square getSquare(String square) {
        square = square.trim();
        return getSquare(square.charAt(0), square.charAt(1));
    }
    
    public Square getSquare(Coord coord) {
        return getSquare(coord.getRange(), coord.getColumn());
    }
}
