package main;

import pieces.Pieces;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input extends MouseAdapter {
   Board board;
   public Input(Board board){
       this.board=board;
   }

    @Override
    public void mousePressed(MouseEvent e) {
       int col=e.getX()/board.tileSize;
       int row =e.getY()/board.tileSize;
        Pieces pieceXY=board.getPiece(col,row);
        if(pieceXY != null)
            board.selectedPiece=pieceXY;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        int col=e.getX()/ board.tileSize;
        int row=e.getY()/ board.tileSize;
        System.out.println(col+" "+row);

        if(board.selectedPiece!=null ){
            Move move=new Move(board,board.selectedPiece,col,row);
             if(board.isValidMove(move))
                 board.makeMove(move);
            else{

                board.selectedPiece.xpos=board.selectedPiece.col* board.tileSize;
                board.selectedPiece.ypos=board.selectedPiece.row*board.tileSize;
             }


        }
        board.selectedPiece=null;
        board.repaint();
       // board.capture(m);
    }





    @Override
    public void mouseDragged(MouseEvent e) {
        if(board.selectedPiece!=null) {
            board.selectedPiece.xpos = e.getX() - board.tileSize / 2;
            board.selectedPiece.ypos=e.getY()-board.tileSize/2;
            board.repaint();
        }
    }

}
