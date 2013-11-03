/********************************************************
* Tombstone.java 
*
* Author: Eswar Dhinakaran/Philip Papadopoulos
* Login: cs11eak
* ID: A99071249
* Creation Date: 23 October 2013
* Last Modified: 23 October 2013
* Description:  Creates a pixel image of a tombstone.
* 		the tombstone moves across the cemetery at
* 		a fixed Y coordinate.
*
* 		It is an ActiveObject 
*
* Build:   javac -classpath '*':'.' Tombstone.java
* Dependencies:  objectdraw.jar, java.awt.*
* 
* Public Methods Defined:
*           Tombstone(Location, double, double, 
*           	Drawable2DInterface, SpaceController, DrawingCanvas)
*           void setVelocity(double velX)
*           void enter(Goblin wraith)
*           void run()
*           boolean overlaps(Drawable2DInterface region)
* 
* Public Class Variables:
*       None
*
***********************************************************************/
import objectdraw.*;
import java.awt.*;
import java.util.Random;
public class Tombstone extends ActiveObject
{
	private VisibleImage stone;
	private Image stoneGraphic;
	private double vx; 	// Current speed of stone in pixels/ms
	private DrawingCanvas canvas;
	private CemeteryController myController;
	private Random myRnd;
	private Location tombinitial;
	private static final String GRAPHIC = "tombstone.jpg";
	private Location RightEnd, LeftEnd;
	private static final int SCALE = CemeteryController.SCALE;

	
	/***** Constructor 
	 *         Location initial 	Where to create the stone on the canvas
	 *         double velX  	initial velocity x (pixels/ms)	
	 *         CemeteryController control   my controller 
	 *         DrawingCanvas canvas The objectdraw canvas
	 *****/ 
	public Tombstone(Location initial, double velX, 
			 CemeteryController control, DrawingCanvas aCanvas) 
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		tombinitial = initial;
        	canvas = aCanvas;
		// Load the image
		stoneGraphic = toolkit.getImage(GRAPHIC);
		stone = new VisibleImage(stoneGraphic,initial,canvas);
		scaleAndPlace();
		vx = velX;
		myController  = control;
		myRnd = new Random();
		start();

    	}
   
	/***** Method: scaleAndPlace()
	* Scale the image so that its width is 1/SCALE of the canvas width
	* Scale the height by the same factor 
	*****/ 
	private void scaleAndPlace()
	{
		double imageWidth, imageHeight;
		double factor = SCALE;
		imageWidth = (1/factor)*(canvas.getWidth());
				

		imageHeight = (1/factor)*(canvas.getHeight()+51);
		stone.setSize(imageWidth, imageHeight);
	}	


	/***** Method: void moveIt(double)
	*  Move the tombstone, based on elapsed time and speed.
	*  roll around edges of canvas
	*	double elapsed -- elapsed time in ms since last update
	*****/ 
	private void moveIt(double elapsed)
	{ 
		double imageWidth;
		double x,y, newX, dx;


		imageWidth = stone.getWidth();
		x = stone.getLocation().getX();
		y = stone.getLocation().getY();
		RightEnd = new Location(canvas.getWidth(), tombinitial.getY());
		LeftEnd = new Location(-imageWidth, tombinitial.getY());
	
		if (vx>0 && x>canvas.getWidth())
		{
			   stone.moveTo(LeftEnd);
			   x = LeftEnd.getX();
		}
		
		if (vx<0 && x<-imageWidth)
		{
			 stone.moveTo(RightEnd);
		         x = RightEnd.getX();	
		}
		newX = x+(vx*elapsed);
		stone.moveTo(newX, y);


	}

	public void setVelocity (double velX) 
	{
		vx = velX; 
	}

	public void enter(Goblin wraith) //randomly decides whether or not to vaporize goblin
	{	
		double prob = CemeteryController.PROBABILITY;
		int f = myRnd.nextInt(1000)+1;
		double divider = prob*1000;//if a number less than or equal to divider is chosen, then the goblin will be vaporized
		if (f<divider)
			wraith.vaporize();
		}	

	public void run() 
	{
        	double prevClock, now, elapsed;
		double tmpvx; 
		double delay; 
		double thresh=1e-2;

		// calculate the pause delay time based upon speed
		// want to pause no more than ~10 milliseconds, update more 
		// quickly if velocity is faster
		
		tmpvx = Math.abs(vx);
		if (tmpvx < thresh) 
			tmpvx = thresh;
		delay = 1/tmpvx;

        	prevClock = System.currentTimeMillis();
		
		// Note: infinite loop. Will exit when main program 
		// (controller) exits.
		while (true)
		{
			// calc elapsed time, update, and moveto new position
			// check if we entered the intersect regioon
			now = System.currentTimeMillis();
			elapsed = now - prevClock;
			prevClock = now;
			moveIt(elapsed);
			pause(delay);
			
		}
	}

	// Method: determine if a Drawable2Dinterface intersects the stone.
	public boolean overlaps(Drawable2DInterface region)
	{
		return stone.overlaps(region); 
	}
}

