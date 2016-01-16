package ar.com.kfgodel.demo.ants;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.Set;
import java.util.function.BiConsumer;

/**
 * This type represents the type of cell an ant can be on
 * Created by ikari on 11/01/2016.
 */
public enum CellType {
  WHITE {
    @Override
    public void turnAnt(Ant ant) {
      ant.turnRight();
    }

    @Override
    public BiConsumer<Set<BidiVector>, BidiVector> getFlipOperation() {
      return Set::add;
    }
  },
  BLACK {
    @Override
    public void turnAnt(Ant ant) {
      ant.turnLeft();
    }

    @Override
    public BiConsumer<Set<BidiVector>, BidiVector> getFlipOperation() {
      return Set::remove;
    }
  };

  /**
   * Changes the given ant direction according to this cell type
   */
  public abstract void turnAnt(Ant ant);

  public abstract BiConsumer<Set<BidiVector>, BidiVector> getFlipOperation();
}
