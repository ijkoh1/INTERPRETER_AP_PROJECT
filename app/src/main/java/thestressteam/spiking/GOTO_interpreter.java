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

    //An array list of strings to store the results from executing command lines
    private ArrayList<String> results;

    //An arrayList to store the list of commands the user had input
    private ArrayList<Command> currentCommand;

    //A DeclaredVariableList object to use the methods from the object such as declaring, assigning a variable
    private DeclaredVariableList variablesDeclared;

    //A boolean to indicate whether the command should keep on running and iterating to the next command
    private Boolean running;

    /**
    * Author: Ivan
    * purpose: Initialize the variablesDeclared, currentLineNumber and results
    * params: commandLine = An array command added in the instruction view
    * pre_conditions: An array of commands, commandLine
    * post-conditions: variablesDeclared, currentLineNumber and results are initialized
    * exception handling: None
    */
    public GOTO_interpreter(CommandLine commandLine)
    {
        this.currentLineNumber = 0;
        this.results = new ArrayList<String>();
        this.variablesDeclared = new DeclaredVariableList();
        this.currentCommand = commandLine.getCommandLines();
        this.running = true;
    }

    /**
     * Author: Ivan
     * purpose: Sorts the commandLine into from 1st to last line to run
     * params: None
     * pre_conditions: An array of commands, commandLine
     * post-conditions: the commandLine are sorted
     * exception handling: None
     */
    public void sortLine()
    {
        if (currentCommand.size() < 2)
        {
            return;
        }
        Integer num1 = currentCommand.get(0).getStatement().getCurrentLine();
        Integer num2 = currentCommand.get(1).getStatement().getCurrentLine();
        if (num1 > num2)
        {
            Command tmp = currentCommand.get(0);
            currentCommand.set(0,currentCommand.get(1));
            currentCommand.set(1,tmp);
        }
        for (int mark = 2; mark < currentCommand.size(); mark++)
        {
            for (int k = 0; k < mark; k++)
            {
                num1 = currentCommand.get(k).getStatement().getCurrentLine();
                num2 = currentCommand.get(mark).getStatement().getCurrentLine();
                if (num1 > num2)
                {
                    Command tmp = currentCommand.get(k);
                    currentCommand.set(k,currentCommand.get(mark));
                    currentCommand.set(mark,tmp);
                }
            }
        }
    }

    /**
     * Author: Ivan
     * purpose: Searches for the lineNumber of the command and returns the index of the commandList
     * params: None
     * pre_conditions: An array of commands, commandLine
     * post-conditions: An index is returned if found, else returns a null
     * exception handling: None
     */
    public Integer searchForLineNumber(Integer lineNumberToBeFound)
    {
        Integer indexNotFound = null;
        for (int i = 0; i < currentCommand.size(); i++)
        {
            Integer lineNumber = currentCommand.get(i).getStatement().getCurrentLine();
            if (lineNumberToBeFound.equals(lineNumber))
            {
                return i;
            }
        }
        return indexNotFound;
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
        //Sorts the commandList
        sortLine();
        //A counter to detect the number of run loops
        Integer count = 0;
        //A index to point to the commandList
        Integer index = 0;
        //A previous index to detect a jump to itself
        Integer prev_index;
        //An array to store the gosub statement so that we know which gosub to return to
        ArrayList<Statement> gosubStackArray = new ArrayList<>();
        //An array to store the index of the gosub so that we can know which line the gosub to return
        ArrayList<Integer> matchingIDList = new ArrayList<>();
        try {
            while (this.running) {
                //detects too many loops
                if (count > 100)
                {
                    throw new Exception("Runned too much");
                }

                Statement statement = currentCommand.get(index).getStatement();
                System.out.println(statement.getStatementID());
                this.currentLineNumber = statement.getCurrentLine();
                if (statement.getStatementID().equals("GOTO") || statement.getStatementID().equals("IF")) {
                    this.running = true;
                }
                variablesDeclared = statement.executeRun(variablesDeclared);
                //This happens only when user entered an undeclared variable
                if (variablesDeclared == null)
                {
                    throw new Exception("Invalid Input.");
                }
                prev_index = index;
                if (statement.getStatementID().equals("GOTO"))
                {
                    index = searchForLineNumber(statement.getJumpToLine());
                    if (index == null)
                    {
                        throw new Exception("GOTO jumped to an non_existent lineNumber");
                    }
                    if (index.equals(prev_index))
                    {
                        throw new Exception("GOTO cannot go to itself");
                    }
                }

                else if (statement.getStatementID().equals("IF"))
                {
                    if (statement.getResult() != 0)
                    {
                        index = searchForLineNumber(statement.getJumpToLine());
                        if (index.equals(prev_index))
                        {
                            throw new Exception("GOTO cannot go to itself");
                        }
                    }
                    else
                    {
                        index += 1;
                    }
                    if (index == null)
                    {
                        throw new Exception("GOTO jumped to an non_existent lineNumber");
                    }
                }

                else if (statement.getStatementID().equals("GOSUB"))
                {
                    //This happends when it is a new gosub we found during runtime
                    if (statement.getResult() == null)
                    {
                        //A matchingID(the current index of commandLine) is added
                        statement.setMatchID(index);
                        matchingIDList.add(0,index);
                    }
                    index = searchForLineNumber(statement.getJumpToLine());
                    if (index == null)
                    {
                        throw new Exception("GOSUB jumped to an non_existent lineNumber");
                    }
                    if (index.equals(prev_index))
                    {
                        throw new Exception("GOSUB cannot go to itself");
                    }
                    //Adds a gosub into array so that we can know which current gosub we must return to
                    gosubStackArray.add(0,statement);
                }

                else if (statement.getStatementID().equals("RETURN"))
                {
                    //Checks if the return statement has a matchingID
                    if (statement.getResult() == null)
                    {
                        //This happends when there is a return before a gosub
                        if (matchingIDList.size() == 0 || gosubStackArray.size() == 0)
                        {
                            throw new Exception("There is no corresponding gosub to return to.");
                        }
                        //matchingID is assigned and removed for the list so that we know we have already assigned this returned object an matchingID
                        statement.setMatchID(matchingIDList.get(0));
                        matchingIDList.remove(0);
                        index = searchForLineNumber(gosubStackArray.get(0).getCurrentLine()) + 1;
                        gosubStackArray.remove(0);
                    }
                    else
                    {
                        //The index to return to
                        index = statement.getResult();
                        index += 1;
                    }
                }
                // IF it is not a IF,GOTO,RETURN or GOSUB statement, we increment the index by 1 to read the next line
                else
                {
                    index += 1;
                }

                if (statement.getStatementID().equals("PRINT")) {
                    //Writes the results to show to the console
                    writeResults(statement.getResult().toString());
                }

                //This happens when we have reached and finished running the code
                if (index.equals(currentCommand.size())) {
                    this.running = false;
                }
                count++;
            }
        }
        catch (Exception e)
        {
            results.clear();
            writeResults("Stopped at Line " + (this.currentLineNumber) + " ERROR: " + e.getMessage());
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
