package thestressteam.spiking;

import java.util.HashMap;

/**
 * Created by Ivan on 5/11/2016.
 */
public class DeclaredVariableList {
    private HashMap<String,Integer> variableDict;
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
    * */
    public boolean searchVariableDeclared(String variableName)
    {
        if (variableDict.containsKey(variableName)){
            return true;
        }
        return false;
    }

    /*
    * Author: Ivan
    * purpose: Clears all elements in the dictionary
    * params: None
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: Removes all elements in variableDict
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
    * pre_conditions: A dictionary of variables, variableDict
    * post-conditions: The value of the variable is edited
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
