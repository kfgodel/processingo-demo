package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

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
  private List<BidiVector> survivingCells;
  private List<BidiVector> dyingCells;
  private List<BidiVector> emergingCells;

  @Override
  public List<BidiVector> survivingCells() {
    if (survivingCells == null) {
      classifyCellsbyState();
    }
    return survivingCells;
  }

  @Override
  public List<BidiVector> dyingCells() {
    if (dyingCells == null) {
      classifyCellsbyState();
    }
    return dyingCells;
  }

  @Override
  public List<BidiVector> emergingCells() {
    if (emergingCells == null) {
      classifyCellsbyState();
    }
    return emergingCells;
  }

  @Override
  public BidiVector dimension() {
    return worldAreaState.dimension();
  }

  private void classifyCellsbyState() {
    survivingCells = new ArrayList<>();
    dyingCells = new ArrayList<>();
    emergingCells = new ArrayList<>();

    Set<Map.Entry<BidiVector, CellState>> entries = worldAreaState.activeCellStates().entrySet();
    for (Map.Entry<BidiVector, CellState> entry : entries) {
      BidiVector cellPosition = worldAreaState.makeRelative(entry.getKey());
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
