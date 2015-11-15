package ar.com.kfgodel.demo.conway;

/**
 * Created by tenpines on 14/11/15.
 */
public class EmergingState implements CellState{

  public static final EmergingState INSTANCE = new EmergingState();

  @Override
  public void whenSurviving(Runnable action) {
    // Do nothing
  }

  @Override
  public void whenDying(Runnable action) {
    // Do nothing
  }

  @Override
  public void whenEmerging(Runnable action) {
    action.run();
  }

}
