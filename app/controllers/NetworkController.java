package controllers;

import models.NetworkUpdate;
import models.NetworkUpdateType;
import models.NetworkUpdates;
import models.User;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Date;

public class NetworkController extends Controller {

  public static void updates() {
    NetworkUpdates networkUpdates = new NetworkUpdates();
    NetworkUpdate update = new NetworkUpdate();
    update.user = new User("Bas ter Brugge", new Date(), "asfdasdasd"); //TODO: UUID
    update.user.image = "img/pasfoto/4.jpg";
    update.networkUpdateTypes.add(new NetworkUpdateType("img/linkedin.gif"));
    update.date = "01-09-2015";
    update.message = "ZZP backend stuff";
    update.nrOfReactions = 10;
    networkUpdates.networkUpdates.add(update);
    // allow cross domain ajax requests (TODO: as @After interceptor)
    Http.Response.current().setHeader("Access-Control-Allow-Origin", "*");
    renderJSON(networkUpdates.networkUpdates);
  }
}
