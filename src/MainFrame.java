
 //Klasa za glavni okvir uspostavljanja GUI-ja
 
import java.util.LinkedList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Dimension;

public class MainFrame extends JFrame {    
    //Konstante
    private static final int FRAME_WIDTH = 600;
    private static final int FRAME_HEIGHT = 500;
    private static final int RODS_WIDTH = 550;
    private static final int RODS_HEIGHT = 400;
    private static final int INITIAL_NUMBER_OF_DISKS = 4;
    private static final int INITIAL_ROD = 0;
    private static final String TITLE = "Resenje hanojskih kula";
    private static final String DISK_NUMBER_LABEL = "Postavite broj diskova:";
    private static final int DELAY = 1000;
    
    //Promenljive varijable
    private JPanel panel;
    private JButton nextButton;
    private JButton animateButton;
    private JLabel diskNumberLabel;
    private JComboBox<Integer> diskNumberSelection;
    private JComponent mainComponent;
    private Rods rods;
    private Timer timer;
    private LinkedList<Integer> movesToSolve;
     
    
    //Konstruktor
     
    public MainFrame() {
        rods = new Rods(INITIAL_NUMBER_OF_DISKS, INITIAL_ROD);
        createComponents();
        setTitle(TITLE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    
    
     //Kreira manje GUI elemente i dodaje ih u okvir.
     
    private void createComponents() {
        timer = new Timer(DELAY, new AnimationListener());
        
        nextButton = new JButton("Next");
        animateButton = new JButton("Animate");
        nextButton.addActionListener(new NextButtonListener());
        animateButton.addActionListener(new AnimateButtonListener());
        
        panel = new JPanel();
        
        mainComponent = new MainComponent(rods);
        mainComponent.setPreferredSize(new Dimension(RODS_WIDTH, RODS_HEIGHT));
        
        panel.add(mainComponent);
        
        diskNumberLabel = new JLabel(DISK_NUMBER_LABEL);
        
        diskNumberSelection = new JComboBox<Integer>(new Integer[] {1, 2, 3, 4, 5, 6});
        diskNumberSelection.setSelectedItem(4);
        diskNumberSelection.addActionListener(new DiskNumberChoiceListener());
        
        rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
        rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        
        panel.add(diskNumberLabel);
        panel.add(diskNumberSelection);
        panel.add(nextButton);
        panel.add(animateButton); 
        add(panel);
    }
    
    //Unutrasnja klasa za ActionListener koriscena od strane next dugmeta.
     
    private class NextButtonListener implements ActionListener {
        
        // Ressavanje problema Hanoi-skih kula :)

        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            }
        }
    }
    
    //Unutrasnja klasa za ActionListener koriscen od stane animate dugmeta.
   
    private class AnimateButtonListener implements ActionListener {
        
         //Pokretanje procesa animacije
        
        @Override
        public void actionPerformed(ActionEvent event) {
            timer.start();
        }
    }
    
    
     //Unutrasnja klasa za Actionlistener koji koristi timer.
     
    private class AnimationListener implements ActionListener {
        
         //Animira disk na nacin da resi problem Kule iz Hanoi-ja
        
        @Override
        public void actionPerformed(ActionEvent event) {
            movesToSolve = rods.getMovesToSolve();
            if (!movesToSolve.isEmpty()) {
                rods.moveDisk(movesToSolve.removeFirst(), movesToSolve.removeFirst());
                ((MainComponent) mainComponent).update();
            } else {
                timer.stop();
            }
        }
    } 
    
    //Unutrasnja klasa za ActionListener koju koristi combo box
     
    private class DiskNumberChoiceListener implements ActionListener {
        
         //Menjanje broja diskova u skladu sa odabranim brojem u kombiniranom okviru.

        @Override
        public void actionPerformed(ActionEvent event) {
            timer.stop();
            timer = new Timer(DELAY, new AnimationListener());
            rods = new Rods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
            ((MainComponent) mainComponent).updateRods(rods);
            rods.solveTowersOfHanoi(rods.getRodArray()[INITIAL_ROD].getDisksOnTop().size(), INITIAL_ROD, 1, 2);
            rods.initializeRods((int) diskNumberSelection.getSelectedItem(), INITIAL_ROD);
        }
    } 
}
