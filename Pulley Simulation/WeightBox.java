/* NAME: Eswar Dhinakaran
 * ID: A99071249
 * LOGIN: cs11eak
 * ASSIGNMENT: Program #2
*/


import objectdraw.*;
import java.awt.*;

public class WeightBox  
{
	// class instance variables. This is an incomplete list
	private FilledRect box;
	private Line rope;
	private Location anchor1;
	private double ropelength1;
	private double pulleylength;
	
	
	private DrawingCanvas canvas1;
	

	public WeightBox(Location anchorPoint, double ropeLength,
			double boxSize, DrawingCanvas canvas, double p)
	{	
		pulleylength = p;
		anchor1 = anchorPoint;
		ropelength1 = ropeLength - p;
		canvas1 = canvas;

		// Record key parameters as state variable
	    	box = new FilledRect(anchorPoint.getX() - (boxSize/2), anchorPoint.getY()+ropeLength, boxSize, boxSize, canvas); 
		rope = new Line(anchorPoint.getX(), anchorPoint.getY(), anchorPoint.getX(), anchorPoint.getY()+ropeLength, canvas);
		
		// create the rope and the box as Line and FilledRect objects
	}

	/* Method: hoist
	 * 		raise (lower) the box and shorten (lengthen) the rope
	 */ 

	public void hoist(double deltaY)
	{ 
		if ((ropelength1 >= -(deltaY)))
		{
		 rope.removeFromCanvas();
	   	 box.move(0,deltaY);
	   	 ropelength1 = ropelength1+deltaY;
	   	 rope = new Line(anchor1.getX(), anchor1.getY(), anchor1.getX(), anchor1.getY()+ropelength1 + pulleylength, canvas1);
	  	} 
	}

	/* Method: getRopeLength() 
	 * 	return the current length of the rope	
	 */ 
	public double getRopeLength()
	{	
		return ropelength1;

	} 

	public void setColor(Color newColor)
    {
	// set the color of the box
	box.setColor(newColor);

	}

}
