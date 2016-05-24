package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/9/2016.
 */
public class PRINTStatement extends Statement {
    //An integer to store the variable/integer the user wants to print
    private String value;
    //An integer to store the current line number for the statment
    private Integer currentLine;
    //A string to store the name of the statement
    private String statementID;
    //An integer to store the result of the variable/integer to print
    private Integer result;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a PRINTStatement
    * params: lineNumber = A integer containing the current line number of the command
    *         value = A string containing a value/variable to be printed out
    * pre_conditions: None
    * post-conditions: Variables are initialized in PRINTStatement object
    * */
    public PRINTStatement(Integer lineNumber, String value)
    {
        this.value = value;
        this.currentLine = lineNumber;
        this.statementID = "PRINT";
        this.result = null;
    }

    /*
    * Author: Ivan
    * purpose: Runs the PRINTStatement object and returns the current state of declaredVariableList
    * params: dvl = A dictionary containing the current variables and values
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of variables
    * */
    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl)
    {
        this.result = dvl.getValue(this.value);
        return dvl;
    }

    @Override
    public ArrayList<String> getDetails() {
        ArrayList<String> details = new ArrayList<String>();
        details.add(this.currentLine.toString());
        details.add(this.statementID);
        details.add(this.value);
        return details;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the result of the statement
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a integer of the statement result from the statement
    * */
    @Override
    public Integer getResult() {
        return this.result;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the name of the statement
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a string of the statement name
    * */
    @Override
    public String getStatementID() {
        return this.statementID;
    }

    /*
    * Author: Ivan
    * purpose: Increments and returns the currentLine of the statement
    * params: currentLineNumber = A currentLine number it is pointing at
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of currentLine
    * */
//    @Override
//    public Integer nextLine()
//    {
//        Integer nextLineNumber = this.currentLine + 1;
//        return nextLineNumber;
//    }

    /*
    * Author: Ivan
    * purpose: Retrieves the currentLine
    * params: None
    * pre_conditions: A statement must exist
    * post-conditions: Returns the currentLine
    * */
    @Override
    public Integer getCurrentLine()
    {
        return currentLine;
    }

    @Override
    public Integer getJumpToLine() {
        return null;
    }
}
