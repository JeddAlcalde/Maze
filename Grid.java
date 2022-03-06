import java.awt.Rectangle;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;

public class Grid
{
	Graphics g;

	GriddedBox[][] Grid1;
	int XRow;
	int YCol;

	GriddedSprite mySprite;

	int rowAnimated;

	int boxX;
	int boxY;

	public Grid(int y,int x, GriddedSprite newSprite, int currentRow, int boxWidth, int boxHeight)
	{
		Grid1 = new GriddedBox[y][x];

		YCol = Grid1.length;
		XRow = Grid1[0].length;

		mySprite = newSprite;

		rowAnimated = currentRow;

		boxX = boxWidth;
		boxY = boxHeight;
	}

	public void setDimensions(int y, int x)
	{
		YCol = y;
		XRow = x;
	}

	public void setSpriteFit(int SpriteX, int SpriteY)
	{

		for(int h = 0; h < YCol; h++)//y axis
		{
			for(int t = 0; t < XRow; t++)//x axis
			{
				Grid1[t][h].fitBackground();//fits all boxes with a background
			}
		}

		Grid1[SpriteX][SpriteY].fitSprite();//fits a specified box with a sprite
	}

	public void draw(Graphics g)
	{
		for(int h = 0; h < YCol; h++)//y axis
		{
			for(int t = 0; t < XRow; t++)//x axis
			{
				Grid1[h][t].draw(g);
			}
		}
	}

	public void setCurrentRow(int newAnimatedRow)
	{
		rowAnimated = newAnimatedRow;
	}

	public void setBoxDimensions(int newBoxWidth, int newBoxHeight)
	{
		for(int h = 0; h < YCol; h++)//y axis
		{
			for(int t = 0; t < XRow; t++)//x axis
			{
				Grid1[h][t].setDimension(newBoxWidth, newBoxHeight);
			}
		}
	}

/*	public GriddedSprite getCurrentSprite(int spriteXLoc, int spriteYLoc)
	{
		Grid1[spriteXLoc][spriteYLoc];
	}*/

	public int getBoxX()
	{
		return boxY;
	}

	public int getBoxY()
	{
		return boxX;
	}

	public int getXRow()
	{
		return XRow;
	}


	public int getYCol()
	{
		return YCol;
	}


	public GriddedBox getBox(int XLoc, int YLoc)
	{
		return Grid1[XLoc][YLoc];
	}

	//will need a method to set path for the robot

	public void setPath(int X, int Y, boolean newState, GriddedSprite otherBackground)//sets background for a specified box, for this project it will also set pathState to true
	{
		Grid1[X][Y].setPathState(newState, otherBackground);
	}

	public void setBackground(GriddedSprite background)//sets background for all boxes, for this project it sets all of them to a wall
	{
		mySprite = background;

		for(int h = 0; h < YCol; h++)//y axis
		{
			for(int t = 0; t < XRow; t++)//x axis
			{
				Grid1[h][t] = new GriddedBox(h, t, boxX, boxY, rowAnimated, mySprite, false);
				//By default all boxes have a pathState of false, and must be set as paths using the setPath() method
			}
		}
	}

	public void setRobot(int X, int Y, PathWalkerRobot newRobot, Graphics g)
	{
		Grid1[X][Y].setSprite(true, newRobot);
	}

	public void setDestinationState(int X, int Y, boolean newDesState)
	{
		Grid1[X][Y].setDestinationState(newDesState);
	}

}