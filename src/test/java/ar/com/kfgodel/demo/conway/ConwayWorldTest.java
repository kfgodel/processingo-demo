package ar.com.kfgodel.demo.conway;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of the conways world
 * Created by tenpines on 15/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class ConwayWorldTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a conway world", () -> {
      context().conwayWorld(() -> ConwayWorld.create(Vector2d.xy(1, 1)));
      it("starts with the given surviving cells", () -> {
        ConwayWorld.create(Vector2d.xy(1,1));

        WorldAreaState area = context().conwayWorld()
          .getStateInside(FieldOfView.create(Vector2d.xy(0, 0), Vector2d.xy(10, 10)));

        assertThat(area.cellStates().get(Vector2d.xy(1,1))).isEqualTo(CellState.surviving());
      });
    });
  }
}
