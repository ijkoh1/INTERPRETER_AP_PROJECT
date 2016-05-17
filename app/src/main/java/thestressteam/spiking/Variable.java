package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 * Purpose: Variable class is in charge of storing the variables
 */
public class Variable {
    //A string to store the name of the variable
    private String name;
    //An integer to store the value of the variable
    private Integer value;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of a Variable
    * params: variableName = A string containing the name of the variable
    * pre_conditions: None
    * post-conditions: Variables are initialized in Variable object
    * */
    public Variable(String variableName)
    {
        try{
            if (Character.isDigit(variableName.charAt(0)))
            {
                throw new Exception("Invalid variableName");
            }
            this.name = variableName;
        }
        catch (Exception e)
        {
            this.name = null;
        }
        this.value = null;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the name of the variable
    * params: None
    * pre_conditions: A variable must exist
    * post-conditions: Returns a variable name
    * */
    public String getVariableName()
    {
        return this.name;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the result of the variable
    * params: None
    * pre_conditions: A variable must exist
    * post-conditions: Returns value of a variable
    * */
    public Integer getValue() { return this.value; }

    /*
    * Author: Ivan
    * purpose: Edits the value of the variable
    * params: None
    * pre_conditions: A variable must exist
    * post-conditions: Value of the variable is edited
    * */
    public void setValue(Integer value) { this.value = value; }
}
