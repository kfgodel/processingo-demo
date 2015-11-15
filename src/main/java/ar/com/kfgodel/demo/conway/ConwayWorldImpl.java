package ar.com.kfgodel.demo.conway;

/**
 * Created by tenpines on 14/11/15.
 */
public class ConwayWorldImpl implements ConwayWorld {

  @Override
  public void advanceOneGeneration() {

  }

  public static ConwayWorldImpl create(){
      ConwayWorldImpl world = new ConwayWorldImpl();
      return world;
  }

  @Override
  public WorldAreaState getStateInside(FieldOfView segment) {
    return WorldAreaState.create(segment);
  }


}
