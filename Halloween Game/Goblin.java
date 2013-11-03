/*Goblin.java
 *
 * Name: Eswar Dhinakaran
 *Login: cs11eak
 *ID: A99071249
 *
 * */
// Create a Goblin that bounces off the top row of the canvas 
// If the Goblin intersects this tombstone, it calls the enter() method of
// its  Tombstone.   When the goblin exits its run loop, it calls the record()
// method of the CemeteryController to tell it whether or not it has been
// vaporized. 

// Goblins at an initial velocities in the X and Y direction.
// Velocities are approximately pixels per ms
//
import objectdraw.*;
import java.awt.*;
import java.util.Random;
import java.lang.Math;

public class Goblin extends ActiveObject
{
    
	private VisibleImage wraith;
	private Image wraithGraphic;
	private double vx, vy; 	
	private DrawingCanvas canvas;
	private boolean vaporized = false;
	private boolean onCanvas = true;
	private boolean callback = false; //determines if goblin has passed through tombstone already.
	private Tombstone tombstone;
	private CemeteryController myController;
	private static final String A = "skeleton.gif";
	private static final String B = "Ghost.jpg";
	private static final String C = "pumpkin.jpg";
	private static final int SCALE = CemeteryController.SCALE;
	private Location startLoc;
	
	public Goblin(int selection, Location initial,double velX, double velY, Tombstone aStone, CemeteryController control,DrawingCanvas aCanvas) 
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		canvas = aCanvas;
		myController = control;
		startLoc = initial;
		if (selection == 0)
			wraithGraphic = toolkit.getImage(A);
		if (selection == 1)
			wraithGraphic = toolkit.getImage(B);	
		if (selection == 2)
			wraithGraphic = toolkit.getImage(C);
		
		wraith = new VisibleImage(wraithGraphic, initial, canvas);
		scaleAndPlace();
		onCanvas = true;
		vx = velX;
		vy = velY;
		tombstone = aStone;
		start();
		

    	}
   
	private void scaleAndPlace()
	{
		double imageWidth, imageHeight;
		double factor = SCALE;
		imageWidth = (1/factor)*(canvas.getWidth());
		imageHeight = (1/factor)*(canvas.getHeight());
		wraith.setSize(imageWidth, imageHeight);
		wraith.moveTo(startLoc.getX(), startLoc.getY()-imageHeight);
	}	


	private void moveIt(double elapsed)
	{ 
		double imageWidth;
		double x, y, newX, newY;
		imageWidth = wraith.getWidth();
		x = wraith.getLocation().getX();
		y = wraith.getLocation().getY();
			
		newX = x+(vx*elapsed);//x-value of goblin is being changed. velocity*time = distance
		if (y<0)
		{
			y = y - (vy*elapsed);
			wraith.moveTo(newX - (vx*elapsed), y);
			setVelocity(vx, -vy);//rebound if goblin hits the top of the panel
		}
		newY = y+(vy*elapsed);
	
		wraith.moveTo(newX, newY);

		//if goblin touches left, right or bottom of canvas, it is removed from the canvs.
		if (x<0 || x>canvas.getWidth()|| y>canvas.getHeight()){
			onCanvas = false;
			wraith.removeFromCanvas();
			myController.record(this,vaporized );
		}
		
		
	}

	//method used to reset velocity of goblin
	public void setVelocity (double velX, double velY) 
	{
		vx = velX;
		vy = velY;
	
	}

	//method where goblin vaporizes itself
	public void vaporize()
	{
		wraith.removeFromCanvas();
		vaporized = true;
		myController.record(this, vaporized);
		
			
	}	

	public void run() 
	{
		double prevClock, now, elapsed;
		double tmpvx;
		double delay;
		double thres = 1e-2;
		tmpvx = Math.sqrt(vx*vx+vy*vy);

		if (tmpvx < thres){
			tmpvx = thres;
		}

		delay = 1/tmpvx;
		prevClock = System.currentTimeMillis();

		while (onCanvas==true && vaporized == false){
			now = System.currentTimeMillis();
			elapsed = now - prevClock;
			prevClock = now;
			moveIt(elapsed);
			pause(delay);
			/*Checks if tombstone exists and if it overlaps the tombstone and
  			 if the goblin survived being vaporized by the tombstone already*/
			if ((tombstone != null) && (tombstone.overlaps(wraith)==true) && (callback == false)){
				tombstone.enter(this);
				callback = true;
			}
		}

		
	}
	

}

