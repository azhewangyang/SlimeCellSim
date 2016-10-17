package slimecellsim;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import static java.lang.System.exit;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 * This panel contains the matrix of the patches.
 * It also build an ArrayList for SlimeCells.
 * When paint this MatrixPanel, each SlimeCell will
 * be painted.
 */

public class MatrixPanel extends JPanel
{
    private ArrayList<SlimeCell> slimesArray;
    private ArrayList<Patch> patchArray;
    private int setupCells;
    private int pWidth; // Patch width, unit in pixels.
    private final double SQRT2 = 1.4142135623; // Square root of 2.
    private final Random ranGen = new Random();


    /**
     Constructor
     @param numCells, the initial number of slime cells needs to be created.
     */
    public MatrixPanel(int numCells)
    {
        setupCells = numCells;
        fillSlimeArray();
        fillPatchArray();
        pWidth = 8; // Patch width, unit in pixels.
    }


    /**
     * Default Constructor
     * Initiate the number slime cells to be 0.
     */
    public MatrixPanel()
    {
        setupCells = 0;
        fillSlimeArray();
        fillPatchArray();
        pWidth = 8; // Patch width, unit in pixels.
    }


    /**
     * This method fills the ArrayList of SlimeCells.
     * It generates random coordinates for the SlimeCells,
     * also generates random facing direction of each cell.
     * Then use the coordinates and direction to create
     * SlimeCell objects.
     */
    private void fillSlimeArray()
    {
        slimesArray = new ArrayList<SlimeCell>();
        int x, y, d;
        for (int i=0; i<setupCells; i++)
        {
            x = ranGen.nextInt(100);
            y = ranGen.nextInt(100);
            d = ranGen.nextInt(8);
            //slimesArray.add( new SlimeCell((2*x+1)*5, (2*y+1)*5, d) );
            slimesArray.add( new SlimeCell(x, y, d) );
        }
    }


    /**
     * This method fills the ArrayList of Patches.
     * It generates random coordinates for the SlimeCells,
     *
     */
    private void fillPatchArray()
    {
        int tempPH=0;
        patchArray = new ArrayList<Patch>();
        for (int i=0; i<100; i++)
        {
            for ( int j=0; j<100; j++)
            {
                patchArray.add( new Patch(j, i, tempPH) );
            }
        }
    }


    /**
     * It's used to draw SlimeCells.
     * @param g2d, used to draw SlimeCells in the ArrayList.
     * This method invokes drawSlime, which is the one doing
     * the drawing.
     */
    public void drawSlimeArray(Graphics2D g2d)
    {
        for (SlimeCell sc: slimesArray)
        {
            drawSlime(g2d, sc);
        }
    }

    /**
     * The method that ultimately does the drawing.
     * @param g2d used to draw SlimeCells in the ArrayList.
     * @param cell each cell needs to be draw by using g2d.
     * It represents the SlimeCells by using a triangle.
     */
    private void drawSlime(Graphics2D g2d, SlimeCell cell)
    {
        g2d.setColor(Color.RED);
        int x = (2*cell.getCellX()+1) * pWidth/2;
        int y = (2*cell.getCellY()+1) * pWidth/2;

        if (cell.getDirection()==SlimeCell.N)
        {
            int[] xPoints = {(int) (x+SQRT2*pWidth/4), (int) (x-SQRT2*pWidth/4) , x      };
            int[] yPoints = {(int) (y+SQRT2*pWidth/4), (int) (y+SQRT2*pWidth/4) , y-pWidth/2 };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.NW)
        {
            int[] xPoints = {x+pWidth/2, x      , (int) (x-SQRT2*pWidth/4) };
            int[] yPoints = {y     , y+pWidth/2 , (int) (y-SQRT2*pWidth/4) };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.W)
        {
            int[] xPoints = {(int) (x+SQRT2*pWidth/4), (int) (x+SQRT2*pWidth/4) , x-pWidth/2 };
            int[] yPoints = {(int) (y-SQRT2*pWidth/4), (int) (y+SQRT2*pWidth/4) , y      };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.SW)
        {
            int[] xPoints = {x     , x+pWidth/2, (int) (x-SQRT2*pWidth/4)};
            int[] yPoints = {y-pWidth/2, y     , (int) (y+SQRT2*pWidth/4)};
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.S)
        {
            int[] xPoints = {(int) (x-SQRT2*pWidth/4), (int) (x+SQRT2*pWidth/4) , x      };
            int[] yPoints = {(int) (y-SQRT2*pWidth/4), (int) (y-SQRT2*pWidth/4) , y+pWidth/2 };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.SE)
        {
            int[] xPoints = {x-pWidth/2 , x     , (int) (x+SQRT2*pWidth/4) };
            int[] yPoints = {y      , y-pWidth/2, (int) (y+SQRT2*pWidth/4) };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.E)
        {
            int[] xPoints = {(int) (x-SQRT2*pWidth/4), (int) (x-SQRT2*pWidth/4) , x+pWidth/2 };
            int[] yPoints = {(int) (y+SQRT2*pWidth/4), (int) (y-SQRT2*pWidth/4) , y      };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
        else if (cell.getDirection()==SlimeCell.NE)
        {
            int[] xPoints = {x     , x-pWidth/2 , (int) (x+SQRT2*pWidth/4) };
            int[] yPoints = {y+pWidth/2, y      , (int) (y-SQRT2*pWidth/4) };
            g2d.fillPolygon(xPoints, yPoints, 3);
        }
    }


    /**
     * It's used to draw Patches.
     * @param g2d, used to draw Patches in the ArrayList.
     * This method invokes drawPatch, which is the one doing
     * the drawing.
     */
    private void drawPatchArray(Graphics2D g2d)
    {
        for (Patch pa: patchArray)
        {
            drawPatch(g2d, pa);
        }
    }


    /**
     * The method that ultimately does the drawing.
     * @param g2d used to draw Patches in the ArrayList.
     * @param cell each patch needs to be draw by using g2d.
     * It represents the Patches by using a rectangle.
     */
    private void drawPatch(Graphics2D g2d, Patch pa)
    {
        int x = pWidth * pa.getPatchX();
        int y = pWidth * pa.getPatchY();
        int ph = (int) pa.getCurrentPH();
        int greenLevel = (ph<20) ? ph*255/20 : 255;
        g2d.setColor( new Color(0, greenLevel, 0 ) );
        g2d.fillRect(x, y, pWidth, pWidth);
        //g2d.setColor(Color.WHITE);
        //g2d.drawString( Integer.toString(ph) ,x , y+pWidth-3 );
    }


    /**
     * When you slide the population slider, the newly
     * assigned value will be passed from controlPanel to setupCells
     * by using this method.
     * @param n number of slime cells to begin with.
     */
    public void setSetupCells(int n)
    {
        setupCells = n;
        fillSlimeArray();
        fillPatchArray();
    }


    /**
     * Get the SlimeCell Array.
     * @return ArrayList<SlimeCell>
     */
    public ArrayList<SlimeCell> getSlimeArray()
    {
        return slimesArray;
    }


    /**
     * Get the Patch Array.
     * @return ArrayList<Patch>
     */
    public ArrayList<Patch> getPatchArray()
    {
        return patchArray;
    }


    /**
     * Paint the matrix of patches and slime cells.
     * @param g used to paint matrix aof patches and slime cells.
     */
    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2d = (Graphics2D) g;
        drawPatchArray(g2d);
        drawSlimeArray(g2d);
    }


    /**
     * During each step, update the pheromone
     * density across the patch matrix.
     */
    public synchronized void pheromoneUpdate()
    {
        Patch pNW;
        Patch pN;
        Patch pNE;
        Patch pE;
        Patch pSE;
        Patch pS;
        Patch pSW;
        Patch pW;


        for (Patch pa: patchArray)
        {
            // Occupied patch should be added 10 units of pheromone.
            if (pa.isOccupied())
            {
                //System.out.println("This patch's current PH is:" + pa.getCurrentPH()); MJ
                pa.setCurrentPH(pa.getCurrentPH()+10.0);
                //System.out.println("Set the occupied to: " + pa.getCurrentPH());
            }
        }

        // 1. Get one patch
        for (Patch pa: patchArray)
        {
            int x = pa.getPatchX();
            int y = pa.getPatchY();

            double diffuseAmount = pa.getCurrentPH() * Patch.diffuseRate / 8;

            // 2. Capture 8 neighbor
            pNW = patchArray.get(patchIndex(x, y, SlimeCell.NW));
            pN = patchArray.get(patchIndex(x, y, SlimeCell.N));
            pNE = patchArray.get(patchIndex(x, y, SlimeCell.NE));
            pE = patchArray.get(patchIndex(x, y, SlimeCell.E));
            pSE = patchArray.get(patchIndex(x, y, SlimeCell.SE));
            pS = patchArray.get(patchIndex(x, y, SlimeCell.S));
            pSW = patchArray.get(patchIndex(x, y, SlimeCell.SW));
            pW = patchArray.get(patchIndex(x, y, SlimeCell.W));

            // 3. Update 8 neighbor.
            pNW.setNextPH( pNW.getNextPH() + diffuseAmount );
            pN.setNextPH( pN.getNextPH() + diffuseAmount );
            pNE.setNextPH( pNE.getNextPH() + diffuseAmount );
            pE.setNextPH( pE.getNextPH() + diffuseAmount );
            pSE.setNextPH( pSE.getNextPH() + diffuseAmount );
            pS.setNextPH( pS.getNextPH() + diffuseAmount );
            pSW.setNextPH( pSW.getNextPH() + diffuseAmount );
            pW.setNextPH( pW.getNextPH() + diffuseAmount );

        } // End of for loop.

        // 4. Load NextPH to CurrentPH
        for (Patch pa: patchArray)
        {
            pa.updatePH();
            pa.setCurrentPH( pa.getCurrentPH() * (1-Patch.evaporateRate) );
            if ( pa.getCurrentPH()<=0.2 )
                pa.setCurrentPH(0);
        }
    } // End of pheromoneUpdate


    /**
     * During each step, based on the pheromone density
     * update each slime cell position.
     */
    public synchronized void slimeUpdate()
    {
        int x;
        int y;
        int d;
        Patch left;
        Patch forward;
        Patch right;

        for (SlimeCell sc: slimesArray)
        {
            // After getting the slime cell. Get the patches.
            x = sc.getCellX();
            y = sc.getCellY();
            d = sc.getDirection();

            if ( d==SlimeCell.N)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.NW));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.N));
                right = patchArray.get(patchIndex(x, y, SlimeCell.NE));
            }
            else if ( d==SlimeCell.NE)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.N));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.NE));
                right = patchArray.get(patchIndex(x, y, SlimeCell.E));
            }
            else if ( d==SlimeCell.E)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.NE));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.E));
                right = patchArray.get(patchIndex(x, y, SlimeCell.SE));
            }
            else if ( d==SlimeCell.SE)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.E));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.SE));
                right = patchArray.get(patchIndex(x, y, SlimeCell.S));
            }
            else if ( d==SlimeCell.S)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.SE));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.S));
                right = patchArray.get(patchIndex(x, y, SlimeCell.SW));
            }
            else if ( d==SlimeCell.SW)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.S));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.SW));
                right = patchArray.get(patchIndex(x, y, SlimeCell.W));
            }
            else if ( d==SlimeCell.W)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.SW));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.W));
                right = patchArray.get(patchIndex(x, y, SlimeCell.NW));
            }
            else // if ( d==SlimeCell.NW)
            {
                left = patchArray.get(patchIndex(x, y, SlimeCell.W));
                forward = patchArray.get(patchIndex(x, y, SlimeCell.NW));
                right = patchArray.get(patchIndex(x, y, SlimeCell.N));
            }

            // After emitting, decide which patch to go next.
            int destination = decideNext(left, forward, right, ranGen);

            if (destination==-1) // All three candidates are occupied
            {
                sc.setDirection(sc.getDirection()+1);
            }
            else if (destination==0)
            {
                sc.move(left.getLocation(), sc.getDirection()+1);
                left.setOccupied();
            }
            else if (destination==1)
            {
                sc.move(forward.getLocation());
                forward.setOccupied();
            }
            else if (destination==2)
            {
                sc.move(right.getLocation(), sc.getDirection()-1);
                right.setOccupied();
            }

            // Set previous location not-occupied
            patchArray.get(100*y+x).resetOccupied();
        }
    } // End of slimeUpdate


    /**
     * Private help method. Given a certain location
     * and a direction, output the corresponding patch
     * index in the ArrayList<Patch>.
     * @param x Location's x coordinate.
     * @param y Location's y coordinate.
     * @param d direction from this location.
     * @return Patch index in the ArrayList<Patch>.
     */
    private int patchIndex(int x, int y, int d)
    {
        if (d==SlimeCell.NW) // x-1, y-1;
        {
            x = (x==0) ? 99 : x-1;
            y = (y==0) ? 99 : y-1;
        }
        else if (d==SlimeCell.N) // x, y-1
        {
            y = (y==0) ? 99 : y-1;
        }
        else if (d==SlimeCell.NE) // x+1, y-1
        {
            x = (x==99) ? 0 : x+1;
            y = (y==0) ? 99 : y-1;
        }
        else if (d==SlimeCell.E) // x+1, y
        {
            x = (x==99) ? 0 : x+1;
        }
        else if (d==SlimeCell.SE) // x+1, y+1
        {
            x = (x==99) ? 0 : x+1;
            y = (y==99) ? 0 : y+1;
        }
        else if (d==SlimeCell.S) // x, y+1
        {
            y = (y==99) ? 0 : y+1;
        }
        else if (d==SlimeCell.SW) // x-1, y+1
        {
            x = (x==0) ? 99 : x-1;
            y = (y==99) ? 0 : y+1;
        }
        else if (d==SlimeCell.W) // x-1, y
        {
            x = (x==0) ? 99 : x-1;
        }
        else
        {
            System.err.println("Provided Direction is out range. Abort.");
            exit(-1);
        }
        return (100*y+x);
    } // End of patchIndex


    /**
     * Private help method. Given three patches in
     * front of a slime cell, select one based on
     * occupation status and pheromone density.
     * @param left Left patch.
     * @param forward Front patch.
     * @param right Right patch.
     * @param ranGen Random number generator
     * @return Decision about which patch to go.
     */
    private int decideNext( Patch left, Patch forward, Patch right, Random ranGen)
    {
        if (left.isOccupied()==true && forward.isOccupied()==true && right.isOccupied()==true)
            return -1;
        else if (left.isOccupied()==true && forward.isOccupied()==false && right.isOccupied()==true)
            return 1;
        else if (left.isOccupied()==true && forward.isOccupied()==true && right.isOccupied()==false)
            return 2;
        else if (left.isOccupied()==false && forward.isOccupied()==true && right.isOccupied()==true)
            return 0;
        else if (left.isOccupied()==true && forward.isOccupied()==false && right.isOccupied()==false)
            return comparePH(-1, forward.getCurrentPH(), right.getCurrentPH(), ranGen);
        else if (left.isOccupied()==false && forward.isOccupied()==true && right.isOccupied()==false)
            return comparePH(left.getCurrentPH(), -1, right.getCurrentPH(), ranGen);
        else if (left.isOccupied()==false && forward.isOccupied()==false && right.isOccupied()==true)
            return comparePH(left.getCurrentPH(), forward.getCurrentPH(), -1, ranGen);
        else // None of them are occupied.
            return comparePH(left.getCurrentPH(), forward.getCurrentPH(), right.getCurrentPH(), ranGen);

    } // End of decideNext


    /**
     * Private help method. Given three pheromone densities,
     * output a number representing which direction should go.
     * If one input is -1, then it means that patch is occupied.
     * @param a Left patches pheromone density.
     * @param b Forward patches pheromone density.
     * @param c Right patches pheromone density.
     * @param ranGen Random number generator
     * @return Number representing the index of the one slime should go.
     */
    private int comparePH( double a, double b, double c , Random ranGen )
    {
        // a -> return 0,
        // b -> return 1,
        // c -> return 2;
        int TH = SlimeCell.sniffThreshold;
        if (a==-1)
        {
            if (b>TH && c>TH)
            {
                if (b>c)
                    return 1;
                else if (b==c)
                    return 2;
                else // b<c
                    return 2;
            }
            else if (b>TH && c==TH)
                return 1;
            else if (b>TH && c<TH)
                return 1;
            else if (b==TH && c>TH)
                return 2;
            else if (b==TH && c==TH)
                return 2;
            else if (b==TH && c<TH)
                return 1;
            else if (b<TH && c>TH)
                return 2;
            else if (b<TH && c==TH)
                return 2;
            else //if (b<TH && c<TH)
                return ranGen.nextInt(2)+1;
        }
        else if (b==-1)
        {
            if (a>TH && c>TH)
            {
                if (a>c)
                    return 0;
                else if (a==c)
                    return 2;
                else // a<c
                    return 2;
            }
            else if (a>TH && c==TH)
                return 0;
            else if (a>TH && c<TH)
                return 0;
            else if (a==TH && c>TH)
                return 2;
            else if (a==TH && c==TH)
                return 2 ;
            else if (a==TH && c<TH)
                return 0;
            else if (a<TH && c>TH)
                return 2;
            else if (a<TH && c==TH)
                return 2;
            else //if (a<TH && c<TH)
                return ranGen.nextInt(2)==1?2:0 ;
        }
        else if (c==-1)
        {
            if (a>TH && b>TH)
            {
                if (a>b)
                    return 0;
                else if (a==b)
                    return 0;
                else // a<b
                    return 1;
            }
            else if (a>TH && b==TH)
                return 0;
            else if (a>TH && b<TH)
                return 0;
            else if (a==TH && b>TH)
                return 1;
            else if (a==TH && b==TH)
                return 0;
            else if (a==TH && b<TH)
                return 0;
            else if (a<TH && b>TH)
                return 1;
            else if (a<TH && b==TH)
                return 1;
            else //if (a<TH && b<TH)
                return ranGen.nextInt(2);
        }
        else // None of them are -1
        {
            if (a>TH && b>TH && c>TH)
            {
                if (b>a && c>a)
                    return comparePH(-1,b,c, ranGen);
                else if (b>a && c==a)
                    return 1;
                else if (b>a && c<a)
                    return 1;
                else if (b==a && c>a)
                    return 2;
                else if (b==a && c==a)
                    return 2;
                else //if (b==a && c<a)
                    return 0;
            }
            else if (a>TH && b>TH && c==TH)
            {
                return comparePH(a,b,-1, ranGen);
            }
            else if (a>TH && b>TH && c<TH)
            {
                return comparePH(a,b,-1, ranGen);
            }
            else if (a>TH && b==TH && c>TH)
            {
                return comparePH(a,-1,c, ranGen);
            }
            else if (a>TH && b==TH && c==TH)
            {
                return 0;
            }
            else if (a>TH && b==TH && c<TH)
            {
                return 0;
            }
            else if (a>TH && b<TH && c>TH)
            {
                return comparePH(a,-1,c,ranGen);
            }
            else if (a>TH && b<TH && c==TH)
            {
                return 0;
            }
            else if (a>TH && b<TH && c<TH)
            {
                return 0;
            }
            else if (a==TH && b>TH && c>TH)
            {
                return comparePH(-1, b, c, ranGen);
            }
            else if (a==TH && b>TH && c==TH)
            {
                return 1;
            }
            else if (a==TH && b>TH && c<TH)
            {
                return 1;
            }
            else if (a==TH && b==TH && c>TH)
            {
                return 2;
            }
            else if (a==TH && b==TH && c==TH)
            {
                return 2;
            }
            else if (a==TH && b==TH && c<TH)
            {
                return 0;
            }
            else if (a==TH && b<TH && c>TH)
            {
                return 2;
            }
            else if (a==TH && b<TH && c==TH)
            {
                return 2 ;
            }
            else if (a==TH && b<TH && c<TH)
            {
                return 0;
            }
            else if (a<TH && b>TH && c>TH)
            {
                return comparePH(-1, b, c, ranGen);
            }
            else if (a<TH && b>TH && c==TH)
            {
                return 1;
            }
            else if (a<TH && b>TH && c<TH)
            {
                return 1;
            }
            else if (a<TH && b==TH && c>TH)
            {
                return 2;
            }
            else if (a<TH && b==TH && c==TH)
            {
                return 2;
            }
            else if (a<TH && b==TH && c<TH)
            {
                return 1;
            }
            else if (a<TH && b<TH && c>TH)
            {
                return 2;
            }
            else if (a<TH && b<TH && c==TH)
            {
                return 2;
            }
            else // if (a<TH && b<TH && c<TH)
            {
                return ranGen.nextInt(3);
            }


        }

    } // End of comparePH method


} // End of MatrixPanel class
