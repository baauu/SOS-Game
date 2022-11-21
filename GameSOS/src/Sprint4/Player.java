package Sprint4;

import Sprint4.Square.SquareVal;


public class Player {
	
	//variables:
	private Square square;
	private Integer pts;
	private PlayerType playerType;
	private final String playerID;
	
	//enumerate: 
	public enum PlayerType {
        Human, Computer
    }
	
	//constructor:
	public Player(SquareVal value, String playerID) {
		
		this.playerType = PlayerType.Human;
        this.square = new Square(value);
		this.playerID = playerID;
        this.pts = 0;
  
    }
	
	public Square getSquare() {
		return this.square;
	}
	
	
	
	//setters: 
	public void setType(PlayerType playerType) {
		this.playerType = playerType;
	}
	
	public void pointReset() {
		this.pts = 0;
	}
	
	public void pointInc() {
		this.pts++;
	}
	
	public void setSquare(SquareVal square) {
		this.square = new Square(square);
		
	}
	
	//getters:
	public PlayerType getType() {
		return this.playerType;
	}
	
	public Integer getPoints() {
		return this.pts;
	}
	
	public String getID() {
		return this.playerID;
	}
	
	public String print() {
		return String.format(" %s (%s, %s) ", this.playerID, this.square, this.playerType);
	}
	
	
	
	
	
	

}
