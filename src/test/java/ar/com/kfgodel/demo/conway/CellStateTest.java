package ar.com.kfgodel.demo.conway;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.dgarcia.javaspec.api.Variable;
import ar.com.kfgodel.demo.DemoTestContext;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of cell states
 * Created by tenpines on 14/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class CellStateTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a cell state", () -> {

      it("can be one of 3 different states", () -> {
        assertThat(CellState.surviving()).isNotEqualTo(CellState.dying());
        assertThat(CellState.surviving()).isNotEqualTo(CellState.emerging());
        assertThat(CellState.emerging()).isNotEqualTo(CellState.dying());
      });

      it("runs only the surviving action for the surviving state", () -> {
        Variable<Boolean> emergingExecuted = Variable.create();
        Variable<Boolean> dyingExecuted = Variable.create();
        Variable<Boolean> survivingExecuted = Variable.create();

        CellState.surviving().whenSurviving(()-> survivingExecuted.set(true));
        CellState.surviving().whenDying(()-> dyingExecuted.set(true));
        CellState.surviving().whenEmerging(()-> emergingExecuted.set(true));

        assertThat(survivingExecuted.get()).isTrue();
        assertThat(dyingExecuted.get()).isNull();
        assertThat(emergingExecuted.get()).isNull();
      });

      it("runs only the dying action for the dying state", () -> {
        Variable<Boolean> emergingExecuted = Variable.create();
        Variable<Boolean> dyingExecuted = Variable.create();
        Variable<Boolean> survivingExecuted = Variable.create();

        CellState.dying().whenSurviving(()-> survivingExecuted.set(true));
        CellState.dying().whenDying(()-> dyingExecuted.set(true));
        CellState.dying().whenEmerging(()-> emergingExecuted.set(true));

        assertThat(survivingExecuted.get()).isNull();
        assertThat(dyingExecuted.get()).isTrue();
        assertThat(emergingExecuted.get()).isNull();
      });

      it("runs only the emerging action for the emerging state", () -> {
        Variable<Boolean> emergingExecuted = Variable.create();
        Variable<Boolean> dyingExecuted = Variable.create();
        Variable<Boolean> survivingExecuted = Variable.create();

        CellState.emerging().whenSurviving(()-> survivingExecuted.set(true));
        CellState.emerging().whenDying(()-> dyingExecuted.set(true));
        CellState.emerging().whenEmerging(()-> emergingExecuted.set(true));

        assertThat(survivingExecuted.get()).isNull();
        assertThat(dyingExecuted.get()).isNull();
        assertThat(emergingExecuted.get()).isTrue();
      });

    });
  }
}
