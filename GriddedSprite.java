import java.awt.geom.AffineTransform;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.io.*;

public class GriddedSprite extends Sprite//Currently only works with uniform sprite sheets
{
	double scaleX;
	double scaleY;

	int translateX;
	int translateY;

	double rotationDegrees;

	double anchorX;
	double anchorY;

	AffineTransform at;

	GriddedBox currentBox;

	public GriddedSprite(int imageHeight, int imageWidth, int spHeight, int spWidth, int Rows, int Columns, int X0, int Y0, int gapX, int gapY, String url, int currentRow, double XScale, double YScale, int XTranslate, int YTranslate, double rotation, double XAnchor, double YAnchor, GriddedBox myBox)
	{
		super(imageHeight, imageWidth, spHeight, spWidth, Rows, Columns, X0, Y0, gapX, gapY, url, currentRow);

		scaleX = XScale;
		scaleY = YScale;

		translateX = XTranslate;
		translateY = YTranslate;

		rotationDegrees = rotation;

		anchorX = XAnchor;
		anchorY = YAnchor;

		at = new AffineTransform();

		//at.rotate(anchorX, anchorY);//sets anchor point of rotation

		at.rotate(rotationDegrees);//rotates an image based on degrees

		at.translate(translateX, translateY);//moves image by amount also affected by scale

		at.scale(scaleX, scaleY);//changes scale of image

		currentBox = myBox;
	}

	public void setScale(double newScaleX, double newScaleY)
	{
		scaleX = newScaleX;
		scaleY = newScaleY;

		at.scale(scaleX, scaleY);
	}

	public void setTranslate(int newTransX, int newTransY)
	{
		translateX = newTransX;
		translateY = newTransY;

		at.translate(translateX, translateY);
	}

	public void setRotate(int newRotate)
	{
		rotationDegrees = newRotate;

		at.rotate(rotationDegrees);
	}

	public void drawGridded(Graphics g)
	{
		Graphics2D g2D = (Graphics2D)g;

		g2D.drawImage(super.getCurrentImage(), at, null);

		//g2D.drawImage(super.getSpriteSheet(), at, null);//just testing if it works
	}

	public double getTranslateX()
	{
		return translateX;
	}

	public double getTranslateY()
	{
		return translateY;
	}

	public double getScaleX()
	{
		return scaleX;
	}

	public double getScaleY()
	{
		return scaleY;
	}

	public AffineTransform getAT()
	{
		return at;
	}

	public void fitSprite()//Stretches sprite to fit box dimensions
	{
		double XScale;
		double YScale;

		double spriteX = getWide();
		double spriteY = getTall();

		double boxX = currentBox.getWide();
		double boxY = currentBox.getTall();

		setTranslate(currentBox.getXCoor()*currentBox.getWide(), currentBox.getYCoor()*currentBox.getTall());

		XScale = boxX / spriteX;
		YScale = boxY / spriteY;

		setScale(XScale, YScale);
	}

}