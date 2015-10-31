package ar.com.kfgodel.demo.bounce;

import ar.com.dgarcia.javaspec.api.TestContext;
import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.function.Supplier;

/**
 * This type defines useful test objects
 * Created by tenpines on 30/10/15.
 */
public interface DemoTestContext extends TestContext {

  BouncingBall ball();
  void ball(Supplier<BouncingBall> definition);

  Vector2d velocity();
  void velocity(Supplier<Vector2d> definition);
}
