import java.awt.*;
import java.text.*;

public class TriangleCommand extends Command {
    private Triangle triangle;
    private int pointCount;

    public TriangleCommand() {
        // this(null, null,null);
        triangle = new Triangle();
        // pointCount = 0;
    }

    public TriangleCommand(Point point) {
        // this(point, null,null);
        triangle = new Triangle(point);

        pointCount = 1;
    }

    public TriangleCommand(Point point1, Point point2, Point point3) {
        // this(point1,point2,point3);
        triangle = new Triangle(point1, point2, point3);
        pointCount = 3;

    }

    public void setTriangleP(Point point) {
        triangle.setPoint1(point);
    }

    public void setTrianglePoint1(Point point) {
        if (++pointCount == 1) {
            triangle.setPoint1(point);
        } else if (pointCount == 2) {
            triangle.setPoint2(point);
        }
    }

    public void setTrianglePoint2(Point point) {
        triangle.setPoint3(point);
        // undo();
    }

    public void execute() {
        model.addItem(triangle);
    }

    public boolean undo() {
        model.removeItem(triangle);
        return true;
    }

    public boolean redo() {
        execute();
        return true;
    }

    public boolean end() {
        if (triangle.getPoint1() == null) {
            undo();
            return false;
        }
        if (triangle.getPoint2() == null) {
            triangle.setPoint2(triangle.getPoint1());
        }
        return true;
    }
}
