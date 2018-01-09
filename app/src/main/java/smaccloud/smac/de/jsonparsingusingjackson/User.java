package smaccloud.smac.de.jsonparsingusingjackson;

/**
 * Created by S Soft on 09-Jan-18.
 */

public class User {
    private String firstname;
    private String lastname;
    private String login;
    private String twitter;
    private String web;

    public User() {
        super();
        this.firstname = "";
        this.lastname = "";
        this.login = "";
        this.twitter = "";
        this.web = "";
    }

    public User(String firstName, String lastName, String login,
                String twitter, String web) {
        super();
        this.firstname = firstName;
        this.lastname = lastName;
        this.login = login;
        this.twitter = twitter;
        this.web = web;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstName) {
        this.firstname = firstName;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastName) {
        this.lastname = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

}