/*      Programming 1, EU3
        Created:2019-10-24
        Last updated: 2020-11-22
        Author: Amir Ali Safizadeh.
        Purpose of the code: A test for chessboard class.
 */

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Test {
    public static void main(String[] args) throws Chessboard.Chesspiece.NotValidFieldException, InterruptedException {
        // Using random to generate random numbers between 1 and 8 (both included);
        Random random = new Random();

        Chessboard chessBoard = new Chessboard ();
        System.out.println (chessBoard + "\n");
        Chessboard.Chesspiece[] pieces = new Chessboard.Chesspiece[6];
        pieces[0] = chessBoard.new Pawn ('w', 'P');
        pieces[1] = chessBoard.new Rook ('b', 'R');
        pieces[2] = chessBoard.new Queen ('w', 'Q');
        pieces[3] = chessBoard.new Bishop ('w', 'B');
        pieces[4] = chessBoard.new King ('b', 'K');
        pieces[5] = chessBoard.new Knight ('w', 'N');


        for (int i = 0; i < pieces.length; i++){
            pieces[i].moveTo((char)('a'+ random.nextInt(8)),(byte)(random.nextInt(8)+1));
            pieces[i].markReachableFields();
            System.out.println(chessBoard);
            TimeUnit.SECONDS.sleep(5); // 5 seconds delay
            pieces[i].unmarkReachableFields();
            pieces[i].moveOut();

        }

    }
}
