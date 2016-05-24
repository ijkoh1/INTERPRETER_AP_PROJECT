package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/24/2016.
 */
public class REMStatement extends Statement{
    private Integer currentLine;
    private String statementID;
    private String comments;

    public REMStatement(Integer lineNumber, String comments)
    {
        this.currentLine = lineNumber;
        this.statementID = "REM";
        this.comments = comments;
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
    public Integer getCurrentLine() {
        return this.currentLine;
    }

    @Override
    public Integer getJumpToLine() {
        return null;
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
        details.add(this.comments);
        return details;
    }

    @Override
    public void setLooped(boolean isLooped) {
        return;
    }
}
