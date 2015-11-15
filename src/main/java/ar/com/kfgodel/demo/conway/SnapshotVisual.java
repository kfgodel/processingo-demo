package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.original.ProcessingCanvas;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.visuals.VisualDescription;
import ar.com.kfgodel.processingo.impl.visuals.RectangleVisual;

import java.awt.*;

/**
 * This type represents the visual description of a conway snapshot
 * Created by tenpines on 14/11/15.
 */
public class SnapshotVisual implements VisualDescription {
  private Snapshot snapshot;
  private ViewSize pixelSpace;
  private Vector2d cellSize;

  @Override
  public void applyOn(ProcessingCanvas canvas) {
    canvas.fill(Color.BLACK.getRGB());
    snapshot.survivingCells().stream()
      .map(this::convertToRectangles)
      .forEach((rectangle) -> rectangle.applyOn(canvas));

    canvas.fill(Color.RED.getRGB());
    snapshot.dyingCells().stream()
      .map(this::convertToRectangles)
      .forEach((rectangle) -> rectangle.applyOn(canvas));

    canvas.fill(Color.GREEN.getRGB());
    snapshot.emergingCells().stream()
      .map(this::convertToRectangles)
      .forEach((rectangle) -> rectangle.applyOn(canvas));
  }

  private RectangleVisual convertToRectangles(Vector2d cellPosition) {
    Vector2d position = cellPosition.elementProduct(getCellSize());
    Vector2d cellSize = getCellSize();
    return RectangleVisual.create(position, cellSize);
  }

  private Vector2d getCellSize() {
    if(cellSize == null){
      cellSize = calculateCellSize();
    }
    return cellSize;
  }

  private Vector2d calculateCellSize() {
    float amountOfHorizontalCells = snapshot.dimension().x();
    float cellWidth = pixelSpace.width() / amountOfHorizontalCells;

    float amountOfVerticalCells = snapshot.dimension().y();
    float cellHeight = pixelSpace.height() / amountOfVerticalCells;

    return Vector2d.xy(cellWidth, cellHeight);
  }


  public static SnapshotVisual create(Snapshot snapshot, ViewSize pixelSpace) {
    SnapshotVisual visual = new SnapshotVisual();
    visual.snapshot = snapshot;
    visual.pixelSpace = pixelSpace;
    return visual;
  }

}
