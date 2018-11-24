package app;

import java.util.prefs.Preferences;

import board.ConstBoard;
import board.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class BoardPainter {
    
    private static GraphicsContext m_graphics;
    
    private final static Preferences m_prefs = Preferences.userNodeForPackage(LuniChess.class);
    
    private int m_sense = m_prefs.getInt("boardsense", 1);
    
    private double m_size = m_prefs.getDouble("boardsize", 500) / 8;
    
    public BoardPainter(GraphicsContext graphics) {
        m_graphics = graphics;
    }
    
    public void paint(ConstBoard board) {
        m_graphics.setFill(Color.BLACK);
        Color white = Color.BEIGE;
        Color black = Color.BROWN;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    m_graphics.setFill(black);
                else
                    m_graphics.setFill(white);
                double x = AppUtil.getX(j, m_sense, m_size);
                double y = AppUtil.getY(i, m_sense, m_size);
                m_graphics.fillRect(x, y, m_size, m_size);
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
