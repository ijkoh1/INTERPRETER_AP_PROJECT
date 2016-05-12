package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class PRINTStatement extends Statement {
    private String value;
    private Integer result;
    private Integer currentLine;
    private String statementID;

    public PRINTStatement(Integer lineNumber, String value)
    {
        this.value = value;
        this.currentLine = lineNumber;
        this.statementID = "PRINT";
        this.result = null;
    }

    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl)
    {
        this.result = dvl.getValue(this.value);
        this.currentLine = nextLine(this.currentLine);
        return dvl;
    }

    @Override
    public Integer getResult() {
        return this.result;
    }

    @Override
    public String getStatementID() {
        return this.statementID;
    }

    @Override
    public Integer nextLine(Integer currentLineNumber)
    {
        return super.nextLine(currentLineNumber);
    }

    @Override
    public Integer getCurrentLine()
    {
        return currentLine;
    }
}
