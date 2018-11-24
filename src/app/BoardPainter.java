package app;

import board.ConstBoard;
import board.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BoardPainter {
    
    private static GraphicsContext m_graphics;
    
    private static AppSettings m_settings;
    
    private static ConstBoard m_board;
    
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
                    emptySquare(square);
            }
    }
    
    public void move(Square from, Square to) {
        emptySquare(from);
        drawPiece(to);
    }
    
    private void emptySquare(Square square) {
        int column = square.getColumn();
        int range = square.getRange();
        if ((column + range) % 2 == 0)
            m_graphics.setFill(Color.BROWN);
        else
            m_graphics.setFill(Color.BEIGE);
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        double x = AppUtil.getX(column, sense, size);
        double y = AppUtil.getY(range, sense, size);
        m_graphics.fillRect(x, y, size, size);
    }
    
    private void drawPiece(Square square) {
        emptySquare(square);
        int column = square.getColumn();
        int range = square.getRange();
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        double x = AppUtil.getX(column, sense, size);
        double y = AppUtil.getY(range, sense, size);
        char piece = square.getPiece().getName();
        char color = square.getPiece().getColor().getName();
        Image image = new Image(getClass().getPackage().getName() +"/a/" + color + "/" + piece + ".png");
        m_graphics.drawImage(image, x,  y);
    }
    
    
    
}
