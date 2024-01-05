
import java.awt.*;

public class PolygonCommand extends Command {

    private Polygon poly;
    private int pointCount;

    public PolygonCommand() {
        this(null);
        pointCount = 0;
    }

    public PolygonCommand(Point point) {
        poly = new Polygon(point);
        pointCount++;

    }

    public void setPolyPoint(Point point) {
        if (pointCount > 0) {
            poly.addPoint(point);
            pointCount++;
        }
    }

    public Polygon getPoly() {
        return poly;
    }

    @Override
    public void execute() {
        model.addItem(poly);
    }

    @Override
    public boolean undo() {
        model.removeItem(poly);
        return true;
    }

    @Override
    public boolean redo() {
        execute();
        return true;
    }

    @Override
    public boolean end() {
        if (poly.getFirstPoint() == null) {
            undo();
            return false;
        }

        poly.setDone();
        for (int x = 0; x < pointCount - 1; x++) {
            undo();
        }
        return true;
    }
}
