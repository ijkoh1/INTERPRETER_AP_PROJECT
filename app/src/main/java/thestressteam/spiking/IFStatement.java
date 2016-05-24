package thestressteam.spiking;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ivan on 5/9/2016.
 * purpose: IFStatement class is in charge of storing its parameters and executing the IF statement
 */
public class IFStatement extends Statement{
    //An integer to store the line number of the statement
    private Integer currentLine;
    //A string to store the name of the statement
    private String statementID;
    //An Expression object to store the parameters of the expression
    private Expression expression;
    //A GOTOStatement object to store the parameters and methods of the gotoStatement
    private GOTOStatement gotoStatement;
    //An integer to store the result from the expression
    private Integer result;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a IFStatement
    * params: lineNumber = A integer containing the current line number of the command
    *         expression = A expression object containing 2 variables/integer and 1 operator
    *         gotoStatement = A GOTOStatement object containing a integer to go to
    * pre_conditions: None
    * post-conditions: Variables are initialized in IFStatement object
    * exception handling: None
    * */
    public IFStatement(Integer lineNumber, Expression expression, GOTOStatement gotoStatement)
    {
        this.currentLine = lineNumber;
        this.expression = expression;
        this.gotoStatement = gotoStatement;
        this.statementID = "IF";
        this.result = null;
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
    * purpose: Runs the IFStatement object and returns the current state of declaredVariableList
    * params: dvl = A dictionary containing the current variables and values
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of variables
    * exception handling: None
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
        else
        {
            this.gotoStatement.executeRun(dvl);
        }
        return dvl;
    }

    @Override
    public ArrayList<String> getDetails() {
        ArrayList<String> details = new ArrayList<String>();
        details.add(this.currentLine.toString());
        details.add(this.statementID);
        details.add(this.expression.getLeftSide());
        details.add(this.expression.getOperator());
        details.add(this.expression.getRightSide());
        details.add(this.gotoStatement.getJumpToLine().toString());
        return details;
    }

    /*
    * Author: Ivan
    * purpose: Increments and returns the currentLine of the statement
    * params: currentLineNumber = A currentLine number it is pointing at
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of currentLine
    * exception handling: None
    * */
//    @Override
//    public Integer nextLine() {
//        Integer nextLineNumber = this.currentLine + 1;
//        if (this.result != 0)
//        {
//            nextLineNumber = this.gotoStatement.nextLine();
//        }
//        return nextLineNumber;
//    }

    /*
    * Author: Ivan
    * purpose: Retrieves the currentLine
    * params: None
    * pre_conditions: A statement must exist
    * post-conditions: Returns the currentLine
    * exception handling: None
    * */
    @Override
    public Integer getCurrentLine() {
        return this.currentLine;
    }

    @Override
    public Integer getJumpToLine() {
        return this.gotoStatement.getJumpToLine();
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the result of the statement
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a integer of the statement result from the statement
    * exception handling: None
    * */
    @Override
    public Integer getResult() {
        return this.result;
    }
}
