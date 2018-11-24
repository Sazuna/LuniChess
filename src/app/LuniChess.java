package app;

import java.awt.Toolkit;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import board.ConstBoard;
import board.Coord;
import board.Square;
import game.Game;
import javafx.beans.value.ChangeListener;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import piece.ChessColor;

public class LuniChess{
    
    public LuniChess() {
        m_game = new Game();
        initGame();
        m_boardPainter = new BoardPainter(m_canvas.getGraphicsContext2D(), m_settings, getBoard());
        m_boardActions = new BoardActions(m_boardPainter, m_game.getToMove());
    }
    
    private final Preferences m_prefs = Preferences.userNodeForPackage(this.getClass());
    
    private Game m_game;
    
    private final static BorderPane m_borderPane = new BorderPane();
    
    private final static Canvas m_canvas = new Canvas();
    
    private final static AppSettings m_settings = new AppSettings();
    
    private final BoardPainter m_boardPainter;
    
    private final BoardActions m_boardActions;
    
    public void initApp(Stage stage) throws Exception {
        initDefault();

        EventHandler<MouseEvent> boardPressed = new EventHandler<MouseEvent>() { 
           @Override 
           public void handle(MouseEvent e) {
               int sense = m_settings.getBoardSense();
               double size = m_settings.getSquareSize();
               Coord coord = AppUtil.getClickedCoord(e.getX(), e.getY(), sense, size);
               Square square = getBoard().getSquare(coord.getRange(), coord.getColumn());
               m_boardActions.setPressed(square);
           } 
        };
        EventHandler<MouseEvent> boardReleased = new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent e) {
                int sense = m_settings.getBoardSense();
                double size = m_settings.getSquareSize();
                Coord coord = AppUtil.getClickedCoord(e.getX(), e.getY(), sense, size);
                Square square = getBoard().getSquare(coord.getRange(), coord.getColumn());
                boolean play = m_boardActions.setReleased(square);
                if (play) {
                    play(m_boardActions.getFrom(), m_boardActions.getTo());
                }
            }
         };

        m_canvas.setWidth(m_prefs.getDouble("boardsize", 500));
        m_canvas.setHeight(m_prefs.getDouble("boardsize", 500));
        m_canvas.setOnMousePressed(boardPressed);
        m_canvas.setOnMouseReleased(boardReleased);
        m_game = new Game();
        m_game.initBoard();
        m_boardPainter.paint();
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
    
    private void play(Square from, Square to) {
        m_game.play(from, to);
        m_boardPainter.move(from, to);
        updateAfterMove();
    }
    
    private void updateAfterMove() {
        ChessColor toMove = getToMove().otherColor();
        setToMove(toMove);
        //TODO 
    }
    
    
    
   /////////////////////////////////// Game methods ////////////
    private void initGame() {
        m_game.initBoard();
        m_game.initTree();
    }
    
    private void setToMove(ChessColor toMove) {
        m_game.setToMove(toMove);
        m_boardActions.setToMove(toMove);
    }
    
    private ChessColor getToMove() {
        return m_game.getToMove();
    }
    ////////////////////////////////////////////////////////////
}
