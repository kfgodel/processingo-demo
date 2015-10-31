package ar.com.kfgodel.demo.bounce;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of a bouncing ball used for demos
 * Created by tenpines on 30/10/15.
 */
@RunWith(JavaSpecRunner.class)
public class BouncingBallTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a bouncing ball", () -> {
      context().ball(BouncingBall::createDefault);

      it("has a radius", () -> {
        assertThat(context().ball().radius()).isEqualTo(0.03f);
      });
      it("has an default position", () -> {
        assertThat(context().ball().position()).isEqualTo(Vector2d.xy(0.5, 0.5));
      });
      it("has a default velocity", () -> {
        assertThat(context().ball().velocity()).isEqualTo(Vector2d.xy(0.02, 0.01));
      });
      it("changes the position according to velocity when moved", () -> {
        context().ball().move();
        assertThat(context().ball().position()).isEqualTo(Vector2d.xy(0.52, 0.51));
      });

      it("has a highest x point", () -> {
        assertThat(context().ball().highestXPoint()).isEqualTo(context().ball().position().x() + context().ball().radius());
      });
      it("has a lowest x point", () -> {
        assertThat(context().ball().lowestXPoint()).isEqualTo(context().ball().position().x() - context().ball().radius());
      });
      it("has a highest y point", () -> {
        assertThat(context().ball().highestYPoint()).isEqualTo(context().ball().position().y() + context().ball().radius());
      });
      it("has a lowest y point", () -> {
        assertThat(context().ball().lowestYPoint()).isEqualTo(context().ball().position().y() - context().ball().radius());
      });

      describe("when velocity is positive", ()->{
        context().ball(()-> BouncingBall.withVelocity(Vector2d.xy(0.1, 0.2)));
        describe("when positioned on the highest x", () -> {
          beforeEach(() -> {
            context().ball().positionOn(Vector2d.xy(1.0 - context().ball().radius(), 0.5));
          });
          it("x velocity becomes negative if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().x()).isEqualTo(-0.1f);
          });
          it("highest x point never gets higher than 1.0 if moved", () -> {
            context().ball().move();
            assertThat(context().ball().highestXPoint()).isLessThanOrEqualTo(1.0f);
          });
        });
        describe("when positioned on the highest y", () -> {
          beforeEach(() -> {
            context().ball().positionOn(Vector2d.xy(0.5, 1.0 - context().ball().radius()));
          });
          it("y velocity becomes negative if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().y()).isEqualTo(-0.2f);
          });
          it("highest y point never gets higher than 1.0 if moved", () -> {
            context().ball().move();
            assertThat(context().ball().highestYPoint()).isLessThanOrEqualTo(1.0f);
          });
        });
      });

      describe("when velocity is negative", ()->{
        context().ball(()-> BouncingBall.withVelocity(Vector2d.xy(-0.1, -0.2)));
        describe("when positioned on the lowest x", () -> {
          beforeEach(() -> {
            context().ball().positionOn(Vector2d.xy(context().ball().radius(), 0.5));
          });
          it("x velocity becomes positive if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().x()).isEqualTo(0.1f);
          });
          it("lowest x point never gets lower than 0.0", () -> {
            context().ball().move();
            assertThat(context().ball().lowestXPoint()).isGreaterThanOrEqualTo(0.0f);
          });
        });
        describe("when positioned on the lowest y", () -> {
          beforeEach(()->{
            context().ball().positionOn(Vector2d.xy(0.5, context().ball().radius()));
          });
          it("y velocity becomes positive if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().y()).isEqualTo(0.2f);
          });
          it("lowest y position never gets lower than 0.0", () -> {
            context().ball().move();
            assertThat(context().ball().lowestYPoint()).isGreaterThanOrEqualTo(0.0f);
          });
        });

      });

    });
  }
}
