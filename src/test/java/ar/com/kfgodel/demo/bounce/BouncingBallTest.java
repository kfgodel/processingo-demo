package ar.com.kfgodel.demo.bounce;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import org.junit.runner.RunWith;

import static ar.com.kfgodel.mathe.api.Mathe.*;
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
        assertThat(context().ball().radius()).isEqualTo(scalar(0.03));
      });
      it("has an default position", () -> {
        assertThat(context().ball().position()).isEqualTo(vector(0.5, 0.5));
      });
      it("has a default velocity", () -> {
        assertThat(context().ball().velocity()).isEqualTo(vector(0.02, 0.01));
      });
      it("changes the position according to velocity when moved", () -> {
        context().ball().move();
        assertThat(context().ball().position()).isEqualTo(vector(0.52, 0.51));
      });
      it("has a diameter",()->{
        assertThat(context().ball().diameter()).isEqualTo(scalar(0.06));
      });   

      it("has a right x point", () -> {
        assertThat(context().ball().rightSide()).isEqualTo(context().ball().position().x().plus(context().ball().radius()));
      });
      it("has a left x point", () -> {
        assertThat(context().ball().leftSide()).isEqualTo(context().ball().position().x().minus(context().ball().radius()));
      });
      it("has a bottom y point", () -> {
        assertThat(context().ball().bottomSide()).isEqualTo(context().ball().position().y().plus(context().ball().radius()));
      });
      it("has a top y point", () -> {
        assertThat(context().ball().topSide()).isEqualTo(context().ball().position().y().minus(context().ball().radius()));
      });

      describe("when velocity is positive", ()->{
        context().ball(()-> BouncingBall.withVelocity(vector(0.1, 0.2)));
        describe("when positioned on the farther left", () -> {
          beforeEach(() -> {
            context().ball().positionOn(vector(ONE_SCALAR.minus(context().ball().radius()), 0.5));
          });
          it("x velocity becomes negative if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().x()).isEqualTo(scalar(-0.1));
          });
          it("left side becomes 1.0 if moved", () -> {
            context().ball().move();
            assertThat(context().ball().rightSide()).isEqualTo(ONE_SCALAR);
          });
        });
        describe("when positioned on the farther bottom", () -> {
          beforeEach(() -> {
            context().ball().positionOn(vector(0.5, ONE_SCALAR.minus(context().ball().radius())));
          });
          it("y velocity becomes negative if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().y()).isEqualTo(scalar(-0.2));
          });
          it("bottom side becomes 1.0 if moved", () -> {
            context().ball().move();
            assertThat(context().ball().bottomSide()).isEqualTo(ONE_SCALAR);
          });
        });
      });

      describe("when velocity is negative", ()->{
        context().ball(()-> BouncingBall.withVelocity(vector(-0.1, -0.2)));
        describe("when positioned on the farther right", () -> {
          beforeEach(() -> {
            context().ball().positionOn(vector(context().ball().radius(), 0.5));
          });
          it("x velocity becomes positive if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().x()).isEqualTo(scalar(0.1));
          });
          it("top side becomes 0.0 if moved", () -> {
            context().ball().move();
            assertThat(context().ball().leftSide()).isEqualTo(ZERO_SCALAR);
          });
        });
        describe("when positioned on the farther top", () -> {
          beforeEach(()->{
            context().ball().positionOn(vector(0.5, context().ball().radius()));
          });
          it("y velocity becomes positive if moved", () -> {
            context().ball().move();
            assertThat(context().ball().velocity().y()).isEqualTo(scalar(0.2));
          });
          it("top side becomes 0.0 if moved", () -> {
            context().ball().move();
            assertThat(context().ball().topSide()).isEqualTo(ZERO_SCALAR);
          });
        });

      });

    });
  }
}

