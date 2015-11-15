package ar.com.kfgodel.demo;

import ar.com.kfgodel.demo.conway.ConwayCamera;
import ar.com.kfgodel.demo.conway.ConwaySimulationVisual;
import ar.com.kfgodel.demo.conway.ConwayWorld;
import ar.com.kfgodel.processingo.api.original.ProcessingRenderer;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.viewports.ViewportDefinition;
import ar.com.kfgodel.processingo.impl.PappletRunner;
import ar.com.kfgodel.processingo.impl.sketchs.DescribeWorldPerFrameSketch;
import ar.com.kfgodel.processingo.worker.api.WorkerTask;
import ar.com.kfgodel.processingo.worker.api.WorkerThread;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * This demo shows the Langston ants simulation
 * Created by tenpines on 04/11/15.
 */
public class DemoConwaysGameOfLife {
  public static void main(String[] args) {

    ConwayWorld world = ConwayWorld.create();
    ConwayCamera camera = ConwayCamera.create(Vector2d.xy(0, 0), Vector2d.xy(100, 80), world);

    Supplier<WorkerTask> workerStartConnector = ()->
      WorkerTask.periodicWith(TimeQuantity.of(10, TimeUnit.MILLISECONDS), world::advanceOneGeneration);


    ViewSize viewSize = ViewSize.of(800, 600);
    DescribeWorldPerFrameSketch sketch = DescribeWorldPerFrameSketch
      .create(ViewportDefinition.window(viewSize, ProcessingRenderer.P2D),
        ConwaySimulationVisual.create(camera, viewSize));

    // Pass the worker to initialize the represented world
    WorkerThread workerThread = WorkerThread.create();
    workerThread.execute(workerStartConnector.get());

    PappletRunner.create().run(sketch);

  }
}
