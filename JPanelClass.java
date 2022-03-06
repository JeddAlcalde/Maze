import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;
import java.awt.Color;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.io.File;
import java.util.Scanner;

public class JPanelClass extends JPanel implements ActionListener, KeyListener
{

	Grid myGrid;//Initializes Grid with upper dimension limits

	GriddedSprite backgroundWall;
	GriddedSprite backgroundPath;

	BufferedImage currentDisplay;

	BufferedImage currentWall;
	BufferedImage currentPath;

	//Image scaledCurrentWall;

	Scanner scan = new Scanner(System.in);

	int destinationX;
	int destinationY;

	PathWalkerRobot robotSprite1;

	//CustomFileReader myFileReader = new CustomFileReader();

	int[][] binaryMap;//Uses file reader to input ones and zeroes, is later used to take out all of the indeces and turn the 1's to paths

	public JPanelClass()
	{
		Timer timer = new Timer(100, this);
		timer.start();

		System.out.println("Boxes are automatically 40x40, and grid is set by user");
		System.out.println("Paths are automatically set, previous version did allow for a custom path");
		int boxWidth = 40;//Width of each box
		int boxHeight = 40;//Height of each box
		int Y = 25;//Max Number of Rows
		int X = 25;//Max Number of Columns

		/*System.out.println("Which row would you like to be the destination of robotSprite1");
		destinationY= scan.nextInt();
		System.out.println("Which column would you like to be the destination of robotSprite1");
		destinationX= scan.nextInt();*/

		System.out.println("Which row would you like animated to show as robotSprite1?");
		int animRow = scan.nextInt();

		myGrid = new Grid(Y, X, robotSprite1, 0, boxWidth, boxHeight);

		robotSprite1 = new PathWalkerRobot(859, 396, 50, 50, 16, 8, 0, 0, 0, 0, "MegamanUniformSpriteSheet.png",1, 1.0, 1.0, 0, 0, 0.0, 0.0, 0.0, 0, 0, myGrid.getBox(0, 0));//Sets up Robot

		backgroundWall = new GriddedSprite(1908, 3002, 1908, 3002, 1, 1, 0, 0, 0, 0, "wallSprite.jpg", 0, 1.0, 1.0, 0, 0, 0.0, 0.0, 0.0, myGrid.getBox(0, 0));//Sets up the background for a wall
		backgroundPath = new GriddedSprite(900, 900, 900, 900, 1, 1, 0, 0, 0, 0, "pathSprite.jpg", 0, 1.0, 1.0, 0, 0, 0.0, 0.0, 0.0, myGrid.getBox(0, 0));//Sets up the background for a path

		myGrid.setBackground(backgroundWall);

		robotSprite1.setAnimatedRow(animRow);
		robotSprite1.resetImage();
		backgroundWall.setAnimatedRow(0);

		backgroundWall.resetImage();
		backgroundPath.resetImage();

		myGrid.setBackground(backgroundWall);
		myGrid.setCurrentRow(0);

		//myGrid.setSpriteFit(0, 0);

		currentDisplay = robotSprite1.getCurrentImage();

		currentWall = backgroundWall.getCurrentImage();

		addKeyListener(this);

		setVisible(true);

		setFocusable(true);

		//Below is me setting up paths for my robot to walk on the grid

		/*for(int e = 0; e < binaryMap.length; e++)
		{
			for(int r = 0; r < binaryMap[r].length; r++)
			{
				binaryMap[e][r] = 1;
			}
		}*/

		try
		{
			binaryMap = this.returnFileAsArray("C:\\Users\\jedds\\Documents\\Adv. Comp Sci Using Java Honors\\1.6 Maze\\TestFile.txt");
		}
		catch (FileNotFoundException ex)
		{
			System.out.println(ex);
		}

		for (int c = 0; c < binaryMap.length; c++)
		{
			for (int r = 0; r < binaryMap[c].length; r++)
			{
				if(binaryMap[r][c] == 1)
				{
					myGrid.setPath(c, r, true, backgroundPath);//Sets specifid grid as path
				}

				if(binaryMap[r][c] == 0)
				{
					//nothing happens because its already set to a wall
					//the grid is either not used or not a path/destination
				}

				if(binaryMap[r][c] == 2)
				{
					myGrid.setDestinationState(c, r, true);//Sets specified grid as destination
				}
			}
		}

		repaint();


	}

	public void paintComponent(Graphics g)//draws image
	{
		//g.drawImage(scaledCurrentWall, 0, 0, null);

		myGrid.draw(g);

		myGrid.setRobot(robotSprite1.getGridLocX(), robotSprite1.getGridLocY(), robotSprite1, g);

		repaint();
	}

	public void actionPerformed(ActionEvent e)//Should call animate at every timer interval which should then set the current image
	{
//		currentDisplay = this.animate(currentDisplay);
//		System.out.println("Working");
	}

/*	public BufferedImage animate(BufferedImage animatedImage)
	{
		if(robotSprite1.getAnimatedColumn() == robotSprite1.getColumns())//used to repeat animation when last frame in row is reached
		{
			robotSprite1.setImage(0, robotSprite1.getAnimatedRow());

			robotSprite1.setAnimatedColumn(0);
		}
		else
		{
			robotSprite1.setImage(robotSprite1.getAnimatedColumn() + 1, robotSprite1.getAnimatedRow());//Sets up animatedImage as the next image
		}

		robotSprite1.setAnimatedColumn(robotSprite1.getAnimatedColumn() + 1);//Sets up animatedColumn as next image

		animatedImage = robotSprite1.getCurrentImage();

		return animatedImage;
	}*/

	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			robotSprite1.eastMove(myGrid);
			repaint();
			System.out.println("MovedRight");
		}
		if(e.getKeyCode() == KeyEvent.VK_UP)
		{
			robotSprite1.northMove(myGrid);
			repaint();
			System.out.println("MovedUp");
		}
		if(e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			robotSprite1.westMove(myGrid);
			repaint();
			System.out.println("MovedLeft");
		}
		if(e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			robotSprite1.southMove(myGrid);
			repaint();
			System.out.println("MovedDown");
		}
	}

	public void keyReleased(KeyEvent e)
	{
	}

	public void keyTyped(KeyEvent e)
	{
	}

  public int[][] returnFileAsArray(String path)throws FileNotFoundException//has a maximum of 25*25 slots//also assumes file has 25 lines
  {
	  File file = new File(path);
	  Scanner sc = new Scanner(file);
	  int[][] numberArray = new int[25][25];

	  if(sc.hasNextLine())
	  {
		  String currentLine = sc.nextLine();
		  for(int n = 0; n < 25; n++)
		  {
			  for(int m = 0; m < currentLine.length(); m++)
			  {

				  char cha = currentLine.charAt(m);
				  if(cha == ' ')
				  {
					  numberArray[n][m] = 1;
					  n++;
				  }
				  else
				  {
					numberArray[n][m] = Integer.parseInt(String.valueOf(cha));
				  }

				  //System.out.println(currentLine);
				  //System.out.println(numberArray[n][m]);
			  }
			  System.out.println(n);
			  if(n == 24)
			  {

			  }
			  else
			  {
				  currentLine = sc.nextLine();
			  }
	  	}
 	}
	return numberArray;
  }
}