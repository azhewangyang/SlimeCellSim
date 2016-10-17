package slimecellsim;

import java.awt.geom.Point2D;

/**
 * This SlimeCell class defines basic single individual slime cell,
 * It has its own current location and pointing-to direction information.
 */
public class SlimeCell
{
    // Current location of the SlimeCell.
    // The coordinates are the center of the SlimeCell.
    private Point2D.Double location;
    private int direction;

    // Direction that a SlimeCell have and can move toward.
    public static final int N  = 0;
    public static final int NW = 1;
    public static final int W  = 2;
    public static final int SW = 3;
    public static final int S  = 4;
    public static final int SE = 5;
    public static final int E  = 6;
    public static final int NE = 7;
    public static final int sniffThreshold = 2;


    /** Constructor.
     *  @param x, x coordinate of the location.
     *  @param y, y coordinate of the location.
     *  @param d, Initial direction of the cell.
     */
    public SlimeCell(int x, int y, int d)
    {
        location = new Point2D.Double(x,y);
        direction = d;
    }


    /** Constructor.
     *  @param p, Location of the cell in Point2D.Double format.
     *  @param d, Initial direction of the cell.
     */
    public SlimeCell(Point2D.Double p, int d)
    {
        location = p;
        direction = d;
    }


    /** Return the current location of the cell.
     *  @return, Current location in Point2D.Double format.
     */
    public Point2D.Double getLocation()
    {
        return location;
    }


    /** Get cell x coordinate of the current location.
     *  @return, x coordinate of the location.
     */
    public int getCellX()
    {
        return (int) location.getX();
    }


    /** Get cell y coordinate of the current location.
     *  @return, y coordinate of the location.
     */
    public int getCellY()
    {
        return (int) location.getY();
    }


    /** Get pointing-to direction of the cell.
     *  @return, Pointing-to direction of the cell.
     */
    public int getDirection()
    {
        return direction;
    }


    /** Set the pointing-to direction of the cell.
     *  @param d, New pointing-to direction.
     */
    public void setDirection(int d)
    {
        while (d<0)
        {    d += 8;  }
        direction = d%8;
    }


    /** Move the cell to a new location.
     *  @param newLocation, New location in Point2D.Double format.
     */
    public void move (Point2D.Double newLocation)
    {
        location = new Point2D.Double( newLocation.getX(), newLocation.getY() );
    }


    /** Move the cell to a new location and assign a new pointing-to direction.
     *  @param newLocation, New location in Point2D.Double format.
     *  @param d, New pointing-to direction.
     */
    public void move (Point2D.Double newLocation, int d)
    {
        location = new Point2D.Double( newLocation.getX(), newLocation.getY() );
        setDirection(d);
    }

} // End of SlimeCell class
