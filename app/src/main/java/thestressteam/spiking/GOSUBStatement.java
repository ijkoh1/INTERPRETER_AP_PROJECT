package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/24/2016.
 * purpose: GOSUBStatement class is in charge of storing its parameters and executing the GOSUB statement
 */
public class GOSUBStatement extends Statement{
    //An integer to store the current line number of the statement
    private Integer currentLine;
    //A string to store the name of the statement
    private String statementID;
    //An integer to store the match number to match with the return
    private Integer matchID;
    //An integer to store the lineNumber to jump
    private Integer jumpToLine;

    /**
     * Author: Ivan
     * purpose: Initialize variables according to the parameters of a GOSUBStatement
     * params: lineNumber = A integer containing the current line number of the command
     * pre_conditions: None
     * post-conditions: Variables are initialized in GOSUBStatement object
     */
    public GOSUBStatement(Integer lineNumber, Integer jumpToLine)
    {
        this.currentLine = lineNumber;
        this.statementID = "GOSUB";
        this.jumpToLine = jumpToLine;
        this.matchID = null;
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
     * purpose: Returns the matching ID to identify with the correct return
     * params: None
     * pre_conditions: None
     * post-conditions: Returns a integer matchID
     */
    @Override
    public Integer getResult() {
        return matchID;
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
     * purpose: Retrieves the lineNumber to jumpTo
     * params: None
     * pre_conditions: A statement must exists
     * post-conditions: Returns a lineNumber to jump
     * exception handling: None
     */
    @Override
    public Integer getJumpToLine() {
        return this.jumpToLine;
    }

    /**
    * Author: Ivan
    * purpose: Runs the GOSUBStatement object and returns the current state of declaredVariableList
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
        details.add(this.jumpToLine.toString());
        return details;
    }

    /**
     * Author: Ivan
     * purpose: Sets the matchID number
     * params: None
     * pre_conditions: None
     * post-conditions: matchID number is assigned
     * exception handling: None
     */
    @Override
    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }
}
