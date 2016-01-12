package ar.com.kfgodel.demo.ant;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.demo.ants.Ant;
import ar.com.kfgodel.demo.ants.CellType;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * This type verifies the behavior of a cell type
 * Created by ikari on 11/01/2016.
 */
@RunWith(JavaSpecRunner.class)
public class CellTypeTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("a cell type", ()->{
      describe("values", () -> {
        it("can be black or white",()->{
          assertThat(CellType.values()).containsExactly(CellType.WHITE, CellType.BLACK);
        });
      });

      describe("turnAnt", () -> {
        it("turns the ant left if black",()->{
          Ant ant = mock(Ant.class);
          CellType.BLACK.turnAnt(ant);
          verify(ant).turnLeft();
        });
        it("turns the ant right if white",()->{
          Ant ant = mock(Ant.class);
          CellType.WHITE.turnAnt(ant);
          verify(ant).turnRight();
        });   
      });


    });
  }
}