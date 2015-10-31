package ar.com.kfgodel.demo;

import ar.com.kfgodel.demo.bounce.BouncingBall;
import ar.com.kfgodel.processingo.api.ProcessingSketch;
import ar.com.kfgodel.processingo.api.original.ProcessingRenderer;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.viewports.ViewportDefinition;
import ar.com.kfgodel.processingo.impl.PappletRunner;
import ar.com.kfgodel.processingo.impl.sketchs.DescribeWorldPerFrameSketch;
import ar.com.kfgodel.processingo.impl.visuals.BackgroundVisual;
import ar.com.kfgodel.processingo.impl.visuals.EllipseVisual;

/**
 * This demo shows a bouncing ball
 * Created by tenpines on 30/10/15.
 */
public class DemoBouncingBall {

  public static void main(String[] args) {
    BouncingBall ball = BouncingBall.createDefault();
    ProcessingSketch sketch = DescribeWorldPerFrameSketch
      .create(ViewportDefinition.window(ViewSize.of(640, 480), ProcessingRenderer.P2D),
        (canvas)-> {
          BackgroundVisual.create(0xAAAAAA)
            .applyOn(canvas);
          EllipseVisual.create(ball.position().product(Vector2d.xy(640, 480)), ball.radius() * 480, ball.radius() * 480)
            .applyOn(canvas);
          ball.move();
        });
    PappletRunner.create().run(sketch);
  }
}
