package app;

import board.ConstBoard;
import board.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BoardPainter {
    
    private static GraphicsContext m_graphics;
    
    private static AppSettings m_settings;
    
    public BoardPainter(GraphicsContext graphics, AppSettings settings) {
        m_graphics = graphics;
        m_settings = settings;
    }
    
    public void paint(ConstBoard board) {
        m_graphics.setFill(Color.BLACK);
        Color white = Color.BEIGE;
        Color black = Color.BROWN;
        int sense = m_settings.getBoardSense();
        double size = m_settings.getSquareSize();
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    m_graphics.setFill(black);
                else
                    m_graphics.setFill(white);
                double x = AppUtil.getX(j, sense, size);
                double y = AppUtil.getY(i, sense, size);
                m_graphics.fillRect(x, y, size, size);
                Square square = board.getSquare(i, j);
                if (square.hasPiece()) {
                    char piece = board.getSquare(i, j).getPiece().getName();
                    char color = board.getSquare(i, j).getPiece().getColor().getName();
                    Image image = new Image(getClass().getPackage().getName() +"/a/" + color + "/" + piece + ".png");
                    m_graphics.drawImage(image, x,  y);
                }
            }
    }
    
    
    
}
