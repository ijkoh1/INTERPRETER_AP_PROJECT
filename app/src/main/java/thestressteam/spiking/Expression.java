package thestressteam.spiking;

/**
 * Created by Ivan on 5/10/2016.
 */
public class Expression {
    private String leftSide;
    private String rightSide;
    private String operator;
    private Integer result;
    private Variable leftVariable;
    private Variable rightVariable;

    public Expression(String leftSide, String operator, String rightSide)
    {
        this.leftSide = leftSide;
        this.operator = operator;
        this.rightSide = rightSide;
        this.result = null;
    }

    public Integer operation(String op, String num1, String num2){
        Integer num3 = 0;
        Integer num1Value =Integer.parseInt(num1);
        Integer num2Value = Integer.parseInt(num2);
        if (op.equals("+")){
            num3 = num1Value+num2Value;
        }
        else if(op.equals("-")){
            num3 = num1Value-num2Value;
        }
        else if (op.equals("==")){
            if (num1Value == num2Value){
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        else if(op.equals(">")) {
            if (num1Value > num2Value) {
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        return num3;
    }

    public String getLeftSide()
    {
        return this.leftSide;
    }

    public String getRightSide()
    {
        return this.rightSide;
    }

    public Integer getResult(String leftSide,String rightSide)
    {
        this.leftSide = leftSide;
        this.rightSide = rightSide;
        this.result = operation(this.operator, this.leftSide, this.rightSide);
        return this.result;
    }
}
