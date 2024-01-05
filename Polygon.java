
import java.awt.*;
import java.util.*;

public class Polygon extends Item {

    private Stack<Point> points;
    private Point firstPoint;
    private Point prevPoint;
    private Point nextPoint;
    private boolean isDone;
    public Point point;

    public Polygon(Point point) {
        this();
        setFirstPoint(point);
        points = new Stack<>();
        points.push(point);

    }

    public Polygon() {
        points = new Stack<>();
    }

    @Override
    public boolean includes(Point point) {
        // return ((distance(point, firstPoint) < 10.0) || (distance(point, prevPoint) <
        // 10.0));
        this.point = point;
        Point p;
        for (int i = 0; i < this.points.size(); i++) {
            p = (Point) points.get(i);
            if (distance(point, p) < 2.0) {
                return true;
            }
        }
        return false;
    }

    public void addPoint(Point point) {

        setPrevPoint(points.peek()); // The previous point is the last one added
        points.push(point); // Add the new point to the stack
        setNextPoint(point); // The new point is now the 'next' point

    }

    public Stack<Point> getPoints() {
        // Create a new stack and push all points from the original stack into it
        Stack<Point> pointsCopy = new Stack<>();
        for (Point point : points) {
            pointsCopy.push(point);
        }
        return pointsCopy;
    }

    @Override
    public void render(UIContext uiContext) {
        uiContext.draw(this);
    }

    public void setFirstPoint(Point point) {
        firstPoint = point;
    }

    public void setNextPoint(Point point) {
        nextPoint = point;
    }

    public void setPrevPoint(Point point) {
        prevPoint = point;
    }

    public Point getFirstPoint() {
        return firstPoint;
    }

    public Point getPrevPoint() {
        return prevPoint;
    }

    public Point getNextPoint() {
        return nextPoint;
    }

    public void setDone() {
        isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    @Override
    public void translate(Point point) {
        for (Point p : points) {
            p.x += point.x;
            p.y += point.y;
        }
    }
}
