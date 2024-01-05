import java.awt.*;
import java.util.*;

public class UnSelectCommand extends Command {
    private Item item;

    public UnSelectCommand(Item item) {
        this.item = item;

    }

    public boolean undo() {
        model.markSelected(item);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public void execute() {
        model.unSelect(item);
    }
}
