package app;

import java.util.prefs.Preferences;

public class AppSettings {
    
    private final static Preferences m_prefs = Preferences.userNodeForPackage(LuniChess.class);
    
    public int getBoardSense() {
        return m_prefs.getInt("boardsense", 1);
    }
    
    public double getSquareSize() {
        return m_prefs.getDouble("boardsize", 500) / 8;
    }
}
