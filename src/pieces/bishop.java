package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class bishop extends Pieces {
    public bishop(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.isWhite=isWhite;
        this.xpos=col*board.tileSize;
        this.ypos=row*board.tileSize;
        this.nam="bishop";
        this.sprite=sheet.getSubimage(2*sheetScale,isWhite?0:sheetScale,sheetScale,sheetScale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }
    public boolean isValidMovement(int col,int row){

        return Math.abs(col-this.col)==Math.abs(row-this.row);
    }
    public boolean moveCollides(int col,int row){
        //left up
        if(this.col>col && this.row>row){
            for(int i=1;i<Math.abs(this.col-col);i++)
                if(board.getPiece(this.col-i,this.row-i)!=null)
                    return true;
        }
        //up right
        if(this.col<col && this.row>row){
            for(int i=1;i<Math.abs(this.col-col);i++)
                if(board.getPiece(this.col+i,this.row-i)!=null)
                    return true;
        }
        //down left
        if(this.col>col && this.row<row){
            for(int i=1;i<Math.abs(this.col-col);i++)
                if(board.getPiece(this.col-i,this.row+i)!=null)
                    return true;
        }
        //down right
        if(this.col<col && this.row<row){
            for(int i=1;i<Math.abs(this.col-col);i++)
                if(board.getPiece(this.col+i,this.row+i)!=null)
                    return true;
        }

        return false;
    }
}
