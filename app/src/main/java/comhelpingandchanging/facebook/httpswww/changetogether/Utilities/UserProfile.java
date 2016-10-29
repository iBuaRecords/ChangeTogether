package comhelpingandchanging.facebook.httpswww.changetogether.Utilities;

import java.util.ArrayList;

/**
 * Created by Yannick on 29.10.2016.
 */

public class UserProfile {

    private String username;
    private String password;
    private String location;
    private String language;
    //private ArrayList<String[]> feedback;

    public UserProfile(String username, String password){

        this.username = username;
        this.password = password;
        this.location = "N/A";
        this.language = "N/A";
    }

    public UserProfile(String username, String password, String location){

        this.username = username;
        this.password = password;
        this.location = location;
        this.language = "N/A";
    }

    public UserProfile(String username, String password, String location, String language){

        this.username = username;
        this.password = password;
        this.location = location;
        this.language = language;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() { return password; }

    public String getLocation(){
        return location;
    }

    public String getLanguage(){
        return language;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setLanguage(String language){
        this.language = language;
    }
}
