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

    public void SaveImage(List<ShapeControl> shapes)
    {
        String outputString = "";
        for (int i = 0; i < shapes.size(); i++)
        {
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
        }
    }

    public void GetUserInput(ioOptions options)
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
        if (returnVal == JFileChooser.APPROVE_OPTION)
            fileSelected = fileChooser.getSelectedFile();
    }

    public ArrayList<ArrayList<String>> RetrieveData() throws IOException {
        ArrayList<ArrayList<String>> imageData;
        imageData = FormatData(RetrieveDataAsString());
        return imageData;
    }

    private String RetrieveDataAsString() throws IOException {
        ArrayList<ArrayList<String>> data = new ArrayList<>();

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


}