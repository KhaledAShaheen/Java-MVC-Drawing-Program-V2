
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Ellipse2D;
import java.util.Stack;

public class NewSwingUI implements UIContext {
  private Graphics graphics;
  private static NewSwingUI swingUI;

  private NewSwingUI() {
  }

  public static NewSwingUI getInstance() {
    if (swingUI == null) {
      swingUI = new NewSwingUI();
    }
    return swingUI;
  }

  public void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }

  public void draw(Label label) {
    if (label.getStartingPoint() != null) {
      if (label.getText() != null) {
        graphics.drawString(label.getText(), (int) label.getStartingPoint().getX(),
            (int) label.getStartingPoint().getY());
      }
    }
    int length = graphics.getFontMetrics().stringWidth(label.getText());
    graphics.drawString("_", (int) label.getStartingPoint().getX() + length, (int) label.getStartingPoint().getY());
  }

  public void draw(Line line) {
    int i1 = 0;
    int i2 = 0;
    int i3 = 0;
    int i4 = 0;
    if (line.getPoint1() != null) {
      i1 = Math.round((float) (line.getPoint1().getX()));
      i2 = Math.round((float) (line.getPoint1().getY()));
      if (line.getPoint2() != null) {
        i3 = Math.round((float) (line.getPoint2().getX()));
        i4 = Math.round((float) (line.getPoint2().getY()));
      } else {
        i3 = i1;
        i4 = i2;
      }
      graphics.drawLine(i1, i2, i3, i4);
    }
  }

  public void draw(Triangle triangle) {
    // Coordinates for the points
    int i1 = 0, i2 = 0, j1 = 0, j2 = 0, k1 = 0, k2 = 0;

    // Draw the first point if it exists
    if (triangle.getPoint1() != null) {
      i1 = Math.round((float) (triangle.getPoint1().getX()));
      i2 = Math.round((float) (triangle.getPoint1().getY()));
      graphics.fillOval(i1 - 2, i2 - 2, 4, 4); // Draw a small circle or dot at the point
    }

    // Draw the line if the second point exists
    if (triangle.getPoint2() != null) {
      j1 = Math.round((float) (triangle.getPoint2().getX()));
      j2 = Math.round((float) (triangle.getPoint2().getY()));
      graphics.drawLine(i1, i2, j1, j2);
    }

    // Complete the triangle if the third point exists
    if (triangle.getPoint3() != null) {
      k1 = Math.round((float) (triangle.getPoint3().getX()));
      k2 = Math.round((float) (triangle.getPoint3().getY()));
      graphics.drawLine(j1, j2, k1, k2);
      graphics.drawLine(k1, k2, i1, i2);
    }
  }

  public void draw(Polygon polygon) {
    // Get a copy of the points stack
    Stack<Point> points = polygon.getPoints();
    Point prevPoint = null;
    Point firstPoint = null;

    // Draw the starting point (first point)
    if (!points.isEmpty()) {
      firstPoint = points.get(0);
      graphics.fillOval(firstPoint.x - 2, firstPoint.y - 2, 4, 4); // Draw a small circle
    }
    // Draw lines between points
    for (Point currentPoint : points) {
      if (prevPoint != null) {
        graphics.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
      }
      prevPoint = currentPoint;
    }

    if (polygon.isDone()) {
      if (firstPoint != null && prevPoint != null && !firstPoint.equals(prevPoint)) {
        graphics.drawLine(prevPoint.x, prevPoint.y, firstPoint.x, firstPoint.y);
      }
    }

  }

  public void draw(Item item) {
    System.out.println("Cant draw unknown Item \n");
  }
}