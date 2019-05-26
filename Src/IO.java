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
    private File fileSelected;

    public IO(Container parentContainer)
    {
        this.parentContainer = parentContainer;
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
                    CustomLine lineToWrite = (CustomLine) shapeToWrite;
                    outputString += "LINE " +
                            lineToWrite.getX1() + " " +
                            lineToWrite.getY1() + " " +
                            lineToWrite.getX2() + " " +
                            lineToWrite.getY2();
                    break;
            }
            outputString += "\r\n";
        }
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