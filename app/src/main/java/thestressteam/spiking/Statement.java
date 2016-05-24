package thestressteam.spiking;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Ivan on 5/9/2016.
 * Purpose: This statement class is an abstract so that GOTO,IF,LET,and PRINT can inherit the methods
 */

public abstract class Statement {
    /*
    * Author: Ivan
    * purpose: An abstract method to get the name of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns name of the statement
    * exception handling: None
    * */
    public abstract String getStatementID();

    /*
    * Author: Ivan
    * purpose: An abstract method to get the result of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns result of the statement
    * exception handling: None
    * */
    public abstract Integer getResult();

    /*
    * Author: Ivan
    * purpose: An abstract method to get the currentLine of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns the currentLine
    * exception handling: None
    * */
    public abstract Integer getCurrentLine();

    /*
    * Author: Ivan
    * purpose: An abstract method which grabs the jumpToNextLine
    * params: None
    * pre_conditions: None
    * post-conditions: Returns the jumpToLineNumber
    * exception handling: None
    * */
    public abstract Integer getJumpToLine();

    /*
    * Author: Ivan
    * purpose: An abstract method to run of the statement
    * params: None
    * pre_conditions: A statement must exists
    * post-conditions: Returns current state of the variables
    * exception handling: None
    * */
    public abstract DeclaredVariableList executeRun(DeclaredVariableList dvl);

    public abstract ArrayList<String> getDetails();

    public abstract void setLooped(boolean isLooped);
}
