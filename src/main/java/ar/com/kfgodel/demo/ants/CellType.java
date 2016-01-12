package ar.com.kfgodel.demo.ants;

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
  },
  BLACK {
    @Override
    public void turnAnt(Ant ant) {
      ant.turnLeft();
    }
  };

  /**
   * Changes the given ant direction according to this cell type
   */
  public abstract void turnAnt(Ant ant);
}
