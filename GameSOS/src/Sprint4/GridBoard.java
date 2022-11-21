package Sprint4;

import Sprint4.Square.SquareVal;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeSet;



public class GridBoard {
	
	//variables:
	public final Player redPlayer = new Player(SquareVal.S, "Red Player");
	public final Player bluePlayer = new Player(SquareVal.O, "Blue Player");

    public static final int startSize = 3;
	public static final int endSize = 9;
	private int size = startSize; //boardSize
	private Square[][] grid;
	private GameType gameType = GameType.general;
	private TreeSet<Three> gameWin;
	
	private GameType type;
	private State gameState; //===//
	private Player playTurn = redPlayer;
	
	private static final Random RANDOM = new Random();
	
	//my ENUMS:
	public enum GameType {
		simple, general
	}
	
    public enum State {
        START, inGame, gameDraw, redPlayerWon, bluePlayerWon
    }
    
    
    //~~~constructors~~~ 1
    public GridBoard() {
    	grid = new Square[startSize][startSize];
    	//add: this.writer = new GameWriter(); 
    	startBoard();
    }
    
    //~~~constructors~~~ #2
    public GridBoard(int num) {
        setSize(num);
        grid = new Square[getSize()][getSize()];
        //add: this.writer = new GameWriter();
        startBoard();
    }
    

	//add: public void recording() function 
    
    //======setters=====
    private void startBoard() {
		// TODO Auto-generated method stub
		this.redPlayer.pointReset();
		this.bluePlayer.pointReset();
		gameState = State.START;
		playTurn = redPlayer;
		
		this.gameWin = new TreeSet<>();
		
		for (int r = 0; r < getSize(); r++) {
			for (int c = 0; c < getSize(); c++) {
				grid[r][c] = new Square(SquareVal.None);
			}
		}
		
		//add writer calls:
		//here
	}
  
	public void setSize(int num) {
		assert (num >= startSize && num <= endSize);
    	this.size = num; 
	}
	
	public void makeGrid(Square[][] grid) {
		assert (grid.length >= startSize && grid.length <= endSize);
    	this.grid = grid; 
    	this.setSize(grid.length);
	}
	
	public void setGameState(State s) {
		this.gameState = s; 
	}
	
	public void setGameType(GameType m) {
		for (Square[] r : grid) {
			for (Square square : r) {
				if (square.getValue() != SquareVal.None) {
					return;
				}
			}
		}
		this.gameType = m;
		//add this to game writer 
	}
	
	
	
	//======getters=====
    public int getSize() {
    	return this.size; 
    }
	
    public State getGameState() {
    	return this.gameState; 
    }
    
    public GameType getGameType() {
    	return this.gameType;
    }
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    
    
    //======board setters=====
    
    public void humanMove(int r, int c) {
    	assert (0 <= r && r < this.getSize() &&
    			0 <= c && c < this.getSize()) :
    				String.format("makeMove(%s, %s) out of bounds for board size %s.", r, c, this.getSize());
    	if (grid[r][c].getValue() == SquareVal.None) {
    		grid[r][c] = (this.playTurn == redPlayer) ? redPlayer.getSquare() : bluePlayer.getSquare();
    		
    		//add recording game variable here
    		
    		updateGameStatus();
    		this.playTurn = (this.playTurn == redPlayer) ? bluePlayer : redPlayer; 
    	}
    	
    	if (this.winner()) {
    		return;
    	}
    	
    	if(playTurn.getType() == Player.PlayerType.Computer && 
    			!fullBoard()) {
    		computerMove();
    	}
    }
    
    public void computerMove() {
    	if (this.winner()) {
    		return;
    	}
    	
    	ArrayList<Set> emptySquares = getEmpSquares();
    	Set playerPick = emptySquares.get(RANDOM.nextInt(emptySquares.size())); 
    	Square square = new Square(SquareVal.values()[RANDOM.nextInt(2)]);
    	
    	grid[playerPick.one][playerPick.two] = square;
    	playTurn.setSquare(square.getValue());
    	//add record game here
    	updateGameStatus();
    	
    	if (emptySquares.size() <= 1) {
    		return;
    	}
    	
    	playTurn = (playTurn == redPlayer) ? bluePlayer : redPlayer; 
    	if (playTurn.getType() == Player.PlayerType.Computer) {
    		computerMove(); 
    	}
    }
    
    private void updateGameStatus() {
    	callWinner(); 
    	switch (this.getGameType()) {
    	case simple:
    		if (simpleGameWon()) {
    			this.setGameState(this.playTurn == redPlayer ? State.redPlayerWon : State.bluePlayerWon);
    			Player playerWon = this.gameState == State.redPlayerWon ? this.redPlayer : this.bluePlayer;
    			//add recording writer call. 
    			//add if else statement
    		} else {
    			this.setGameState(this.fullBoard() ? State.gameDraw : State.inGame);
    		}
    		break;
    	case general: {
    		if (this.fullBoard()) {
    			if (generalGameWon()) {
    				this.setGameState(redPlayer.getPoints() > bluePlayer.getPoints() ? State.redPlayerWon\);
    			}
    		}
    	}
    		
    	
    	}
    }
    
    
    //======board getters======
    public Square getSquare (int r, int c) {
    	
    	assert (r >= 0 && 
    			r < this.getSize() && 
    			c >= 0 && 
    			c < this.getSize()) : 
    				String.format("getSquare(%s, %s) out of bounds for board size %s.", r, c, this.getSize());
    	
    	//returning grid based on the parameters:
    	return grid[r][c];
    }
    
    public ArrayList<Set> getEmpSquares() {
    	
    	ArrayList<Set> empSquares = new ArrayList<>();
    	for (int i = 0; i < this.getSize(); i++) {
    		for (int j = 0; j < this.getSize(); j++) {
    			if (this.grid[i][j].getValue() == SquareVal.None) {
    				empSquares.add(new Set(i, j));
    			}
    		}
    	}
    	return empSquares;
    }
	
	public Player getTurn() {
		return playTurn; 
	}
	
	//checks for the board is full [true] 
	public boolean fullBoard() {
		for (int i = 0; i < this.getSize(); i++) {
			for (int j = 0; j < this.getSize(); j++) {
				if ( this.grid[i][j].getValue() == Square.SquareVal.None) {
					return false;
				}
			}
		}
		return true;
	}
	

	
	
	//======winners===== 
	private boolean winner() {
		return this.getGameState() == State.redPlayerWon || 
				this.getGameState() == State.bluePlayerWon;
	}
	
	
    

    

}
