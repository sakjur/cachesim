package is.mjuk.cache;

import java.util.Date;

/**
* Class for user-specific data.
*
* @author Emil Tullstedt
*/
public class User
{
    private Date datetime;
    private String nickname;

    public User() 
    {
        datetime = new Date();    
    }

    /**
    * Get currently stored datetime object as a string.
    */
    public String getDateTime()
    {
        return this.datetime.toString(); 
    }

    /**
    * Sets the user's datetime variable to whatever the date and time is when
    * the function is called.
    */
    public void updateDateTime()
    {
        datetime = new Date();
    }

    /**
    * @param nickname - Sets new nickname for the user
    */
    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    /**
    * Gets the stored nickname
    *
    * @return Current value of nickname.
    */
    public String getNickname()
    {
        return nickname;
    }
}
