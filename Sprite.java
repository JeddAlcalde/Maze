import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class Sprite extends AffineTransform//Currently only works with uniform sprite sheets
{
	int imgHeight;
	int imgWidth;
	int spriteHeight;
	int spriteWidth;
	int sheetRows;
	int sheetColumns;
	int XStart;
	int YStart;
	int XGap;
	int YGap;

	String imagePath;

	int animateRow;
	int animateColumn;

	BufferedImage SpriteSheet = null;
	BufferedImage subimageTest = null;
	BufferedImage[][] subimageArray;
	BufferedImage displayImage = null;

	public Sprite(int imageHeight, int imageWidth, int spHeight, int spWidth, int Rows, int Columns, int X0, int Y0, int gapX, int gapY, String url, int currentRow)
	{
		imgHeight = imageHeight;
		imgWidth = imageWidth;
		spriteHeight = spHeight;
		spriteWidth = spWidth;
		sheetRows = Rows;
		sheetColumns = Columns;
		XStart = X0;
		YStart = Y0;
		XGap = gapX;
		YGap = gapY;

		imagePath = url;

		animateRow = currentRow;

		subimageArray =  new BufferedImage[sheetRows][sheetColumns];

		for(int h = 0; h<sheetRows; h++)
		{
			for(int f = 0; f<sheetColumns; f++)
			{
				subimageArray[h][f] = null;
			}
		}

		try
		{
			SpriteSheet = ImageIO.read(new File(url));

			for(int h = 0; h<Rows; h++)
			{
				for(int f = 0; f<Columns; f++)
				{
					subimageArray[h][f] = SpriteSheet.getSubimage((spriteWidth + gapX) * h + XStart, (spriteHeight + gapY) * f + YStart, spriteWidth, spriteHeight);
				}
			}

		}
		catch(Exception e)
		{
		}
	}

	public void draw(Graphics g, BufferedImage drawnImage, int XCoor, int YCoor)
	{
		Graphics2D g2D = (Graphics2D)g;
		g.drawImage(drawnImage, XCoor, YCoor, null);
	}

	public void setAnimatedRow(int row)
	{
		animateRow = row;
	}

	public BufferedImage getCurrentImage()
	{
		return displayImage;
	}

	public int getColumns()
	{
		return sheetColumns;
	}

	public int getRows()
	{
		return sheetRows;
	}

	public int getAnimatedRow()
	{
		return animateRow;
	}

	public int getAnimatedColumn()
	{
		return animateColumn;
	}

	public void setAnimatedColumn(int col)
	{
		animateColumn = col;
	}

	public BufferedImage getImageFromArray(int W, int H)
	{
		BufferedImage newImage = null;
		newImage = subimageArray[W][H];
		return newImage;
	}

	public void resetImage()
	{
		displayImage = subimageArray[0][animateRow];
	}

	public void setImage(int T, int S)
	{
		displayImage = this.getImageFromArray(T,S);
	}

	public BufferedImage getSpriteSheet()
	{
		return SpriteSheet;
	}

	public int getWide()
	{
		return spriteWidth;
	}

	public int getTall()
	{
		return spriteHeight;
	}
}