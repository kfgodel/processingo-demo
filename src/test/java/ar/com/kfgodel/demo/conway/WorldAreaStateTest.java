package ar.com.kfgodel.demo.conway;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import com.google.common.collect.Sets;
import org.junit.runner.RunWith;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * This type verifies the behavior of a world area
 * Created by tenpines on 15/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class WorldAreaStateTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("an area state", () -> {
      context().areaState(()->
        WorldAreaState.create(context().fieldOfView(), context().previousCells(), context().currentCells())
      );
      context().fieldOfView(()-> mock(FieldOfView.class));
      context().currentCells(HashSet::new);
      context().previousCells(HashSet::new);

      it("has the dimension of its field of view", () -> {
        when(context().fieldOfView().dimension()).thenReturn(Vector2d.xy(4,5));

        assertThat(context().areaState().dimension()).isEqualTo(Vector2d.xy(4,5));
      });

      describe("makeRelative", ()->{
        context().fieldOfView(()-> FieldOfView.create(Vector2d.xy(-10, -10), Vector2d.xy(20, 20)));
        it("changes a position to use the area's top left corner as origin", () -> {
          Vector2d relativePosition = context().areaState().makeRelative(Vector2d.xy(0, 0));
          assertThat(relativePosition).isEqualTo(Vector2d.xy(10, 10));
        });
      });

      describe("cellState", () -> {
        context().cellStates(()-> context().areaState().activeCellStates());

        beforeEach(()->{
          when(context().fieldOfView().includes(any(Vector2d.class))).thenReturn(true);
        });

        it("is emerging if the cell is present in current living set but not on previous", () -> {
          context().currentCells(()-> Sets.newHashSet(Vector2d.xy(1,2)));

          CellState cellState = context().cellStates().get(Vector2d.xy(1, 2));

          assertThat(cellState).isEqualTo(CellState.emerging());
        });

        it("is dying if it's absent in current, and present in previous", () -> {
          context().previousCells(()-> Sets.newHashSet(Vector2d.xy(1,2)));

          CellState cellState = context().cellStates().get(Vector2d.xy(1, 2));

          assertThat(cellState).isEqualTo(CellState.dying());

        });

        it("is surviving if it's present in both", () -> {
          context().previousCells(()-> Sets.newHashSet(Vector2d.xy(1,2)));
          context().currentCells(()-> Sets.newHashSet(Vector2d.xy(1,2)));

          CellState cellState = context().cellStates().get(Vector2d.xy(1, 2));

          assertThat(cellState).isEqualTo(CellState.surviving());
        });

        it("excludes cells outside the fieldOfView", () -> {
          context().previousCells(()-> Sets.newHashSet(Vector2d.xy(1,2)));
          context().currentCells(()-> Sets.newHashSet(Vector2d.xy(1,2)));

          when(context().fieldOfView().includes(any(Vector2d.class))).thenReturn(false);

          assertThat(context().cellStates()).isEmpty();
        });
      });
    });
  }
}
