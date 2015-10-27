package ar.com.kfgodel.demo;

import ar.com.kfgodel.processingo.api.ProcessingRunner;
import ar.com.kfgodel.processingo.api.ProcessingSketch;
import ar.com.kfgodel.processingo.impl.PappletRunner;
import ar.com.kfgodel.processingo.impl.SketchSupport;

/**
 * This is the most basic example showing a hello world text in a small window
 * Created by tenpines on 26/10/15.
 */
public class DemoHelloWorld {

  public static void main(String[] args) {
    ProcessingSketch sketch = new SketchSupport(){};
    ProcessingRunner runner = PappletRunner.create();
    runner.run(sketch);
  }

}
