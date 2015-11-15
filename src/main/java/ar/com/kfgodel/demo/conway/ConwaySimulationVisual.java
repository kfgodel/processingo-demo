package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.original.ProcessingCanvas;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.visuals.VisualDescription;
import ar.com.kfgodel.processingo.impl.visuals.BackgroundVisual;

/**
 * This type represents the description of the game visual representation
 * Created by tenpines on 14/11/15.
 */
public class ConwaySimulationVisual implements VisualDescription {

  private ConwayCamera camera;
  private ViewSize viewSize;

  @Override
  public void applyOn(ProcessingCanvas canvas) {
    BackgroundVisual.create(0xAAAAAAAA)
      .applyOn(canvas);

    Snapshot snapshot = camera.takeSnapshot();
    SnapshotVisual.create(snapshot, viewSize).applyOn(canvas);
  }

  public static ConwaySimulationVisual create(ConwayCamera camera, ViewSize viewportSize) {
    ConwaySimulationVisual visual = new ConwaySimulationVisual();
    visual.camera = camera;
    visual.viewSize = viewportSize;
    return visual;
  }


}
