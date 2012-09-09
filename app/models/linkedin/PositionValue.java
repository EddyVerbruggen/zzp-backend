package models.linkedin;

import java.io.Serializable;

public class PositionValue implements Serializable {
  private Company company;

  public Company getCompany() {
    return company;
  }

  public void setCompany(Company company) {
    this.company = company;
  }
}
