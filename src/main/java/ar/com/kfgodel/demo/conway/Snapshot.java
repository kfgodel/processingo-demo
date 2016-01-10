package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import java.util.List;

/**
 * This type represents a piece of the conway space in a certain moment
 *
 * Created by tenpines on 14/11/15.
 */
public interface Snapshot {
  /**
   * @return The positions relative to this snapshot that contains living cells from previous generation
   */
  List<BidiVector> survivingCells();

  /**
   * @return The positions relative to this snapshot that contains cells dying in this generation
   */
  List<BidiVector> dyingCells();

  /**
   * @return The positions relative to this snapshot that contains cells appearing in this generation
   */
  List<BidiVector> emergingCells();

  /**
   * @return The cell size of this snapshot
   */
  BidiVector dimension();

  static Snapshot create(WorldAreaState areaState) {
    return SnapshotImpl.create(areaState);
  }
}
