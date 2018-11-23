package app;

import java.util.prefs.Preferences;

import board.BoardUtil;
import board.ConstBoard;
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
        System.out.println("painting");
        m_graphics.drawImage(darkSquares[2], 10,10);
        m_graphics.setFill(Color.BLACK);
        Color white = Color.WHITE;
        Color black = Color.BLACK;
        for (int i = 0; i < BoardUtil.SIZE; i++)
            for (int j = 0; j < BoardUtil.SIZE; j++) {
                if ((i + j) % 2 == 0)
                    m_graphics.setFill(white);
                else
                    m_graphics.setFill(black);
                m_graphics.fillRect(i * m_size, j * m_size, m_size, m_size);
                char piece = board.getSquare(i, j).getPiece().getName();
                char color = board.getSquare(i, j).getPiece().getColor().getName();
                //In chess color, must return null !
                Image image = new Image(getClass().getPackage().getName() + "resources/" + color + "" + piece + ".png");
                m_graphics.drawImage(image, i* m_size,  j * m_size);
            }
        m_graphics.fillRect(0, 0, 30, 30);
    }

}
