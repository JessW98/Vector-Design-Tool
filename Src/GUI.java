import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JFrame {
    /**
     * Create and show the GUI
     */
    private static void createAndShowGUI() throws IOException {

        GUI mainPanel = new GUI();
        Canvas canvas = new Canvas();

        //Import and scale images
        Image linePic = ImageIO.read(GUI.class.getResource("images/line.png"));
        Image linePicScaled = linePic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        BufferedImage ellipsePic = ImageIO.read(GUI.class.getResource("images/ellipse.png"));
        Image ellipsePicScaled = ellipsePic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        BufferedImage plotPic = ImageIO.read(GUI.class.getResource("images/plot.png"));
        Image plotPicScaled = plotPic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        BufferedImage polygonPic = ImageIO.read(GUI.class.getResource("images/polygon.png"));
        Image polygonPicScaled = polygonPic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);

        BufferedImage rectanglePic = ImageIO.read(GUI.class.getResource("images/rectangle.png"));
        Image rectanglePicScaled = rectanglePic.getScaledInstance(40, 40, Image.SCALE_SMOOTH);


        //Colour initialize and declare buttons and entry
        JButton blackBtn = new JButton(" ");
        JButton greenBtn = new JButton(" ");
        JButton redBtn = new JButton(" ");
        JButton pinkBtn = new JButton(" ");
        JButton blueBtn = new JButton(" ");
        JButton cyanBtn = new JButton(" ");
        JTextField hexEntry = new JTextField("Enter Hex");

        hexEntry.setColumns(8);


        //create the main frame
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = mainPanel.getContentPane();

        //Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        mainPanel.setJMenuBar(menuBar);

        //create the canvas and add to main container
        JPanel drawingArea = new JPanel();
        drawingArea.setBackground(Color.WHITE);
        drawingArea.setBorder(new LineBorder(Color.black));
        mainContainer.add(drawingArea, BorderLayout.CENTER);

        //Populate menu bar with menus
        JMenu file = new JMenu("File");
        menuBar.add(file);
        file.add("Load");
        file.add("save");

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

        //hexEntry panel
        JPanel hexPanel = new JPanel();
        hexPanel.setLayout(new GridLayout(1, 1, 1, 1));
        hexPanel.setBackground(Color.gray);

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
        gbc1.weighty = 1;

        //constraints for colours
        GridBagConstraints gbc2 = new GridBagConstraints();
        gbc2.anchor = GridBagConstraints.WEST;
        gbc2.weightx = 1;

        //constraints for colours
        GridBagConstraints gbc3 = new GridBagConstraints();
        gbc3.anchor = GridBagConstraints.CENTER;
        gbc3.weightx = 1;

        //hexadecimal entry for colours

        //add tools to left panel
        left.add(tools, gbc1);
        //add colours and hex entry to bottom panel
        bottom.add(colours, gbc2);
        bottom.add(hexPanel, gbc3);

        //Buttons for tools

        JButton rectangleButton = new JButton(new ImageIcon(rectanglePicScaled));
        tools.add(rectangleButton);

        JButton lineButton = new JButton(new ImageIcon(linePicScaled));
        tools.add(lineButton);

        JButton ellipseButton = new JButton(new ImageIcon(ellipsePicScaled));
        tools.add(ellipseButton);

        JButton plotButton = new JButton(new ImageIcon(plotPicScaled));
        tools.add(plotButton);

        JButton polygonButton = new JButton(new ImageIcon(polygonPicScaled));
        tools.add(polygonButton);

        JButton clearButton = new JButton("Clear");
        tools.add(clearButton);

        //colour buttons
        colours.add(blackBtn);
        colours.add(greenBtn);
        colours.add(blueBtn);
        colours.add(pinkBtn);
        colours.add(redBtn);
        colours.add(cyanBtn);


        hexPanel.add(hexEntry);

        //Make colour buttons their relevant colour
        blackBtn.setOpaque(true);
        blackBtn.setBackground(Color.black);

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
        mainContainer.add(canvas, BorderLayout.CENTER);

        //add bottom panel to content pane
        mainContainer.add(bottom, BorderLayout.SOUTH);

        //What happens when a button is pressed

        ActionListener actionListener = e -> {
            if (e.getSource() == blackBtn) {
                canvas.black();
            } else if (e.getSource() == greenBtn) {
                canvas.green();
            } else if (e.getSource() == redBtn) {
                canvas.red();
            } else if (e.getSource() == pinkBtn) {
                canvas.pink();
            } else if (e.getSource() == blueBtn) {
                canvas.blue();
            } else if (e.getSource() == cyanBtn) {
                canvas.cyan();
            } else if (e.getSource() == clearButton) {
                canvas.clear();
            }else if (e.getSource() == rectangleButton){
                canvas.rectangle();
            }else if (e.getSource() == lineButton){
                canvas.line();
            }else if (e.getSource() == ellipseButton){
                canvas.ellipse();
            }else if (e.getSource() == plotButton){
                canvas.plot();
            }else if (e.getSource() == polygonButton){
                canvas.polygon();
            }


        };

            //add action listeners to buttons
            blackBtn.addActionListener(actionListener);
            greenBtn.addActionListener(actionListener);
            redBtn.addActionListener(actionListener);
            pinkBtn.addActionListener(actionListener);
            blueBtn.addActionListener(actionListener);
            cyanBtn.addActionListener(actionListener);
            lineButton.addActionListener(actionListener);
            rectangleButton.addActionListener(actionListener);
            polygonButton.addActionListener(actionListener);
            ellipseButton.addActionListener(actionListener);
            plotButton.addActionListener(actionListener);


            //Display the window
            mainPanel.setPreferredSize(new Dimension(500, 500));
            mainPanel.setLocation(new Point(200, 300));
            mainPanel.pack();
            mainPanel.setVisible(true);

    }

    //Main entry point for program
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}