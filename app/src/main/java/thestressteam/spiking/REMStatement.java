package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/24/2016.
 * purpose: REMStatement class is in charge of storing its parameters and executing the REM statement
 */
public class REMStatement extends Statement{
    //An integer to store the current line number of the statement
    private Integer currentLine;
    //A string to store the name of the statement
    private String statementID;
    //A String to store the comments
    private String comments;

    /**
     * Author: Ivan
     * purpose: Initialize variables according to the parameters of a REMStatement
     * params: lineNumber = A integer containing the current line number of the command
     *         comments = A string containing the comments the user typed
     * pre_conditions: None
     * post-conditions: Variables are initialized in REMStatement object
     */
    public REMStatement(Integer lineNumber, String comments)
    {
        this.currentLine = lineNumber;
        this.statementID = "REM";
        this.comments = comments;
    }

    /**
     * Author: Ivan
     * purpose: Retrieves the name of the statement
     * params: None
     * pre_conditions: None
     * post-conditions: Returns a string of the statement name
     */
    @Override
    public String getStatementID() {
        return this.statementID;
    }

    /**
     * Author: Ivan
     * purpose: None
     * params: None
     * pre_conditions: None
     * post-conditions: None
     * exception handling: None
     */
    @Override
    public Integer getResult() {
        return null;
    }

    /**
     * Author: Ivan
     * purpose: Retrieves the currentLine
     * params: None
     * pre_conditions: A statement must exist
     * post-conditions: Returns the currentLine
     */
    @Override
    public Integer getCurrentLine() {
        return this.currentLine;
    }

    /**
     * Author: Ivan
     * purpose: None
     * params: None
     * pre_conditions: None
     * post-conditions: None
     * exception handling: None
     */
    @Override
    public Integer getJumpToLine() {
        return null;
    }

    /**
     * Author: Ivan
     * purpose: Runs the REMStatement object and returns the current state of declaredVariableList
     * params: dvl = A dictionary containing the current variables and values
     * pre_conditions: A statement must exist
     * post-conditions: Returns the edited state of variables
     */
    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl) {
        return dvl;
    }

    /**
     * Author: Ivan
     * purpose: Retrieves and returns a string list to save
     * params: None
     * pre_conditions: A statement must exists
     * post-conditions: Returns a string list to save
     * exception handling: None
     */
    @Override
    public ArrayList<String> getDetails() {
        ArrayList<String> details = new ArrayList<String>();
        details.add(this.currentLine.toString());
        details.add(this.statementID);
        details.add(this.comments);
        return details;
    }

    /**
     * Author: Ivan
     * purpose: None
     * params: None
     * pre_conditions: None
     * post-conditions: None
     * exception handling: None
     */
    @Override
    public void setMatchID(Integer matchID) {
        return;
    }
}
