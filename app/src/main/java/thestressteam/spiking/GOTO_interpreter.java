package thestressteam.spiking;


import java.util.ArrayList;


/**
 * Created by Ivan on 4/26/2016.
 */

public class GOTO_interpreter{
    private Integer currentLineNumber;
    private Integer previousLineNumber;
    private ArrayList<String> results;
    private CommandLine commandLine;
    private DeclaredVariableList variableList;
    private Boolean running;

    public GOTO_interpreter(CommandLine commandLine)
    {
        this.currentLineNumber = 0;
        this.previousLineNumber = null;
        this.commandLine = commandLine;
        this.results = new ArrayList<String>();
        this.variableList = new DeclaredVariableList();
        this.running = true;
    }

    public void readAllCode()
    {
        ArrayList<Command> currentCommand = commandLine.getCommandLines();

        try {
            while (this.running) {
                if (this.currentLineNumber.equals(currentCommand.size() - 1)) {
                    this.running = false;
                } else if (this.currentLineNumber >= currentCommand.size()) {
                    throw new Exception("GOTO exceed the lineNumber");
                }
                Statement statement = currentCommand.get(this.currentLineNumber).getStatement();
                if (statement.getStatementID().equals("GOTO") || statement.getStatementID().equals("IF")) {
                    this.running = true;
                }
                variableList = statement.executeRun(variableList);
                if (variableList == null)
                {
                    throw new Exception("Invalid Input.");
                }
                this.previousLineNumber = this.currentLineNumber;
                this.currentLineNumber = statement.getCurrentLine();
                if (statement.getStatementID().equals("GOTO"))
                {
                    if (this.currentLineNumber.equals(this.previousLineNumber))
                    {
                        throw new Exception("GOTO cannot go to itself");
                    }
                }
                if (statement.getStatementID().equals("PRINT")) {
                    writeResults(statement.getResult().toString());
                }
            }
        }
        catch (Exception e)
        {
            this.results.clear();
            writeResults("Line " + (this.currentLineNumber + 1) + " ERROR: " + e.getMessage());
        }
    }

    public void writeResults(String result)
    {
        this.results.add(result);
    }

    public ArrayList<String> getResults()
    {
        return this.results;
    }
}
