/*
  ID: A99071249
  NAME: Eswar Dhinakaran
  LOGIN: cs11eak
  ASSIGNMENT: Program #2
  COMPILATION: javac -CLASSPATH '*':'.' BlockAndTackle.java
  EXECUTION: jaca -CLASSPATH '*':'.' BlockAndTackle
*/

import objectdraw.*;
import java.awt.*;

public class BlockAndTackle extends WindowController
{

	// *** Constants defined by this class ***
	public static final int CANVAS_HEIGHT = 800;
	public static final int CANVAS_WIDTH = 600;
	public static final int BOXWIDTH = 65;
	public static final int PULLEYDIAMETER = 100;
	public static final int STEP = 33;

	// *** Instance Variables ***
	private FilledOval pulley;  
	private WeightBox box1,box2;	 	
	private Text instructions, instructions2;  	// Display of instructions
	
	// *** Methods ***
	// Method: begin
	// Make a WeightBox instance in the middle of canvas 
	public void begin() 
	{
		int midx = CANVAS_WIDTH/2;	// Middle of the Canvas 
		int radius = PULLEYDIAMETER/2;
		Location pulleyloc = new Location(midx - radius, radius);
		Location anchor = new Location(pulleyloc.getX(), pulleyloc.getY()+radius);
		Location anchor2 = new Location(pulleyloc.getX()+PULLEYDIAMETER,pulleyloc.getY()+radius);
		double ropeLength = CANVAS_HEIGHT/4;
		

		pulley = new FilledOval(pulleyloc,PULLEYDIAMETER, PULLEYDIAMETER, canvas);
 
		box1 = new WeightBox(anchor,ropeLength,BOXWIDTH,canvas, radius);
		box2 = new WeightBox(anchor2, ropeLength, BOXWIDTH,canvas, radius);
		box1.setColor(Color.blue);
		box2.setColor(Color.yellow);
		instructions = new Text( "Click in left half to make left box move down.", 10, CANVAS_HEIGHT - 100, canvas);
		instructions2 = new Text("Click in right half to make right box move down.", 10, CANVAS_HEIGHT - 75, canvas);
	}

	// Method: onMouseClick
	// Move the Box up or down a bit each time the mouse is clicked
	public void onMouseClick(Location point) 
	{ 
		instructions.hide();
		instructions2.hide();
		double delta = STEP;

		//CASE 1
		if (point.getX()<CANVAS_WIDTH/2 && box2.getRopeLength() >= STEP)
		{
			box1.hoist(delta);
			box2.hoist(-delta);
		}
		//CASE 2
		if (point.getX()<CANVAS_WIDTH/2 && box2.getRopeLength() < STEP)
		{
			box1.hoist(box2.getRopeLength());
			box2.hoist(-(box2.getRopeLength()));
		}
		//CASE 3
		if (point.getX()>CANVAS_WIDTH/2 && box1.getRopeLength() >= STEP)
		{
			box1.hoist(-delta);
			box2.hoist(delta);
		}
		//CASE 4
		if (point.getX()>CANVAS_WIDTH/2 && box1.getRopeLength() < STEP)
		{
			box1.hoist(-(box1.getRopeLength()));
			box2.hoist(box1.getRopeLength());
		}

	}
	// Method: onMouseExit
	//  	Show instructions
	// 
	public void onMouseExit(Location p2oint)
	{
		instructions.show();
		instructions2.show();
	}

	// Method: main
	// Make this a Java Application
	public static void main (String[] args) 
	{
		new BlockAndTackle().startController(CANVAS_WIDTH,CANVAS_HEIGHT); 
	}
}
