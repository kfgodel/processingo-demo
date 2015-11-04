package ar.com.kfgodel.demo;

import ar.com.kfgodel.demo.ripple.RippleWorld;
import ar.com.kfgodel.demo.ripple.SystemWorldClock;
import ar.com.kfgodel.processingo.api.original.ProcessingRenderer;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.viewports.ViewportDefinition;
import ar.com.kfgodel.processingo.impl.PappletRunner;
import ar.com.kfgodel.processingo.impl.sketchs.DescribeWorldPerFrameSketch;
import ar.com.kfgodel.processingo.impl.visuals.BackgroundVisual;
import ar.com.kfgodel.processingo.impl.visuals.EllipseVisual;
import ar.com.kfgodel.processingo.worker.api.WorkerTask;
import ar.com.kfgodel.processingo.worker.api.WorkerThread;

import java.util.concurrent.TimeUnit;

/**
 * This demo shows a ripple effect around mouse clicks
 *
 * Created by tenpines on 02/11/15.
 */
public class DemoRippleClick {
  public static void main(String[] args) {

    // How can I focus on the represented world rather than the architecture?
    RippleWorld rippleWorld = RippleWorld.create(SystemWorldClock.create());

    // Pass the worker to initialize the represented world
    WorkerThread.create()
      .execute(WorkerTask.periodicWith(TimeQuantity.of(100, TimeUnit.MILLISECONDS), rippleWorld::removeDeadRipples));

    // Safe Thread visual description ?
    DescribeWorldPerFrameSketch sketch = DescribeWorldPerFrameSketch
      .create(ViewportDefinition.window(ViewSize.of(640, 480), ProcessingRenderer.P2D), (canvas)->{
          BackgroundVisual.create(0xAAAAAAAA)
            .applyOn(canvas);
          rippleWorld.ripples().forEach((wave) -> {
            EllipseVisual.create(wave.position(), wave.radius(), wave.radius())
            .applyOn(canvas);
          });
        }
      );

    // Function<MouseEvent,WorkerTask> ? Take it from the represented world configuration?
    sketch.plugOnMouseClicked((mouseEvent)->{
      rippleWorld.mouseClickedOn(Vector2d.xy(mouseEvent.mouseX(), mouseEvent.mouseY()));
    });

    PappletRunner.create().run(sketch);
  }
}
