package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class king extends Pieces {
    public king(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.isWhite=isWhite;
        this.xpos=col*board.tileSize;
        this.ypos=row*board.tileSize;
        this.nam="king";
        this.sprite=sheet.getSubimage(0,isWhite?0:sheetScale,sheetScale,sheetScale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }
    public boolean isValidMovement(int col,int row){

       return (Math.abs(col-this.col)+Math.abs(row-this.row)<2) || (Math.abs(col-this.col)==1 &&(Math.abs(row-this.row)==1));
    }
}
