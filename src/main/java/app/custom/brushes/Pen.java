package app.custom.brushes;

import app.custom.Layer;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Pen implements Instrument {
    private GraphicsContext gc;

    public Pen() {}

    public void setGraphicContext(Layer layer) {
        this.gc = layer.getGraphicsContext2D();
    }

    @Override
    public void startDraw(double x, double y) {
        this.gc.beginPath();
        this.gc.moveTo(x, y);
        this.gc.lineTo(x, y);
        this.gc.stroke();
    }

    @Override
    public void draw(double x, double y) {
        this.gc.lineTo(x, y);
        this.gc.stroke();
    }

    @Override
    public void endDraw(double x, double y) {
    
    }

    @Override
    public void setColor(Paint color) {
        this.gc.setStroke(color);
    }

    @Override
    public void setSize(int size) {
        this.gc.setLineWidth(size);
    }
    
}
