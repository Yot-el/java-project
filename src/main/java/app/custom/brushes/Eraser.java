package app.custom.brushes;

import app.custom.Layer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Eraser implements Instrument {
  private GraphicsContext gc;
  int size = 1;

  public Eraser() {};

  @Override
  public void startDraw(double x, double y) {
    this.gc.clearRect(x + size/2, y + size/2, size, size);
  }

  @Override
  public void draw(double x, double y) {
    this.gc.clearRect(x + size/2, y + size/2, size, size);
  }

  @Override
  public void endDraw(double x, double y) {
    this.gc.clearRect(x + size/2, y + size/2, size, size);
  }

  @Override
  public void setColor(Paint color) {
  }

  @Override
  public void setSize(int size) {
    this.size = size;
  }

  @Override
  public void setGraphicContext(Layer layer) {
    this.gc = layer.getGraphicsContext2D();
  }
}
