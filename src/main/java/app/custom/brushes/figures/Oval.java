package app.custom.brushes.figures;

public class Oval extends Figure {
  public Oval() {}

  @Override
  public void showFigure() {
    gc.fillOval(this.getStart(x1, x2), this.getStart(y1, y2), this.getLength(x1, x2), this.getLength(y1, y2));
  }
}
