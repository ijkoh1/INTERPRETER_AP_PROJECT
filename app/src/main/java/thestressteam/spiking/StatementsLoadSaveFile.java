package thestressteam.spiking;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by Ivan on 5/23/2016.
 */
public class StatementsLoadSaveFile {
    private String allGotoCodes;
    private ArrayList<Command> commandList;
    public StatementsLoadSaveFile(CommandLine commandLine, String fileName)
    {
        commandList = new ArrayList<Command>();
        allGotoCodes = "";

    }

    public void saveCode(ArrayList<Command> commandList, String fileName, Context context)
    {
        try{
            FileOutputStream outputStream = context.openFileOutput(fileName,Context.MODE_PRIVATE);

            for (int lineCount = 0; lineCount < commandList.size(); lineCount++)
            {
                Statement statement = commandList.get(lineCount).getStatement();
                ArrayList<String> details = statement.getDetails();
                for (int j = 0; j < details.size(); j++)
                {
                    allGotoCodes += details.get(j) + " ";
                }
                allGotoCodes += "\n";
            }
            OutputStreamWriter outputWriter = new OutputStreamWriter(outputStream);
            outputWriter.write(allGotoCodes);
            outputWriter.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void loadCode(String fileName, Context context)
    {
        try
        {
            FileInputStream fileInput = context.openFileInput(fileName);
            InputStreamReader inputReader = new InputStreamReader(fileInput);
            BufferedReader br = new BufferedReader(inputReader);
            String line = null;
            while ((line = br.readLine()) != null)
            {
                System.out.println(line);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
