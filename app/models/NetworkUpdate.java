package models;

import play.db.jpa.Model;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.List;

@Entity
public class NetworkUpdate extends Model {

  public User user;
  @Transient
  public List<NetworkUpdateType> networkUpdateTypes = new ArrayList<NetworkUpdateType>(); // TODO this needs to be removed as its not state of the networkupdate, but of the relation between the user and this update (prolly best to add it dynamically when loading, not from the database)
//  @Required
//  @InPast
  public String date;
  public String message;
  public int nrOfReactions;

  public NetworkUpdate(User user, List<NetworkUpdateType> networkUpdateTypes, String date, String message, int nrOfReactions) {
    this.user = user;
    this.networkUpdateTypes = networkUpdateTypes;
    this.date = date;
    this.message = message;
    this.nrOfReactions = nrOfReactions;
  }

}