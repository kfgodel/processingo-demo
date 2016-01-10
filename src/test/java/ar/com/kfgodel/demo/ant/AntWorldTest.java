package ar.com.kfgodel.demo.ant;

import ar.com.dgarcia.javaspec.api.JavaSpec;
import ar.com.dgarcia.javaspec.api.JavaSpecRunner;
import ar.com.kfgodel.demo.DemoTestContext;
import ar.com.kfgodel.demo.ants.Ant;
import ar.com.kfgodel.demo.ants.AntWorld;
import ar.com.kfgodel.mathe.api.BidiVector;
import org.junit.runner.RunWith;

import java.util.Set;

import static ar.com.kfgodel.mathe.api.Mathe.vector;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * This type verifies the behavior of the ant world simulation
 * Created by tenpines on 04/11/15.
 */
@RunWith(JavaSpecRunner.class)
public class AntWorldTest extends JavaSpec<DemoTestContext> {
  @Override
  public void define() {
    describe("an ant world", () -> {
      context().antWorld(() -> AntWorld.create(vector(10, 10)));

      it("has a default cell space", () -> {
        assertThat(context().antWorld().cellSpace()).isEqualTo(vector(10, 10));
      });

      it("has no black cells", () -> {
        assertThat(context().antWorld().blackCells().size()).isEqualTo(0);
      });

      it("has an ant in the center pointing upwards", () -> {
        Ant ant = context().antWorld().ant();
        assertThat(ant.position()).isEqualTo(vector(5,5));
        assertThat(ant.direction()).isEqualTo(vector(0,-1));
      });

      describe("when ant is on a white cell and time advances", () -> {
        beforeEach(()->{
          context().antWorld().advanceOneTimeUnit();
        });

        it("flips its white cell to black", () -> {
          Set<BidiVector> blackCells = context().antWorld().blackCells();
          assertThat(blackCells).containsOnly(vector(5, 5));
        });

        it("turns ant to right", () -> {
          assertThat(context().antWorld().ant().direction()).isEqualTo(vector(1,0));
        });

        it("advances the ant to its right cell", () -> {
          assertThat(context().antWorld().ant().position()).isEqualTo(vector(6,5));
        });
      });

      describe("when ant is on a black cell and time advances", () -> {
        beforeEach(()->{
          context().antWorld().setBlackCellOn(vector(5,5));
          context().antWorld().advanceOneTimeUnit();
        });

        it("flips its black cell to white", () -> {
          Set<BidiVector> blackCells = context().antWorld().blackCells();
          assertThat(blackCells).isEmpty();
        });

        it("turns ant to left", () -> {
          assertThat(context().antWorld().ant().direction()).isEqualTo(vector(-1,0));
        });

        it("advances the ant to its left cell", () -> {
          assertThat(context().antWorld().ant().position()).isEqualTo(vector(4,5));
        });
      });

      describe("when ant is on a leftmost white cell pointing upwards and time advances", () -> {
        beforeEach(()->{
          context().antWorld().replaceAntWith(Ant.create(vector(9,5), vector(0, -1)));
          context().antWorld().advanceOneTimeUnit();
        });

        it("stays where it is", () -> {
          assertThat(context().antWorld().ant().position()).isEqualTo(vector(9,5));
        });

        it("still turns to the right", () -> {
          assertThat(context().antWorld().ant().direction()).isEqualTo(vector(1,0));
        });

        it("doesn't flip the cell color", () -> {
          assertThat(context().antWorld().blackCells()).isEmpty();
        });
      });

      describe("when ant is on a rightmost white cell pointing downwards and time advances", () -> {
        beforeEach(()->{
          context().antWorld().replaceAntWith(Ant.create(vector(0,5), vector(0, 1)));
          context().antWorld().advanceOneTimeUnit();
        });

        it("stays where it is", () -> {
          assertThat(context().antWorld().ant().position()).isEqualTo(vector(0,5));
        });

        it("still turns to the right", () -> {
          assertThat(context().antWorld().ant().direction()).isEqualTo(vector(-1,0));
        });

        it("doesn't flip the cell color", () -> {
          assertThat(context().antWorld().blackCells()).isEmpty();
        });
      });

      describe("when ant is on a topmost white cell pointing leftwards and time advances", () -> {
        beforeEach(()->{
          context().antWorld().replaceAntWith(Ant.create(vector(5,0), vector(-1, 0)));
          context().antWorld().advanceOneTimeUnit();
        });

        it("stays where it is", () -> {
          assertThat(context().antWorld().ant().position()).isEqualTo(vector(5,0));
        });

        it("still turns to the right", () -> {
          assertThat(context().antWorld().ant().direction()).isEqualTo(vector(0,-1));
        });

        it("doesn't flip the cell color", () -> {
          assertThat(context().antWorld().blackCells()).isEmpty();
        });
      });

      describe("when ant is on a bottommost white cell pointing rightwards and time advances", () -> {
        beforeEach(()->{
          context().antWorld().replaceAntWith(Ant.create(vector(5,9), vector(1, 0)));
          context().antWorld().advanceOneTimeUnit();
        });

        it("stays where it is", () -> {
          assertThat(context().antWorld().ant().position()).isEqualTo(vector(5,9));
        });

        it("still turns to the right", () -> {
          assertThat(context().antWorld().ant().direction()).isEqualTo(vector(0,1));
        });

        it("doesn't flip the cell color", () -> {
          assertThat(context().antWorld().blackCells()).isEmpty();
        });
      });


    });
  }


}
