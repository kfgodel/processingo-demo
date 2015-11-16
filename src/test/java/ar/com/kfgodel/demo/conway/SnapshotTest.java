package ar.com.kfgodel.demo.conway;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This type verifies the expected behavior of a snapshot
 * Created by tenpines on 14/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class SnapshotTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a snapshot", () -> {
      context().snapshot(() -> Snapshot.create(context().areaState()));
      context().areaState(()-> mock(WorldAreaState.class));

      it("has the dimension of the area", () -> {
        when(context().areaState().dimension()).thenReturn(Vector2d.xy(2,3));

        assertThat(context().snapshot().dimension()).isEqualTo(Vector2d.xy(2,3));
      });

      describe("when the area has all dead cells", () -> {
        beforeEach(()->{
          when(context().areaState().activeCellStates()).thenReturn(new HashMap<>());
        });
        it("has no surviving cells", () -> {
          assertThat(context().snapshot().survivingCells()).isEmpty();
        });
        it("has no dying cells", () -> {
          assertThat(context().snapshot().dyingCells()).isEmpty();
        });
        it("has no emerging cells", () -> {
          assertThat(context().snapshot().emergingCells()).isEmpty();
        });
      });

      describe("when the area has one of each type of cells", () -> {
        beforeEach(()->{
          Map<Vector2d, CellState> cellStates = new HashMap<>();
          cellStates.put(Vector2d.xy(1,1), CellState.surviving());
          cellStates.put(Vector2d.xy(2,2), CellState.dying());
          cellStates.put(Vector2d.xy(3,3), CellState.emerging());
          when(context().areaState().activeCellStates()).thenReturn(cellStates);
        });
        it("has no surviving cells", () -> {
          assertThat(context().snapshot().survivingCells().get(0)).isEqualTo(Vector2d.xy(1,1));
        });
        it("has no dying cells", () -> {
          assertThat(context().snapshot().dyingCells().get(0)).isEqualTo(Vector2d.xy(2,2));
        });
        it("has no emerging cells", () -> {
          assertThat(context().snapshot().emergingCells().get(0)).isEqualTo(Vector2d.xy(3,3));
        });
      });

    });
  }
}
