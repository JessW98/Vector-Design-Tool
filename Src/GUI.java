import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * <h1>GUI</h1>
 * <p>
 * GUI is the graphical display for the Vector Design Tool.
 * </p>
 * @author Jessica Williams, William Daley, Jacob Kraut
 * @version 1.0
 * @since 2019-05-03
 */
public class GUI extends JFrame {
    enum ShapeType{
        RECTANGLE, ELLIPSE, LINE, PLOT, POLYGON
    }

    private static JButton colorPicker;
    private static JButton fillPicker;
    private static JButton clearButton;
    private static JButton gridButton;
    private static JTextField gridEntry;
    private static double windowWidth;
    private static double windowHeight;
    private static double displayWidth;
    private static double displayHeight;
    private static GUI mainPanel;
    private static JPanel left;
    private static JPanel bottom;
    private static Canvas drawingCanvas;
    private static ActionListener actionListener;
    private static IO io;
    private static JMenuItem load;
    private static JMenuItem save;
    private static File[] fileNames = new File[5];
    private static String[] pathNames = new String[5];
    private static String[] shapeNames = new String[5];
    private static JButton[] shapeButtons = new JButton[5];
    private static Image[] buttonImageIcons = new Image[5];

    private static void PopulatePathNamesArray(){
        pathNames[0] = "Src/images/line.png";
        pathNames[1] = "Src/images/ellipse.png";
        pathNames[2] = "Src/images/plot.png";
        pathNames[3] = "Src/images/polygon.png";
        pathNames[4] = "Src/images/rectangle.png";
    }

    private static void PopulateShapeNamesArray(){
        shapeNames[0] = "Line";
        shapeNames[1] = "Ellipse";
        shapeNames[2] = "Plot";
        shapeNames[3] = "Polygon";
        shapeNames[4] = "Rectangle";
    }

    private static void resizeCanvas()
    {
        Dimension newWindowSize = mainPanel.getSize();
        double newHeight = newWindowSize.getHeight();
        double newWidth = newWindowSize.getWidth();

        double heightDifference = (newHeight-63)/(windowHeight-63);
        double widthDifference = (newWidth-214)/(windowWidth-214);

        drawingCanvas.ResizeShapes(widthDifference, heightDifference);

        windowHeight = newHeight;
        windowWidth = newWidth;
    }

    private static void AssignPathNamesToFileNames(){
        PopulatePathNamesArray();
        for(int i = 0; i < fileNames.length; i++){
            try {
                fileNames[i] = new File(pathNames[i]);
            }
            catch(Exception e){
                fileNames[i] = null;
            }
        }
    }

    private static void ReadInLinePNG()throws IOException{
        if(fileNames[0].exists()) {
            BufferedImage linePic = ImageIO.read(GUI.class.getResource(
                    "images/line.png"));
            Image linePicScaled = linePic.getScaledInstance(
                    40, 40, Image.SCALE_SMOOTH);
            buttonImageIcons[0] = linePicScaled;
        }
    }

    private static void ReadInEllipsePNG() throws IOException{
        if(fileNames[1].exists()) {
            BufferedImage ellipsePic = ImageIO.read(GUI.class.getResource(
                    "images/ellipse.png"));
            Image ellipsePicScaled = ellipsePic.getScaledInstance(40, 40,
                    Image.SCALE_SMOOTH);
            buttonImageIcons[1] = ellipsePicScaled;
        }
    }

    private static void ReadInPlotPNG() throws IOException{
        if(fileNames[2].exists()) {
            BufferedImage plotPic = ImageIO.read(GUI.class.getResource(
                    "images/plot.png"));
            Image plotPicScaled = plotPic.getScaledInstance(40, 40,
                    Image.SCALE_SMOOTH);
            buttonImageIcons[2] = plotPicScaled;
        }
    }

    private static void ReadInPolygonPNG() throws IOException{
        if(fileNames[3].exists()) {
            BufferedImage polygonPic = ImageIO.read(GUI.class.getResource(
                    "images/polygon.png"));
            Image polygonPicScaled = polygonPic.getScaledInstance(40, 40,
                    Image.SCALE_SMOOTH);
            buttonImageIcons[3] = polygonPicScaled;
        }
    }

    private static void ReadInRectanglePNG() throws IOException{
        if(fileNames[4].exists()) {
            BufferedImage rectanglePic = ImageIO.read(GUI.class.getResource(
                    "images/rectangle.png"));
            Image rectanglePicScaled = rectanglePic.getScaledInstance(40,
                    40, Image.SCALE_SMOOTH);
            buttonImageIcons[4] = rectanglePicScaled;
        }
    }

    private static void ReadInImages() throws IOException{
        AssignPathNamesToFileNames();
        PopulateShapeNamesArray();
        ReadInLinePNG();
        ReadInEllipsePNG();
        ReadInPlotPNG();
        ReadInPolygonPNG();
        ReadInRectanglePNG();
    }

    private static void CreateButtons(){
        colorPicker = new JButton("Pen Colour");
        fillPicker = new JButton("Fill Colour");
        clearButton = new JButton("Clear");
        gridButton = new JButton("Grid View");
        gridEntry = new JTextField();

        for(int i = 0; i < shapeButtons.length; i++){
            if(fileNames[i].exists()){
                shapeButtons[i] =
                        new JButton(new ImageIcon (buttonImageIcons[i]));
            }
            else{
                shapeButtons[i] = new JButton(shapeNames[i]);
            }
        }
    }

    private static void CreateMenuNavigationBar(){
        //Create the Menu Bar
        JMenuBar menuBar = new JMenuBar();
        mainPanel.setJMenuBar(menuBar);

        //Populate menu bar with menus
        JMenu file = new JMenu("File");
        menuBar.add(file);
        load = new JMenuItem("Load");
        save = new JMenuItem("Save");
        file.add(load);
        file.add(save);
    }

    private static void CreateToolBar(){
        JPanel tools = new JPanel();
        tools.setLayout(new GridLayout(5, 1, 1, 1));
        tools.setBackground(Color.gray);

        left = new JPanel(new GridBagLayout());
        left.setBorder(new LineBorder(Color.black, 1));
        left.setBackground(Color.gray);

        GridBagConstraints toolGridBagConstraints = new GridBagConstraints();
        toolGridBagConstraints.anchor = GridBagConstraints.NORTH;
        toolGridBagConstraints.weighty = 1;

        //add tools to left panel
        left.add(tools, toolGridBagConstraints);

        for(int i = 0; i < shapeButtons.length; i++){
            tools.add(shapeButtons[i]);
        }
        tools.add(clearButton);
        tools.add(gridButton);
        tools.add(gridEntry);

    }
    private static void createColourBar(){
        JPanel colours = new JPanel();
        colours.setLayout(new GridLayout(1,4,1,1));
        colours.setBackground(Color.gray);

        bottom = new JPanel(new GridBagLayout());
        bottom.setBorder(new LineBorder(Color.black, 1));
        bottom.setBackground(Color.gray);

        GridBagConstraints toolGridBagConstraints = new GridBagConstraints();
        toolGridBagConstraints.anchor = GridBagConstraints.WEST;
        toolGridBagConstraints.weighty = 1;

        //add tools to left panel
        bottom.add(colours, toolGridBagConstraints);
        colours.add(colorPicker);
        colours.add(fillPicker);

        colorPicker.setBackground(drawingCanvas.GetPenColor());
        fillPicker.setBackground(drawingCanvas.GetFillColor());
    }

    private static void HandleActionListeners(){
        actionListener = e -> {
            if (e.getSource() == clearButton) {
                drawingCanvas.Clear();
            }

            else if (e.getSource() == shapeButtons[0]){
                drawingCanvas.SetCurrentSelectedShape(ShapeType.LINE);

            }
            else if (e.getSource() == shapeButtons[1]){
                drawingCanvas.SetCurrentSelectedShape(ShapeType.ELLIPSE);

            }
            else if (e.getSource() == shapeButtons[2]){
                drawingCanvas.SetCurrentSelectedShape(ShapeType.PLOT);

            }
            else if (e.getSource() == shapeButtons[3]){
                drawingCanvas.SetCurrentSelectedShape(ShapeType.POLYGON);

            }
            else if (e.getSource() == shapeButtons[4]){
                drawingCanvas.SetCurrentSelectedShape(ShapeType.RECTANGLE);

            }
            else if (e.getSource() == colorPicker){
                drawingCanvas.SetPenColor(JColorChooser.showDialog(
                        null,
                        "Pick your pen colour!",
                        drawingCanvas.GetPenColor()));
                colorPicker.setBackground(drawingCanvas.GetPenColor());
            }
            else if (e.getSource() == fillPicker){
                drawingCanvas.SetFillColor(JColorChooser.showDialog(
                        null,
                        "Pick your fill colour!",
                        drawingCanvas.GetFillColor()));
                fillPicker.setBackground(drawingCanvas.GetFillColor());
            }
            else if(e.getSource() == load){
                drawingCanvas.OverrideCanvas(io.LoadImage());
            }
            else if(e.getSource() == save){
                io.SaveImage(drawingCanvas.GetShapesDrawn());
            }
            else if(e.getSource() == gridButton){
                drawingCanvas.toggleGrid();
            }
            else if(e.getSource() == gridEntry){
                int i = 0;
            }

            left.getActionMap().put("undo", new AbstractAction("undo") {
                @Override
                public void actionPerformed(ActionEvent e) {
                    undo();
                }
            });
            left.getInputMap().put(KeyStroke.getKeyStroke("control Z"), "undo");
        };
    }

    private static void undo()
    {
        drawingCanvas.RemoveLastShape();
    }

    private static void CreateMainPanel(){
        //create the main frame
        mainPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container mainContainer = mainPanel.getContentPane();

        //add left panel to content pane
        mainContainer.add(left, BorderLayout.WEST);
        mainContainer.add(bottom, BorderLayout.SOUTH);

        //add draw area to content
        mainContainer.add(drawingCanvas, BorderLayout.CENTER);
        io = new IO(mainContainer, drawingCanvas);
    }

    private static void DimensionGUI(){
        //Get the screen resolution
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        displayWidth = screenSize.getWidth();
        displayHeight = screenSize.getHeight();
        //How much of the screen will the window take up from 0.0-1.0
        double windowToScreenRatio = 0.75;
        windowHeight = (int)(displayHeight * windowToScreenRatio);
        windowWidth = (int)(displayWidth * windowToScreenRatio);
    }

    private static void ShowGUI(){
        //Display the window
        mainPanel.setPreferredSize(new Dimension((int)windowWidth, (int)windowHeight));
        mainPanel.setLocation(new Point(
                (int)(displayWidth - windowWidth) / 2,
                (int)(displayHeight - windowHeight) / 2));
        mainPanel.pack();
        mainPanel.setVisible(true);
    }

    private static void updateGridSize() {
        try {
            drawingCanvas.updateGrid(Integer.parseInt(gridEntry.getText()));
            gridEntry.setBackground(Color.WHITE);
        }
        catch (Exception ex)
        {
            gridEntry.setBackground(Color.RED);
        }
    }

    private static void AttachActionListeners(){
        for(int i = 0; i < shapeButtons.length; i++){
            shapeButtons[i].addActionListener(actionListener);
        }
        colorPicker.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);
        fillPicker.addActionListener(actionListener);
        gridButton.addActionListener(actionListener);
        gridEntry.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateGridSize();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateGridSize();
            }

            public void changedUpdate(DocumentEvent e) {
                updateGridSize();
            }});
        load.addActionListener(actionListener);
        save.addActionListener(actionListener);
        mainPanel.addComponentListener(new ComponentAdapter( ) {
            public void componentResized(ComponentEvent ev) {
                resizeCanvas();
            }
        });
    }

    private static void LaunchProgram() throws IOException {
        mainPanel = new GUI();
        drawingCanvas = new Canvas();
        ReadInImages();
        CreateMenuNavigationBar();
        CreateButtons();
        createColourBar();
        CreateToolBar();
        HandleActionListeners();
        CreateMainPanel();
        DimensionGUI();
        ShowGUI();
        AttachActionListeners();
    }

    /**
     * Entry point of the program.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                LaunchProgram();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}