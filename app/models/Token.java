package models;

import play.data.validation.Required;
import play.db.jpa.Model;

import javax.persistence.Entity;

@Entity
public class Token extends Model {
  @Required
  public String token;
  public String secret;
  public String rawResponse;

  public Token(org.scribe.model.Token token) {
    this.token = token.getToken();
    this.secret = token.getSecret();
    this.rawResponse = token.getRawResponse();
  }

  @Override
  public String toString() {
    return "Token{" +
        "secret='" + secret + '\'' +
        ", token='" + token + '\'' +
        '}';
  }
}

