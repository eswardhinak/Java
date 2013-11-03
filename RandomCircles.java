import java.util.Random;
import objectdraw.*;
import java.awt.*;
public class RandomCircles extends WindowController
{
	private FilledOval oval;
	public static RandomCircles eswar;
	private Color c;
	private Location point;

	public static void main(String args[])
	{
		int number = Integer.parseInt(args[0]);
		int size = Integer.parseInt(args[1]);
		int sizey = size+51;
		int diameter = size/10;
		int counter = 0;
		RandomCircles eswar = new RandomCircles();
		eswar.startController(size, sizey);
		while (counter < number)
		{
			eswar.createCircle(size,sizey, diameter);
			counter += 1;
		}
	}
	
	public void createCircle (int s,int sy, int d)
	{	
		Location point = new Location(Random(s-d), Random(sy-d));
		FilledOval oval = new FilledOval(point, d, d, canvas);
		Color c = new Color(Random(256), Random(256), Random(256));
		oval.setColor(c);
	}
	
	public int Random(int r)
	{
		Random rand = new Random();
		return rand.nextInt(r);
	}

}


			
