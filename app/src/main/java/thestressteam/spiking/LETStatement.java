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

    public LETStatement(Integer lineNumber, Variable variable, Expression expression)
    {
        this.currentLine = lineNumber;
        this.variable = variable;
        this.expression = expression;
        this.statementID = "LET";
    }

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
            dvl.assgnVariable(variable.getVariableName(),value);
            this.currentLine = nextLine(this.currentLine);
        }
        catch (Exception e)
        {
            return null;
        }
        return dvl;
    }

    @Override
    public String getStatementID() {
        return this.statementID;
    }

    @Override
    public Integer getResult() {
        return this.rightSide;
    }

    @Override
    public Integer nextLine(Integer currentLineNumber) {
        return super.nextLine(currentLineNumber);
    }

    @Override
    public Integer getCurrentLine() {
        return this.currentLine;
    }
}
