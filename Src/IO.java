import javax.swing.*;
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

    public void SaveImage(List<ShapeControl> shapes)
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
        String outputString = "";
        //Iterates over the list of Shape control objects
        for (int i = 0; i < shapes.size(); i++) {
            //Convert the ShapeControl to Shape type
            Shape shapeToWrite;
            shapeToWrite = shapes.get(i).GetShape();

            switch (shapes.get(i).GetShapeType())
            {
                case LINE:
                    outputString += ConvertLineShapeToString(shapeToWrite);
                    break;
                case POLYGON:
                    outputString += ConvertPolygonShapeToString(shapeToWrite);
                    break;
                case RECTANGLE:
                    outputString += ConvertRectangleShapeToString(shapeToWrite);
                    break;
                case PLOT:
                    outputString += ConvertPlotShapeToString(shapeToWrite);
                    break;
                case ELLIPSE:
                    outputString += ConvertEllipseShapeToString(shapeToWrite);
                    break;
            }
            outputString += "\r\n";
        }
        return outputString;
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
        String outputString = "";
        CustomEllipse ellipseToWrite = (CustomEllipse) shapeToWrite;
        outputString += "ELLIPSE " +
                ellipseToWrite.getX() + " " +
                ellipseToWrite.getY() + " " +
                ellipseToWrite.getWidth() + " " +
                ellipseToWrite.getHeight();
        return outputString;
    }

    private void WriteStringToFile (String shapeData) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileSelected));
        writer.write(shapeData);
        writer.close();
    }

    private void DisplaySaveError() {

    }

    public ArrayList<ArrayList<String>> LoadDataFromFile() {
        Boolean fileChosenSuccess = PromptUserToSelectFile(IO.ioOptions.load);
        if (fileChosenSuccess) {
            ArrayList<ArrayList<String>> imageData;

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

    private ArrayList<ArrayList<String>> FormatData(String dataString) {
        ArrayList<ArrayList<String>> formattedData = new ArrayList<>();

        //Separate shapes
        String[] dataSeperatedShapes = dataString.split("\r\n");

        //Separate arguments
        for (int i = 0; i < dataSeperatedShapes.length; i++) {
            ArrayList<String> temp = new ArrayList<>();
            String[] dataSeperatedbyArgs = dataSeperatedShapes[i].split(" ");
            for (int o = 0; o < dataSeperatedbyArgs.length; o++) {
                temp.add(dataSeperatedbyArgs[o]);
            }
            formattedData.add(temp);
        }
        return formattedData;
    }

    private void DisplayLoadError() {

    }
}