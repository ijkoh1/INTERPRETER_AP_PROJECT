package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class Command {
    private int lineNumber;
    private Statement statement;

    public Command(Statement statement) {
        this.statement = statement;
    }

    public Statement getStatement()
    {
        return this.statement;
    }
}
