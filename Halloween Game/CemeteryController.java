/*
 *CemeteryController.java
 * Name: Eswar Dhinakaran
 * Login: cs11eak
 * ID: A99071249
 * This interface sets constants required for the program.
 * */


public interface CemeteryController
{
	public static final double PROBABILITY = 0.5;
	public static final int SCALE = 20;
	public static final double MAXV = 0.3;
	public static final int SIZE = 600;
	
	public void record(Goblin wraith, boolean vaporized);
}
