import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class PathWalkerRobot extends GriddedSprite
{

	int currentLocX;
	int currentLocY;

	boolean pathCheck;

	GriddedSprite displayRobot;

	public PathWalkerRobot(int imageHeight, int imageWidth, int spHeight, int spWidth, int Rows, int Columns, int X0, int Y0, int gapX, int gapY, String url, int currentRow, double XScale, double YScale, int XTranslate, int YTranslate, double rotation, double XAnchor, double YAnchor, int newLocX, int newLocY, GriddedBox myBox)
	{
		super(imageHeight, imageWidth, spHeight, spWidth, Rows, Columns, X0, Y0, gapX, gapY, url, currentRow, XScale, YScale, XTranslate, YTranslate, rotation, XAnchor, YAnchor, myBox);

		currentLocX = newLocX;
		currentLocY = newLocY;

		pathCheck = false;
	}

	public boolean adjSquareCheck(Grid currentGrid, String moveType)//returns a boolean and will check adjacent squares if they are a path or not
	{
		boolean pathState = false;

		if(moveType == "east")
		{
			if(currentGrid.getBox(currentLocX + 1, currentLocY).getPathState() == true)
			{
				pathState = true;
			}
			else
			{
				pathState = false;
			}
		}
		else if(moveType == "west")
		{
			if(currentGrid.getBox(currentLocX - 1, currentLocY).getPathState() == true)
			{
				pathState = true;
			}
			else
			{
				pathState = false;
			}
		}
		else if(moveType == "north")
		{
			if(currentGrid.getBox(currentLocX, currentLocY - 1).getPathState() == true)
			{
				pathState = true;
			}
			else
			{
				pathState = false;
			}
		}
		else if(moveType == "south")
		{
			if(currentGrid.getBox(currentLocX, currentLocY + 1).getPathState() == true)
			{
				pathState = true;
			}
			else
			{
				pathState = false;
			}
		}
		return pathState;
	}

	public int getLocX()
	{
		return currentLocX;
	}

	public int getLocY()
	{
		return currentLocY;
	}

	public void eastMove(Grid Grid1)//will eventually implement adjSquare check to before initiating a move
	{
		if(this.adjSquareCheck(Grid1, "east") == true)
		{
			if(currentLocX == Grid1.getXRow())
			{
				currentLocX = currentLocX;
			}
			else
			{
				Grid1.getBox(currentLocX,currentLocY).setSprite(false, this);

				Grid1.getBox(currentLocX+1,currentLocY).setSprite(true, this);

				currentLocX = currentLocX + 1;
			}
		}
		System.out.println(currentLocX);
		System.out.println(currentLocY);
	}
	public void westMove(Grid Grid1)//will eventually implement adjSquare check to before initiating a move
	{
		if(this.adjSquareCheck(Grid1, "west") == true)
		{
			if(currentLocX == 0)
			{
				currentLocX = currentLocX;
			}
			else
			{
				Grid1.getBox(currentLocX,currentLocY).setSprite(false,this);

				Grid1.getBox(currentLocX-1,currentLocY).setSprite(true, this);

				currentLocX = currentLocX - 1;
			}
		}
		System.out.println(currentLocX);
		System.out.println(currentLocY);
	}
	public void northMove(Grid Grid1)//will eventually implement adjSquare check to before initiating a move
	{
		if(this.adjSquareCheck(Grid1, "north") == true)
		{
			if(currentLocY == 0)
			{
				currentLocY = currentLocY;
			}
			else
			{
				Grid1.getBox(currentLocX,currentLocY).setSprite(false, this);

				Grid1.getBox(currentLocX,currentLocY-1).setSprite(true, this);

				currentLocY = currentLocY +-1;
			}
		}
		System.out.println(currentLocX);
		System.out.println(currentLocY);
	}
	public void southMove(Grid Grid1)//will eventually implement adjSquare check to before initiating a move
	{
		if(this.adjSquareCheck(Grid1, "south") == true)
		{
			if(currentLocY == Grid1.getYCol())
			{
				currentLocY = currentLocY;
			}
			else
			{
				Grid1.getBox(currentLocX,currentLocY).setSprite(false, this);

				Grid1.getBox(currentLocX,currentLocY+1).setSprite(true, this);

				currentLocY = currentLocY + 1;
			}
		}
		System.out.println(currentLocX);
		System.out.println(currentLocY);
	}

	public int getGridLocX()
	{
		return currentLocX;
	}
	public int getGridLocY()
	{
		return currentLocY;
	}
}