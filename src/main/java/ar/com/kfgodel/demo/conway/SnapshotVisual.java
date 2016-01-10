package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.processingo.api.original.ProcessingCanvas;
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
  private BidiVector cellSize;

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

  private RectangleVisual convertToRectangles(BidiVector cellPosition) {
    BidiVector position = cellPosition.componentProduct(getCellSize());
    BidiVector cellSize = getCellSize();
    return RectangleVisual.create(position, cellSize);
  }

  private BidiVector getCellSize() {
    if(cellSize == null){
      cellSize = calculateCellSize();
    }
    return cellSize;
  }

  private BidiVector calculateCellSize() {
    float amountOfHorizontalCells = snapshot.dimension().x().asFloat();
    float cellWidth = pixelSpace.width() / amountOfHorizontalCells;

    float amountOfVerticalCells = snapshot.dimension().y().asFloat();
    float cellHeight = pixelSpace.height() / amountOfVerticalCells;

    return Mathe.vector(cellWidth, cellHeight);
  }


  public static SnapshotVisual create(Snapshot snapshot, ViewSize pixelSpace) {
    SnapshotVisual visual = new SnapshotVisual();
    visual.snapshot = snapshot;
    visual.pixelSpace = pixelSpace;
    return visual;
  }

}
