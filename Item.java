
import java.io.*;
import java.awt.*;

public abstract class Item implements Serializable {
  protected static UIContext uiContext;
  protected boolean selection = false;

  protected Point point;

  public abstract boolean includes(Point point);

  protected double distance(Point point1, Point point2) {
    double xDifference = point1.getX() - point2.getX();
    double yDifference = point1.getY() - point2.getY();
    return ((double) (Math.sqrt(xDifference * xDifference + yDifference * yDifference)));
  }

  public abstract void translate(Point point);

  protected void setSelection(boolean selection) {
    this.selection = selection;
  }

  protected boolean selection() {
    return selection;
  }

  public boolean containsPoint(int x, int y) {
    return false;
  }

  public abstract void render(UIContext uiContext);

}
