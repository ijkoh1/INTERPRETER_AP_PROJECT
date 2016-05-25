package thestressteam.spiking;

/**
 * Created by Ivan on 5/10/2016.
 * Purpose: Expression is in charge of storing the parameters of expression and calculating and returns the result of the operation.
 */
public class Expression {
    // A string to store the variable/integer at the left side of the expression
    private String leftSide;
    // A string to store the variable/integer at the right side of the expression
    private String rightSide;
    //A string to store the operator
    private String operator;
    //An integer to store the result of the operation
    private Integer result;

    /*
    * Author: Ivan
    * purpose: Initialize variables according to the parameters of an expression
    * params: leftSize = A string of the variable/integer of the left side of the expression
    *         operator = A string of the operator
    *         rightSide = A string of the variable/integer of the right side of the expression
    * pre_conditions: None
    * post-conditions: Variables are initialized in expression object
    * exceptions handling: None
    * */
    public Expression(String leftSide, String operator, String rightSide)
    {
        this.leftSide = leftSide;
        this.operator = operator;
        this.rightSide = rightSide;
        this.result = null;
    }

    /*
    * Author: Ivan
    * purpose: Calculates the result after applying the operator from 2 values
    * params: num1 = A string of the variable/integer of the left side of the expression
    *         op = A string of the operator
    *         num2 = A string of the variable/integer of the right side of the expression
    * pre_conditions: None
    * post-conditions: Returns a integer as the result from applying the operator
    * exceptions handling: None
    * */
    public Integer operation(String op, String num1, String num2){
        Integer num3 = null;
        Integer num1Value = Integer.parseInt(num1);
        Integer num2Value = Integer.parseInt(num2);
        if (op.equals("+")){
            num3 = num1Value+num2Value;
        }
        else if(op.equals("-")){
            num3 = num1Value-num2Value;
        }
        else if(op.equals("/")) {
            num3 = num1Value/num2Value;
        }
        else if(op.equals("%")) {
            num3 = num1Value%num2Value;
        }
        else if(op.equals("*")) {
            num3 = num1Value*num2Value;
        }
        else if(op.equals("**")) {
            num3 = 1;
            for (int i = 0; i < num2Value; i++)
            {
                num3 *= num1Value;
            }
        }
        else if (op.equals("==")){
            if (num1Value.equals(num2Value)){
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        else if (op.equals("!=")){
            if (!num1Value.equals(num2Value)){
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
        else if(op.equals(">=")) {
            if (num1Value >= num2Value) {
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        else if(op.equals("<")) {
            if (num1Value < num2Value) {
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        else if(op.equals("<=")) {
            if (num1Value <= num2Value) {
                num3 = 1;
            }
            else{
                num3 = 0;
            }
        }
        return num3;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the string of the leftSide of expression
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a string
    * exceptions handling: None
    * */
    public String getLeftSide()
    {
        return this.leftSide;
    }

    /*
    * Author: Ivan
    * purpose: Retrieves the string of the rightSide of expression
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a string
    * exceptions handling: None
    * */
    public String getRightSide()
    {
        return this.rightSide;
    }

    public String getOperator() { return this.operator; }
    /*
    * Author: Ivan
    * purpose: Retrieves the result of the expression
    * params: None
    * pre_conditions: None
    * post-conditions: Returns a integer
    * exceptions handling: None
    * */
    public Integer getResult(String leftSide,String rightSide)
    {
        String left = leftSide;
        String right = rightSide;
        this.result = operation(this.operator, left, right);
        return this.result;
    }
}
