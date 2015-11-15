package ar.com.kfgodel.demo.conway;

/**
 * This type represents the state of a cell
 * Created by tenpines on 14/11/15.
 */
public interface CellState {
  static CellState surviving(){
    return SurvivingState.INSTANCE;
  }

  static CellState dying() {
    return DyingState.INSTANCE;
  }

  static CellState emerging() {
    return EmergingState.INSTANCE;
  }

  void whenSurviving(Runnable action);

  void whenDying(Runnable action);

  void whenEmerging(Runnable action);
}
