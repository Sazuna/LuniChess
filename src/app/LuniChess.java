package app;

import java.awt.Toolkit;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import board.ConstBoard;
import board.Coord;
import game.Game;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class LuniChess extends Application{

    public static void main(String[] args) {
       /*try {
            //Clear the user's prefs
            Preferences.userNodeForPackage(LuniChess.class).removeNode();
        } catch (BackingStoreException e) {
        }*/
      Application.launch(args);
    }
    
    private final Preferences m_prefs = Preferences.userNodeForPackage(this.getClass());
    
    private Game m_game;
    
    private final static BorderPane m_borderPane = new BorderPane();
    
    private final static Canvas m_canvas = new Canvas();
    
    private final static AppSettings m_settings = new AppSettings();
    
    private final static BoardPainter m_boardPainter = new BoardPainter(m_canvas.getGraphicsContext2D(), m_settings);
    
    private final static BoardActions m_boardActions = new BoardActions();
    

    @Override
    public void start(Stage stage) throws Exception {
        initDefault();

        EventHandler<MouseEvent> boardPressed = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) {
               int sense = m_settings.getBoardSense();
               double size = m_settings.getSquareSize();
               Coord coord = AppUtil.getClickedSquare(e.getX(), e.getY(), sense, size);
               m_boardActions.setPressed(coord);
           } 
        };
        EventHandler<MouseEvent> boardReleased = new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent e) {
                int sense = m_settings.getBoardSense();
                double size = m_settings.getSquareSize();
                Coord coord = AppUtil.getClickedSquare(e.getX(), e.getY(), sense, size);
                m_boardActions.setReleased(coord);
            } 
         };

        m_canvas.setWidth(m_prefs.getDouble("boardsize", 500));
        m_canvas.setHeight(m_prefs.getDouble("boardsize", 500));
        m_canvas.setOnMousePressed(boardPressed);
        m_canvas.setOnMouseReleased(boardReleased);
        m_game = new Game();
        m_game.initBoard();
        m_boardPainter.paint(getBoard());
        m_borderPane.setCenter(m_canvas);
        Button menu = new Button("Play");
        m_borderPane.setTop(menu);
        Scene scene = new Scene(m_borderPane);
        
        
        double width = m_prefs.getDouble("width", 1000);
        double height = m_prefs.getDouble("height", 700);
        
        stage.setWidth(width);
        stage.setHeight(height);
        stage.setX(m_prefs.getDouble("xPosition", 0));
        
        ChangeListener<Number> stageWidthListener = (observable, oldValue, newValue) -> {
            m_prefs.putDouble("width", stage.getWidth());
            };
        ChangeListener<Number> stageHeightListener = (observable, oldValue, newValue) -> {
            m_prefs.putDouble("height", stage.getHeight()); };
        ChangeListener<Number> stageXPosListener = (observable, oldValue, newValue) -> {
            m_prefs.putDouble("xPosition", (double)newValue); };
        ChangeListener<Number> stageYPosListener = (observable, oldValue, newValue) -> {
            m_prefs.putDouble("yPosition", (double)newValue); };
        stage.widthProperty().addListener(stageWidthListener);
        stage.heightProperty().addListener(stageHeightListener);
        stage.xProperty().addListener(stageXPosListener);
        stage.yProperty().addListener(stageYPosListener);
        
        stage.setTitle("LuniChess App");
        Image icon = new Image(LuniChess.class.getPackage().getName() +"/a/w/B.png");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        
        stage.show();
    }
    
    private ConstBoard getBoard() {
        return m_game.getBoard();
    }
    
    private void initDefault() {
        try {
            if (m_prefs.nodeExists("/app"))
                return;
            System.err.println("Preferences not found. Creating...");
            m_prefs.putDouble("width", Toolkit.getDefaultToolkit().getScreenSize().width / 1.5);
            m_prefs.putDouble("height", Toolkit.getDefaultToolkit().getScreenSize().height / 1.5);
            m_prefs.putDouble("xPosition", 0);
            m_prefs.putDouble("yPosition", 0);
            m_prefs.putDouble("boardsize", Toolkit.getDefaultToolkit().getScreenSize().height / 2);
            m_prefs.putInt("boardsense", 1);
        } catch (BackingStoreException e) {
            e.printStackTrace();
        }
    }
}
