package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/24/2016.
 */
public class RETURNStatement extends Statement {
    private Integer currentLine;
    private String statementID;
    private boolean isLooped;
    private Integer runOnce;

    public RETURNStatement(Integer lineNumber)
    {
        this.currentLine = lineNumber;
        this.statementID = "RETURN";
        this.isLooped = false;
        this.runOnce = 1;
    }

    @Override
    public String getStatementID() {
        return this.statementID;
    }

    @Override
    public Integer getResult() {
        if (this.isLooped)
        {
            return this.runOnce - 1;
        }
        return this.runOnce;
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
        return details;
    }

    @Override
    public void setLooped(boolean isLooped) {
        this.isLooped = isLooped;
    }
}
