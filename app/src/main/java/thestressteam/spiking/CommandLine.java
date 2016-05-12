package thestressteam.spiking;

import java.util.ArrayList;

/**
 * Created by Ivan on 5/9/2016.
 */

public class CommandLine {
    private ArrayList<Command> commandLines;

    public CommandLine()
    {
        this.commandLines = new ArrayList<Command>();
    }

    public void addCommand(Command command)
    {
        this.commandLines.add(command);
    }

    public void removeExpression(Command command)
    {
        this.commandLines.remove(command);
    }

    public void removeALLExpressions()
    {
        this.commandLines.clear();
    }

    public ArrayList<Command> getCommandLines()
    {
        return this.commandLines;
    }
}
