package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tenpines on 14/11/15.
 */
public class WorldAreaStateImpl implements WorldAreaState {

  private FieldOfView fieldOfView;
  private Set<Vector2d> previousLivingCells;
  private Set<Vector2d> currentLivingCells;

  public static WorldAreaStateImpl create(FieldOfView fieldOfView, Set<Vector2d> previousLivingCells, Set<Vector2d> currentLivingCells) {
    WorldAreaStateImpl state = new WorldAreaStateImpl();
    state.fieldOfView = fieldOfView;
    state.previousLivingCells = previousLivingCells;
    state.currentLivingCells = currentLivingCells;
    return state;
  }

  @Override
  public Vector2d dimension() {
    return fieldOfView.dimension();
  }

  @Override
  public Map<Vector2d, CellState> cellStates() {
    Map<Vector2d, CellState> statePerPosition = new HashMap<>();

    previousLivingCells.stream()
      .filter(fieldOfView::includes)
      .filter(currentLivingCells::contains)
      .forEach((survivingPosition) -> statePerPosition.put(survivingPosition, CellState.surviving()));

    previousLivingCells.stream()
      .filter(fieldOfView::includes)
      .filter((dyingPosition)-> !currentLivingCells.contains(dyingPosition))
      .forEach((dyingPosition) -> statePerPosition.put(dyingPosition, CellState.dying()));

    currentLivingCells.stream()
      .filter(fieldOfView::includes)
      .filter((emergingPosition) -> !previousLivingCells.contains(emergingPosition))
      .forEach((emergingPosition) -> statePerPosition.put(emergingPosition, CellState.emerging()));

    return statePerPosition;
  }

}
