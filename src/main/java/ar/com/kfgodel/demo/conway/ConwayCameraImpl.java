package ar.com.kfgodel.demo.conway;

import ar.com.kfgodel.mathe.api.BidiVector;

import static ar.com.kfgodel.mathe.api.Mathe.scalar;

/**
 * Implementation of the camera
 * Created by tenpines on 14/11/15.
 */
public class ConwayCameraImpl implements ConwayCamera {

  private ConwayWorld world;
  private BidiVector target;
  private BidiVector size;

  @Override
  public Snapshot takeSnapshot() {
    FieldOfView fieldOfview = calculateFieldOfView();
    WorldAreaState areaState = world.getStateInside(fieldOfview);
    return Snapshot.create(areaState);
  }

  private FieldOfView calculateFieldOfView() {
    BidiVector margin = size.scalarProduct(scalar(0.5));
    BidiVector topLeft = target.minus(margin);
    BidiVector bottomRight = target.plus(margin);
    return FieldOfView.create(topLeft, bottomRight);
  }

  public static ConwayCameraImpl create(BidiVector target, BidiVector size, ConwayWorld world) {
    ConwayCameraImpl camera = new ConwayCameraImpl();
    camera.world = world;
    camera.target = target;
    camera.size = size;
    return camera;
  }

  @Override
  public BidiVector target() {
    return target;
  }

  @Override
  public BidiVector size() {
    return size;
  }

}
