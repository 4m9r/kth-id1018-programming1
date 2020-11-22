/*      Programming 1, EU3
        Created:2019-10-24
        Last updated: 2020-11-22
        Author: Amir Ali Safizadeh.
        Purpose of the code: This class contains the chess-field and all the chess pieces, one can
        put a chess piece into the field. This class can mark all the possible moves of a chess piece
        on the chessboard.
 */



public class Chessboard
{
    // A helper class
    public static class Field

    {
        private char row;
        private byte column;
        private Chesspiece piece = null;
        private boolean marked = false;

        /**
         * Initializes the field
         * @param row number of rows
         * @param column number of columns
         */
        public Field (char row, byte column) {
            this.column =column;
            this.row = row;

        }

        /**
         *
         * @param piece put this chess piece into the field
         */
        public void put (Chesspiece piece) {
            this.piece = piece;

        }

        /**
         * Take the chess piece out of the board and return it
         * @return the chess piece inside the field
         */
        public Chesspiece take () {
            Chesspiece cp ;
            cp  = piece;
            piece = null;
            return cp;
        }

        /**
         * Initialize "marked" as true
         */
        public void mark () {
        marked = true;
        }

        /**
         * Initialize "marked" as false
         */
        public void unmark () {
        marked = false;
        }


        /**
         * Return a String Representation of field
         * the string is Either the piece's name or xx if marked, -- if unmarked
         * @return a String Representation of field
         */
        public String toString ()
        {
            String s = (marked)? "xx" : "--";
            return (piece == null)? s : piece.toString ();
        }
    }


    public static final int NUMBER_OF_ROWS = 8;
    public static final int NUMBER_OF_COLUMNS = 8;
    public static final char FIRST_ROW = 'a';
    public static final int FIRST_COLUMN = 1;
    // A two dimensional array for creating the chessboard
    private Field[][] fields;

    /**
     * Initializes a chessboard
     */
    public Chessboard ()
    {
        fields = new Field[NUMBER_OF_ROWS][NUMBER_OF_COLUMNS];
        char row = 0;
        byte column = 0;
        for (int r = 0; r < NUMBER_OF_ROWS; r++)
        {
            row = (char) (FIRST_ROW + r);
            column = FIRST_COLUMN;
            for (int c = 0; c < NUMBER_OF_COLUMNS; c++)
            {
                fields[r][c] = new Field (row, column);
                column++;
            }
        }
    }

    /**
     * Return a String representation of chessboard
     * @return a String representation of chessboard
     */
    public String toString () {
        StringBuilder sb = new StringBuilder();
        sb.append("  1   2   3   4   5   6   7   8").append("\n");
        for (int i = 0; i < NUMBER_OF_ROWS; i++) {
            char c = (char) (FIRST_ROW + i);
            sb.append(c);


            for (int j = 0; j < NUMBER_OF_COLUMNS; j++)
                sb.append(" ").append(fields[i][j].toString()).append(" ");
            sb.append("\n");
        }
        return String.valueOf(sb);
    }


    /**
     * Return true if the given row and column are inside of the chessboard
     * @param row the given row
     * @param column the given column
     * @return true if the given row and column are inside of the chessboard, false otherwise
     */
    public boolean isValidField (char row, byte column) {
        return (row >= 'a' && row <= 'h' && column > 0 &&
                column <= NUMBER_OF_COLUMNS);
    }

    public abstract class Chesspiece
    {
        private char color;
        // w - white , b - black
        private char name;
        // K - King, Q - Queen , R - Rook, B - Bishop , N - Knight ,
        // P Pawn

        // The piece is created outside of the chessboard
        protected char row = 0;
        protected byte column = -1;

        /**
         * Initialize a new chess piece
         * @param color the color of the piece
         * @param name the name of the piece
         */
        protected Chesspiece (char color , char name) {
            this.color = color;
            this.name = name;
        }

        /**
         * Return a string representation of the chess piece(name + color).
         * @return a string representation of the chess piece
         */
        public String toString ()
        {
            return "" + color + name;
        }

        /**
         * Return true if the row and the column are valid
         * @return true if the row and the column are valid, otherwise false
         */
        public boolean isOnBoard ()
        {
            return Chessboard.this.isValidField (row, column);
        }

        /**
         * Move the chess piece onto the given coordinate(row, column) in the chessboard,
         * if the given row and column are valid
         * @param row given row
         * @param column given column
         * @throws NotValidFieldException trows exception if the given column and row are not valid
         */
        public void moveTo (char row, byte column)
                throws NotValidFieldException
        {
            if (!Chessboard.this.isValidField (row, column))
                throw new NotValidFieldException
                        ("bad field: " + row + column );
            this.row = row;
            this.column = column;
            int r = row - FIRST_ROW;
            int c = column - FIRST_COLUMN;
            Chessboard.this.fields[r][c].put (this);
        }

        /**
         *  Custom exception
          */
        public class NotValidFieldException extends Exception {
            public NotValidFieldException(String message){
                super(message);
            }
        }

        /**
         * Remove the chess piece from the board
         */
        public void moveOut () {
            if (isOnBoard())
            Chessboard.this.fields[row - FIRST_ROW][column - FIRST_COLUMN].take();

        }

        // These method are implemented in subclasses
        public abstract void markReachableFields ();
        public abstract void unmarkReachableFields ();
    }
    public class Pawn extends Chesspiece
    {
        public Pawn (char color , char name)
        {
            super (color , name);
        }

        public void markReachableFields ()
        {
            byte col = (byte) (column + 1);
            if (Chessboard.this.isValidField (row, col))
            {
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].mark ();
            }
        }
        public void unmarkReachableFields ()
        {
            byte col = (byte) (column + 1);
            if (Chessboard.this.isValidField (row, col))
            {
                int r = row - FIRST_ROW;
                int c = col - FIRST_COLUMN;
                Chessboard.this.fields[r][c].unmark ();
            }
        }
    }

    public class Rook extends Chesspiece {
        public Rook (char color , char name)
        {
            super (color , name);
        }

        public void markReachableFields () {
            for (byte col = 1; col <= NUMBER_OF_COLUMNS; col++) {
                if (isValidField(row, col) && col != column) {

                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;
                    fields[r][c].mark();
                }
            }

            for (char row = 'a'; row <= 'h'; row++) {
                if (isValidField(row, column) && row != this.row) {

                    int r = row - FIRST_ROW;
                    int c = column - FIRST_COLUMN;
                    fields[r][c].mark();
                }

            }
        }
        public void unmarkReachableFields()
        {
            for (byte col = 1; col <= NUMBER_OF_COLUMNS; col++) {
                if (isValidField(row, col) && col != column ) {

                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;
                    fields[r][c].unmark();
                }
            }

            for (char row = 'a'; row <= 'h'; row++) {
                if (isValidField(row, column) && row != this.row) {

                    int r = row - FIRST_ROW;
                    int c = column - FIRST_COLUMN;
                    fields[r][c].unmark();
                }

            }
        }
    }
    public class Knight extends Chesspiece {
        public Knight (char color , char name)
        {
            super (color , name);
        }

        int[] knR ={1, 2, 2, 1, -1, -2, -2, -1};
        int[] knC ={2, 1, -1, -2, 2, 1, -1, -2};
        public void markReachableFields() {


            for (int i = 0; i < NUMBER_OF_COLUMNS;i++)
                {
                    byte col = (byte)( this.column + knC[i]);
                    char row = (char) (this.row + knR[i]);
                    if (isValidField (row, col)) {

                        int r = row - FIRST_ROW;
                        int c = col - FIRST_COLUMN;

                        Chessboard.this.fields[r][c].mark();
                    }

                }

        }


        public void unmarkReachableFields() {
            for (int i = 0; i < NUMBER_OF_COLUMNS;i++)
            {
                byte col = (byte)( this.column + knC[i]);
                char row = (char) (this.row + knR[i]);
                if (isValidField (row, col)) {

                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;

                    fields[r][c].unmark();
                }

            }

        }
    }
    public class Bishop extends Chesspiece {
        public Bishop(char color, char name) {
            super(color, name);
        }

        int[] bC = {1, 1, -1, -1};
        int[] bR = {1, -1, 1, -1};


        public void markReachableFields() {
            for (int i = 1; i < 8; i++)
                for (int j = 0; j < 4; j++) {
                    byte col = (byte) (this.column + i * bC[j]);
                    char row = (char) (this.row + i * bR[j]);
                    if (isValidField(row, col)) {

                        int r = row - FIRST_ROW;
                        int c = col - FIRST_COLUMN;

                        fields[r][c].mark();
                    }

                }

        }


        public void unmarkReachableFields() {
            for (int i = 1; i < 8; i++)
                for (int j = 0; j < 4; j++) {
                    byte col = (byte) (this.column + i * bC[j]);
                    char row = (char) (this.row + i * bR[j]);
                    if (isValidField(row, col)) {

                        int r = row - FIRST_ROW;
                        int c = col - FIRST_COLUMN;

                        Chessboard.this.fields[r][c].unmark();

                    }
                }
        }
    }
    public class Queen extends Chesspiece {
        public Queen(char color , char name)
        {
            super (color , name);
        }

        int[] qC = {1, 1, -1, -1};
        int[] qR = {1, -1, 1, -1};

        public void markReachableFields() {

            for (byte col = 1; col <= NUMBER_OF_COLUMNS; col++) {
                if (isValidField(row, col) && col != column) {

                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;
                    fields[r][c].mark();
                }
            }

            for (char row = 'a'; row <= 'h'; row++) {
                if (isValidField(row, column) && row != this.row) {

                    int r = row - FIRST_ROW;
                    int c = column - FIRST_COLUMN;
                    fields[r][c].mark();
                }

            }

            for (int i = 1; i < 8; i++)
                for (int j = 0; j < 4; j++) {
                    byte col = (byte) (this.column + i * qC[j]);
                    char row = (char) (this.row + i * qR[j]);
                    if (isValidField(row, col)) {

                        int r = row - FIRST_ROW;
                        int c = col - FIRST_COLUMN;

                       fields[r][c].mark();
                    }

                }
        }


        public void unmarkReachableFields() {

            for (byte col = 1; col <= NUMBER_OF_COLUMNS; col++) {
                if (isValidField(row, col) && col != column) {

                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;
                    fields[r][c].unmark();
                }
            }

            for (char row = 'a'; row <= 'h'; row++) {
                if (Chessboard.this.isValidField(row, column)  && row != this.row) {

                    int r = row - FIRST_ROW;
                    int c = column - FIRST_COLUMN;
                    Chessboard.this.fields[r][c].unmark();
                }

            }

            for (int i = 1; i < 8; i++)
                for (int j = 0; j < 4; j++) {
                    byte col = (byte) (this.column + i * qC[j]);
                    char row = (char) (this.row + i * qR[j]);
                    if (isValidField(row, col)) {

                        int r = row - FIRST_ROW;
                        int c = col - FIRST_COLUMN;

                        Chessboard.this.fields[r][c].unmark();
                    }

                }

        }
    }
    public class King extends Chesspiece {
        public King (char color , char name)
        {
            super (color , name);
        }
        int[] kR = {0, 0, 1, 1,-1, -1, 1, -1 };
        int[] kC = {1, -1, 1, -1, 1, -1, 0, 0};
        public void markReachableFields() {
            for (int i =0; i < 8; i++){
                char row = (char) (this.row +kR[i]);
                byte col = (byte) (this.column + kC[i] );
                if(isValidField(row,col)){
                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;

                    Chessboard.this.fields[r][c].mark();
                }
            }


        }


        public void unmarkReachableFields() {
            for (int i =0; i < 8; i++){
                char row = (char) (this.row +kR[i]);
                byte col = (byte) (this.column + kC[i] );
                if(isValidField(row,col)){
                    int r = row - FIRST_ROW;
                    int c = col - FIRST_COLUMN;

                    Chessboard.this.fields[r][c].unmark();
                }
            }

        }
    }
}
