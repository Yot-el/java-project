package app.custom.brushes.figures;

import app.custom.Layer;
import app.custom.brushes.Instrument;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Figure implements Instrument {
  protected GraphicsContext gc;
  protected double x1;
  protected double y1;
  protected double x2;
  protected double y2;

  public Figure() {}

  @Override
  public void startDraw(double x, double y) {
    this.x1 = x;
    this.y1 = y;
  }

  @Override
  public void draw(double x, double y) {
  }

  @Override
  public void endDraw(double x, double y) {
    this.x2 = x;
    this.y2 = y;

    this.showFigure();
  }

  @Override
  public void setColor(Paint color) {
    this.gc.setFill(color);
    this.gc.setStroke(color);
  }

  @Override
  public void setSize(int size) {
  }

  @Override
  public void setGraphicContext(Layer layer) {
    this.gc = layer.getGraphicsContext2D();
  }

  public void showFigure() {
  }

  protected double getStart(double c1, double c2) {
    if (c1 < c2) {
      return c1;
    }

    return c2;
  }

  protected double getLength(double c1, double c2) {
    return Math.abs(c1 - c2);
  }
}
