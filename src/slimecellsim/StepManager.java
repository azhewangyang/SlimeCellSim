package slimecellsim;

import javax.swing.SwingWorker;

/**
 *
 * @author Mingzhe Jiang
 */
public class StepManager extends SwingWorker<Boolean, String>
{
    private final MatrixPanel matrixPanel;

    /**
     Constructor
     @param mp, matrixPanel passed as parameter.
     */
    public StepManager(MatrixPanel mp)
    {
        matrixPanel = mp;
    }


    @Override
    protected Boolean doInBackground() //throws Exception
    {
        // Define PheromoneUpdator Runnable
        Runnable PheromoneUpdater = new Runnable() {
            @Override
            public void run()
            {
                matrixPanel.pheromoneUpdate();
            }
        };

        // Define SlimeUpdater Runnable
        Runnable SlimeUpdater = new Runnable() {
            @Override
            public void run()
            {
                matrixPanel.slimeUpdate();
            }
        };

        // Define threads
        Thread SlimeThread = new Thread( SlimeUpdater );
        Thread pheromoneThread = new Thread ( PheromoneUpdater );


        // Kick off threads
        SlimeThread.start();
        pheromoneThread.start();

        return true;
    }

    @Override
    protected void done()
    {
        matrixPanel.repaint();
    }

} // End of StepManager class