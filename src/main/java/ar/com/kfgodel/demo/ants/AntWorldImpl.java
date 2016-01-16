package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiInterval;
import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.mathe.api.Scalar;
import ar.com.kfgodel.nary.impl.NaryFromNative;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.function.BiConsumer;

import static ar.com.kfgodel.mathe.api.Mathe.intervalInclusiveExclusive;

/**
 * This type implements the ant world
 * Created by tenpines on 04/11/15.
 */
public class AntWorldImpl implements AntWorld {

  private BidiInterval cellInterval;
  private BidiVector cellSpace;
  private Ant ant;
  private Set<BidiVector> blackCells;

  public static AntWorldImpl create(BidiVector cellSpace) {
    AntWorldImpl world = new AntWorldImpl();
    world.cellSpace = cellSpace;
    world.cellInterval = BidiInterval.from(intervalInclusiveExclusive(Scalar.ZERO, cellSpace.x()), intervalInclusiveExclusive(Scalar.ZERO, cellSpace.y()));

    BidiVector initialAntPosition = cellSpace.center().integered();
    BidiVector initialAntDirection = Mathe.vector(0, -1);
    world.ant = Ant.create(initialAntPosition, initialAntDirection);
    world.blackCells = new CopyOnWriteArraySet<>();
    return world;
  }

  @Override
  public void advanceOneTimeUnit() {
    CellType steppedType = getCellTypeUnderAnt();
    steppedType.turnAnt(ant());

    if(antCanMove()){
      flipCellType();
      ant().advance();
    }
  }

  private CellType getCellTypeUnderAnt() {
    return NaryFromNative.of(ant.position())
      .filter(blackCells::contains)
      .map((blackCell)-> CellType.BLACK)
      .findAny()
      .orElse(CellType.WHITE);
  }

  private void flipCellType() {
    CellType steppedType = getCellTypeUnderAnt();
    BiConsumer<Set<BidiVector>, BidiVector> flipOperation = steppedType.getFlipOperation();
    flipOperation.accept(blackCells, ant.position());
  }

  private boolean antCanMove() {
    BidiVector nextPosition = ant().nextPosition();
    return cellInterval.contains(nextPosition);
  }

  @Override
  public Set<BidiVector> blackCells() {
    return blackCells;
  }

  @Override
  public Ant ant() {
    return ant;
  }

  @Override
  public BidiVector cellSpace() {
    return cellSpace;
  }

  @Override
  public void setBlackCellOn(BidiVector cellCoordinate) {
    blackCells.add(cellCoordinate);
  }

  @Override
  public void replaceAntWith(Ant newAnt) {
    this.ant = newAnt;
  }


}
