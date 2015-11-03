package ar.com.kfgodel.demo;

import ar.com.kfgodel.demo.ripple.RippleWave;
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

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * This demo shows a ripple effect around mouse clicks
 *
 * Created by tenpines on 02/11/15.
 */
public class DemoRippleClick {
  public static void main(String[] args) {

    List<RippleWave> currentWaves = new CopyOnWriteArrayList<>();

    WorkerThread.create()
      .execute(WorkerTask.periodicWith(TimeQuantity.of(100, TimeUnit.MILLISECONDS), () -> {
        currentWaves.removeIf(RippleWave::isDead);
      }));

    DescribeWorldPerFrameSketch sketch = DescribeWorldPerFrameSketch
      .create(ViewportDefinition.window(ViewSize.of(640, 480), ProcessingRenderer.P2D), (canvas)->{
          BackgroundVisual.create(0xAAAAAAAA)
            .applyOn(canvas);
          currentWaves.forEach((wave) -> {
            EllipseVisual.create(wave.position(), wave.radius(), wave.radius())
            .applyOn(canvas);
          });
        }
      );

    SystemWorldClock worldClock = SystemWorldClock.create();
    TimeQuantity defaultRippleLifeSpan = TimeQuantity.of(2, TimeUnit.SECONDS);
    sketch.plugOnMouseClicked((mouseEvent)->{
      currentWaves.add(RippleWave.create(Vector2d.xy(mouseEvent.mouseX(), mouseEvent.mouseY()), defaultRippleLifeSpan, worldClock));
    });

    PappletRunner.create().run(sketch);
  }
}
