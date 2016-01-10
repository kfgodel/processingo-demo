package ar.com.kfgodel.demo.ant;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.demo.ants.Ant;
import org.assertj.core.data.Offset;
import org.junit.runner.RunWith;

import static ar.com.kfgodel.mathe.api.Mathe.vector;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of the ant
 * Created by tenpines on 04/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class AntTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("an ant", () -> {
      context().ant(() -> Ant.create(vector(1, 1), context().antDirection()));
      context().antDirection(() -> vector(0, 1));
      it("has a position", () -> {
        assertThat(context().ant().position()).isEqualTo(vector(1, 1));
      });
      it("has a direction", () -> {
        assertThat(context().ant().direction()).isEqualTo(vector(0, 1));
      });
      it("has a next position on its direction", () -> {
        assertThat(context().ant().nextPosition()).isEqualTo(vector(1, 2));
      });

      it("moves to its next position when advanced", () -> {
        context().ant().advance();
        assertThat(context().ant().position()).isEqualTo(vector(1, 2));
      });

      describe("when pointing up", () -> {
        context().antDirection(()-> vector(0, -1));

        it("points to the right if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction().x().asFloat()).isEqualTo(1);
          assertThat(context().ant().direction().y().asFloat()).isEqualTo(0, Offset.offset(0.00000001f));
        });

        it("points to the left if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction().x().asFloat()).isEqualTo(-1);
          assertThat(context().ant().direction().y().asFloat()).isEqualTo(0, Offset.offset(0.000000001f));
        });

      });

      describe("when pointing down", () -> {
        context().antDirection(()-> vector(0, 1));

        it("points to the left if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction()).isEqualTo(vector(-1, 0));
        });

        it("points to the right if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction()).isEqualTo(vector(1, 0));
        });

      });

      describe("when pointing left", () -> {
        context().antDirection(()-> vector(1, 0));

        it("points down if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction()).isEqualTo(vector(0, 1));
        });

        it("points up if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction()).isEqualTo(vector(0, -1));
        });

      });

      describe("when pointing right", () -> {
        context().antDirection(()-> vector(-1, 0));

        it("points up if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction().x().asFloat()).isEqualTo(0, Offset.offset(0.00000001f));
          assertThat(context().ant().direction().y().asFloat()).isEqualTo(-1);
        });

        it("points down if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction().x().asFloat()).isEqualTo(0, Offset.offset(0.000000001f));
          assertThat(context().ant().direction().y().asFloat()).isEqualTo(1);
        });

      });

    });
  }
}
