package app.custom.brushes.figures;

public class Rectangle extends Figure {
  public Rectangle() {}

  @Override
  public void showFigure() {
    gc.fillRect(this.getStart(x1, x2), this.getStart(y1, y2), this.getLength(x1, x2), this.getLength(y1, y2));
  }
}
