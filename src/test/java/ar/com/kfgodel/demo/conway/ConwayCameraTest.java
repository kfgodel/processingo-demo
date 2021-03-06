package ar.com.kfgodel.demo.conway;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.mathe.api.BidiVector;
import org.junit.runner.RunWith;

import static ar.com.kfgodel.mathe.api.Mathe.vector;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * This type captures the behavior spec for the camera
 * Created by tenpines on 14/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class ConwayCameraTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a conway camera", () -> {
      context().camera(()-> ConwayCamera.create(vector(0, 0), vector(100,80), context().conwayWorld()));
      context().conwayWorld(()-> ConwayWorld.create(vector(1, 1)));

      it("targets a cell position of the conways world", () -> {
        assertThat(context().camera().target()).isEqualTo(vector(0,0));
      });

      it("has a limited size", () -> {
        assertThat(context().camera().size()).isEqualTo(vector(100,80));
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
            .getStateInside(FieldOfView.create(vector(-50,-40),vector(50, 40)));
        });

        describe("when a cell is in the frame", () -> {
          it("makes its position relative to the snapshot", () -> {
            BidiVector positionRelativeToSnapshot = context().snapshot().survivingCells().get(0);
            assertThat(positionRelativeToSnapshot).isEqualTo(vector(51,41));
          });
        });

      });

    });
  }
}
