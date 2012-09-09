package models.linkedin;

import java.io.Serializable;
import java.util.List;

public class Positions implements Serializable {
  List<PositionValue> values;

  public List<PositionValue> getValues() {
    return values;
  }

  public void setValues(List<PositionValue> values) {
    this.values = values;
  }
}
