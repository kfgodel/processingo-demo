package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by tenpines on 14/11/15.
 */
public class WorldAreaStateImpl implements WorldAreaState {

  private FieldOfView fieldOfView;
  private Set<BidiVector> previousLivingCells;
  private Set<BidiVector> currentLivingCells;

  public static WorldAreaStateImpl create(FieldOfView fieldOfView, Set<BidiVector> previousLivingCells, Set<BidiVector> currentLivingCells) {
    WorldAreaStateImpl state = new WorldAreaStateImpl();
    state.fieldOfView = fieldOfView;
    state.previousLivingCells = previousLivingCells;
    state.currentLivingCells = currentLivingCells;
    return state;
  }

  @Override
  public BidiVector dimension() {
    return fieldOfView.dimension();
  }

  @Override
  public Map<BidiVector, CellState> activeCellStates() {
    Map<BidiVector, CellState> statePerPosition = new HashMap<>();

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

  @Override
  public BidiVector makeRelative(BidiVector absolute) {
    return fieldOfView.makeRelative(absolute);
  }

}
