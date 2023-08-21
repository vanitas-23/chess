package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class pawn extends Pieces {
    public pawn(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.isWhite=isWhite;
        this.xpos=col*board.tileSize;
        this.ypos=row*board.tileSize;
        this.nam="pawn";
        this.sprite=sheet.getSubimage(5*sheetScale,isWhite?0:sheetScale,sheetScale,sheetScale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }
    public boolean isValidMovement(int col,int row){
        int id=isWhite?1:-1;

        if(isFirst)
            return (Math.abs(row-this.row)==1 || Math.abs(row-this.row)==2)&&(Math.abs(col-this.col)==0);
        if(this.col==col && row==this.row -id && board.getPiece(col,row)==null)
            return true;

        // capture left
        if (col == this.col - 1 && row == this.row -id && board.getPiece (col, row) != null)
        return true;
        //capture right
        if (col == this.col + 1 && row == this.row -id && board.getPiece (col, row) != null)
            return true;
        //en-passant left
        if(board.enPassant== board.getfile(col, row) && col==this.col-1 && row==this.row-id && board.getPiece(col, row+id)!=null)
            return true;
        //en-passant right
        if(board.enPassant== board.getfile(col, row) && col==this.col+1 && row==this.row-id && board.getPiece(col, row+id)!=null)
            return true;
        return false;
    }

}
