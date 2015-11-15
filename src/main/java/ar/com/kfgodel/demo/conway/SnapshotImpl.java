package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This type implements the snapshot of a conways world
 * Created by tenpines on 14/11/15.
 */
public class SnapshotImpl implements Snapshot {

  private WorldAreaState worldAreaState;
  private List<Vector2d> survivingCells;
  private List<Vector2d> dyingCells;
  private List<Vector2d> emergingCells;

  @Override
  public List<Vector2d> survivingCells() {
    if (survivingCells == null) {
      classifyCellsbyState();
    }
    return survivingCells;
  }

  @Override
  public List<Vector2d> dyingCells() {
    if (dyingCells == null) {
      classifyCellsbyState();
    }
    return dyingCells;
  }

  @Override
  public List<Vector2d> emergingCells() {
    if (emergingCells == null) {
      classifyCellsbyState();
    }
    return emergingCells;
  }

  @Override
  public Vector2d dimension() {
    return worldAreaState.dimension();
  }

  private void classifyCellsbyState() {
    survivingCells = new ArrayList<>();
    dyingCells = new ArrayList<>();
    emergingCells = new ArrayList<>();

    Set<Map.Entry<Vector2d, CellState>> entries = worldAreaState.cellStates().entrySet();
    for (Map.Entry<Vector2d, CellState> entry : entries) {
      Vector2d cellPosition = entry.getKey();
      CellState cellState = entry.getValue();
      cellState.whenSurviving(() -> survivingCells.add(cellPosition));
      cellState.whenDying(() -> dyingCells.add(cellPosition));
      cellState.whenEmerging(() -> emergingCells.add(cellPosition));
    }
  }

  public static SnapshotImpl create(WorldAreaState worldAreaState) {
    SnapshotImpl snapshot = new SnapshotImpl();
    snapshot.worldAreaState = worldAreaState;
    return snapshot;
  }

}
