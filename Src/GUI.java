import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class GUI extends JFrame {
    /**
     * <h1>GUI</h1>
     * <p>
     * GUI is the graphical display for our Vector Design Tool.
     * This class contains all graphical objects,
     * </p>
     *
     */

    enum ShapeType{
        RECTANGLE, ELLIPSE, LINE, PLOT, POLYGON
    }

    private static void createAndShowGUI() throws IOException {
        GUI mainPanel = new GUI();
        Canvas drawingCanvas = new Canvas();

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
        JButton colorPicker = new JButton("Pen Colour");
        JButton fillPicker = new JButton("Fill Colour");

        //create the main frame
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = mainPanel.getContentPane();

        //Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        mainPanel.setJMenuBar(menuBar);

        //Populate menu bar with menus
        JMenu file = new JMenu("File");
        menuBar.add(file);
        JMenuItem load = new JMenuItem("Load");
        JMenuItem save = new JMenuItem("Save");
        file.add(load);
        file.add(save);

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

        colorPicker.setBackground(drawingCanvas.getPenColor());
        tools.add(colorPicker);

        fillPicker.setBackground(drawingCanvas.getFillColor());
        tools.add(fillPicker);

        IO io = new IO(mainContainer);

        //add left panel to content pane
        mainContainer.add(left, BorderLayout.WEST);

        //add draw area to content
        mainContainer.add(drawingCanvas, BorderLayout.CENTER);

        //Action listener that controls the buttons method outputs
        ActionListener actionListener = e -> {
            if (e.getSource() == clearButton) {
                drawingCanvas.clear();
            }
            else if (e.getSource() == rectangleButton){
                drawingCanvas.setCurrentSelectedShape(ShapeType.RECTANGLE);

            }
            else if (e.getSource() == lineButton){
                drawingCanvas.setCurrentSelectedShape(ShapeType.LINE);

            }
            else if (e.getSource() == ellipseButton){
                drawingCanvas.setCurrentSelectedShape(ShapeType.ELLIPSE);

            }
            else if (e.getSource() == plotButton){
                drawingCanvas.setCurrentSelectedShape(ShapeType.PLOT);

            }
            else if (e.getSource() == polygonButton){
                drawingCanvas.setCurrentSelectedShape(ShapeType.POLYGON);

            }
            else if (e.getSource() == colorPicker){
                drawingCanvas.setPenColor(JColorChooser.showDialog(null, "Pick your pen colour!", drawingCanvas.getPenColor()));
                colorPicker.setBackground(drawingCanvas.getPenColor());

            }
            else if (e.getSource() == fillPicker){
                drawingCanvas.setFillColor(JColorChooser.showDialog(null, "Pick your fill colour!", drawingCanvas.getFillColor()));
                fillPicker.setBackground(drawingCanvas.getFillColor());
            }
            else if(e.getSource() == load){
                io.GetUserInput(IO.ioOptions.load);
                try {
                    io.RetrieveData();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            else if(e.getSource() == save){
                io.GetUserInput(IO.ioOptions.save);
                io.SaveImage(drawingCanvas.getShapesDrawn());
            }
        };

        //add action listener to buttons
        lineButton.addActionListener(actionListener);
        rectangleButton.addActionListener(actionListener);
        polygonButton.addActionListener(actionListener);
        ellipseButton.addActionListener(actionListener);
        plotButton.addActionListener(actionListener);
        colorPicker.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);
        fillPicker.addActionListener(actionListener);
        load.addActionListener(actionListener);
        save.addActionListener(actionListener);

        //Get the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double displayWidth = screenSize.getWidth();
        double displayHeight = screenSize.getHeight();
        //How much of the screen will the window take up from 0.0-1.0
        double windowToScreenRatio = 0.75;
        int windowStartupHeight = (int)(displayHeight * windowToScreenRatio);
        int windowStartupWidth = (int)(displayWidth * windowToScreenRatio);

        //Display the window
        mainPanel.setPreferredSize(new Dimension(windowStartupWidth, windowStartupHeight));
        mainPanel.setLocation(new Point(
                (int)(displayWidth - windowStartupWidth) / 2,
                (int)(displayHeight - windowStartupHeight) / 2));
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