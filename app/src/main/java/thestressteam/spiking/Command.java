package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 * Purpose: Command class is in charge of storing a statement object. This means each time command class is created there is a statement class inside.
 */
public class Command {
    //A Statement Object to store the lineNumber and statement of a command
    private Statement statement;

    /*
    * Author: Ivan
    * purpose: Initialize the Statement
    * params: statement = A Statement class to store statement and its parameters
    * pre_conditions: None
    * post-conditions: Command Class now has a statement
    * exceptions handling: None
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
    * exceptions handling: None
    * */
    public Statement getStatement()
    {
        return this.statement;
    }
}
