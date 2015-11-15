package ar.com.kfgodel.demo.ant;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.demo.ants.Ant;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import org.assertj.core.data.Offset;
import org.junit.runner.RunWith;

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
      context().ant(() -> Ant.create(Vector2d.xy(1, 1), context().antDirection()));
      context().antDirection(() -> Vector2d.xy(0, 1));
      it("has a position", () -> {
        assertThat(context().ant().position()).isEqualTo(Vector2d.xy(1, 1));
      });
      it("has a direction", () -> {
        assertThat(context().ant().direction()).isEqualTo(Vector2d.xy(0, 1));
      });
      it("has a next position on its direction", () -> {
        assertThat(context().ant().nextPosition()).isEqualTo(Vector2d.xy(1, 2));
      });

      it("moves to its next position when advanced", () -> {
        context().ant().advance();
        assertThat(context().ant().position()).isEqualTo(Vector2d.xy(1, 2));
      });

      describe("when pointing up", () -> {
        context().antDirection(()-> Vector2d.xy(0, -1));

        it("points to the right if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction().x()).isEqualTo(1);
          assertThat(context().ant().direction().y()).isEqualTo(0, Offset.offset(0.00000001f));
        });

        it("points to the left if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction().x()).isEqualTo(-1);
          assertThat(context().ant().direction().y()).isEqualTo(0, Offset.offset(0.000000001f));
        });

      });

      describe("when pointing down", () -> {
        context().antDirection(()-> Vector2d.xy(0, 1));

        it("points to the left if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction()).isEqualTo(Vector2d.xy(-1, 0));
        });

        it("points to the right if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction()).isEqualTo(Vector2d.xy(1, 0));
        });

      });

      describe("when pointing left", () -> {
        context().antDirection(()-> Vector2d.xy(1, 0));

        it("points down if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction()).isEqualTo(Vector2d.xy(0, 1));
        });

        it("points up if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction()).isEqualTo(Vector2d.xy(0, -1));
        });

      });

      describe("when pointing right", () -> {
        context().antDirection(()-> Vector2d.xy(-1, 0));

        it("points up if turned right", () -> {
          context().ant().turnRight();
          assertThat(context().ant().direction().x()).isEqualTo(0, Offset.offset(0.00000001f));
          assertThat(context().ant().direction().y()).isEqualTo(-1);
        });

        it("points down if turned left", () -> {
          context().ant().turnLeft();
          assertThat(context().ant().direction().x()).isEqualTo(0, Offset.offset(0.000000001f));
          assertThat(context().ant().direction().y()).isEqualTo(1);
        });

      });

    });
  }
}
