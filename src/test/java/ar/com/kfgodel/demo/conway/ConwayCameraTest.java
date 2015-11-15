package ar.com.kfgodel.demo.conway;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.processingo.api.space.Vector2d;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * This type captures the behavior spec for the camera
 * Created by tenpines on 14/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class ConwayCameraTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a conway camera", () -> {
      context().camera(()-> ConwayCamera.create(Vector2d.xy(10,10), Vector2d.xy(100,80), context().conwayWorld()));
      context().conwayWorld(()-> ConwayWorld.create());

      it("targets a cell position of the conways world", () -> {
        assertThat(context().camera().target()).isEqualTo(Vector2d.xy(10,10));
      });

      it("has a limited size", () -> {
        assertThat(context().camera().size()).isEqualTo(Vector2d.xy(100,80));
      });

      describe("when capturing a snapshot", () -> {
        context().snapshot(()-> context().camera().takeSnapshot());

        it("limits snapshot dimension to its size", () -> {
          Snapshot snapshot = context().snapshot();
          assertThat(snapshot.dimension()).isEqualTo(context().camera().size());
        });

        it("captures the state of the cells at its observable area", () -> {
          context().conwayWorld(()-> mock(ConwayWorld.class));

          context().camera().takeSnapshot();

          verify(context().conwayWorld())
            .getStateInside(FieldOfView.create(Vector2d.xy(-40,-30),Vector2d.xy(60,50)));
        });

        xdescribe("when a cell is captured", () -> {
          beforeEach(()->{
            Map<Vector2d, CellState> cellStates = new HashMap<>();
            cellStates.put(Vector2d.xy(-40,-30), CellState.surviving());
            WorldAreaState capturedArea = mock(WorldAreaState.class);

            when(context().conwayWorld().getStateInside(any(FieldOfView.class)))
              .thenReturn(capturedArea);
          });

          it("makes its position relative to the snapshot", () -> {
            Vector2d positionRelativeToSnapshot = context().snapshot().survivingCells().get(0);
            assertThat(positionRelativeToSnapshot).isEqualTo(Vector2d.xy(0,0));
          });
        });

      });

    });
  }
}
