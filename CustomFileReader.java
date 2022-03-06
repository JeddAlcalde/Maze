import java.io.File;
import java.util.Scanner;
public class CustomFileReader
{
  public static void main(String[] args) throws Exception
  {
    // pass the path to the file as a parameter
    /*
    File file =
      new File("C:\\Users\\jedds\\Documents\\Adv. Comp Sci Using Java Honors\\1.6 Maze\\TestFile.txt");
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine())
      System.out.println(sc.nextLine());
    */


	  File file = new File("C:\\Users\\jedds\\Documents\\Adv. Comp Sci Using Java Honors\\1.6 Maze\\TestFile.txt");
	  Scanner sc = new Scanner(file);
	  int r = 0;
	  int c = 0;

	  int[][] numberArray = new int[25][25];

	  for(int c1 = 0; c1 < numberArray[0].length; c1++)
	  {
		  for(int r1 = 0; r1 < numberArray[0].length; r1++)
		  {
			  numberArray[c1][r1] = 0;
		  }
	  }

	  while (sc.hasNextLine())
	  {
		  if(sc.nextLine() == "\n")
		  {
			  c++;
		  }

		  numberArray[c][r] = Integer.parseInt(sc.nextLine());
		  r++;
		  System.out.println(sc.nextLine());
		  System.out.println(numberArray);
	 }
  }

  public int[][] returnFile()throws Exception//has a maximum of 25*25 slots
  {
	  File file = new File("C:\\Users\\jedds\\Documents\\Adv. Comp Sci Using Java Honors\\1.6 Maze\\TestFile.txt");
	  Scanner sc = new Scanner(file);
	  int r = 0;
	  int c = 0;

	  int[][] numberArray = new int[25][25];

	  for(int c1 = 0; c1 < numberArray[0].length; c1++)
	  {
		  for(int r1 = 0; r1 < numberArray[0].length; r1++)
		  {
			  numberArray[c1][r1] = 0;
		  }
	  }

	  while (sc.hasNextLine())
	  {
		  if(sc.nextLine() == "\n")
		  {
			  c++;
		  }

		  numberArray[c][r] = Integer.parseInt(sc.nextLine());
		  r++;
		  System.out.println(sc.nextLine());
		  System.out.println(numberArray);
	 }
		  return numberArray;
  }
}