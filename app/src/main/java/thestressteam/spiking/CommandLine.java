package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/9/2016.
 */

public class CommandLine {
    private ArrayList<Command> commandLines;

    /*
    * Author: Ivan
    * purpose: Initialize the arrayList of commandLine
    * params: None
    * pre_conditions: None
    * post-conditions: CommandLine Object now stores an arrayList of commandLine
    * */
    public CommandLine()
    {
        this.commandLines = new ArrayList<Command>();
    }

    /*
    * Author: Ivan
    * purpose: Inserts a command into the arrayList of commandLine
    * params: command = the command Object to be inserted
    * pre_conditions: An array of commands, commandLine
    * post-conditions: command is inserted into commandLine
    * */
    public void addCommand(Command command)
    {
        this.commandLines.add(command);
    }

    /*
    * Author: Ivan
    * purpose: Removes the selected command from commandLine
    * params: index = An index of an element to be deleted
    * pre_conditions: An array of commands, commandLine
    * post-conditions: the index of the commandLine is removed
    * */
    public void removeExpression(Integer index)
    {
        this.commandLines.remove(index-1);
    }

    /*
    * Author: Ivan
    * purpose: Removes all commands from commandLine
    * params: None
    * pre_conditions: An array of commands, commandLine
    * post-conditions: All commands are removed
    * */
    public void removeALLExpressions()
    {
        this.commandLines.clear();
    }

    public ArrayList<Command> getCommandLines()
    {
        return this.commandLines;
    }
}
