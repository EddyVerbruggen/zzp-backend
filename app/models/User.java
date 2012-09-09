package models;

import play.data.validation.InPast;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

@Entity
public class User extends Model {
  public String name;
  public String email;
  public String image;
  public String linkedInID;

  @Required
  public String uuid;

  @OneToMany
  public List<Status> statusList;

  @OneToOne
  public Token linkedInRequestToken;

  @OneToOne
  public Token linkedInAccessToken;

  // TODO add list of companies the user is part of

  @Required
  @InPast
  public Date registrationDate;

  public User(String name, Date registrationDate, String uuid) {
    this.name = name;
    this.registrationDate = registrationDate;
    this.uuid = uuid;
  }

  public static User findByUUID(String uuid) {
    return find("uuid", uuid).first();
  }

  @Override
  public String toString() {
    return "User{" +
        "name='" + name + '\'' +
        ", uuid='" + uuid + '\'' +
        '}';
  }
}