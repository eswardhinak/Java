/* 
 * This program creates a circle and two lines where the user clicks his or her button
*/

import objectdraw.*;
import java.awt.*;

public class Fred extends WindowController {

	private FilledOval oval;
	private Color blue;

	public void onMousePress(Location point) {
		oval = new FilledOval(point.getX(), point.getY(), 30, 30,canvas);
		blue = new Color(0,0,255);
		oval.setColor(blue);
		new Line(point.getX(), point.getY(), point.getX()+45, point.getY(), canvas);
		new Line(point.getX(), point.getY(), point.getX(), point.getY()+45, canvas);
}
	public void onMouseRelease(Location point){
		canvas.clear();}
	
	public static void main(String args[]){
	
		new Fred().startController(400,400);


}
}


		
		
