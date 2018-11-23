package board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import game.Game;
import piece.Bishop;
import piece.ChessColor;
import piece.King;
import piece.Knight;
import piece.Pawn;
import piece.Piece;
import piece.Queen;
import piece.Rook;

public class Fen {
    
    private String m_fen;
    /**
     * Generates the FEN position into a document in the user's files
     * @param s, documentURL
     */
    public static void generateFEN(Game game, File documentURL) {
        PrintWriter writer;
        try {
            writer = new PrintWriter(documentURL);
            writer.println(positionToFEN(game));
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("Sorry, we could not create the FEN File");
        }
    }
    
    private static String positionToFEN(Game game) {
        ConstBoard board = game.getBoard();
        String res = "";
        int amountOfEmptySquares = 0;
        for (int i = 0; i < 8; i++) { // visit only the lines
            if (amountOfEmptySquares > 0) {
                res += amountOfEmptySquares;
            }
            amountOfEmptySquares = 0;
            if (i > 0) {
                res += '/';
            }
            for (int j = 0; j < 8; j++) {
                Piece currentPiece = board.getSquare(i,j).getPiece();
                if (currentPiece != null) {
                    
                    if (amountOfEmptySquares > 0) {
                        res += amountOfEmptySquares;
                        amountOfEmptySquares = 0;
                    }
                    res += currentPiece;
                }
                else {amountOfEmptySquares ++;}
            }
        }
        if (res.charAt(res.length()-1)=='/') {
            res+='8';
        }
        res += ' ';
        res += game.getToMove();
        res += ' ';
        if (board.getWhiteCastle()) {
            res += 'K';
        }
        if (board.getWhiteLongCastle()) {
            res += 'Q';
        }
        if (board.getBlackCastle()) {
            res += 'k';
        }
        if (board.getBlackLongCastle()) {
            res += 'q';
        }

        res += " - ";
        res += game.getMovesNumber();
        res += ' ';
        res += game.getActualMove();
        return res;
    }
    
    /**
     * generates a Fen from file f.
     * Instantiate a new board and copy the f content.
     * @param f the file from which the position will be copied.
     * @throws IOException
     */
    public Board insertFen(File f) throws IOException {
        if (! f.canRead()) {
            System.out.println("The file can not be read");
            return null;
        }
        m_fen = readFile(f.getPath(), Charset.defaultCharset());
        
        if (!checkCorrectFEN(m_fen)) {
            System.err.println("Incorrect file format.");
            return null;
        }
        return setPosition();
    }
    
    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
    
    private boolean checkCorrectFEN(String FEN) {
        char c = '$';
        char previous =c;
        int blackKing = 0;
        int whiteKing = 0;
        int nbRange = 1;
        int nbSquareForARange = 0;
        int nbBlackPieces = 0;
        int nbWhitePieces = 0;
        int nbBlackPawns = 0;
        int nbWhitePawns = 0;
        ArrayList <Character> possibleChar = new ArrayList<Character>();
        possibleChar.add('r');
        possibleChar.add('R');
        possibleChar.add('n');
        possibleChar.add('N');
        possibleChar.add('b');
        possibleChar.add('B');
        possibleChar.add('k');
        possibleChar.add('K');
        possibleChar.add('q');
        possibleChar.add('Q');
        possibleChar.add('p');
        possibleChar.add('P');
        possibleChar.add('w');
        possibleChar.add('/');
        possibleChar.add('1');
        possibleChar.add('2');
        possibleChar.add('3');
        possibleChar.add('4');
        possibleChar.add('5');
        possibleChar.add('6');
        possibleChar.add('7');
        possibleChar.add('8');
        possibleChar.add(' ');
        
        for (int i = 0; i < FEN.length() && c != ' '; i++) {
            previous = c;
            c = FEN.charAt(i);
            if ( ! possibleChar.contains(c)) {
                System.err.println("There is a non valid character in the file");
                return false;
            }
            if (c=='k') {
                blackKing += 1;
                nbBlackPieces += 1;
                nbSquareForARange += 1;
            }
            else if (c == 'K') {
                whiteKing += 1;
                nbWhitePieces += 1;
                nbSquareForARange += 1;
            }

            else if (c == 'p') {
                nbBlackPawns += 1;
                nbSquareForARange += 1;
                if (nbRange == 1 || nbRange == 8) {
                    System.err.println("There is a pawn on a back range");
                    return false;
                }
            }
            else if (c == 'P') {
                nbWhitePawns += 1;
                nbSquareForARange += 1;
                if (nbRange == 1 || nbRange == 8) {
                    System.err.println("There is a pawn on a back range");
                    return false;
                }
            }

            else if (c == 'n' || c == 'r' || c == 'b' || c == 'q') {
                nbBlackPieces += 1;
                nbSquareForARange += 1;
            }
            else if (c == 'N' || c == 'R' || c == 'B' || c == 'Q') {
                nbWhitePieces += 1;
                nbSquareForARange += 1;
            }

            else if (c == '/') {
                nbRange += 1;
                if (nbSquareForARange != 8 ){
                    System.err.println("There is not the good amount of squares on range " + nbRange);
                    return false;
                }
                nbSquareForARange = 0;
            }
            else {
                nbSquareForARange += c - '0';
            }
            if (nbSquareForARange > 8) {
                System.err.println("There is not the good amount of squares on range " + nbRange);
                return false;
            }
        }
        if (previous == '/') {
            System.out.println("There is a missing range");
            return false;
        }
        if (blackKing != 1 || whiteKing != 1) {
            System.out.println("There is not one king of each color");
            return false;
        }
        if (nbWhitePawns > 8 || nbBlackPawns > 8) {
            System.out.println("There is too much pawns on the board");
            return false;
        }
        if (nbBlackPieces > 8 && 8 - nbBlackPawns < nbBlackPieces - 8 || nbWhitePieces > 8 && 8 - nbWhitePawns < nbWhitePieces - 8) {
            System.out.println("There is too much pieces on the board");
            return false;
        }
        if (nbRange != 8 ) {
            System.out.println("There is not eight ranges on the board");
            return false;
        }
        return true;
    }
    
    private Board setPosition() {
        Board board = new Board();
        for (int i = 7 ; i >= 0 ; i--) {
            for (int j = 0 ; j < 8; j++) {
                char actual = m_fen.charAt((7 - i) * 8 + j);
                Coord coord = new Coord(i,j);
                Piece piece = Fen.newPiece(actual, new Coord(i,j));
                board.setPiece(piece, coord);
            }
        }
        return board;
    }
    
    /**
     * Creates a new piece from a fen character.
     * @param piece
     * @param coord
     * @return
     */
    public static Piece newPiece(char piece, Coord coord) {
        char upper = Character.toUpperCase(piece);
        switch (upper) {
            case 'N' : return new Knight(ChessColor.getFenColorOf(piece), coord);
            case 'B' : return new Bishop(ChessColor.getFenColorOf(piece), coord);
            case 'R' : return new Rook(ChessColor.getFenColorOf(piece), coord);
            case 'P' : return new Pawn(ChessColor.getFenColorOf(piece), coord);
            case 'K' : return new King(ChessColor.getFenColorOf(piece), coord);
            case 'Q' : return new Queen(ChessColor.getFenColorOf(piece), coord);
        }
        return null;
    }
}
