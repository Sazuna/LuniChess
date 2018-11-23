package app;

import java.util.prefs.Preferences;

import board.BoardUtil;
import board.ConstBoard;
import board.Square;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class BoardPainter {
    
    private static GraphicsContext m_graphics;
    
    private Image[] darkSquares = new Image[32];
    
    private final static Preferences m_prefs = Preferences.userNodeForPackage(LuniChess.class);
    
    private int m_sense = 1;
    
    private double m_size = m_prefs.getDouble("boardsize", 500) / 8;
    
    public BoardPainter(GraphicsContext graphics) {
        m_graphics = graphics;
        Image wood = new Image(LuniChess.class.getPackage().getName() +"/resources/w/B.png");
        PixelReader reader = wood.getPixelReader();
        for (int i = 0 ; i < 32 ; i++) {
            darkSquares[i]= new WritableImage(reader,0,0,20,20);
        }
    }
    
    public void paint(ConstBoard board) {
    //    m_graphics.drawImage(darkSquares[2], 10,10);
        m_graphics.setFill(Color.BLACK);
        Color white = Color.BEIGE;
        Color black = Color.BROWN;
        for (int i = 0; i < 8; i++)
            for (int j = 0; j < 8; j++) {
                if ((i + j) % 2 == 0)
                    m_graphics.setFill(white);
                else
                    m_graphics.setFill(black);
                m_graphics.fillRect(j * m_size, (7-i) * m_size, m_size, m_size);
                Square square = board.getSquare(i, j);
                if (square.hasPiece()) {
                    char piece = board.getSquare(i, j).getPiece().getName();
                    char color = board.getSquare(i, j).getPiece().getColor().getName();
                    Image image = new Image(getClass().getPackage().getName() +"/a/" + color + "/" + piece + ".png");
                    m_graphics.drawImage(image, j* m_size,  (7-i)* m_size);
                }
            }
    }

}