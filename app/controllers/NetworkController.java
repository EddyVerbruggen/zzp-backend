package controllers;

import models.NetworkUpdate;
import models.NetworkUpdateType;
import models.NetworkUpdates;
import models.User;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Arrays;
import java.util.Date;

public class NetworkController extends Controller {

  public static void updates() {
    NetworkUpdates networkUpdates = new NetworkUpdates();
    NetworkUpdateType nutFavo = new NetworkUpdateType("favourite", "img/favourite.png");
    NetworkUpdateType nutCompany = new NetworkUpdateType("company", "img/company.gif");
    NetworkUpdateType nutLinkedIn = new NetworkUpdateType("linkedin", "img/linkedin.gif");

    // TODO uuid, etc
    final User u1 = new User("u1", new Date(), "img/pasfoto/1.gif", null);
    final User u2 = new User("u2", new Date(), "img/pasfoto/2.gif", null);
    final User u3 = new User("u3", new Date(), "img/pasfoto/3.jpg", null);
    final User u4 = new User("u4", new Date(), "img/pasfoto/4.jpg", null);

    networkUpdates.networkUpdates.add(new NetworkUpdate(u4, Arrays.asList(nutLinkedIn), "22-09-2012", "Ik heb een klus gevonden! Thx all!", 2));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u2, Arrays.asList(nutFavo, nutCompany), "18-09-2012", "Ik hou van deze killer app :)", 1));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u1, Arrays.asList(nutFavo), "08-09-2012", "Wie weet iets van Magento?", 45));

    networkUpdates.networkUpdates.add(new NetworkUpdate(u4, Arrays.asList(nutLinkedIn), "01-09-2012", "Ik zoek een PHP opdracht in Amsterdam", 0));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u3, null, "25-08-2012", "Ik zoek een Java klus in de buurt van Enschede", 9));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u4, Arrays.asList(nutLinkedIn), "17-08-2012", "Skills toegevoegd: PHP, HTML, CSS.", 9));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u1, Arrays.asList(nutFavo), "17-08-2012", "Leuke app dit, eens kijken of ik er wat mee kan.", 3));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u2, Arrays.asList(nutFavo, nutCompany), "14-08-2012", "Skills toegevoegd: Java, Scala.", 13));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u4, Arrays.asList(nutLinkedIn), "09-08-2012", "Skills toegevoegd: Scala.", 4));
    networkUpdates.networkUpdates.add(new NetworkUpdate(u4, Arrays.asList(nutLinkedIn), "07-08-2012", "Erm, komen er wel eens Scala klussen langs?", 4));

    // allow cross domain ajax requests (TODO: as @After interceptor)
    Http.Response.current().setHeader("Access-Control-Allow-Origin", "*");
    renderJSON(networkUpdates.networkUpdates);
  }
}