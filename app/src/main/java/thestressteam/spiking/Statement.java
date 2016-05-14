package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */

public abstract class Statement {
    /*
    * Author: Ivan
    * purpose: Iterates to the next line by incrementing the currentLineNumber
    * params: currentLineNumber = A integer containing the current line number of the command
    * pre_conditions: None
    * post-conditions: Returns the currentLineNumber
    * */
    public Integer nextLine(Integer currentLineNumber)
    {
        ++currentLineNumber;
        return currentLineNumber;
    }

    /*
    * Author: Ivan
    * purpose: An abstract method to get the name of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns name of the statement
    * */
    public abstract String getStatementID();

    /*
    * Author: Ivan
    * purpose: An abstract method to get the result of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns result of the statement
    * */
    public abstract Integer getResult();

    /*
    * Author: Ivan
    * purpose: An abstract method to get the currentLine of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns the currentLine
    * */
    public abstract Integer getCurrentLine();

    /*
    * Author: Ivan
    * purpose: An abstract method to run of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns current state of the variables
    * */
    public abstract DeclaredVariableList executeRun(DeclaredVariableList dvl);
}
