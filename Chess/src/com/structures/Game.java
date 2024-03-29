package com.structures;
import javax.swing.ImageIcon;
import java.util.*;
import com.structures.pieces.NullPiece;
import com.structures.command.*;

public class Game{
	private static Game instance;
	private Board gBoard;
	private UndoHistoryBoard historyBoard;
	private int playerTurn;
	private boolean gameover=false;
	public Chess check;
	private static final long serialVersionUID = 1L;

	private Game()
	{	
		gBoard=Board.getBoard();
		historyBoard=UndoHistoryBoard.getHistoryBoard();
		
	}
	public static Game getGame()
	{
		if(instance==null)
			instance=new Game();
		return instance;
	}
	public ImageIcon getPieceIcon(int i,int j)
	{
		return gBoard.mat[i][j].icon();
	}
	public boolean isDraw()
	{
		if (gBoard.getPieceNumber()<3)
			return true;
		return false;
	}
	public int[][] getPiecePosibleMove(int i,int j)
	{
		if(getPiece(i,j)%10==9)
			return check.mutariposibile(check.Checkmat());
		return gBoard.mat[i][j].posibleMove(gBoard.configBoard[i][j],i,j, gBoard.configBoard);
	}
	public void movePiece(int pieceInitialPosition,int pieceNewPosition)
	{
		historyBoard.addBoard(new HistoryEntry(playerTurn,gBoard.configBoard,""+pieceInitialPosition/10+pieceInitialPosition%10,""+pieceNewPosition/10+pieceNewPosition%10));
		int movedPiece;
		movedPiece=gBoard.configBoard[pieceInitialPosition/10][pieceInitialPosition%10];
		gBoard.configBoard[pieceInitialPosition/10][pieceInitialPosition%10]=0;
		gBoard.configBoard[pieceNewPosition/10][pieceNewPosition%10]=movedPiece;

		Piece movedPieceObject=gBoard.mat[pieceInitialPosition/10][pieceInitialPosition%10];
		gBoard.mat[pieceInitialPosition/10][pieceInitialPosition%10]=new NullPiece();
		gBoard.mat[pieceNewPosition/10][pieceNewPosition%10]=movedPieceObject;
	}
	public void setConfigBoardTo(int Position,int pieceId)
	{
		gBoard.configBoard[Position/10][Position%10]=pieceId;
	}
	public void setPieceTo(int Position,Piece piesa)
	{
		gBoard.mat[Position/10][Position%10]=piesa;
	}
	public int getPiece(int i,int j)
	{
		return gBoard.configBoard[i][j];
	}
	public int getPlayerTurn() {
		return playerTurn;
	}
	public void setPlayerTurn(int playerTurn) {
		this.playerTurn = playerTurn;
	}
	
	public boolean isCheck()
	{
		check=new Chess(this.getPlayerTurn(),gBoard);
		if(check.check)
			return true;
		return false;
	}
	public boolean isCheckMate()
	{
		check=new Chess(this.getPlayerTurn(),gBoard);
		if (check.checkmate)
			return true;
		return false;
	}
	
	public boolean isGameover() {
		return gameover;
	}
	public void setGameover(boolean gameover) {
		this.gameover = gameover;
	}
	public ArrayList<Integer> getUncheckPieces()
	{
		check=new Chess(this.getPlayerTurn(),gBoard);
		return check.getpiesemutabile();
	}
}
