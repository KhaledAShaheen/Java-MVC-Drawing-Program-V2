import java.awt.*;
import java.util.*;

public class SelectCommand extends Command {
  public Item item;
  public int flag = 0;

  public SelectCommand() {
  }

  public boolean setPoint(Point point) {
    boolean found = false;
    Enumeration enumeration = model.getItems();
    Enumeration enumerationSelected = model.getSelectedItems();

    while (enumeration.hasMoreElements()) {
      item = (Item) (enumeration.nextElement());
      if (item.includes(point)) {
        model.markSelected(item);
        found = true;
        return found;
      }
    }

    while (enumerationSelected.hasMoreElements()) {
      item = (Item) (enumerationSelected.nextElement());
      if (item.includes(point)) {
        found = true;
        flag = 1;
        return found;
      }
    }
    return found;
  }

  public boolean undo() {
    model.unSelect(item);
    return true;
  }

  public boolean redo() {
    execute();
    return true;
  }

  public void execute() {
    model.markSelected(item);
  }
}
