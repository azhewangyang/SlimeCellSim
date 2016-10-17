package slimecellsim;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * This panel instantiate a MatrixPanel. It also has buttons, sliders
 * and labels. It implements ChangeListener and ActionListener, registered
 * as listeners for slider sliding events and button click events.
 * @author Mingzhe.Jiang
 *
 */
public class ControlPanel extends JPanel implements ChangeListener, ActionListener
{
    /**
     * Buttons and Sliders definitions.
     */
    private final JButton setupButton;
    private final JButton stepButton;
    private final JButton goButton;
    private final JButton stopButton;

    private final JSlider population;
    private final JSlider speed;
    private final JLabel  populLabel;
    private final JLabel  speedLabel;

    private final int PMIN = 0;
    private final int PMAX = 1000;
    private final int PTYP = 500;
    private final int SMIN = 0;
    private final int SMAX = 1000;
    private final int STYP = 900;

    private int setupCells = 1000;
    private int simSpeed   = 900;

    private final MatrixPanel mp;
    private final Timer t;

    /**
     * Default constructor
     * 1. Set layout manager to be GridBagLayout.
     * 2. Create buttons, sliders and labels.
     * 3. Set sliders.
     * 4. Set how each element should be placed in the layout and place them.
     * 5. Register listener of each buttons and sliders.
     */
    public ControlPanel()
    {
        setLayout(new GridBagLayout());

        mp = new MatrixPanel(0);
        setupButton = new JButton("Setup");
        stepButton = new JButton("Step");
        goButton = new JButton("Go");
        stopButton = new JButton("Stop");
        population = new JSlider(JSlider.HORIZONTAL, PMIN, PMAX, PTYP);
        speed = new JSlider(JSlider.HORIZONTAL, SMIN, SMAX, STYP);
        populLabel = new JLabel("Initial Population");
        speedLabel = new JLabel("Simlution Speed");

        population.setMinorTickSpacing(200);
        population.setMajorTickSpacing(500);
        population.setPaintTicks(true);
        population.setPaintLabels(true);
        population.setPaintTrack(true);

        speed.setMinorTickSpacing(200);
        speed.setMajorTickSpacing(500);
        speed.setPaintTicks(true);
        speed.setPaintLabels(true);
        speed.setPaintTrack(true);

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        add(setupButton, c);

        c.gridx = 0;
        c.gridy = 1;
        add(stepButton, c);

        c.gridx = 0;
        c.gridy = 2;
        add(goButton, c);

        c.gridx = 0;
        c.gridy = 3;
        add(stopButton, c);

        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        add(populLabel, c);

        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 2;
        add(population, c);

        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        add(speedLabel, c);

        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        add(speed, c);

        c.gridx = 2;
        c.gridy = 0;
        c.weightx = 1;
        c.weighty = 1;
        c.gridwidth = 400;
        c.gridheight = 400;
        c.fill = GridBagConstraints.BOTH;
        add(mp, c);



        setupButton.addActionListener(this);
        stepButton.addActionListener(this);
        goButton.addActionListener(this);
        stopButton.addActionListener(this);
        population.addChangeListener(this);
        speed.addChangeListener(this);

        t = new Timer(1001 - simSpeed, this);

    } // End of constructor


    /** Listen to the slider.
     *  @param e Change event when you slide the sliders.
     */
    @Override
    public void stateChanged(ChangeEvent e)
    {
        JSlider source = (JSlider) e.getSource();
        if ( source == population )
        {
            if (!source.getValueIsAdjusting())
            {
                setupCells = (int)source.getValue();
                System.out.println("setCells is changed to:" + setupCells);
            }
        }
        else if ( source == speed )
        {
            if (!source.getValueIsAdjusting())
            {
                simSpeed = (int)source.getValue();
                t.setDelay(1001-simSpeed);
                System.out.println("speed is changed to:" + simSpeed);
            }
        }

    }


    /** Listen to the buttons' presses.
     *  Currently if setup is pressed, it will repaint the whole panel
     *  with the number of cells given by slider or initial setup.
     *  @param ae Action event when button is pressed.
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if (ae.getSource() == setupButton )
        {
            System.out.println("Setup is pressed.");
            t.stop();
            mp.setSetupCells(setupCells);
            repaint();
        }
        else if (ae.getSource() == stepButton)
        {
            System.out.println("Step is pressed.");
            t.stop();
            StepManager newStep = new StepManager(mp);
            newStep.execute();
        }
        else if (ae.getSource() == goButton)
        {
            System.out.println("Go is pressed.");
            if (simSpeed!=0)
                t.start();
        }
        else if (ae.getSource() == stopButton)
        {
            System.out.println("Stop is pressed.");
            t.stop();
        }
        else if (ae.getSource() == t)
        {
            System.out.println("Timer expire, execute!");
            StepManager newStep = new StepManager(mp);
            newStep.execute();
        }
    }

} // End of ControlPanel class

