package Backend;

public class Undo {
    CommandCollection Pop;
    Command commandInstance;

    public Undo(CommandCollection commandPop, Command commandInstance) {
        this.Pop = commandPop;
        this.commandInstance = commandInstance;
    }

    public void undoAction(){
       Pop.undoLastCommand();
    }
}
