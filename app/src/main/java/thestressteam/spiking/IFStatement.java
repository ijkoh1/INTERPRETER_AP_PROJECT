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

    public IFStatement(Integer lineNumber, Expression expression, GOTOStatement gotoStatement)
    {
        this.currentLine = lineNumber;
        this.expression = expression;
        this.gotoStatement = gotoStatement;
        this.statementID = "IF";
    }

    @Override
    public String getStatementID() {
        return this.statementID;
    }

    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl) {
        String rightSide = dvl.getValue((expression.getRightSide())).toString();
        String leftSide = dvl.getValue((expression.getLeftSide())).toString();
        this.result = expression.getResult(leftSide,rightSide);
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

    @Override
    public Integer nextLine(Integer currentLineNumber) {
        return super.nextLine(currentLineNumber);
    }

    @Override
    public Integer getCurrentLine() {
        return this.currentLine;
    }

    @Override
    public Integer getResult() {
        return null;
    }
}
