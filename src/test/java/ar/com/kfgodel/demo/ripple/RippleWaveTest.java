package ar.com.kfgodel.demo.ripple;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This type verifies teh behavior of a ripple wave for the demo
 * Created by tenpines on 02/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class RippleWaveTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a ripple wave", () -> {
      context().ripple(()-> RippleWave.create(Vector2d.xy(1,2), TimeQuantity.of(2, TimeUnit.SECONDS), context().clock()));
      context().clock(()-> mock(WorldClock.class));

      it("has a position", () -> {
        assertThat(context().ripple().position()).isEqualTo(Vector2d.xy(1, 2));
      });
      it("starts with a 0 radius", () -> {
        when(context().clock().currentMillis())
          .thenReturn(0L) // On creation
          .thenReturn(0L); // On radius invocation
        assertThat(context().ripple().radius()).isEqualTo(0.0f);
      });
      it("ends with a 200 radius at the end of its lifespan", () -> {
        when(context().clock().currentMillis())
          .thenReturn(0L) // On creation
          .thenReturn(TimeUnit.SECONDS.toMillis(2)); // On radius invocation
        assertThat(context().ripple().radius()).isEqualTo(200.0f);
      });
      it("is dead after its lifespan", () -> {
        when(context().clock().currentMillis())
          .thenReturn(0L) // On creation
          .thenReturn(TimeUnit.SECONDS.toMillis(2) + 1); // On radius invocation
        assertThat(context().ripple().isDead()).isTrue();
      });
    });
  }
}
