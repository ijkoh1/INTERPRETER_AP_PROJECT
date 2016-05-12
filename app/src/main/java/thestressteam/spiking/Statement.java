package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */

public abstract class Statement {
    public Integer nextLine(Integer currentLineNumber)
    {
        ++currentLineNumber;
        return currentLineNumber;
    }

    public abstract String getStatementID();
    public abstract Integer getResult();
    public abstract Integer getCurrentLine();
    public abstract DeclaredVariableList executeRun(DeclaredVariableList dvl);
}
