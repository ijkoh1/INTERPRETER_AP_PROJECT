package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class GOTOStatement extends Statement{
    private Integer jumpToLine;
    private Integer currentLine;
    private String statementID;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a GOTOStatement
    * params: lineNumber = A integer containing the current line number of the command
    *         jumpToLine = A integer containing the line to jump
    * pre_conditions: None
    * post-conditions: Variables are initialized in GOTOStatement object
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
    * */
    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl)
    {
        this.currentLine = this.nextLine(this.jumpToLine-1);
        return dvl;
    }

    /*
    * Author: Ivan
    * purpose: Increments and returns the currentLine of the statement
    * params: jumpToLine = A linenumber to go to
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of currentLine
    * */
    @Override
    public Integer nextLine(Integer jumpToLine)
    {
        return jumpToLine;
    }

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
        return this.currentLine;
    }
}
