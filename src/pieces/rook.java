package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class rook extends Pieces {
    public rook(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.isWhite=isWhite;
        this.xpos=col*board.tileSize;
        this.ypos=row*board.tileSize;
        this.nam="rook";
        this.sprite=sheet.getSubimage(4*sheetScale,isWhite?0:sheetScale,sheetScale,sheetScale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }
    public boolean isValidMovement(int col,int row){

        return Math.abs(col-this.col)==0 || Math.abs(row-this.row)==0;
    }
    public boolean moveCollides(int col,int row){
        //left
        if(this.col>col)
            for(int c=this.col-1;c>col;c--)
                if(board.getPiece(c,this.row)!=null)
                    return true;
        //right
        if(this.col<col)
            for(int c=this.col+1;c<col;c++)
                if(board.getPiece(c,this.row)!=null)
                    return true;
        //up
        if(this.row<row)
            for(int r=this.row+1;r<row;r++)
                if(board.getPiece(this.col,r)!=null)
                    return true;
        //down
        if(this.row>row)
            for(int r=this.row-1;r>row;r--)
                if(board.getPiece(this.col,r)!=null)
                    return true;

        return false;
    }
}
