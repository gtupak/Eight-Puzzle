import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;  
import java.io.*;  
import java.awt.*;

import javax.swing.*;



public class EightPuzzle extends JFrame implements ActionListener {

	private static Board b;
	public static ImageIcon[] pictures; //A public array to store the divided images

	public EightPuzzle(){
		super("Eight-Puzzle");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBackground(Color.WHITE);

		b = new Board();
		add(b, BorderLayout.CENTER);

		JButton newGame = new JButton("Start a new game");
		newGame.addActionListener(this);

		JPanel control = new JPanel();
		control.setBackground(Color.WHITE);
		control.add(newGame);
		add(control, BorderLayout.SOUTH);

		pack();
		setResizable(false);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command == "Start a new game" ){
			b.init();
		}

	}

	public static void main(String[] args) throws IOException{
		StudentInfo.display();
		
		//Breaks the img into pieces
		File picture = new File("data/Karateka.jpg");

		FileInputStream fis = new FileInputStream(picture);
		BufferedImage image = ImageIO.read(fis);

		//get number of pieces
		int rows = 3;
		int cols = 3;
		int pieces = rows*cols;

		//get piece width and height
		int pieceWidth = image.getWidth() / cols;
		int pieceHeight = image.getHeight() / rows;
		int count = 0;
		BufferedImage imgs[] = new BufferedImage[pieces]; //Array to hold the pieces

		for (int x = 0; x < rows; x++){
			for (int y = 0; y < cols; y++){
				//Initialise the image array with image pieces
				imgs[count] = new BufferedImage(pieceWidth, pieceHeight, image.getType());

				//Draws the image piece
				Graphics2D gr = imgs[count++].createGraphics();
				gr.drawImage(image, 0, 0, pieceWidth,pieceHeight, pieceWidth * y, pieceHeight * x, pieceWidth * y +pieceWidth, pieceHeight * x + pieceHeight, null);
				gr.dispose();
			}
		}
		//Write splitted imgs into jpg files
		for (int i = 0; i < imgs.length; i++){
			ImageIO.write(imgs[i], "jpg", new File("data/img"+ i + ".jpg"));
		}
		//Store pictures into public static array
		pictures = new ImageIcon[pieces];
		for (int i = 0; i < pieces; i++){
			if (i != pieces - 1){
				pictures [i+1] = new ImageIcon("data/img" + i + ".jpg");
			}
			else{
				pictures [0] = new ImageIcon("data/blank.jpg");
			}
		}
		//Launges application
		new EightPuzzle();
	}

}


