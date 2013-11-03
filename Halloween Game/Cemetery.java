/*Cemetery.java
 *
 *NAME: Eswar Dhinakaran
 *ID: cs11eak
 *Login: A99071249
 *
 *This program runs the game and creates new goblin and tombstone instances.
*/

import objectdraw.*;
import java.awt.*;
import java.util.Random;
public class Cemetery extends WindowController implements CemeteryController
{
	private Goblin phantom; 
	private Tombstone eswar;
	private int count = 0;//total number of goblins created
	private FilledOval theExit;
	private FilledRect vControl;
	private Random myRnd;
	private int vapor = 0;//number of goblins vaporized
	private int pass = 0;//number of goblins passed to afterlife
	private Location init;
	private Location tombinit; //initial location of the tombstone
	private Line vector;
	private Text scoreboard;
	private static final int SIZE = CemeteryController.SIZE;
	private static final int DIM = 20;
	private static final int INSTX = 10;
	private static final double MAXV = CemeteryController.MAXV;
	private static final double GMAXV = Math.sqrt(2*CemeteryController.MAXV*CemeteryController.MAXV);


	public void begin() 
	{       
		new Text("Drag to create goblins", INSTX, INSTX, canvas);
		new Text("Click Square to change", INSTX, 2*INSTX, canvas);
		new Text("Click Circle to  exit...", INSTX, 3*INSTX, canvas);
		scoreboard = new Text("#goblins: "+count+ "  #vapor: "+vapor + "  #passed: " + pass, INSTX, 4*INSTX, canvas);

		vControl = new FilledRect(SIZE - DIM, DIM, DIM, DIM, canvas);
		theExit = new FilledOval(SIZE - DIM, 2*DIM, DIM, DIM, canvas);
		myRnd = new Random();
		tombinit = new Location(canvas.getWidth()/2, canvas.getHeight()/4);
		
		eswar = new Tombstone(tombinit,MAXV, this, canvas);
			
		
		
	}
	public void onMouseClick(Location point)
	{
		if (theExit.contains(point)) 
			System.exit(0);
		if (vControl.contains(point) && phantom != null)
		{
			double vx,vy;
			vx = (MAXV/2.0) - (myRnd.nextDouble() * MAXV);
			vy = (MAXV/2.0) - (myRnd.nextDouble() * MAXV);
			phantom.setVelocity(vx,vy); 
		}
	}		
	
	public void onMousePress(Location point)
	{
		
		init = new Location(point.getX(), canvas.getHeight());
		vector = new Line(init, point, canvas);
		vector.setColor(Color.green);
		
	}
	public void onMouseDrag(Location point) 
	{
		vector.show();
		vector.setEnd(point);
		
	}
	public void onMouseRelease(Location point)
	{
		 //checks that release point is not inside the change velocity bo
		if (!(vControl.contains(point)))
		{
			vector.hide();
			double startx = init.getX();
			double curwidth = canvas.getWidth();
	
	
			double dx = point.getX()-init.getX();
			double dy = point.getY()-init.getY();

			double vx = MAXV * (dx/curwidth);
			double vy = MAXV * (dy/canvas.getHeight());	
			count++;
	     		phantom = new Goblin(myRnd.nextInt(3), new Location(startx,canvas.getHeight()),
				 vx, vy,eswar, this, canvas);
		}
		else
		{
			vector.hide();
		}
	}

	// Implement for CemeteryController Interface Specification
	public void record(Goblin wraith, boolean vaporized)
	{
		if (vaporized == true)
		{
			vapor++;
		}
		else 
		{	
			pass++;
		}
		scoreboard.removeFromCanvas();
		scoreboard = new Text("#goblins: "+count+ "  #vapor: "+vapor + "  #passed: " 
				+ pass, INSTX, 4*INSTX, canvas);//update scoreboard

		
	}

	 static public void main(String[] args)
	{
		new Cemetery().startController(SIZE, SIZE);
	}
}

