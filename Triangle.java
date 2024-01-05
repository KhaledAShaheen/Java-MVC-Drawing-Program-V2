import java.awt.*;

public class Triangle extends Item {
    private Point point1;
    private Point point2;
    private Point point3;
    public Point point;

    public Triangle(Point point1, Point point2, Point point3) {
        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
    }

    public Triangle(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Triangle(Point point1) {
        this.point1 = point1;
    }

    public Triangle() {
    }

    public boolean includes(Point point) {
        this.point = point;
        return ((distance(point, point1) < 2.0) || (distance(point, point2) < 2.0)
                || (distance(point, point3) < 2.0));
    }

    public void render(UIContext uiContext) {
        uiContext.draw(this);
    }

    public void setPoint1(Point point) {
        point1 = point;
    }

    public void setPoint2(Point point) {
        point2 = point;
    }

    public void setPoint3(Point point) {
        point3 = point;
    }

    public Point getPoint1() {
        return point1;
    }

    public Point getPoint2() {
        return point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public String toString() {
        return "Line  from " + point1 + " to " + point2 + "to " + point3;
    }

    @Override
    public void translate(Point point) {
        if (point1 != null) {
            point1.x += point.x;
            point1.y += point.y;
        }
        if (point2 != null) {
            point2.x += point.x;
            point2.y += point.y;
        }
        if (point3 != null) {
            point3.x += point.x;
            point3.y += point.y;
        }
    }

    public boolean containsPoint(int x, int y) {
        return true;
    }
}
