package models;

import java.util.ArrayList;
import java.util.List;

public class NetworkUpdate {

  public User user;
  public List<NetworkUpdateType> networkUpdateTypes = new ArrayList<NetworkUpdateType>();
  public String date;
  public String message;
  public int nrOfReactions;

}
