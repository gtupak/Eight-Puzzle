import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.Border;


public class Cell extends JButton {

	private int id, row, col;

	public Cell(Board board, int row, int col){
		this.row = row;
		this.col = col;
		
		//Sets white background, removes borders
		setBackground(Color.WHITE);
		Border emptyBorder = BorderFactory.createEmptyBorder(0, 0, 0, 0);
		setBorder(emptyBorder);
		setBorderPainted(false);
		
		//finds the id, depending on the row and col
		if (row == 0){
			id = col +1;
		}
		else if (row == 1){
			id = col + 4;
		}
		else if (row == 2){
			if (col == 2){
				id = 0;
			}
			else{
				id = col + 7;
			}
		}
		//sets icon
		setIcon(EightPuzzle.pictures[id]);

		addActionListener(board);
	}

	public int getId(){
		return id;
	}

	//sets cell id and updates picture
	public void setId(int id){
		this.id = id;
		setIcon(EightPuzzle.pictures[id]);
	}

	public int getRow(){
		return row;
	}

	public int getColumn(){
		return col;
	}

	public String toString(){
		return ("The id of this cell is "+ id );
	}



}
