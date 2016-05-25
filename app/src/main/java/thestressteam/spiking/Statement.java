package thestressteam.spiking;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ivan on 5/9/2016.
 * Purpose: This statement class is an abstract so that GOTO,IF,LET,and PRINT can inherit the methods
 */

public abstract class Statement {
    /**
    * Author: Ivan
    * purpose: An abstract method to get the name of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns name of the statement
    * exception handling: None
    */
    public abstract String getStatementID();

    /**
    * Author: Ivan
    * purpose: An abstract method to get the result of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns result of the statement
    * exception handling: None
    */
    public abstract Integer getResult();

    /**
    * Author: Ivan
    * purpose: An abstract method to get the currentLine of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns the currentLine
    * exception handling: None
    */
    public abstract Integer getCurrentLine();

    /**
    * Author: Ivan
    * purpose: An abstract method which grabs the jumpToNextLine
    * params: None
    * pre_conditions: None
    * post-conditions: Returns the jumpToLineNumber
    * exception handling: None
    */
    public abstract Integer getJumpToLine();

    /**
    * Author: Ivan
    * purpose: An abstract method to run of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns current state of the variables
    * exception handling: None
    */
    public abstract DeclaredVariableList executeRun(DeclaredVariableList dvl);

    /**
     * Author: Ivan
     * purpose: An abstract method to retrieve all the parameters from a statement so that we can save and load the parameters of a statement
     * pre-conditions: A statement must exists
     * post-conditions: Returns a string list containing the parameters of a statement
     * @return {ArrayList<String} details of a statement
     */
    public abstract ArrayList<String> getDetails();

    /**
     * Author: Ivan
     * purpose: An abstract method to set a matchID for a gosub and return
     * pre-conditions: A statement must exists
     * post-conditions: A integer, matchID, is set into the statement
     * @param {Integer} matchID
     */
    public abstract void setMatchID(Integer matchID);
}
