package board;

public final class BoardUtil {
    
    public static boolean isOnBoard(int range, int column) {
        return (BoardUtil.isOnBoard(range) && BoardUtil.isOnBoard(column));
    }
    
    public static boolean isOnBoard(int index) {
        return (index < 8 && index >= 0);
    }
}
