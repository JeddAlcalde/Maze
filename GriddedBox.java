import java.awt.Rectangle;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GriddedBox extends Rectangle
{
	int XCoor;
	int YCoor;

	int width;
	int height;

	GriddedSprite currentSprite;

	BufferedImage display;

	boolean spriteSet;

	boolean pathState;

	boolean destinationState;

	GriddedSprite background1;

	BufferedImage backgroundDisplay1;

	Image scaledBackground1;

	ArrayList <PathWalkerRobot> RobotList;

	PathWalkerRobot currentRobot;

	public GriddedBox(int X, int Y, int Width, int Height, int animateRow, GriddedSprite backGround1, boolean pathOrNot)
	{
		XCoor = X;
		YCoor = Y;

		this.width = Width;
		this.height = Height;

		this.background1 = backGround1;

		background1.setAnimatedColumn(0);
		background1.setAnimatedRow(0);
		background1.resetImage();
		background1.setImage(0,0);

		backgroundDisplay1 = background1.getCurrentImage();

		scaledBackground1 = backgroundDisplay1.getScaledInstance(width, height, Image.SCALE_FAST);//Can also be Image.SCALE_DEFAULT or IMAGE.SCALE_SMOOTH

		//spriteSet = true;//usually false but for robot, all boxes have a sprite

		pathState = false;

		destinationState = false;

		RobotList = new ArrayList<PathWalkerRobot>();

		spriteSet = false;
	}

	public void draw(Graphics g)
	{
		Toolkit tk;
		tk = Toolkit.getDefaultToolkit();

		g.drawRect(this.getXCoor() * width, this.getYCoor() * height, width, height);//draws box

		g.drawImage(scaledBackground1, this.getXCoor() * width, this.getYCoor() * height, null);

		if(spriteSet == true)
		{
			//currentSprite.drawGridded(g);//draws sprite
			/*for(int i=0; i<RobotList.size(); i++)
			{
				//RobotList.get(i).drawGridded(g);Has been taken out due to drawing
				display = RobotList.get(i).getCurrentImage();
				Image scaledDisplay = display.getScaledInstance(width, height, Image.SCALE_DEFAULT);
				g.drawImage(scaledDisplay, this.getXCoor() * width, this.getYCoor() * height, null);
			}*/
			display = currentRobot.getCurrentImage();
			Image scaledDisplay = display.getScaledInstance(width, height, Image.SCALE_FAST);
			g.drawImage(scaledDisplay, this.getXCoor() * width, this.getYCoor() * height, null);
			//System.out.println(XCoor);
			//System.out.println(YCoor);
		}
	}

	public int getXCoor()
	{
		return XCoor;
	}

	public int getYCoor()
	{
		return YCoor;
	}

	public int getWide()
	{
		return width;
	}

	public int getTall()
	{
		return height;
	}

	public boolean spriteSet()
	{
		return spriteSet;
	}

	public void setSprite(boolean newSpriteState, PathWalkerRobot newRobot)
	{
		spriteSet = newSpriteState;
		if(newSpriteState == true)
		{
			RobotList.add(newRobot);
			currentRobot = newRobot;
		}
		else
		{
			RobotList.remove(newRobot);
		}
	}

	public GriddedSprite getSprite()
	{
		return currentSprite;
	}

	public void fitSprite()//Stretches sprite to fit box dimensions
	{
		double XScale;
		double YScale;

		double spriteX = currentSprite.getWide();
		double spriteY = currentSprite.getTall();

		double boxX = this.getWide();
		double boxY = this.getTall();

		currentSprite.setTranslate(this.getXCoor()*width, this.getYCoor()*height);

		XScale = boxX / spriteX;
		YScale = boxY / spriteY;

		currentSprite.setScale(XScale, YScale);

		spriteSet = true;
	}

	public void fitBackground()//works the same as set sprite but in organization is used to differentiate backgrounds and sprites
	{
/*		double X1Scale;
		double Y1Scale;

		double X2Scale;
		double Y2Scale;


		double spriteX1 = background1.getWide();
		double spriteY1 = background1.getTall();

		double spriteX2 = background2.getWide();
		double spriteY2 = background2.getTall();

		double boxX = this.getWide();
		double boxY = this.getTall();

		background1.setTranslate(this.getXCoor()*width, this.getYCoor()*height);
		background2.setTranslate(this.getXCoor()*width, this.getYCoor()*height);

		X1Scale = boxX / spriteX1;
		Y1Scale = boxY / spriteY1;

		X2Scale = boxX / spriteX2;
		Y2Scale = boxY / spriteY2;

		background1.setScale(X1Scale, Y1Scale);
		background2.setScale(X2Scale, Y2Scale);
		if(XCoor == 1)
		{
			if(YCoor == 1)
			{
				System.out.println(background2.getScaleX());
				System.out.println(background2.getScaleY());
				System.out.println(background2.getTranslateX());
				System.out.println(background2.getTranslateY());
				System.out.println(background2.getAT());
			}
		}
		*///Remnant of using affine transform for background, now using Image.getScaledInstance

		scaledBackground1 = backgroundDisplay1.getScaledInstance(width, height, Image.SCALE_DEFAULT);

	}

	public void setDimension(int newWidth, int newHeight)
	{
		width = newWidth;
		height = newHeight;
	}

	public boolean getPathState()
	{
		return pathState;
	}

	public boolean getDestinationState()
	{
		return destinationState;
	}

	public void setPathState(boolean newPathState, GriddedSprite path)
	{
		background1 = path;

		backgroundDisplay1 = background1.getCurrentImage();

		scaledBackground1 = backgroundDisplay1.getScaledInstance(width, height, Image.SCALE_DEFAULT);

		pathState = newPathState;
	}

	public void setDestinationState(boolean newDestinationState)
	{
		destinationState = newDestinationState;
	}

}