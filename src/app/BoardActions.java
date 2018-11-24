package app;

import board.Coord;

public class BoardActions {
    
    protected Coord m_pressed;
    
    protected Coord m_released;
    
    public void setPressed(Coord coord) {
        m_pressed = coord;
        m_released = null;
    }
    
    public void setReleased(Coord coord) {
        m_released = coord;
    }
    
    public void executeAction() {
        if (m_pressed.equals(m_released))
            System.out.println("same square");
    }
}
