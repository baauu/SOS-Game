package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GUI implements ActionListener{
	
	public static JPanel centerPanel;
	public static int boardSize;
	private static JPanel northPanel; 
	
	public GUI() {
		
		JFrame mainFrame = new JFrame();
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setTitle("SOS Game");
		mainFrame.setBounds(200, 200, 450, 300);
		
		//Layout
		mainFrame.getContentPane().setLayout(new BorderLayout(0,0));
		
		//North Panel:
		northPanel = new JPanel();
		mainFrame.getContentPane().add(northPanel, BorderLayout.NORTH);
		
		JLabel setLabel = new JLabel("Settings:");
		northPanel.add(setLabel);
		
		//North button Group
		ButtonGroup topGroup = new ButtonGroup(); 
		JRadioButton simpleButton = new JRadioButton("Simple");
		JRadioButton generalButton = new JRadioButton("General");
		topGroup.add(simpleButton);
		topGroup.add(generalButton);

		northPanel.add(simpleButton);
		northPanel.add(generalButton);
		
		JLabel sizeText = new JLabel("Size:");
		northPanel.add(sizeText);
		

		//Left Panel: 
		JPanel leftPanel = new JPanel();
		mainFrame.getContentPane().add(leftPanel, BorderLayout.WEST);
		leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));
		
		//Left Button Group:
		JLabel bluePlayer = new JLabel("Blue:");
		leftPanel.add(bluePlayer);
		
		ButtonGroup leftGroup = new ButtonGroup(); 
		JRadioButton sLeftButton = new JRadioButton("S");
		JRadioButton oLeftButton = new JRadioButton("O");
		leftGroup.add(sLeftButton);
		leftGroup.add(oLeftButton);

		leftPanel.add(sLeftButton);
		leftPanel.add(oLeftButton);
		
		//Right Panel:
		JPanel rightPanel = new JPanel();
		mainFrame.getContentPane().add(rightPanel, BorderLayout.EAST);
		rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

		//Right Button Group:
		JLabel redPlayer = new JLabel("Red:");
		rightPanel.add(redPlayer);
		
		ButtonGroup rightGroup = new ButtonGroup(); 
		JRadioButton sRightButton = new JRadioButton("S");
		JRadioButton oRightButton = new JRadioButton("O");
		rightGroup.add(sRightButton);
		rightGroup.add(oRightButton);

		rightPanel.add(sRightButton);
		rightPanel.add(oRightButton);


		//Center Panel:
			//changed JPanel here****** 
		centerPanel = new JPanel();
		mainFrame.getContentPane().add(centerPanel, BorderLayout.CENTER);

		
		//TEXTFIELDS ~~~~~~~~~~~~~~~~~~~
		JTextField boardSizeText = new JTextField(2);
		northPanel.add(boardSizeText);

		boardSizeText.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String input = boardSizeText.getText();
				boardSize = Integer.parseInt(input);
				
			}
			
		});
		
		//northPanel.add(boardSizeText);
		
		
		mainFrame.setVisible(true);
		
	
	}

	public int getSize() {
			
		return boardSize;
	}
	
	public static void displayBoard () {
		Board grid = new Board();
		centerPanel.add(grid);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GUI();
		//displayBoard();
		Board grid = new Board();
		centerPanel.add(grid);
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
				
	}

}
