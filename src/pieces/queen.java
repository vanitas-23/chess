package pieces;

import main.Board;

import java.awt.image.BufferedImage;

public class queen extends Pieces {
    public queen(Board board,int col,int row,boolean isWhite){
        super(board);
        this.col=col;
        this.row=row;
        this.isWhite=isWhite;
        this.xpos=col*board.tileSize;
        this.ypos=row*board.tileSize;
        this.nam="queen";
        this.sprite=sheet.getSubimage(1*sheetScale,isWhite?0:sheetScale,sheetScale,sheetScale).getScaledInstance(board.tileSize,board.tileSize, BufferedImage.SCALE_SMOOTH);

    }
    public boolean isValidMovement(int col,int row){

        return (Math.abs(col-this.col)==Math.abs(row-this.row)) || (Math.abs(col-this.col)==0 || Math.abs(row-this.row)==0);
    }

    public boolean moveCollides(int col,int row) {

        if(Math.abs(col-this.col)==0 || Math.abs(row-this.row)==0){
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

        }
        else if(Math.abs(col-this.col)==Math.abs(row-this.row)){
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
        }
        return false;
    }
}
