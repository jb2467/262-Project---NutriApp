package Backend;

import java.util.Stack;

public class CommandCollection {
    private Stack<Command> commandLog;

    public CommandCollection() {
        this.commandLog = new Stack<>();
    }

    // Method to execute a command and add it to the stack
    public void executeCommand(Command command) {
        command.executeAction();
        commandLog.push(command);
    }

    // Method to undo the last command
    public void undoLastCommand() {
        if (!commandLog.isEmpty()) {
            Command lastCommand = commandLog.pop();
            lastCommand.undoAction();
        }
    }

}
