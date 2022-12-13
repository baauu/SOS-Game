package Sprint4;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

import sprint_5.src.Board;
import sprint_5.src.GUI;
import sprint_5.src.Player;
import sprint_5.src.Tile;
import sprint_5.src.GUI.GameBoardCanvas;

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
    
    private int canvas_width;
    private int canvas_height;
    
   
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
		SwingUtilities.invokeLater(() -> new GUI(new GridBoard()));

	}
	
	//get gridboard
	public GridBoard getGridBoard() {
		return gridBoard;
	}
	
	private void setContentPane () {
		
		
		//size of the menu
		this.gameBoardCanvas = new GameBoardCanvas();
		canvas_width = sizeCube * board.getBoardSize();
		canvas_height = sizeCube * board.getBoardSize();
        gameBoardCanvas.setPreferredSize(new Dimension(canvas_width, canvas_height));

        gameStatusBar = new JLabel("");
        gameStatusBar.setFont(new Font(Font.DIALOG_INPUT, Font.BOLD, 15));
        gameStatusBar.setBorder(BorderFactory.createEmptyBorder(2, 5, 4, 5));

        JPanel topMenu = generateGameMenu();
        JPanel leftMenu = generateTileMenu(this.gridBoard.rplayer);
        JPanel rightMenu = generateTileMenu(this.gridBoard.bplayer);

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        contentPane.removeAll();
        
        //arranging the display board 
        
        contentPane.add(topMenu, BorderLayout.PAGE_START);
        contentPane.add(leftMenu, BorderLayout.WEST);
        contentPane.add(rightMenu, BorderLayout.EAST);
        contentPane.add(gameBoardCanvas, BorderLayout.CENTER);
        contentPane.add(gameStatusBar, BorderLayout.AFTER_LAST_LINE);
	}
	
	private void resetGame() {
        this.gridBoard.initBoard();
        setContentPane();
        pack();
        this.gameBoardCanvas.repaint();
        this.gameBoardCanvas.printStatusBar();
    }
	

	public JPanel generateGameMenu() {
		//setting the menu
        JPanel menu = new JPanel();
        menu.setPreferredSize(new Dimension(canvas_width + offset, 100));

        JPanel boardSizeMenu = new JPanel();
        boardSizeMenu.setPreferredSize(new Dimension(canvas_width + halfwidth, 20));

        ButtonGroup gameModeSelection = new ButtonGroup();
        menu.setLayout(new BorderLayout());
        JRadioButton simpleGame = new JRadioButton("Simple      ");
        JRadioButton generalGame = new JRadioButton("General");
        JButton newGame = new JButton("New Game");
        JButton replayGame = new JButton("Replay Game");
        JButton incButton = new JButton("+");
        JButton decButton = new JButton("-");
        JCheckBox recordGame = new JCheckBox("Record");

        Runnable resetSelected = () -> {
            simpleGame.setSelected(this.gridBoard.getGameMode() == GridBoard.GameMode.Simple);
            generalGame.setSelected(this.gridBoard.getGameMode() == GridBoard.GameMode.General);
            recordGame.setSelected(this.gridBoard.recordGame);
        };

        newGame.addActionListener(e -> {
            resetGame();
            resetSelected.run();
        });

        resetSelected.run();

        //~~~~~~~Action Listener~~~~~~~~~`
        simpleGame.addActionListener(e -> gridBoard.setGameMode(GridBoard.GameMode.Simple));
        generalGame.addActionListener(e -> gridBoard.setGameMode(GridBoard.GameMode.General));
        recordGame.addActionListener(e -> gridBoard.toggleRecording());
        replayGame.addActionListener(e -> {
            
        	resetGame();
            try {
                replayGame();
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        });

        //action listener
        incButton.addActionListener(e -> {
            if (this.gridBoard.getBoardSize() >= GridBoard.MAX_BOARD_SIZE) {
                return;
            }
            gridBoard.setGrid(new Tile[gridBoard.getBoardSize() + 1][gridBoard.getBoardSize() + 1]);
            resetGame();
        });

      //action listener
        decButton.addActionListener(e -> {
            if (gridBoard.getBoardSize() <= GridBoard.MIN_BOARD_SIZE) {
                return;
            }
            gridBoard.setGrid(new Square[gridBoard.getBoardSize() - 1][gridBoard.getBoardSize() - 1]);
            resetGame();
        });

        
        
        menu.setLayout(new BorderLayout());
        menu.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        boardSizeMenu.setLayout(new BorderLayout());
        boardSizeMenu.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));

        boardSizeMenu.add(decButton, BorderLayout.WEST);
        boardSizeMenu.add(incButton, BorderLayout.EAST);
        
        
        //grouping button
        gameModeSelection.add(simpleGame);
        gameModeSelection.add(generalGame);
        
        
        

        ////////////////////////////////
        JPanel gameStartMenu = new JPanel();
        gameStartMenu.setLayout(new BorderLayout());
        gameStartMenu.add(newGame, BorderLayout.WEST);
        gameStartMenu.add(replayGame, BorderLayout.EAST);
        /////////////////

        
        
        
        //setting up the menu
        menu.add(boardSizeMenu, BorderLayout.PAGE_START);
        menu.add(simpleGame, BorderLayout.WEST);
        menu.add(recordGame, BorderLayout.CENTER);
        menu.add(generalGame, BorderLayout.EAST);
        menu.add(gameStartMenu, BorderLayout.PAGE_END);
        return menu;
        //+++++++++++++++
    }
	
	public JPanel generateSquareMenu (Player player) {
		//variabel settings:
        JPanel menu = new JPanel();
        ButtonGroup tileSelection = new ButtonGroup();
        ButtonGroup styleSelection = new ButtonGroup();
        menu.setLayout(new FlowLayout());
        menu.add(new Label(player.getName()));
        JRadioButton s = new JRadioButton("S");
        JRadioButton o = new JRadioButton("O");
        JRadioButton human = new JRadioButton("Human");
        JRadioButton comp = new JRadioButton("Computer");
        
        //setting the menu in the players

        s.setSelected(player.getSquare().getValue() == Square.SquareVal.S);
        o.setSelected(player.getSquare().getValue() == Square.SquareVal.O);
        //player types
        human.setSelected(player.getType() == Player.PlayerType.Human);
        comp.setSelected(player.getType() == Player.PlayerType.Computer);

        s.addActionListener(e -> player.setSquare(Square.SquareVal.S));
        o.addActionListener(e -> player.setSquare(Square.SquareVal.O));
        human.addActionListener(e -> player.setType(Player.PlayerType.Human));
        
        //action listener
        comp.addActionListener(e -> {
        	
            player.setStyle(Player.PlayerTyper.Computer);
            if (gridBoard.getTurn() == player) {
            	gridBoard.makeComputerMove();
                //repaint();
            }
        });

        tileSelection.add(s);
        tileSelection.add(o);
        styleSelection.add(human);
        styleSelection.add(comp);
        menu.add(s);
        menu.add(o);
        menu.add(human);
        menu.add(comp);
        menu.setPreferredSize(new Dimension(100, canvas_height + 15));
        return menu;
    }
}
