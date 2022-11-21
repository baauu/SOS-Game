package Sprint4;

import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GUI {
	
	//declarations: 
	//class:
	private GridBoard gridBoard; //GridBoard.java
	//function:
	private GameBoard gameBoard; //function underneith
	
	
	//variables:
	private static final int stroke = 2;
	private static final int sizeCube = 100;
    private static final int offset = 200;
    private static final int width = 8;
    private static final int halfWidth = width/2;
    private static final int pad = sizeCube/6;
    
   
	//constructor:
	public GUI(GridBoard gridBoard) {
		//reference
		this.gridBoard = board; 
		
	}
	
	class GameBoard extends JPanel {
		
		//constructor:
		GameBoard() {
			
			//MouseListener
			addMouseListener( new MouseAdapter() {
				//decalare event
				public void mouseClicked(MouseEvent e) {
					if(gridBoard.getStat() == gridBoard.Stat.inGame)
				}
			})
		}
	}
	
	
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
