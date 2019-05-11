import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    /**
     * Create and show the GUI
     */
    private GUI() {
        super("VectorDesignTool");
        Canvas canvas = new Canvas();
        JButton blackBtn, greenBtn, redBtn, pinkBtn, blueBtn, cyanBtn;

        //Colour initialize buttons
        blackBtn = new JButton(" ");
        greenBtn = new JButton(" ");
        redBtn = new JButton(" ");
        pinkBtn = new JButton(" ");
        blueBtn = new JButton(" ");
        cyanBtn = new JButton(" ");

        ActionListener actionListener = e -> {
            if (e.getSource()== blackBtn){
                canvas.black();
            }else if (e.getSource() == greenBtn){
                canvas.green();
            }else if (e.getSource() == redBtn){
                canvas.red();
            }else if (e.getSource() == pinkBtn){
                canvas.pink();
            }else if (e.getSource() == blueBtn){
                canvas.blue();
            }else if (e.getSource() == cyanBtn){
                canvas.cyan();
            }

        };

        //add action listeners to buttons
        blackBtn.addActionListener(actionListener);
        greenBtn.addActionListener(actionListener);
        redBtn.addActionListener(actionListener);
        pinkBtn.addActionListener(actionListener);
        blueBtn.addActionListener(actionListener);
        cyanBtn.addActionListener(actionListener);



        //create the main frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = getContentPane();
        //Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        //create the canvas and add to main container
        JPanel drawingArea = new JPanel();
        drawingArea.setBackground(Color.WHITE);
        drawingArea.setBorder(new LineBorder(Color.black));
        mainContainer.add(drawingArea, BorderLayout.CENTER);

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


        //tools panel
        JPanel tools = new JPanel();
        tools.setLayout(new GridLayout(5, 1, 1, 1));
        tools.setBackground(Color.gray);

        //colours panel
        JPanel colours = new JPanel();
        colours.setLayout(new GridLayout(2, 10, 1, 1));
        colours.setBackground(Color.gray);

        //left side panel
        JPanel left = new JPanel(new GridBagLayout());
        left.setBorder(new LineBorder(Color.black, 1));
        left.setBackground(Color.gray);

        //bottom panel
        JPanel bottom = new JPanel(new GridBagLayout());
        bottom.setBorder(new LineBorder(Color.black, 1));
        bottom.setBackground(Color.gray);

        //constraints for tools
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.anchor = GridBagConstraints.NORTH;

        //contracts for colours
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.SOUTH;

        //add tools to left panel
        left.add(tools, gbc1);
        //add colours to bottom panel
        bottom.add(colours, gbc2);

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

        //colour buttons
        colours.add(blackBtn);
        colours.add(greenBtn);
        colours.add(blueBtn);
        colours.add(pinkBtn);
        colours.add(redBtn);
        colours.add(cyanBtn);

        //Make colour buttons their relevant colour
        blackBtn.setOpaque(true);
        blackBtn.setBackground(Color.black);
        blackBtn.setSize(25,25);

        greenBtn.setOpaque(true);
        greenBtn.setBackground(Color.green);

        blueBtn.setOpaque(true);
        blueBtn.setBackground(Color.blue);

        pinkBtn.setOpaque(true);
        pinkBtn.setBackground(Color.PINK);

        redBtn.setOpaque(true);
        redBtn.setBackground(Color.RED);

        cyanBtn.setOpaque(true);
        cyanBtn.setBackground(Color.CYAN);

        //add left panel to content pane
        mainContainer.add(left, BorderLayout.WEST);

        //add draw area to content
        mainContainer.add(canvas,BorderLayout.CENTER);

        //add bottom panel to content pane
        mainContainer.add(bottom, BorderLayout.SOUTH);

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