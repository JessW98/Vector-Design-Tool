import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;

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

    public String RetrieveData() throws IOException {
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

    public ArrayList<ArrayList<String>> FormatData()
    {
        return null;
    }


}