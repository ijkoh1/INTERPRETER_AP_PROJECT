package thestressteam.spiking;

import java.util.HashMap;

/**
 * Created by Ivan on 5/11/2016.
 * Purpose: DeclaredVariableList is in charge of storing the variables that are declared.
 *          This class helps remove,add,retrieve variables from the HashMap of variableDict
 */
public class DeclaredVariableList {
    // A dictionary to store the values and names of the variable.
    // Each variable names are unique and must have a value
    private HashMap<String,Integer> variableDict;

    /*
    * Author: Ivan
    * purpose: Initialize the dictionary,variableDict, to empty
    * params: None
    * pre_conditions: None
    * post-conditions: An empty dictionary is created
    * exceptions handling: None
    * */
    public DeclaredVariableList()
    {
        variableDict = new HashMap<String,Integer>();
    }

    /*
    * Author: Ivan
    * purpose: Searches for the variableName weather it exists in the dictionary
    * params: variableName = A string of the variable to be found
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: Returns a boolean of whether it is found
    * exceptions handling: None
    * */
    public boolean searchVariableDeclared(String variableName)
    {
        if (variableDict.containsKey(variableName)){
            return true;
        }
        return false;
    }

    public HashMap<String,Integer> getHash(){
        return this.variableDict;
    }
    /*
    * Author: Ivan
    * purpose: Clears all elements in the dictionary
    * params: None
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: Removes all elements in variableDict
    * exceptions handling: None
    * */
    public void clearHash()
    {
        variableDict.clear();
    }

    /*
    * Author: Ivan
    * purpose: Declares a variable by adding a variableName as the key and null value in variableDict
    * params: variableName = A string of the variable name
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: variable is added into the variableDict
    * exceptions handling: None
    * */
    public void declareVariable(String variableName)
    {
        if (!variableDict.containsKey(variableName))
        {
            variableDict.put(variableName, null);
        }
    }

    /*
    * Author: Ivan
    * purpose: Removes a specific element from variableDict
    * params: variableName = A string of the variable
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: The variable is deleted from variableDict
    * exceptions handling: None
    * */
    public void removeVariable(String variableName)
    {
        if (variableDict.containsKey(variableName))
        {
            variableDict.remove(variableName);
        }
    }

    /*
    * Author: Ivan
    * purpose: Assigns a value into a declared variable
    * params: variableName = A string of the variable
    *         value = A integer to store the value of variable
    * pre_conditions: A dictionary of variables, variableDict, and the variable name must exists in the dictionary
    * post-conditions: The value of the variable is edited
    * exceptions handling: None
    * */
    public void assgnVariable(String variableName, int value)
    {
        if (variableDict.containsKey(variableName))
        {
            variableDict.put(variableName, value);
        }
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the value from variableDict if it exits, else returns an integer
    * params: variableName = A string of the variable
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: Returns a value of the variable if it exits, else returns null
    * exceptions handling: None
    * */
    public Integer getValue(String variableName)
    {
        Integer val;
        try {
            val = Integer.parseInt(variableName);
        } catch(NumberFormatException e)
        {
            if (variableDict.containsKey(variableName))
            {
                val = variableDict.get(variableName);
            }
            else
            {
                val = null;
            }
        }
        return val;
    }
}
