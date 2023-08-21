package main;

import pieces.*;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Board extends JPanel {

    public int tileSize=70;
    int cols=8;
    int rows=8;

    static ArrayList<Pieces>pieceList=new ArrayList<>();
    public Pieces selectedPiece;

    Input input=new Input(this);
    public int enPassant=-1;

    public Board(){
        this.setPreferredSize(new Dimension(cols*tileSize,rows*tileSize));
        //this.setBackground(Color.green);
        this.addMouseListener(input);
        this.addMouseMotionListener(input);
        addpieces();
    }
    public Pieces getPiece(int col,int row)
    {
        for(Pieces piece:pieceList){
            if(piece.col==col && piece.row==row)
                return piece;
        }
        return null;
    }

    public void makeMove(Move move){
        if(move.piece.nam.equals("pawn"))
            movePawn(move);
        else {
            move.piece.col = move.newCol;
            move.piece.row = move.newRow;
            move.piece.xpos = move.newCol * tileSize;
            move.piece.ypos = move.newRow * tileSize;
            capture(move.captured);
            move.piece.isFirst = false;
        }
    }
    public void movePawn(Move move){
        //System.out.println("Pawn");
        int id=move.piece.isWhite?1:-1;
        if(getfile(move.newCol,move.newRow)==enPassant){
            move.captured=getPiece(move.newCol,move.newRow+id);
        }
        if(Math.abs(move.piece.row-move.newRow)==2)
            enPassant=getfile(move.newCol,move.newRow+id);
        else
            enPassant=-1;
        id=move.piece.isWhite?0:7;
        if(move.newRow==id)
            promotion(move);
        move.piece.col = move.newCol;
        move.piece.row = move.newRow;
        move.piece.xpos = move.newCol * tileSize;
        move.piece.ypos = move.newRow * tileSize;
        capture(move.captured);
        move.piece.isFirst = false;
    }

    private void promotion(Move move) {
        pieceList.add(new queen(this,move.newCol,move.newRow,move.piece.isWhite));
        capture(move.piece);
    }

    public void capture(Pieces piece){
        pieceList.remove(piece);
    }


    public boolean isValidMove(Move move){

        if(sameTeam(move.piece,move.captured))
            return false;
        if(move.newCol>7 || move.newRow>7 || move.newCol<0 || move.newRow<0)
            return false;
        if(!move.piece.isValidMovement(move.newCol, move.newRow))
            return false;
        return !move.piece.moveCollides(move.newCol, move.newRow);
    }
    public boolean sameTeam(Pieces p1,Pieces p2){
        if(p1==null || p2==null)
            return false;
        return p1.isWhite==p2.isWhite;
    }

    public int getfile(int row,int col){
        return rows*row+col;
    }
    public void addpieces(){
        pieceList.add(new knight(this,1,0,false));
        pieceList.add(new knight(this,1,7,true));
        pieceList.add(new knight(this,6,0,false));
        pieceList.add(new knight(this,6,7,true));
        pieceList.add(new rook(this,0,0,false));

        pieceList.add(new rook(this,0,7,true));
        pieceList.add(new rook(this,7,0,false));
        pieceList.add(new rook(this,7,7,true));
        pieceList.add(new bishop(this,2,0,false));
        pieceList.add(new bishop(this,2,7,true));
        pieceList.add(new bishop(this,5,0,false));
        pieceList.add(new bishop(this,5,7,true));
        pieceList.add(new queen(this,3,0,false));
        pieceList.add(new queen(this,3,7,true));
        pieceList.add(new king(this,4,0,false));
        pieceList.add(new king(this,4,7,true));
        pieceList.add(new pawn(this,0,1,false));
        pieceList.add(new pawn(this,1,1,false));
        pieceList.add(new pawn(this,2,1,false));
        pieceList.add(new pawn(this,3,1,false));
        pieceList.add(new pawn(this,4,1,false));
        pieceList.add(new pawn(this,5,1,false));
        pieceList.add(new pawn(this,6,1,false));
        pieceList.add(new pawn(this,7,1,false));
        pieceList.add(new pawn(this,0,6,true));
        pieceList.add(new pawn(this,1,6,true));
        pieceList.add(new pawn(this,2,6,true));
        pieceList.add(new pawn(this,3,6,true));
        pieceList.add(new pawn(this,4,6,true));
        pieceList.add(new pawn(this,5,6,true));
        pieceList.add(new pawn(this,6,6,true));
        pieceList.add(new pawn(this,7,6,true));
      //  pieceList.remove(pieceList.size()-1);
    }
    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D) g;
        //paint the board
        for(int i=0;i<rows;i++)
            for(int j=0;j<cols;j++){
                g2d.setColor((i+j)%2==0?new Color(255, 255, 255):new Color(3, 64, 57));
                g2d.fillRect(j*tileSize,i*tileSize,tileSize,tileSize);
            }
        //paints highlights
        if(selectedPiece!=null)
        for(int r=0;r<rows;r++)
            for(int c=0;c<cols;c++){
                if(isValidMove(new Move(this,selectedPiece,c,r))){
                    g2d.setColor(new Color(201, 128, 7,190));
                    g2d.fillRect(c*tileSize,r*tileSize,tileSize,tileSize);
                }
            }


        for (Pieces piece:pieceList){
            piece.paint(g2d);
        }
    }



}
