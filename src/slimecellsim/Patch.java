package slimecellsim;

import java.awt.geom.Point2D;

/**
 * This Patch class defines basic single individual patch,
 * It has its own location and pheromone density information.
 */
public class Patch
{
    private double currentPH; // Current pheromone density.
    private double nextPH; // Next step future pheromone density.
    private boolean occupied;

    public static double diffuseRate = 1;
    public static double evaporateRate = 0.2;

    // Location of the Patch.
    // The coordinates are the center of the Patch.
    private Point2D.Double location;


    /** Constructor.
     *  @param x, x coordinate of the location.
     *  @param y, y coordinate of the location.
     *  @param ph, Initial pheromone density of the patch.
     */
    public Patch(int x, int y, int ph)
    {
        location = new Point2D.Double(x,y);
        currentPH = ph;
        nextPH = 0;
        occupied = false;
    }


    /** Constructor.
     *  @param p, Location of the patch in Point2D.Double format.
     *  @param ph, Initial pheromone density of the patch.
     */
    public Patch(Point2D.Double p, int ph)
    {
        location = p;
        currentPH = ph;
        nextPH = 0;
        occupied = false;
    }


    /** Get the location of the patch in Point2D.Double format.
     *  @return , Location of the patch in Point2D.Double format.
     */
    public Point2D.Double getLocation()
    {
        return location;
    }


    /** Get patch x coordinate of the location.
     *  @return, x coordinate of the location.
     */
    public int getPatchX()
    {
        return (int) location.getX();
    }


    /** Get patch y coordinate of the current location.
     *  @return, y coordinate of the location.
     */
    public int getPatchY()
    {
        return (int) location.getY();
    }


    /** Get current pheromone density of the patch.
     *  @return, Current pheromone density of the patch.
     */
    public double getCurrentPH()
    {
        return currentPH;
    }


    /** Set the current pheromone density of the patch.
     *  @param ph, set the current pheromone density of the patch.
     */
    public void setCurrentPH(double ph)
    {
        currentPH = ph;
    }


    /** Get pheromone density of the patch that will be updated to.
     *  @return, Pheromone density of the patch that will be updated to.
     */
    public double getNextPH()
    {
        return nextPH;
    }


    /** Set pheromone density of the patch that will be updated to.
     *  @param ph, set pheromone density of the patch that will be updated to.
     */
    public void setNextPH(double ph)
    {
        nextPH = ph;
    }


    /** Update the current pheromone density with the next value.
     */
    public void updatePH()
    {
        currentPH = nextPH;
        nextPH = 0.0;
    }


    /** Indicate if a patch is occupied by a slime cell.
     *  @return , return true is this patch is occupied by a slime cell.
     */
    public boolean isOccupied()
    {
        return occupied;
    }


    /** Set the occupied variable to true to indicate this patch is occupied.
     */
    public void setOccupied()
    {
        occupied = true;
    }


    /** Set the occupied variable to false to indicate this patch is empty.
     */
    public void resetOccupied()
    {
        occupied = false;
    }

} // End of Patch class
