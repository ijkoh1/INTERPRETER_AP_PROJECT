package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class GOTOStatement extends Statement{
    private Integer jumpToLine;
    private Integer currentLine;
    private String statementID;

    public GOTOStatement(Integer lineNumber, Integer jumpToLine)
    {
        this.currentLine = lineNumber;
        this.jumpToLine = jumpToLine;
        this.statementID = "GOTO";
    }

    @Override
    public String getStatementID() {
        return this.statementID;
    }

    @Override
    public Integer getResult() {
        return null;
    }

    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl)
    {
        this.currentLine = this.nextLine(this.jumpToLine-1);
        return dvl;
    }

    @Override
    public Integer nextLine(Integer jumpToLine)
    {
        return jumpToLine;
    }

    @Override
    public Integer getCurrentLine()
    {
        return this.currentLine;
    }
}
