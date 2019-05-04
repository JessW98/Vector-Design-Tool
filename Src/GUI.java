import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    /**
     * Create and show the GUI
     */
    public GUI(){
        //create the main frame
        super("VectorDesignTool");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container content = getContentPane();


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

        //Side Panel Layout
        JPanel tools = new JPanel();

        //Rectangle button
        JButton button = new JButton("Rectangle");
        tools.add(button);

        //add to content pane
        content.add(tools, BorderLayout.WEST);

        //Display the window
        setPreferredSize(new Dimension(300, 100));
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
