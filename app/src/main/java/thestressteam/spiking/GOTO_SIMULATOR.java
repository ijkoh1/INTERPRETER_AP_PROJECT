package thestressteam.spiking;

import android.content.ClipData;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.io.Console;
import java.util.ArrayList;

/*
* Author: Hanna & Ivan
* Purpose: GOTO_SIMULATOR is in charge of handling the user inputs such as button clicks.*/
public class GOTO_SIMULATOR extends AppCompatActivity{
    //LinearLayout so that we can add command gui
    LinearLayout from, to, clear;
    //TextView so that we can get the text of the block
    TextView  console;
    int transitionCount = 0, lineNum;
    //A viewgroup to add command blocks
    ViewGroup instruction;
    //A view for all the commands available
    View printBlock, remBlock, gotoBlock,ifBlock, letBlock;
    //An integer to detect number of lines added
    private Integer currentLine = 0;
    //A CommandLine object to store the command lines
    private CommandLine commandLine = new CommandLine();
    //An integer to count the number of lines added
    private Integer lineCount = 0;

    String[] operatorList;
    String[] varList;

    /*
    * Author: Hanna
    * purpose: Recreates the gui if the app reopens again and initialize the view of instructions
    * params: savedInstanceState = Retrieves the previous state if the app reopens again
    * pre_conditions: None
    * post-conditions: None
    * exception handling: None
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goto__simulator);

        //initialising by clearing display screen and console
        instruction = (ViewGroup) findViewById(R.id.displayInstructions);
        if (instruction.getChildCount() > 0) {
            instruction.removeAllViews();
        }
        console = (TextView) findViewById(R.id.console);
        console.setText("");

        //initialise spinner lists
        operatorList = new String[]{">", "+", "-", "=="};
        varList = new String[]{"a", "b", "c", "d"};

        //initialise drag and drop on variable View
        View var = findViewById(R.id.variable);
        onTouchVariable(var);

        //initialise listeners
        View displayScroll = findViewById(R.id.displayScroll);
        displayScroll.setOnDragListener(new removeVarListener());



    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of PRINT statement with TextBox shown as user inputs
    * params: v = view of the PRINT to detect when it is clicked
    * pre_conditions: Instruction and console view must exist and when the PRINT button is clicked
    * post-conditions: PRINT block statement is created
    * exception handling: None
    */
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
//        console.append("PRINT block added.\n");
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of REM statement with TextBox shown as user inputs
    * params: v = view of the REM to detect when it is clicked
    * pre_conditions: Instruction and console view must exist and when the REM button is clicked
    * post-conditions: REM block statement is created
    * exception handling: None
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

    /**
    * @author: Hanna & Ivan
    * purpose: Creates a block of GOTO statement with TextBox shown as user inputs
    * params: v = view of the GOTO to detect when it is clicked and when the GOTO button is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: GOTO block statement is created
    * exception handling: None
    */
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

        //initialise onFocus listener
        View gotoEditText = findViewById(R.id.gotoEditText);
        gotoEditText.setOnFocusChangeListener(new onFocusGOTO());
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of IF statement with TextBox shown as user inputs
    * params: v = view of the IF to detect when it is clicked and when the IF button is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: IF block statement is created
    * exception handling: None
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

        //initialise spinner
        Spinner ifOperator = (Spinner)ifBlock.findViewById(R.id.ifOperator);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, operatorList);
        ifOperator.setAdapter(adapter);

        //initialise drag and drop listener
        View ifVariable = ifBlock.findViewById(R.id.ifVariable);
        View ifValue = ifBlock.findViewById(R.id.ifValue);
        ifVariable.setOnDragListener(new MyDragListener());
        ifValue.setOnDragListener(new MyDragListener());
        //initialise onFocus listener
        View ifGotoEditText = findViewById(R.id.ifGotoEditText);
        ifGotoEditText.setOnFocusChangeListener(new onFocusGOTO());

    }

    /*
    * Author: Hanna & Ivan
    * purpose: Creates a block of LET statement with TextBox shown as user inputs
    * params: v = view of the LET to detect when it is clicked and when the LET button is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: LET block statement is created
    * exception handling: None
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


        //initialise operator Spinner
        Spinner letOperator = (Spinner)letBlock.findViewById(R.id.letOperator);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, operatorList);
        letOperator.setAdapter(adapter);


        //initialise drag and drop listener
        View letVariable = letBlock.findViewById(R.id.letVariable);
        View letValue1 = letBlock.findViewById(R.id.letValue);
        View letValue2 = letBlock.findViewById(R.id.letValue2);
        letVariable.setOnDragListener(new MyDragListener());
        letValue1.setOnDragListener(new MyDragListener());
        letValue2.setOnDragListener(new MyDragListener());

    }

    /*
    * Author: Hanna & Ivan
    * purpose: Reads in the user inputs and creates and adds command into the commandLine variable
    * params: None
    * pre_conditions: Command statements must be added
    * post-conditions: Commands are added into commandLine
    * exception handling: case 1: if user inputs are null
    *                     case 2: if name of variable starts an integer
    * */
    public void checkCodeLine()
    {
        Command cmdObj;
        try
        {
            if (clear.getId() == R.id.letBlock) {
                EditText variableNameInput = (EditText) clear.findViewById(R.id.letVariable);
                EditText expressionValue1Input = (EditText) clear.findViewById(R.id.letValue);
                //EditText operatorInput = (EditText) clear.findViewById(R.id.letOperator);
                EditText expressionValue2Input = (EditText) clear.findViewById(R.id.letValue2);

                Spinner operator = (Spinner)findViewById(R.id.letOperator);
                //&& !operatorInput.getText().toString().equals("")
                if (!variableNameInput.getText().toString().equals("") && !expressionValue1Input.getText().toString().equals("") && !operator.getSelectedItem().equals(null) && !expressionValue2Input.getText().toString().equals("")) {
                    if (Character.isDigit(variableNameInput.getText().toString().charAt(0))) {
                        throw new Exception("Variable must not start with an integer");
                    }
                    Variable variableObj = new Variable(variableNameInput.getText().toString());
                    //operatorInput.getText().toString()
                    Expression expressionObj = new Expression(expressionValue1Input.getText().toString(), operator.getSelectedItem().toString(), expressionValue2Input.getText().toString());
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
                //EditText operator = (EditText) clear.findViewById(R.id.ifOperator);
                EditText expressionRightSideInput = (EditText) clear.findViewById(R.id.ifValue);
                EditText jumpToLine = (EditText) clear.findViewById(R.id.ifGotoEditText);

                Spinner operator = (Spinner)findViewById(R.id.ifOperator);
                if (!expressionLeftSideInput.getText().toString().equals("") && !operator.getSelectedItem().toString().equals(null) && !expressionRightSideInput.getText().toString().equals("") && !jumpToLine.getText().toString().equals("")) {
                    Expression expObj = new Expression(expressionLeftSideInput.getText().toString(), operator.getSelectedItem().toString(), expressionRightSideInput.getText().toString());
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

    public void onClickSave(View v)
    {
        String fileName = "bitchPls.txt";
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        currentLine = 0;
        commandLine.removeALLExpressions();
        //reset all transition lines to default
        for (int x = 0; x<instruction.getChildCount();x++){
            clear = (LinearLayout) instruction.getChildAt(x);
            checkCodeLine();
        }
        StatementsLoadSaveFile ls = new StatementsLoadSaveFile(commandLine,fileName);
        ls.saveCode(commandLine.getCommandLines(),fileName,this);
        ls.loadCode(fileName,this);
    }

    /*
    * Author: Hanna & Ivan
    * purpose: Uses GOTO_interpretr methods to run the commandLine statements and display the output
    * params: v = view of the Transition button to detect when it is clicked
    * pre_conditions: Instruction and console view must exist
    * post-conditions: CommandLine is executed and output is displayed in the console
    * exception handling: None
    * */
    public void onClickStart(View v){

        //get display-screen object
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        currentLine = 0;
        commandLine.removeALLExpressions();
        //reset all transition lines to default
        for (int x = 0; x<instruction.getChildCount();x++){
            clear = (LinearLayout) instruction.getChildAt(x);

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
    * exception handling: None
    * */
    public void onClickNewProgram(View v){
        lineCount = 0;
        console = (TextView) findViewById(R.id.console);
        if (!console.getText().equals("")){
            console.setText("");
        }
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
    * exception handling: None
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
    * exception handling: None
    * */
    public void onClickDeleteStatement(View v){
        instruction = (LinearLayout) findViewById(R.id.displayInstructions);
        LinearLayout block = (LinearLayout) v.getParent();
        instruction.removeView(block);

        //update line numbers
        TextView blockNum = (TextView) block.getChildAt(1);
        TextView blockName = (TextView) block.getChildAt(2);
        String statement = blockName.getText().toString();
        if (commandLine.getCommandLines().size() > 0 && statement.equals("REM")){
            commandLine.removeExpression(Integer.parseInt(blockNum.getText().toString()));
        }
        lineCount =  Integer.parseInt((String)blockNum.getText())-1;
        for (int i = lineCount; i<instruction.getChildCount(); i++){
            lineCount++;
            block = (LinearLayout) instruction.getChildAt(i);
            blockNum = (TextView) block.getChildAt(1);
            blockNum.setText(Integer.toString(lineCount));
        }
    }


    public void onTouchVariable(View v) {
        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
                ClipData data = ClipData.newPlainText("", "");
                v.startDrag(data, shadow, v, 0);
                return true;
            }
        });
    }

    public void onLongTouchVariable(View v){
        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                View.DragShadowBuilder shadow = new View.DragShadowBuilder(v);
                ClipData data = ClipData.newPlainText("", "");
                v.startDrag(data, shadow, v, 0);

                //instruction.setOnDragListener(new removeVarListener());
                return true;
            }
        });
    }

    float textboxWeight;
    public class MyDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //View dragged = (View) event.getLocalState();
            if (event.getAction() == DragEvent.ACTION_DROP) {
                //initialise variable selection spinner
                Spinner varSelection = new Spinner(GOTO_SIMULATOR.this);
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(GOTO_SIMULATOR.this, android.R.layout.simple_spinner_item, varList);
                varSelection.setAdapter(adapter);

                //"replace" textbox with variable spinner (make textbox invisible)
                ViewGroup parent = (ViewGroup) v.getParent();
                int index = parent.indexOfChild(v);
                v.setVisibility(View.INVISIBLE);
                v.setLayoutParams(new LinearLayout.LayoutParams(0,0,0));
                parent.addView(varSelection,index);
                onLongTouchVariable(varSelection);

            }
            return true;
        }
    }

    public class removeVarListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            if (event.getAction() == DragEvent.ACTION_DROP) {
                View dragged = (View) event.getLocalState();
                if (dragged instanceof Spinner){
                    ViewGroup parent = (ViewGroup)dragged.getParent();
                    System.out.println(parent.getChildCount());
                    int index = parent.indexOfChild(dragged);

                    //"replace" spinner back to textbox (make textbox visible)
                    parent.removeView(dragged);
                    View textbox = parent.getChildAt(index);
                    textbox.setVisibility(View.VISIBLE);
                    if (!textbox.getResources().getResourceName(textbox.getId()).equals("letVariable")){
                        textbox.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0.3f));
                    }else{
                        textbox.setLayoutParams(new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.MATCH_PARENT,0.15f));
                    }

                }
            }
            return true;
        }
    }

    public class touchGOTO implements View.OnClickListener{
        @Override
        public void onClick(View v){
            ViewGroup line = (ViewGroup) v;
            int gotoLine = instruction.indexOfChild(v)+1;
            //System.out.println(gotoLine);
            EditText textbox = (EditText) getCurrentFocus();

            ViewGroup parent = (ViewGroup)textbox.getParent();
            int textboxIndex = parent.indexOfChild(textbox);
            if (parent.getChildAt(textboxIndex-1) instanceof TextView){ //if its a TextView
                TextView blockName = (TextView)parent.getChildAt(textboxIndex-1); //get the name beside the Edittext
                System.out.println(blockName.getText().toString());
                if(blockName.getText().toString().equals("GOTO")) {
                    textbox.setText(Integer.toString(gotoLine));
                }
            }

        }
    }

    public class onFocusGOTO implements EditText.OnFocusChangeListener{
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus){
                int numLines = instruction.getChildCount();
                for (int i=0;i<numLines;i++){
                    instruction.getChildAt(i).setOnClickListener(new touchGOTO());

                }
            }
        }
    }


}