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

    public boolean searchVariableDeclared(String variableName)
    {
        if (variableDict.containsKey(variableName)){
            return true;
        }
        return false;
    }

    public void clearHash()
    {
        variableDict.clear();
    }

    public void declareVariable(String variableName)
    {
        if (!variableDict.containsKey(variableName))
        {
            variableDict.put(variableName, null);
        }
    }

    public void removeVariable(String variableName)
    {
        if (variableDict.containsKey(variableName))
        {
            variableDict.remove(variableName);
        }
    }

    public void assgnVariable(String variableName, int value)
    {
        if (variableDict.containsKey(variableName))
        {
            variableDict.put(variableName, value);
        }
    }

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
