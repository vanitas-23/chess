package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class knight extends Pieces {

    public knight(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.isWhite=isWhite;
        this.xpos=col*board.tileSize;
        this.ypos=row*board.tileSize;
        this.nam="knight";
        this.sprite=sheet.getSubimage(3*sheetScale,isWhite?0:sheetScale,sheetScale,sheetScale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }
    public boolean isValidMovement(int col,int row){

        return Math.abs(col-this.col)*Math.abs(row-this.row)==2;
    }
}
