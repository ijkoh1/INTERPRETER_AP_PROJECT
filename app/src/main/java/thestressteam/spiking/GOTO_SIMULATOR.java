package thestressteam.spiking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;


public class GOTO_SIMULATOR extends AppCompatActivity{

    LinearLayout from, to, clear;
    TextView transitionFrom,transitionTo, transitionClear, console;
    int transitionCount = 0, lineNum;
    ViewGroup instruction;
    View printBlock, remBlock, gotoBlock,ifBlock, letBlock;

    private Integer currentLine = 0;
    private CommandLine commandLine = new CommandLine();
    private Integer lineCount = 0;

    /*
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto__simulator);

        //initialising by clearing display screen and console
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        if (instruction.getChildCount() > 0) {
            instruction.removeAllViews();
        }
        console = (TextView) findViewById(R.id.console);
        console.setText("");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of PRINT statement with TextBox shown as user inputs
    * params: v = view of the PRINT to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: PRINT block statement is created
    * */
    //when the PRINT button is clicked
    public void onClickPrint(View v) {
        //add PRINT block to display screen
        ++lineCount;
        instruction = (ViewGroup) findViewById(R.id.displayInstructions);
        printBlock = getLayoutInflater().inflate(R.layout.printblock,instruction,false);
        TextView printLineNumber = (TextView) printBlock.findViewById(R.id.printTransition);
        printLineNumber.setText(lineCount.toString());
        instruction.addView(printBlock);

        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
        console.append("PRINT block added.\n");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of REM statement with TextBox shown as user inputs
    * params: v = view of the REM to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: REM block statement is created
    * */
    public void onClickRem(View v) {
        //add REM block to display screen
        ++lineCount;
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        remBlock = getLayoutInflater().inflate(R.layout.remblock,instruction,false);
        TextView lineNumber = (TextView) remBlock.findViewById(R.id.remTransition);
        lineNumber.setText(lineCount.toString());
        instruction.addView(remBlock);
        transitionCount++;
        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
//        console.append("REM block added.\n");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of GOTO statement with TextBox shown as user inputs
    * params: v = view of the GOTO to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: GOTO block statement is created
    * */
    public void onClickGoto(View v) {
        //add GOTO block to display screen
        ++lineCount;
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        gotoBlock = getLayoutInflater().inflate(R.layout.gotoblock,instruction,false);
        TextView lineNumber = (TextView) gotoBlock.findViewById(R.id.gotoTransition);
        lineNumber.setText(lineCount.toString());
        instruction.addView(gotoBlock);
        transitionCount++;
        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
//        console.append("GOTO block added.\n");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of IF statement with TextBox shown as user inputs
    * params: v = view of the IF to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: IF block statement is created
    * */
    public void onClickIf(View v) {
        //add IF block to display screen
        ++lineCount;
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        ifBlock = getLayoutInflater().inflate(R.layout.ifblock,instruction,false);
        TextView lineNumber = (TextView) ifBlock.findViewById(R.id.ifTransition);
        lineNumber.setText(lineCount.toString());
        instruction.addView(ifBlock);
        transitionCount++;
        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
//        console.append("IF block added.\n");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of LET statement with TextBox shown as user inputs
    * params: v = view of the LET to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: LET block statement is created
    * */
    public void onClickLet(View v) {
        //add LET block to display screen
        ++lineCount;
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        letBlock = getLayoutInflater().inflate(R.layout.letblock,instruction,false);
        TextView lineNumber = (TextView) letBlock.findViewById(R.id.letTransition);
        lineNumber.setText(lineCount.toString());
        instruction.addView(letBlock);
        transitionCount++;
        //add text to console indicating user action
        console = (TextView) findViewById(R.id.console);
//        console.append("LET block added.\n");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Reads in the user inputs and creates and adds command into the commandLine variable
    * params: None
    * pre_conditions: Command statements must be added
    * post-conditions: Commands are added into commandLine
    * */
    public void checkCodeLine()
    {
        Command cmdObj;
        try
        {
            if (clear.getId() == R.id.letBlock) {
                EditText variableNameInput = (EditText) clear.findViewById(R.id.letVariable);
                EditText expressionValue1Input = (EditText) clear.findViewById(R.id.letValue);
                EditText operatorInput = (EditText) clear.findViewById(R.id.letOperator);
                EditText expressionValue2Input = (EditText) clear.findViewById(R.id.letValue2);
                if (!variableNameInput.getText().toString().equals("") && !expressionValue1Input.getText().toString().equals("") && !operatorInput.getText().toString().equals("") && !expressionValue2Input.getText().toString().equals("")) {
                    if (Character.isDigit(variableNameInput.getText().toString().charAt(0))) {
                        throw new Exception("Variable must not start with an integer");
                    }
                    Variable variableObj = new Variable(variableNameInput.getText().toString());
                    Expression expressionObj = new Expression(expressionValue1Input.getText().toString(), operatorInput.getText().toString(), expressionValue2Input.getText().toString());
                    LETStatement letObj = new LETStatement(currentLine, variableObj, expressionObj);
                    cmdObj = new Command(letObj);
                    commandLine.addCommand(cmdObj);
                } else {
                    console.append(String.format("Line %d Please fill up the textBox input\n", currentLine + 1));
                }
            } else if (clear.getId() == R.id.gotoBlock) {
                EditText gotoLineInput = (EditText) clear.findViewById(R.id.gotoEditText);
                if (!gotoLineInput.getText().toString().equals("")) {
                    GOTOStatement gotoObj = new GOTOStatement(currentLine, Integer.parseInt(gotoLineInput.getText().toString()));
                    cmdObj = new Command(gotoObj);
                    commandLine.addCommand(cmdObj);
                } else {
                    console.append(String.format("Line %d Please fill up the textBox input\n", currentLine + 1));
                }
            } else if (clear.getId() == R.id.ifBlock) {
                EditText expressionLeftSideInput = (EditText) clear.findViewById(R.id.ifVariable);
                EditText operator = (EditText) clear.findViewById(R.id.ifOperator);
                EditText expressionRightSideInput = (EditText) clear.findViewById(R.id.ifValue);
                EditText jumpToLine = (EditText) clear.findViewById(R.id.ifGotoEditText);
                if (!expressionLeftSideInput.getText().toString().equals("") && !operator.getText().toString().equals("") && !expressionRightSideInput.getText().toString().equals("") && !jumpToLine.getText().toString().equals("")) {
                    Expression expObj = new Expression(expressionLeftSideInput.getText().toString(), operator.getText().toString(), expressionRightSideInput.getText().toString());
                    GOTOStatement go2Obj = new GOTOStatement(currentLine, Integer.parseInt(jumpToLine.getText().toString()));
                    IFStatement ifObj = new IFStatement(currentLine, expObj, go2Obj);
                    cmdObj = new Command(ifObj);
                    commandLine.addCommand(cmdObj);
                } else {
                    console.append(String.format("Line %d Please fill up the textBox input\n", currentLine + 1));
                }
            } else if (clear.getId() == R.id.printBlock) {
                EditText printInput = (EditText) clear.findViewById(R.id.printEditText);
                if (!printInput.getText().toString().equals("")) {
                    PRINTStatement printObj = new PRINTStatement(currentLine, printInput.getText().toString());
                    cmdObj = new Command(printObj);
                    commandLine.addCommand(cmdObj);
                } else {
                    console.append(String.format("Line %d Please fill up the textBox input\n", currentLine + 1));
                }
            }
            currentLine++;
        }
        catch (Exception e)
        {
            console.append(String.format("Line %d ERROR: %s\n",currentLine+1,e.getMessage()));
        }
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Uses GOTO_interpretr methods to run the commandLine statements and display the output
    * params: v = view of the Transition button to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: CommandLine is executed and output is displayed in the console
    * */
    public void onClickTransition(View v){

        //get display-screen object
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        currentLine = 0;
        commandLine.removeALLExpressions();
        //reset all transition lines to default
        for (int x = 0; x<instruction.getChildCount();x++){
            clear = (LinearLayout) instruction.getChildAt(x);

//            transitionClear = (TextView) clear.getChildAt(0);
//            transitionClear.setText("||");

            checkCodeLine();
        }

//        ArrayList<Command> cmLine = commandLine.getCommandLines();
//        DeclaredVariableList dvl = new DeclaredVariableList();
//        Statement let = cmLine.get(0).getStatement();
//        dvl = let.executeRun(dvl);
//        console.append(String.format("result: %s\n", dvl.getValue("w")));
//        console.append(String.format("line: %s\n", let.getCurrentLine()));

//        for (int i=0; i<cmLine.size(); i++){
//            Statement st = cmLine.get(i).getStatement();
//            console.append(String.format("Statement: %s\n",st.getStatementID()));
//        }
        if (commandLine.getCommandLines().size() != 0)
        {
                GOTO_interpreter gotoInterpreter = new GOTO_interpreter(commandLine);
                gotoInterpreter.readAllCode();
                ArrayList<String> results = gotoInterpreter.getResults();

                console = (TextView) findViewById(R.id.console);
                for (int i=0; i<results.size(); i++)
                {
                    console.append(String.format("%s\n",results.get(i)));
                }
        }

    }

    /*
    * Author: Hanna & Ivan
    * purpose: Clears all commandLine
    * params: v = view of the ClearDisplay to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: All command blocks are deleted
    * */
    public void onClickClearDisplay(View v){
        lineCount = 0;
        commandLine.removeALLExpressions();
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        if (instruction.getChildCount() > 0){
            instruction.removeAllViews();
            commandLine.removeALLExpressions();
        }
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Clears the console
    * params: v = view of the ClearConsole to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: All text in the console is cleared
    * */
    public void onClickClearConsole(View v){
        console = (TextView) findViewById(R.id.console);
        if (!console.getText().equals("")){
            console.setText("");
        }
//        String txt = lineCount + " lines were created";
//        console.setText(txt);
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Deletes a specified command line
    * params: v = view of the DeleteStatement to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: A command block statement is deleted
    * */
    public void onClickDeleteStatement(View v){
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        LinearLayout block = (LinearLayout) v.getParent();
        instruction.removeView(block);

        //update line numbers
        TextView blockNum = (TextView) block.getChildAt(0);
        TextView blockName = (TextView) block.getChildAt(1);
        String statement = blockName.getText().toString();
        if (commandLine.getCommandLines().size() > 0 && statement.equals("REM")){
            commandLine.removeExpression(Integer.parseInt(blockNum.getText().toString()));
        }
        lineCount =  Integer.parseInt((String)blockNum.getText())-1;
        for (int i = lineCount; i<instruction.getChildCount(); i++){
            lineCount++;
            block = (LinearLayout) instruction.getChildAt(i);
            blockNum = (TextView) block.getChildAt(0);
            blockNum.setText(Integer.toString(lineCount));
        }
    }
}