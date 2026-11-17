package Fun;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Sudoku extends JFrame{

	
	private JButton numSelected = null;
	private int error = 0;
	private JLabel textLabel;
	
	//puzzle and solution

    private String[] puzzle = {
        "--74916-5",
        "2---6-3-9",
        "-----7-1-",
        "-586----4",
        "--3----9-",
        "--62--187",
        "9-4-7---2",
        "67-83----",
        "81--45---"
    };

    private String[] solution = {
        "387491625",
        "241568379",
        "569327418",
        "758619234",
        "123784596",
        "496253187",
        "934176852",
        "675832941",
        "812945763"
    };
	
	
	//number button
	class Tile extends JButton{
		int r;
		int c;
		public Tile(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
	}
	
	public Sudoku() {
		super("Sudoku");
	//setting
	setLayout(new BorderLayout());
	textLabel = new JLabel();
	JPanel textPanel = new JPanel();
	JPanel boardPanel = new JPanel();
	JPanel buttonPanel = new JPanel();
	
	//sudoku label
	textLabel.setFont(new Font("Arial", Font.BOLD,30));
	textLabel.setHorizontalAlignment(JLabel.CENTER);
	textLabel.setText("Sudoku: 0");
	textPanel.add(textLabel);
	add(textPanel,BorderLayout.NORTH);
	
	//board
	boardPanel.setLayout(new GridLayout(9,9));
	setupTiles(boardPanel);
	add(boardPanel,BorderLayout.CENTER);
	
	//button at Sourth
	buttonPanel.setLayout(new GridLayout(1,9));
	setupButton(buttonPanel);
	add(buttonPanel,BorderLayout.SOUTH );
	}
	//button row, col, setup button
	private void setupButton(JPanel buttonPanel) {

		for(int i=  0; i<10; i++) {
			JButton button = new JButton();
			button.setText(String.valueOf(i));
			button.setFont(new Font("Arial",Font.BOLD,20));
			button.setBackground(Color.white);
			button.setFocusable(false);
			buttonPanel.add(button);
			
			//actionListener
			button.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					//unselected thing
					if(numSelected != null) {
						numSelected.setBackground(Color.WHITE);
					}
					numSelected = (JButton) e.getSource();
					numSelected.setBackground(Color.LIGHT_GRAY);
				}
			});
		}
	}
	
	// board row & col , setupTiles
	private void setupTiles(JPanel boardPanel) {
		for(int r = 0; r< 9; r++) {
			for(int c = 0; c < 9; c++) {
				Tile tile = new Tile(r,c);
				char tileChar = puzzle[r].charAt(c);
				
				//make invisible to '-'
				if(tileChar != '-') {
					tile.setFont(new Font("Arial", Font.BOLD,20));
					tile.setBackground(Color.LIGHT_GRAY);
					//adding char in tile
					tile.setText(String.valueOf(tileChar));
				}
				else {
					tile.setFont(new Font("Arial", Font.PLAIN,20));
					tile.setBackground(Color.WHITE);
				}
				
				//optional border line
				if((r == 2 && c == 2) || (r == 5 && c == 5) || (r == 2 && c == 5) || (r == 5 && c == 2)) {
	                tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 5, Color.BLACK));
	            } else if(r == 2 || r == 5) {
	                tile.setBorder(BorderFactory.createMatteBorder(1, 1, 5, 1, Color.BLACK));
	            } else if(c == 2 || c == 5) {
	                // Changed right border width from 1 to 5
	                tile.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 5, Color.BLACK));
	            } else {
	                tile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	            }
				//fix click 
				tile.setFocusable(false);
				//adding
				boardPanel.add(tile);
				
				tile.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Tile tile = (Tile) e.getSource();
						int r = tile.r;
						int c = tile.c;
						//fill num
						if(numSelected != null) {
							if(!tile.getText().isEmpty()) {
								return ;
							}
							String numSelectedText = numSelected.getText();
							String tileSolution = String.valueOf(solution[r].charAt(c));
							
							if(tileSolution.equals(numSelectedText)) {
								tile.setText(numSelectedText);
							}
							else {
								error += 1;
								textLabel.setText("Sudoku: " + String.valueOf( error));
								
							}
						}
					}
					
				});
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.setVisible(true);
		s.setSize(600, 650);
		s.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setLocationRelativeTo(null);

		
	}

}
