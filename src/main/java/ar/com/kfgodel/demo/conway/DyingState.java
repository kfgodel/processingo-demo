package ar.com.kfgodel.demo.conway;

/**
 * Created by tenpines on 14/11/15.
 */
public class DyingState implements CellState {

  public static final DyingState INSTANCE = new DyingState();

  @Override
  public void whenSurviving(Runnable action) {
    // Do nothing
  }

  @Override
  public void whenDying(Runnable action) {
    action.run();
  }

  @Override
  public void whenEmerging(Runnable action) {
    //Do nothing
  }

}
