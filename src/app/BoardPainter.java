package app;

import java.util.ArrayList;

import board.ConstBoard;
import board.Coord;
import board.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import piece.ChessColor;

public class BoardPainter {
    
    private static GraphicsContext m_graphics;
    
    private static AppSettings m_settings;
    
    private ConstBoard m_board;
    
    public BoardPainter(GraphicsContext graphics, AppSettings settings, ConstBoard board) {
        m_graphics = graphics;
        m_settings = settings;
        m_board = board;
    }
    
    public void paint() {
        m_graphics.setFill(Color.BLACK);
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                Square square = m_board.getSquare(i, j);
                if (square.hasPiece())
                    drawPiece(square);
                else
                    emptySquare(square.getCoord());
            }
    }
    
    public void move(Square from, Square to) {
        emptySquare(from.getCoord());
        drawPiece(to);
    }
    
    public void emptySquare(Coord coord) {
        int column = coord.getColumn();
        int range = coord.getRange();
        if ((column + range) % 2 == 0)
            m_graphics.setFill(Color.BROWN);
        else
            m_graphics.setFill(Color.BEIGE);
        double size = m_settings.getSquareSize();
        double x = getX(coord);
        double y = getY(coord);
        m_graphics.fillRect(x, y, size, size);
    }
    
    public void drawPiece(Coord coord) {
        Square square = m_board.getSquare(coord);
        drawPiece(square);
    }
    
    private void drawPiece(Square square) {
        emptySquare(square.getCoord());
        if (! square.hasPiece())
            return;
        double x = getX(square);
        double y = getY(square);
        char piece = square.getPiece().getName();
        char color = square.getPiece().getColor().getName();
        Image image = new Image(getClass().getPackage().getName() +"/a/" + color + "/" + piece + ".png");
        m_graphics.drawImage(image, x,  y);
    }
    
    public void highlight(ArrayList<Coord> possibleSquares, ChessColor toMove) {
        for (Coord s : possibleSquares) {
            highlight(m_board.getSquare(s), toMove);
        }
    }
    
    private void highlight(Square square, ChessColor toMove) {
        double x = getX(square);
        double y = getY(square);
        double size = m_settings.getSquareSize();
        boolean capture = square.hasPiece() && square.getPiece().getColor().equals(toMove);
        for (int i = (int)(size / 2) ; i > size / 10 ; i --) {
            if (! capture)
                m_graphics.setFill(Color.rgb(0, 255 - i, 255 - i, 0.04));
            else
                m_graphics.setFill(Color.rgb(255 - i, 0, 0, 0.1));
            m_graphics.fillOval(x + i, y + i, size - 2 * i, size - 2 * i);
        }
    }
    
    private double getX(Square square) {
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        return AppUtil.getX(square.getColumn(), sense, size);
    }

    private double getX(Coord coord) {
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        return AppUtil.getX(coord.getColumn(), sense, size);
    }
    
    private double getY(Square square) {
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        return AppUtil.getY(square.getRange(), sense, size);
    }
    
    private double getY(Coord coord) {
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        return AppUtil.getY(coord.getRange(), sense, size);
    }
}
