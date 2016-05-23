package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/9/2016.
 * purpose: GOTOStatement class is in charge of storing its parameters and executing the goto statement
 */
public class GOTOStatement extends Statement{
    //An integer to store the line to jump
    private Integer jumpToLine;
    //An integer to store the current line number for the statment
    private Integer currentLine;
    //A string to store the name of the statement
    private String statementID;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a GOTOStatement
    * params: lineNumber = A integer containing the current line number of the command
    *         jumpToLine = A integer containing the line to jump
    * pre_conditions: None
    * post-conditions: Variables are initialized in GOTOStatement object
    * exception handling: None
    * */
    public GOTOStatement(Integer lineNumber, Integer jumpToLine)
    {
        this.currentLine = lineNumber;
        this.jumpToLine = jumpToLine;
        this.statementID = "GOTO";
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the name of the statement
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a string of the statement name
    * exception handling: None
    * */
    @Override
    public String getStatementID() {
        return this.statementID;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the result of the statement
    * params: None
    * pre_conditions: A result must exist
    * post-conditions: Returns a integer of the statement result from the statement
    * exception handling: None
    * */
    @Override
    public Integer getResult() {
        return null;
    }

    /*
    * Author: Ivan
    * purpose: Runs the GOTOStatement object and returns the current state of declaredVariableList
    * params: dvl = A dictionary containing the current variables and values
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of variables
    * exception handling: None
    * */
    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl)
    {
        return dvl;
    }

    @Override
    public ArrayList<String> getDetails() {
        ArrayList<String> details = new ArrayList<String>();
        details.add(this.currentLine.toString());
        details.add(this.getStatementID());
        details.add(this.jumpToLine.toString());
        return null;
    }

    /*
    * Author: Ivan
    * purpose: Increments and returns the currentLine of the statement
    * params: jumpToLine = A linenumber to go to
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of currentLine
    * exception handling: None
    * */
    @Override
    public Integer nextLine()
    {
        Integer nextLineNumber = this.jumpToLine-1;
        return nextLineNumber;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the currentLine
    * params: None
    * pre_conditions: A statement must exist
    * post-conditions: Returns the currentLine
    * exception handling: None
    * */
    @Override
    public Integer getCurrentLine()
    {
        return this.currentLine;
    }

    public String getJumpToLine()
    {
        return this.jumpToLine.toString();
    }
}
