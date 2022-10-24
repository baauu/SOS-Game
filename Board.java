package GUI;

import java.awt.Graphics;

import javax.swing.JPanel;


public class Board extends JPanel {
	
	//getting number
	static GUI variable = new GUI();
	static int ourSize = variable.getSize(); 
	
	private static final long serialVersionUID = 1L;
	
	
	static final int cols = 7;
	static final int rows = 7;

	static final int originX = 5;
	static final int originY = 10;
	static final int cellSide = 50;
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		for (int i = 0; i < rows + 1; i++) {
			g.drawLine(originX, originY + i * cellSide, originX + cols * cellSide, originY + i * cellSide);
		}
		for (int i = 0; i < cols + 1; i++) {
			g.drawLine(originX + i * cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
		}
		
	}
	
	
}
	
/*	
//GUI variable = new GUI();
	
	
		public static void displayBoard() {
		final int cols = ourSize; 
		final int rows = ourSize;

		final int originx = 5;
		final int originy = 37;
		final int cellSide = 59;
		
		
	
	protected void paintComponent (Graphics g) {
		super.paintComponent(g);

		for (int i = 0; i < rows + 1; i++) {
			g.drawLine(originx, originy + 1 * cellSide, originX + cols
	}

}
*/
	
