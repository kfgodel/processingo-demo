package ar.com.kfgodel.demo;

import ar.com.kfgodel.demo.ants.AntWorld;
import ar.com.kfgodel.demo.ants.AntWorldVisual;
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
public class DemoLangstonAnts {
  public static void main(String[] args) {

    AntWorld world = AntWorld.create(Vector2d.xy(200, 150));

    Supplier<WorkerTask> workerStartConnector = ()->
      WorkerTask.periodicWith(TimeQuantity.of(10, TimeUnit.MILLISECONDS), world::advanceOneTimeUnit);


    ViewSize viewSize = ViewSize.of(800, 600);
    DescribeWorldPerFrameSketch sketch = DescribeWorldPerFrameSketch
      .create(ViewportDefinition.window(viewSize, ProcessingRenderer.P2D),
        AntWorldVisual.create(world, viewSize));

    // Pass the worker to initialize the represented world
    WorkerThread workerThread = WorkerThread.create();
    workerThread.execute(workerStartConnector.get());

    PappletRunner.create().run(sketch);

  }
}
