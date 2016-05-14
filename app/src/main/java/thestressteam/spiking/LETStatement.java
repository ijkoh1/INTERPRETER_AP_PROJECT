package thestressteam.spiking;

/**
 * Created by Ivan on 5/10/2016.
 */
public class LETStatement extends Statement {
    private Variable variable;
    private Expression expression;
    private Integer currentLine;
    private String statementID;
    private Integer rightSide;
    private Integer leftSide;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a LETStatement
    * params: lineNumber = A integer containing the current line number of the command
    *         variable = A variable object containing the value and name of a variable
    *         expression = A expression object containing 2 variables/integer and 1 operator
    * pre_conditions: None
    * post-conditions: Variables are initialized in LETStatement object
    * */
    public LETStatement(Integer lineNumber, Variable variable, Expression expression)
    {
        this.currentLine = lineNumber;
        this.variable = variable;
        this.expression = expression;
        this.statementID = "LET";
    }

    /*
    * Author: Ivan
    * purpose: Runs the LETStatement object and returns the current state of declaredVariableList
    * params: dvl = A dictionary containing the current variables and values
    * pre_conditions: A statement must exist
    * post-conditions: Returns the edited state of variables
    * */
    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl)
    {
        Integer value;
        dvl.declareVariable(variable.getVariableName());
        rightSide = dvl.getValue((expression.getRightSide()));
        leftSide = dvl.getValue((expression.getLeftSide()));
        try
        {
            if (rightSide == null || leftSide == null){
                throw new Exception("variable not declared");
            }
            value = this.expression.getResult(leftSide.toString(),rightSide.toString());
            if (value == null)
            {
                throw new Exception("Invalid operation given");
            }
            dvl.assgnVariable(variable.getVariableName(),value);
            this.currentLine = nextLine(this.currentLine);
        }
        catch (Exception e)
        {
            return null;
        }
        return dvl;
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
    * pre_conditions: None
    * post-conditions: Returns a integer of the statement result from the statement
    * */
    @Override
    public Integer getResult() {
        return null;
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
}
