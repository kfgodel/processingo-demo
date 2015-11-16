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
      context().conwayWorld(() -> ConwayWorld.create(context().initialCells()));
      context().initialCells(()-> new Vector2d[]{
                                                                  Vector2d.xy(2,-1),
        Vector2d.xy(-1, 0), Vector2d.xy(0, 0), Vector2d.xy(1, 0), Vector2d.xy(2, 0),
                                                                  Vector2d.xy(2, 1)
      });
      context().cellStates(()-> context().conwayWorld()
        .getStateInside(FieldOfView.create(Vector2d.xy(-10, -10), Vector2d.xy(10, 10)))
        .activeCellStates()
      );

      it("starts with the given surviving cells", () -> {
        assertThat(context().cellStates().get(Vector2d.xy(2,-1))).isEqualTo(CellState.surviving());
        assertThat(context().cellStates().get(Vector2d.xy(-1,0))).isEqualTo(CellState.surviving());
        assertThat(context().cellStates().get(Vector2d.xy(0,0))).isEqualTo(CellState.surviving());
        assertThat(context().cellStates().get(Vector2d.xy(1,0))).isEqualTo(CellState.surviving());
        assertThat(context().cellStates().get(Vector2d.xy(2,0))).isEqualTo(CellState.surviving());
        assertThat(context().cellStates().get(Vector2d.xy(2,1))).isEqualTo(CellState.surviving());
      });

      describe("when the generation changes", () -> {
        beforeEach(()->{
          context().conwayWorld().advanceOneGeneration();
        });

        it("kills the cells with less than 2 live neighbors", () -> {
          assertThat(context().cellStates().get(Vector2d.xy(-1,0))).isEqualTo(CellState.dying());
        });

        it("keeps cells alive with 2 or 3 live neighbors", () -> {
          assertThat(context().cellStates().get(Vector2d.xy(2,-1))).isEqualTo(CellState.surviving());
          assertThat(context().cellStates().get(Vector2d.xy(0,0))).isEqualTo(CellState.surviving());
          assertThat(context().cellStates().get(Vector2d.xy(2,0))).isEqualTo(CellState.surviving());
          assertThat(context().cellStates().get(Vector2d.xy(2,1))).isEqualTo(CellState.surviving());
        });

        it("kills cells with more than 3 live neighbors", () -> {
          assertThat(context().cellStates().get(Vector2d.xy(1,0))).isEqualTo(CellState.dying());
        });

        it("revives cells with exactly 3 live neighbors", () -> {
          assertThat(context().cellStates().get(Vector2d.xy(0,-1))).isEqualTo(CellState.emerging());
          assertThat(context().cellStates().get(Vector2d.xy(3,0))).isEqualTo(CellState.emerging());
          assertThat(context().cellStates().get(Vector2d.xy(0,1))).isEqualTo(CellState.emerging());
        });

      });

    });
  }
}
