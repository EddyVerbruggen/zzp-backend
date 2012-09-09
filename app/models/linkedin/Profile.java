package models.linkedin;

import java.io.Serializable;

public class Profile implements Serializable {
  private String id;
  private String firstName;
  private String lastName;
  private String pictureUrl;

  private Positions positions;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getPictureUrl() {
    return pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }

  public Positions getPositions() {
    return positions;
  }

  public void setPositions(Positions positions) {
    this.positions = positions;
  }
}