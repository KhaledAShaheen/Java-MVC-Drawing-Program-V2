
import java.awt.*;

public class Label extends Item {
  private Point startingPoint;
  private String text = "";
  public Point point;

  public Label(Point point) {
    startingPoint = point;
  }

  public void addCharacter(char character) {
    text += character;
  }

  public void removeCharacter() {
    if (text.length() > 0) {
      text = text.substring(0, text.length() - 1);
    }
  }

  public boolean includes(Point point) {
    this.point = point;
    return distance(point, startingPoint) < 10.0;
  }

  public void render(UIContext uiContext) {
    uiContext.draw(this);
  }

  public String getText() {
    return text;
  }

  public Point getStartingPoint() {
    return startingPoint;
  }

  public void translate(Point point) {
    startingPoint.x += point.x;
    startingPoint.y += point.y;
  }

  public boolean containsPoint(int x, int y) {
    return true;
  }

}