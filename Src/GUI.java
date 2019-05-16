import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI extends JFrame {
    /**
     * Create and show the GUI
     */

    enum ShapeType{
        Rectangle, Ellipse, Line, Plot, Polygon
    }

    private static void createAndShowGUI() throws IOException {

        GUI mainPanel = new GUI();
        Canvas canvas = new Canvas();

        //Import and scale images for the buttons they will be attached too.
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
        JButton colorPicker = new JButton("Colour");

        //create the main frame
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = mainPanel.getContentPane();

        //Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        mainPanel.setJMenuBar(menuBar);

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

        //constraints for tools
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.anchor = GridBagConstraints.NORTH;
        gbc1.weighty = 1;

        //add tools to left panel
        left.add(tools, gbc1);


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

        colorPicker.setBackground(canvas.getDrawColour());
        tools.add(colorPicker);


        //add left panel to content pane
        mainContainer.add(left, BorderLayout.WEST);

        //add draw area to content
        mainContainer.add(canvas, BorderLayout.CENTER);




        //Action listener that controls the buttons method outputs
        ActionListener actionListener = e -> {
            if (e.getSource() == clearButton) {
                canvas.clear();
            }else if (e.getSource() == rectangleButton){
                canvas.setShape(ShapeType.Rectangle.toString());

            }else if (e.getSource() == lineButton){
                canvas.setShape(ShapeType.Line.toString());

            }else if (e.getSource() == ellipseButton){
                canvas.setShape(ShapeType.Ellipse.toString());

            }else if (e.getSource() == plotButton){
                canvas.setShape(ShapeType.Plot.toString());

            }else if (e.getSource() == polygonButton){
                canvas.setShape(ShapeType.Polygon.toString());

            }else if (e.getSource() == colorPicker){
                canvas.setDrawColour(JColorChooser.showDialog(null, "Pick your colour", canvas.getDrawColour()));
                colorPicker.setBackground(canvas.getDrawColour());
            }

        };

            //add action listener to buttons
            lineButton.addActionListener(actionListener);
            rectangleButton.addActionListener(actionListener);
            polygonButton.addActionListener(actionListener);
            ellipseButton.addActionListener(actionListener);
            plotButton.addActionListener(actionListener);
            colorPicker.addActionListener(actionListener);


            //Display the window
            mainPanel.setPreferredSize(new Dimension(500, 500));
            mainPanel.setLocation(new Point(200, 300));
            mainPanel.pack();
            mainPanel.setVisible(true);

    }

    //Main entry point for program invokes creation of the GUI
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