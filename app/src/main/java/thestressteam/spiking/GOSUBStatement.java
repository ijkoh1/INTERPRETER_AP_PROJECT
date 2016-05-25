package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/24/2016.
 */
public class GOSUBStatement extends Statement{
    private Integer currentLine;
    private String statementID;
    private Integer jumpToLine;
    private Integer matchID;

    public GOSUBStatement(Integer lineNumber, Integer jumpToLine)
    {
        this.currentLine = lineNumber;
        this.statementID = "GOSUB";
        this.jumpToLine = jumpToLine;
        this.matchID = null;
    }

    @Override
    public String getStatementID() {
        return this.statementID;
    }

    @Override
    public Integer getResult() {
        return matchID;
    }

    @Override
    public Integer getCurrentLine() {
        return this.currentLine;
    }

    @Override
    public Integer getJumpToLine() {
        return this.jumpToLine;
    }

    @Override
    public DeclaredVariableList executeRun(DeclaredVariableList dvl) {
        return dvl;
    }

    @Override
    public ArrayList<String> getDetails() {
        ArrayList<String> details = new ArrayList<String>();
        details.add(this.currentLine.toString());
        details.add(this.statementID);
        details.add(this.jumpToLine.toString());
        return details;
    }

    @Override
    public void setMatchID(Integer matchID) {
        this.matchID = matchID;
    }
}
