package app.custom.brushes;

import app.custom.Layer;
import javafx.scene.paint.Paint;

public interface Instrument {
  public void startDraw(double x, double y);
  public void draw(double x, double y);
  public void endDraw(double x, double y);
  public void setColor(Paint color);
  public void setSize(int size);
  public void setGraphicContext(Layer layer);
}
