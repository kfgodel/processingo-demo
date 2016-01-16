package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiVector;
import ar.com.kfgodel.mathe.api.Mathe;
import ar.com.kfgodel.mathe.api.Scalar;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * This type implements the ant world
 * Created by tenpines on 04/11/15.
 */
public class AntWorldImpl implements AntWorld {

  private BidiVector cellSpace;
  private Ant ant;
  private Set<BidiVector> blackCells;

  public static AntWorldImpl create(BidiVector cellSpace) {
    AntWorldImpl world = new AntWorldImpl();
    world.cellSpace = cellSpace;
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
      flipAntCell();
      ant().advance();
    }
  }

  private CellType getCellTypeUnderAnt() {
    return blackCells.stream()
      .filter(ant().position()::equals)
      .map((blackCell)-> CellType.BLACK)
      .findAny()
      .orElse(CellType.WHITE);
  }

  private void flipAntCell() {
    if(antIsOnBlackCell()){
      blackCells.remove(ant().position());
    }else{
      blackCells.add(ant().position());
    }
  }

  private boolean antCanMove() {
    BidiVector nextPosition = ant().nextPosition();
    boolean willBeOnValidXSpace = nextPosition.x().isGreaterOrEqualTo(Scalar.ZERO) && nextPosition.x().isLessThan(cellSpace.x());
    boolean willBeOnValidYSpace = nextPosition.y().isGreaterOrEqualTo(Scalar.ZERO) && nextPosition.y().isLessThan(cellSpace.y());
    return willBeOnValidXSpace && willBeOnValidYSpace;
  }

  private boolean antIsOnBlackCell() {
    return blackCells.contains(ant().position());
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
