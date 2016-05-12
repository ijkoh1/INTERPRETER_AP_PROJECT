package thestressteam.spiking;

/**
 * Created by Ivan on 5/9/2016.
 */
public class Variable {
    private String name;
    private Integer value;
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

    public String getVariableName()
    {
        return this.name;
    }
    public Integer getValue() { return this.value; }
    public void setValue(Integer value) { this.value = value; }
}
