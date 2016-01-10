package ar.com.kfgodel.demo;

import ar.com.kfgodel.demo.bounce.BouncingBall;
import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.processingo.api.ProcessingSketch;
import ar.com.kfgodel.processingo.api.original.ProcessingRenderer;
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
 * This demo shows a bouncing ball
 * Created by tenpines on 30/10/15.
 */
public class DemoBouncingBall {

  public static void main(String[] args) {
    BouncingBall ball = BouncingBall.createDefault();

    WorkerThread.create()
      .execute(WorkerTask.periodicWith(TimeQuantity.of(500, TimeUnit.MILLISECONDS), ball::move));

    ProcessingSketch sketch = DescribeWorldPerFrameSketch
      .create(ViewportDefinition.window(ViewSize.of(640, 480), ProcessingRenderer.P2D),
        (canvas)-> {
          BackgroundVisual.create(0xAAAAAAAA)
            .applyOn(canvas);
          EllipseVisual.create(ball.position().componentProduct(Mathe.vector(640, 480)), ball.radius() * 640, ball.radius() * 480)
            .applyOn(canvas);
        });
    PappletRunner.create().run(sketch);
  }
}
