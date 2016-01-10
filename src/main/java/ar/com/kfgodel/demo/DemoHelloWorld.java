package ar.com.kfgodel.demo;

import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.processingo.api.ProcessingRunner;
import ar.com.kfgodel.processingo.api.original.ProcessingRenderer;
import ar.com.kfgodel.processingo.api.viewports.ViewSize;
import ar.com.kfgodel.processingo.api.viewports.ViewportDefinition;
import ar.com.kfgodel.processingo.impl.PappletRunner;
import ar.com.kfgodel.processingo.impl.sketchs.DescribeWorldPerFrameSketch;
import ar.com.kfgodel.processingo.impl.visuals.TextVisual;

/**
 * This is the a basic example showing a hello world text in a small window
 * Created by tenpines on 26/10/15.
 */
public class DemoHelloWorld {

  public static void main(String[] args) {
    DescribeWorldPerFrameSketch sketch = DescribeWorldPerFrameSketch.create(
      ViewportDefinition.window(ViewSize.of(200, 100), ProcessingRenderer.P2D),
      TextVisual.create("Hello World!", Mathe.vector(50, 50))
      );
    ProcessingRunner runner = PappletRunner.create();
    runner.run(sketch);
  }

}
