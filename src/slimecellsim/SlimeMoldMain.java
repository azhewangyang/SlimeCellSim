package slimecellsim;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * This SlimeMoldMain class has main function,
 which instantiated Frame to display the controlPanel
 */
public class SlimeMoldMain
{
    public static void main(String[] args)
    {
        System.out.println("Current thread is: " + Thread.currentThread().getName());
        // Create another Swing thread here.
        // Serve as EDT.
        SwingUtilities.invokeLater( new Runnable()
        {
            @Override
            public void run()
            {
                ControlPanel mp = new ControlPanel();
                JFrame frame = new JFrame("SlimeMoldViewer");
                frame.add(mp);

                frame.setSize(1300, 1300);
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                System.out.println("Current thread is: " + Thread.currentThread().getName());
            }
        });
    }

} // End of SlimeMoldMain class
