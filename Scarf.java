import java.awt.*;
import objectdraw.*;

public class Scarf extends WindowController
{
	private static final int SCARF_WIDTH  = 100;
	private static final int SCARF_HEIGHT = 324;
	private static final int DISP = 8;
	private static final int DIAM = 12;
	private static final int WIN_SIZEx = 200;
	private static final int WIN_SIZEy = 395;
	private FramedOval circle;

	public void begin()
	{
		int row = 0;
		while (row < SCARF_HEIGHT)
		{
			int column = 0;
			while (column < SCARF_WIDTH)
			{
				circle = new FramedOval(column + 50, row + 10, DIAM, DIAM, canvas);
				column += DISP;
			}
			row += DISP;
		}
	}
	
	public static void main (String args[])
	{
		Scarf eswar;
		eswar = new Scarf();
		eswar.startController(WIN_SIZEx, WIN_SIZEy);
	}
}			
