package controllers;

import models.User;
import play.libs.Codec;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.Date;

public class UserController extends Controller {

  public static void create() {
    System.err.println("creating a user");
    final User user = new User("anonymous", new Date(), Codec.UUID()).save();
    // allow cross domain ajax requests (TODO: as @After interceptor)
    Http.Response.current().setHeader("Access-Control-Allow-Origin", "*");
    renderJSON(user);
  }

  public static void load(String uuid) {
    System.err.println("load called for userid: " + uuid);
    final User user = User.findByUUID(uuid);
    System.err.println(user);
    // allow cross domain ajax requests (TODO: as @After interceptor)
    Http.Response.current().setHeader("Access-Control-Allow-Origin", "*");
    renderJSON(user);
  }

}
