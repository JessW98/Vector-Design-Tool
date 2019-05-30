import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IO {
    enum ioOptions{
        save,
        load,
    }

    private Container parentContainer;
    Canvas drawingCanvas;
    private File fileSelected;

    public IO(Container parentContainer, Canvas drawingCanvas)
    {
        this.parentContainer = parentContainer;
        this.drawingCanvas = drawingCanvas;
    }

    private Boolean PromptUserToSelectFile(ioOptions options)
    {
        final JFileChooser fileChooser = new JFileChooser();
        int returnVal;

        switch (options){
            case load:
                returnVal = fileChooser.showOpenDialog(parentContainer);
                break;
            case save:
                returnVal = fileChooser.showSaveDialog(parentContainer);
                break;
            default:
                returnVal =  JFileChooser.CANCEL_OPTION;
                break;
        }
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            fileSelected = fileChooser.getSelectedFile();
            return true;
        }
        return false;
    }

    void SaveImage(List<ShapeControl> shapes)
    {
        Boolean fileChosenSuccess = PromptUserToSelectFile(IO.ioOptions.save);
        if (fileChosenSuccess) {
            String outputString = FormatShapeControlListToString(shapes);
            try {
                WriteStringToFile(outputString);
            } catch (IOException e) {
                DisplaySaveError();
            }
        }
    }

    private String FormatShapeControlListToString(List<ShapeControl> shapes){
        StringBuilder outputString = new StringBuilder();

        Color currentFillColour = null;
        Color currentPenColour = null;

        //Iterates over the list of Shape control objects
        for (int i = 0; i < shapes.size(); i++) {
            //Convert the ShapeControl to Shape type
            Shape shapeToWrite = shapes.get(i).GetShape();

            Color shapeFillColour = shapes.get(i).getShapeFillColour();
            Color shapePenColour = Color.BLACK;
            if (shapes.get(i).getShapePenColour() != null)
                shapePenColour = shapes.get(i).getShapePenColour();

            if (shapeFillColour != currentFillColour)
            {
                currentFillColour = shapeFillColour;
                if (shapeFillColour == null)
                    outputString.append("FILL OFF\r\n");
                else
                    outputString.append("FILL " + RGBToHex(currentFillColour) + "\r\n");
            }

            if (shapePenColour != currentPenColour)
            {
                currentPenColour = shapePenColour;
                outputString.append("PEN " + RGBToHex(currentPenColour) + "\r\n");
            }

            switch (shapes.get(i).GetShapeType())
            {
                case LINE:
                    outputString.append(ConvertLineShapeToString(shapeToWrite));
                    break;
                case POLYGON:
                    outputString.append(ConvertPolygonShapeToString(shapeToWrite));
                    break;
                case RECTANGLE:
                    outputString.append(ConvertRectangleShapeToString(shapeToWrite));
                    break;
                case PLOT:
                    outputString.append(ConvertPlotShapeToString(shapeToWrite));
                    break;
                case ELLIPSE:
                    outputString.append(ConvertEllipseShapeToString(shapeToWrite));
                    break;
                default:
                    DisplaySaveError();
                    break;
            }
            outputString.append("\r\n");
        }
        return outputString.toString();
    }

    private String RGBToHex(Color colorToConvert){
        if (colorToConvert == null)
            return null;
        String hex = "#"+Integer.toHexString(colorToConvert.getRGB()).substring(2);
        return hex;
    }

    private String ConvertLineShapeToString(Shape shapeToWrite)
    {
        CustomLine lineToWrite = (CustomLine) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the canvas for vec format
        double x1 = lineToWrite.getX1() / drawingCanvas.getWidth();
        double y1 = lineToWrite.getY1() / drawingCanvas.getHeight();
        double x2 = lineToWrite.getX2() / drawingCanvas.getWidth();
        double y2 = lineToWrite.getY2() / drawingCanvas.getHeight();

        outputString += "LINE " + x1 + " " + y1 + " " + x2 + " " + y2;
        return outputString;
    }

    private String ConvertPolygonShapeToString(Shape shapeToWrite)
    {
        CustomPolygon polygonToWrite = (CustomPolygon) shapeToWrite;
        String outputString = "";

        double x = 0;
        double y = 0;

        outputString += "POLYGON ";
        for (int o = 0; o < polygonToWrite.npoints; o++) {
            //Encode absolute coordinates to fractions of the canvas for vec format
            x = (double) polygonToWrite.xpoints[o] / drawingCanvas.getWidth();
            y = (double) polygonToWrite.ypoints[o] / drawingCanvas.getHeight();

            outputString += x + " " + y + " ";
        }
        return outputString;
    }

    private String ConvertRectangleShapeToString(Shape shapeToWrite)
    {
        CustomRectangle rectangleToWrite = (CustomRectangle) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the canvas for vec format
        double x1 = rectangleToWrite.getX() / drawingCanvas.getWidth();
        double y1 = rectangleToWrite.getY() / drawingCanvas.getHeight();
        double x2 = (rectangleToWrite.getX() + rectangleToWrite.getWidth()) / drawingCanvas.getWidth();
        double y2 = (rectangleToWrite.getY() + rectangleToWrite.getHeight()) / drawingCanvas.getHeight();

        outputString += "RECTANGLE " +
                x1 + " " +
                y1 + " " +
                x2 + " " +
                y2;
        return outputString;

    }

    private String ConvertPlotShapeToString(Shape shapeToWrite)
    {
        CustomPlot plotToWrite = (CustomPlot) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the canvas for vec format
        double x = plotToWrite.getX() / drawingCanvas.getWidth();
        double y = plotToWrite.getY() / drawingCanvas.getHeight();

        outputString += "PLOT " +
                x + " " +
                y;
        return outputString;
    }

    private String ConvertEllipseShapeToString(Shape shapeToWrite)
    {
        CustomEllipse ellipseToWrite = (CustomEllipse) shapeToWrite;
        String outputString = "";

        //Encode absolute coordinates to fractions of the canvas for vec format
        double x1 = ellipseToWrite.getX() / drawingCanvas.getWidth();
        double y1 = ellipseToWrite.getY() / drawingCanvas.getHeight();
        double x2 = (ellipseToWrite.getX() + ellipseToWrite.getWidth()) / drawingCanvas.getWidth();
        double y2 = (ellipseToWrite.getY() + ellipseToWrite.getHeight()) / drawingCanvas.getHeight();

        outputString += "ELLIPSE " +
                x1 + " " +
                y1 + " " +
                x2 + " " +
                y2;
        return outputString;
    }

    private void WriteStringToFile (String shapeData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileSelected));
        writer.write(shapeData);
        writer.close();
    }

    public List<Shape> LoadShapeDataFromFile() {
        Boolean fileChosenSuccess = PromptUserToSelectFile(IO.ioOptions.load);
        if (fileChosenSuccess) {
            List<Shape> imageData;

            try {
                imageData = FormatData(RetrieveDataAsString());
                return imageData;
            } catch (IOException e) {
                DisplayLoadError();
            }
        }
        //Returns an empty list if loading fails or user cancels
        return new ArrayList<>();
    }

    private String RetrieveDataAsString() throws IOException {
        BufferedReader thingThatsReadingTheFiles = new BufferedReader(new FileReader(fileSelected));
        StringBuilder sb = new StringBuilder();
        String line = thingThatsReadingTheFiles.readLine();

        while (line != null) {
            sb.append(line);
            sb.append(System.lineSeparator());
            line = thingThatsReadingTheFiles.readLine();
        }
        return sb.toString();
    }

    private List<Shape> FormatData(String dataString) {
        ArrayList<ArrayList<String>> dataAsStrings = splitString(dataString);
        List<Shape> formattedShapes = getCommandsFromStrings(dataAsStrings);
        return formattedShapes;
    }

    private ArrayList<ArrayList<String>> splitString(String dataString)
    {
        ArrayList<ArrayList<String>> dataAsStrings = new ArrayList<>();

        //Separate shapes
        String[] dataSeperatedShapes = dataString.split("\r\n");

        //Separate arguments
        for (int i = 0; i < dataSeperatedShapes.length; i++) {
            ArrayList<String> temp = new ArrayList<>();
            String[] dataSeperatedbyArgs = dataSeperatedShapes[i].split(" ");
            for (int o = 0; o < dataSeperatedbyArgs.length; o++) {
                temp.add(dataSeperatedbyArgs[o]);
            }
            dataAsStrings.add(temp);
        }
        return dataAsStrings;
    }

    private List<Shape> getCommandsFromStrings(ArrayList<ArrayList<String>> dataAsStrings)
    {
        List<Shape> formattedShapes = new ArrayList<Shape>();
        Color currentPenColour = Color.BLACK;
        Color currentFillColour = null;

        for (int shapeNo = 0; shapeNo < dataAsStrings.size(); shapeNo++) {
            Shape shapeBeingRead;
            String command = dataAsStrings.get(shapeNo).get(0);
            switch (command) {
                case "LINE":
                    formattedShapes.add(
                            ConvertStringsToLine(dataAsStrings.get(shapeNo), currentPenColour)
                    );
                    break;
                case "POLYGON":
                    formattedShapes.add(
                            ConvertStringsToPolygon(dataAsStrings.get(shapeNo), currentPenColour, currentFillColour)
                    );
                    break;
                case "RECTANGLE":
                    formattedShapes.add(
                            ConvertStringsToRectangle(dataAsStrings.get(shapeNo), currentPenColour, currentFillColour)
                    );
                    break;
                case "PLOT":
                    formattedShapes.add(
                            ConvertStringsToPlot(dataAsStrings.get(shapeNo), currentPenColour)
                    );
                    break;
                case "ELLIPSE":
                    formattedShapes.add(
                            ConvertStringsToEllipse(dataAsStrings.get(shapeNo), currentPenColour, currentFillColour)
                    );
                    break;
                case "PEN":
                    currentPenColour = new Color(Color.decode(dataAsStrings.get(shapeNo).get(1)).getRGB());
                    break;
                case "FILL":
                    if (dataAsStrings.get(shapeNo).get(1).equals("OFF"))
                        currentFillColour = null;
                    else
                        currentFillColour = new Color(Color.decode(dataAsStrings.get(shapeNo).get(1)).getRGB());
                    break;
                default:
                    DisplayLoadError();
                    break;
            }
        }
        return formattedShapes;
    }

    private Shape ConvertStringsToLine(List<String> lineDataAsStrings, Color currentPenColour)
    {
        double[] coords = {0,0,0,0};
        for (int i = 1; i < 5; i++)
            if (i%2 == 0)
                coords[i-1] = Double.parseDouble(lineDataAsStrings.get(i)) *  drawingCanvas.getHeight();
            else
                coords[i-1] = Double.parseDouble(lineDataAsStrings.get(i)) *  drawingCanvas.getWidth();
        return new CustomLine(coords[0],coords[1],coords[2],coords[3],currentPenColour);
    }

    private Shape ConvertStringsToPolygon(List<String> polygonDataAsStrings, Color currentPenColour, Color currentFillColour)
    {
        List<double[]> coordinates = new ArrayList<>();
        for (int i = 0; i < polygonDataAsStrings.size(); i++)
        {
            try {
                double x = Double.parseDouble(polygonDataAsStrings.get(i)) * drawingCanvas.getWidth();
                double y = Double.parseDouble(polygonDataAsStrings.get(++i)) * drawingCanvas.getHeight();
                double[] coords = {x,y};
                coordinates.add(coords);

            }
            catch (Exception e)
            {
                DisplayLoadError();
            }
        }
        return new CustomPolygon(coordinates, currentPenColour, currentFillColour);
    }

    private Shape ConvertStringsToRectangle(List<String> rectangleDataAsStrings, Color currentPenColour, Color currentFillColour) {
        double[] coords = {0,0,0,0};
        for (int i = 1; i < 5; i++)
            if (i%2 == 0)
                coords[i-1] = Double.parseDouble(rectangleDataAsStrings.get(i)) *  drawingCanvas.getHeight();
            else
                coords[i-1] = Double.parseDouble(rectangleDataAsStrings.get(i)) *  drawingCanvas.getWidth();
        return new CustomRectangle(coords[0],coords[1],coords[2],coords[3],currentPenColour, currentFillColour);
    }

    private Shape ConvertStringsToPlot(List<String> plotDataAsStrings, Color currentPenColour)
    {
        double x = Double.parseDouble(plotDataAsStrings.get(1)) *  drawingCanvas.getWidth();
        double y = Double.parseDouble(plotDataAsStrings.get(2)) *  drawingCanvas.getHeight();
        return new CustomPlot(x, y, currentPenColour, 3, 3);
    }

    private Shape ConvertStringsToEllipse(List<String> ellipseDataAsStrings, Color currentPenColour, Color currentFillColour)
    {
        double[] coords = {0,0,0,0};
        for (int i = 1; i < 5; i++)
            if (i%2 == 0)
                coords[i-1] = Double.parseDouble(ellipseDataAsStrings.get(i)) *  drawingCanvas.getHeight();
            else
                coords[i-1] = Double.parseDouble(ellipseDataAsStrings.get(i)) *  drawingCanvas.getWidth();
        return new CustomEllipse(coords[0],coords[1],coords[2],coords[3],currentPenColour, currentFillColour);
    }

    private void DisplayLoadError() {

    }

    private void DisplaySaveError() {

    }
}