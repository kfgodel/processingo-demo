package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.processingo.api.space.Vector2d;

import java.util.Collections;
import java.util.List;

/**
 * This type implements the snapshot of a conways world
 * Created by tenpines on 14/11/15.
 */
public class SnapshotImpl implements Snapshot {

  private WorldAreaState worldAreaState;

  @Override
  public List<Vector2d> survivingCells() {
    return Collections.emptyList();
  }

  @Override
  public List<Vector2d> dyingCells() {
    return Collections.emptyList();
  }

  @Override
  public List<Vector2d> emergingCells() {
    return Collections.emptyList();
  }

  @Override
  public Vector2d dimension() {
    return worldAreaState.dimension();
  }

  public static SnapshotImpl create(WorldAreaState worldAreaState) {
    SnapshotImpl snapshot = new SnapshotImpl();
    snapshot.worldAreaState = worldAreaState;
    return snapshot;
  }

}
