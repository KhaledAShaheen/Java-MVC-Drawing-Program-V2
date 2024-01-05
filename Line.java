import java.awt.*;

public class Line extends Item {
  private Point point1;
  private Point point2;
  public Point point;

  public Line(Point point1, Point point2) {
    this.point1 = point1;
    this.point2 = point2;
  }

  public Line(Point point1) {
    this.point1 = point1;
  }

  public Line() {
  }

  public boolean includes(Point point) {
    this.point = point;
    return ((distance(point, point1) < 2.0) || (distance(point, point2) < 2.0));
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

  public Point getPoint1() {
    return point1;
  }

  public Point getPoint2() {
    return point2;
  }

  public String toString() {
    return "Line  from " + point1 + " to " + point2;
  }

  public void translate(Point point) {
    // Translate the start point
    point1.x += point.x;
    point1.y += point.y;

    // Translate the end point
    point2.x += point.x;
    point2.y += point.y;
  }

  public boolean containsPoint(int x, int y) {
    return true;
  }
}
