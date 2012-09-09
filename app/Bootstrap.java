import models.User;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;

import java.util.Date;

@OnApplicationStart // run this job synchronously at application start-up
public class Bootstrap extends Job {
  @Override
  public void doJob() {
    // pre-load data, but first check if there are no data yet
    if (User.count() == 0) {
//      System.err.println("--- Bootstrap: creating user");
//      new User("eddy@x-services.nl", new Date()).save();
//      Fixtures.loadModels("initial-data.yml");
    }
  }
}
