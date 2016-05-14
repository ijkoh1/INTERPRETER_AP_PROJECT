package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class Command {
    private int lineNumber;
    private Statement statement;

    /*
    * Author: Ivan
    * purpose: Initialize the Statement
    * params: statement = A Statement class to store statement and its parameters
    * pre_conditions: None
    * post-conditions: Command Class now has a statement
    * */
    public Command(Statement statement) {
        this.statement = statement;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the statement
    * params: None
    * pre_conditions: None
    * post-conditions: returns Statement object
    * */
    public Statement getStatement()
    {
        return this.statement;
    }
}
