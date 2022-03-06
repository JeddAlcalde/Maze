import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.awt.*;
import javax.swing.*;
import java.util.Timer;

public class MazeDriver
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setContentPane(new JPanelClass());
		frame.setVisible(true);
		frame.setSize(2000,2000);
		frame.setTitle("Maze");
	}
}