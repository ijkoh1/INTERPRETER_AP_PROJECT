package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class IFStatement extends Statement{
    private Integer result;
    private Integer currentLine;
    private String statementID;
    private Expression expression;
    private GOTOStatement gotoStatement;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a IFStatement
    * params: lineNumber = A integer containing the current line number of the command
    *         expression = A expression object containing 2 variables/integer and 1 operator
    *         gotoStatement = A GOTOStatement object containing a integer to go to
    * pre_conditions: None
    * post-conditions: Variables are initialized in IFStatement object
    * */
    public IFStatement(Integer lineNumber, Expression expression, GOTOStatement gotoStatement)
    {
        this.currentLine = lineNumber;
        this.expression = expression;
        this.gotoStatement = gotoStatement;
        this.statementID = "IF";
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
    * purpose: Runs the IFStatement object and returns the current state of declaredVariableList
    * params: dvl = A dictionary containing the current variables and values
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of variables
    * */
    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl) {
        String rightSide = dvl.getValue((expression.getRightSide())).toString();
        String leftSide = dvl.getValue((expression.getLeftSide())).toString();
        this.result = expression.getResult(leftSide,rightSide);
        if (this.result == null)
        {
            return null;
        }
        if (this.result != 0)
        {
            this.gotoStatement.executeRun(dvl);
            this.currentLine = this.gotoStatement.getCurrentLine();
        }
        else
        {
            this.currentLine = nextLine(this.currentLine);
        }
        return dvl;
    }

    /*
    * Author: Ivan
    * purpose: Increments and returns the currentLine of the statement
    * params: currentLineNumber = A currentLine number it is pointing at
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of currentLine
    * */
    @Override
    public Integer nextLine(Integer currentLineNumber) {
        return super.nextLine(currentLineNumber);
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the currentLine
    * params: None
    * pre_conditions: A statement must exist
    * post-conditions: Returns the currentLine
    * */
    @Override
    public Integer getCurrentLine() {
        return this.currentLine;
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
        return null;
    }
}
