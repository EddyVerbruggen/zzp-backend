package models;

import play.data.validation.InPast;
import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Status extends Model {
  @Required
  public String text;

  @Required
  @InPast
  public Date date;

  public Status(String text, Date date) {
    this.text = text;
    this.date = date;
  }


  @Override
  public String toString() {
    return "Status{" +
        "text='" + text + '\'' +
        ", date=" + date +
        '}';
  }
}

