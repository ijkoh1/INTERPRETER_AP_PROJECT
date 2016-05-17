package thestressteam.spiking;


import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Ivan on 4/26/2016.
 * purpose: GOTO_interpreter is in charge of executing and retreiving the results from each command line
 */

public class GOTO_interpreter{
    //An integer to store the current line number, to indicate which statement we are reading
    private Integer currentLineNumber;
    //An integer to store the previous line number to catch if the goto jumps to itself
    private Integer previousLineNumber;
    //An array list of strings to store the results from executing command lines
    private ArrayList<String> results;
    //A CommandLine object to store the list of command objects
    private CommandLine commandLine;
    //A DeclaredVariableList object to use the methods from the object such as declaring, assigning a variable
    private DeclaredVariableList variablesDeclared;
    //A boolean to indicate whether the command should keep on running and iterating to the next command
    private Boolean running;

    /*
    * Author: Ivan
    * purpose: Initialize the variablesDeclared, currentLineNumber and results
    * params: commandLine = An array command added in the instruction view
    * pre_conditions: An array of commands, commandLine
    * post-conditions: variablesDeclared, currentLineNumber and results are initialized
    * exception handling: None
    * */
    public GOTO_interpreter(CommandLine commandLine)
    {
        this.currentLineNumber = 0;
        this.commandLine = commandLine;
        this.results = new ArrayList<String>();
        this.variablesDeclared = new DeclaredVariableList();
        this.running = true;
    }

    /*
    * Author: Ivan
    * purpose: Iterates according to the commands in commandLine and throws any invalid inputs
    * params: None
    * pre_conditions: An array of commands, commandLine
    * post-conditions: CommandLines are executed and results are stored
    * exception handling: case 1: if goto went to a lineNumber that didn't exists
    *                     case 2: if invalid operators are detected
    *                     case 3: if variables are not declared as input
    *                     case 4: if goto went to itself
    * */
    public void readAllCode()
    {
        ArrayList<Command> currentCommand = commandLine.getCommandLines();
        Integer count = 0;
        try {
            while (this.running) {
                if (count > 100)
                {
                    throw new Exception("Runned too much");
                }
                if (this.currentLineNumber > currentCommand.size()) {
                    throw new Exception("GOTO exceeded the lineNumber");
                }
                Statement statement = currentCommand.get(this.currentLineNumber).getStatement();
                if (statement.getStatementID().equals("GOTO") || statement.getStatementID().equals("IF")) {
                    this.running = true;
                }
//                writeResults(statement.getStatementID() + " lineNumber ");
                variablesDeclared = statement.executeRun(variablesDeclared);
                if (variablesDeclared == null)
                {
                    throw new Exception("Invalid Input.");
                }
                this.previousLineNumber = this.currentLineNumber;
                this.currentLineNumber = statement.nextLine();
//                writeResults("After execution " + this.currentLineNumber);
                if (statement.getStatementID().equals("GOTO"))
                {
                    if (this.currentLineNumber.equals(previousLineNumber))
                    {
                        throw new Exception("GOTO cannot go to itself");
                    }
                }
                if (statement.getStatementID().equals("PRINT")) {
                    writeResults(statement.getResult().toString());
                }
                if (this.currentLineNumber.equals(currentCommand.size())) {
                    this.running = false;
                }
                count++;
            }
        }
        catch (Exception e)
        {
            results.clear();
            writeResults("Stopped at Line " + (previousLineNumber+1) + " ERROR: " + e.getMessage());
        }
    }

    /*
    * Author: Ivan
    * purpose: Writes the results from the commandLine
    * params: result = A array of Strings to store the results
    * pre_conditions: A string of result
    * post-conditions: results are stored
    * exception handling: None
    * */
    public void writeResults(String result)
    {
        this.results.add(result);
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the result
    * params: None
    * pre_conditions: None
    * post-conditions: Returns the array results
    * exception handling: None
    * */
    public ArrayList<String> getResults()
    {
        return this.results;
    }
}
