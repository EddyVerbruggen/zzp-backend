package controllers;

import com.google.gson.Gson;
import models.User;
import models.linkedin.Profile;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.LinkedInApi;
import org.scribe.model.*;
import org.scribe.oauth.OAuthService;
import play.Play;
import play.mvc.Controller;
import play.mvc.Http;

import java.util.List;

// For APi see https://developer.linkedin.com/documents/Profile-api
public class LinkedIn extends Controller {



  private static final String API_SUFFIX = "?format=json";
  private static final String PROFILE_API_OPTIONS = ":(id,first-name,last-name,email-address,picture-url,positions:(company:(id,name)))";

  // key/secret and oauth token/secret are retrieved from developer.linkedin.com
  final static String consumerKey = "fncalnuytjqi";
  final static String consumerKeySecret = "c8FaJ1aCBAL7ayWz";

  public static void auth(String uuid) {
    System.err.println(uuid);
    final User user = User.findByUUID(uuid);

    // build the service
    final OAuthService service = getService();

    // get the request token
    final Token requestToken = service.getRequestToken();
    // remember the requestToken for the user
    user.linkedInRequestToken = new models.Token(requestToken).save();
    user.save();

    // redirect the user to the auth page of linkedin
    redirect(service.getAuthorizationUrl(requestToken));
  }

  public static void authenticated() {
    // find the user based on the token
    final User user = User.find("linkedInRequestToken.token = ?", request.params.get("oauth_token")).first();

    // get the access token
    final Token accessToken = getService().getAccessToken(
        new Token(user.linkedInRequestToken.token, user.linkedInRequestToken.secret, user.linkedInRequestToken.rawResponse),
        new Verifier(request.params.get("oauth_verifier")));

    // remember the accessToken for the user
    user.linkedInAccessToken = new models.Token(accessToken).save();
    user.save();

    // go back to the app (there is no accessible url, so use this neat back-hack)
    renderTemplate("LinkedIn/redirectBack.html");
  }

  public static void authorised(String uuid) {
    // allow cross domain ajax requests (TODO: as @After interceptor)
    Http.Response.current().setHeader("Access-Control-Allow-Origin", "*");
    System.err.println("finding user for uuid " + uuid);

    List<User> users = User.findAll();
    System.err.println("Found " + users.size() + " users");
    for (User usr : users) {
      System.err.println(usr);
    }

    final User user = User.findByUUID(uuid);
    renderJSON(user != null && user.linkedInAccessToken != null);
  }

  public static void profile(String uuid) {

    // first a dummy test for connections
    /*
    {
      // create and sign the request
      final OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people/~/connections"+PROFILE_API_OPTIONS+API_SUFFIX);
      final User user = User.findByUUID(uuid);
      System.err.println(user);

      getService().signRequest(
          new Token(user.linkedInAccessToken.token, user.linkedInAccessToken.secret, user.linkedInAccessToken.rawResponse),
          request);
      final Response response = request.send();
      System.out.println(response.getBody());
    }
    */

    // create and sign the request
    final OAuthRequest request = new OAuthRequest(Verb.GET, "http://api.linkedin.com/v1/people/~"+PROFILE_API_OPTIONS+API_SUFFIX);
    final User user = User.findByUUID(uuid);
    System.err.println(user);

    // allow cross domain ajax requests (TODO: as @After interceptor)
    Http.Response.current().setHeader("Access-Control-Allow-Origin","*");

    // now get the Profile
    getService().signRequest(
        new Token(user.linkedInAccessToken.token, user.linkedInAccessToken.secret, user.linkedInAccessToken.rawResponse),
        request);
    final Response response = request.send();
    System.err.println(response.getBody());

    final Profile profile = new Gson().fromJson(response.getBody(), Profile.class);
    System.err.println("Profile id: " + profile.getId());
    System.err.println("Profile company: " + profile.getPositions().getValues().get(0).getCompany().getName());

    // output the result to the app
    renderJSON(response.getBody());
  }

  private static OAuthService getService() {
    final String port = Play.runingInTestMode() ? "9006" : "9005";

    // build the service
    return new ServiceBuilder()
        .provider(LinkedInApi.class)
        .apiKey(consumerKey)
        .apiSecret(consumerKeySecret)
        .scope("r_network,r_emailaddress")
        .callback("http://www.thumbrater.com:"+port+"/linkedin/authenticated")
//        .debug()
        .build();
  }
}
