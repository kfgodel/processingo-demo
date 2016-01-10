package ar.com.kfgodel.demo.ripple;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.processingo.api.time.TimeQuantity;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;

import static ar.com.kfgodel.mathe.api.Mathe.vector;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This type verifies the behavior of the ripple demo model
 * Created by tenpines on 03/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class RippleWorldTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a ripple world", () -> {

      context().rippleWorld(() -> RippleWorld.create(context().clock()));
      context().clock(()-> mock(WorldClock.class));

      it("has a default lifespan for ripples", () -> {
        assertThat(context().rippleWorld().rippleLifespan()).isEqualTo(TimeQuantity.of(2, TimeUnit.SECONDS));
      });

      it("starts with no ripples", () -> {
        assertThat(context().rippleWorld().ripples()).isEmpty();
      });

      describe("when the mouse is clicked", ()->{
        beforeEach(()->{
          context().rippleWorld().mouseClickedOn(vector(1, 2));
        });

        it("creates a new ripple", () -> {
          assertThat(context().rippleWorld().ripples()).hasSize(1);
        });

        it("centers the ripple on the clicked position", () -> {
          assertThat(context().rippleWorld().ripples().get(0).position()).isEqualTo(vector(1,2));
        });

        it("keeps the ripple during its lifespan", () -> {
          context().rippleWorld().removeDeadRipples();
          assertThat(context().rippleWorld().ripples()).hasSize(1);
        });

        describe("when the ripple lifespan has ended", () -> {
          beforeEach(()->{
            long momentAfterLifespanEnd = context().rippleWorld().rippleLifespan().toMillis() + 1;
            when(context().clock().currentMillis()).thenReturn(momentAfterLifespanEnd);
          });
          it("removes the dead ripple", () -> {
            context().rippleWorld().removeDeadRipples();
            assertThat(context().rippleWorld().ripples()).hasSize(0);
          });
        });
      });

    });
  }
}
