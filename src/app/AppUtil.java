package app;

import board.Coord;

public final class AppUtil {

    public static double getY(int range, int sense, double size) {
        double y;
        if (sense == 1)
            y = (7-range) * size;
        else
            y = range * size;
        return y;
    }
    
    public static double getX(int column, int sense, double size) {
        double x;
        if (sense == 1)
            x = column * size;
        else 
            x = (7 - column) * size;
        return x;
    }
    
    public static int getRange(double y, int sense, int size) {
        int range;
        if (sense == 1)
            range = (int)(y / size);
        else 
            range = (int)(- y / size + 7);
        return 7 - range;
    }
    
    public static int getColumn(double x, int sense, int size) {
        int column;
        if (sense == 1)
            column = (int)(- x / size + 7);
        else 
            column = (int)(x / size);
        return 7 - column;
    }
    
    public static Coord getClickedSquare(double x, double y, int sense, int size) {
        Coord coord = new Coord(AppUtil.getRange(y, sense, size),AppUtil.getColumn(x, sense, size));
        return coord;
    }
}
