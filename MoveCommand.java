import java.awt.Point;
import java.util.Enumeration;
import java.util.Vector;

public class MoveCommand extends Command {
    private Point translationPoint;
    private Point originalPoint;
    private Vector itemTypes;

    public MoveCommand(Point StartPoint, Point Endpoint) {
        itemTypes = new Vector();

        model.fillType(itemTypes);

        int dx = Endpoint.x - StartPoint.x;
        int dy = Endpoint.y - StartPoint.y;

        translationPoint = new Point(dx, dy);
        int dxx = StartPoint.x - Endpoint.x;
        int dyy = StartPoint.y - Endpoint.y;
        originalPoint = new Point(dxx, dyy);

    }

    public boolean undo() {
        model.moveItems(originalPoint, itemTypes);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public void execute() {
        model.moveItems(translationPoint, itemTypes);
    }

    public boolean end() {
        return true;
    }

}
