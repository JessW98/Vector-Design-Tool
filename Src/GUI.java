import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class GUI extends JFrame {
    /**
     * Create and show the GUI
     */
    private GUI(){
        //create the main frame
        super("VectorDesignTool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = getContentPane();
        mainContainer.setLayout(new BorderLayout(8,6));

        //Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //Populate menu bar with menus
        JMenu file = new JMenu("File");
        menuBar.add(file);
        file.add("exit");

        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);
        JMenu view = new JMenu("View");
        menuBar.add(view);
        JMenu image = new JMenu("Image");
        menuBar.add(image);
        JMenu colors = new JMenu("Colours");
        menuBar.add(colors);
        JMenu help = new JMenu("Help");
        menuBar.add(help);

        //tools panel
        JPanel tools = new JPanel();
        tools.setLayout(new GridLayout(5,1,5,5));
        tools.setBackground(Color.gray);

        //left side panel
        JPanel left = new JPanel(new GridBagLayout());
        left.setBorder(new LineBorder(Color.black, 1));
        left.setBackground(Color.gray);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.NORTH;
        gbc.weighty = 1;
        left.add(tools,gbc);
        
        //Buttons for tools
        JButton rectangleButton = new JButton("Rectangle");
        tools.add(rectangleButton);

        JButton lineButton = new JButton("Line");
        tools.add(lineButton);

        JButton ellipseButton = new JButton("Ellipse");
        tools.add(ellipseButton);

        JButton plotButton = new JButton("Plot");
        tools.add(plotButton);

        JButton polygonButton = new JButton("Polygon");
        tools.add(polygonButton);

        //add tools to content pane
        mainContainer.add(left, BorderLayout.WEST);

        //main drawing pane

        //Display the window
        setPreferredSize(new Dimension(500, 500));
        setLocation(new Point(200, 300));
        pack();
        setVisible(true);

    }

    //Main entry point for program
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        new GUI();
    }
}
