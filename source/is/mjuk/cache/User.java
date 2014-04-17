package is.mjuk.cache;

import java.util.Date;

/**
* @author Emil Tullstedt
*/
class User
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

	public void updateDateTime()
	{
		datetime = new Date();
	}

	/**
	* @args String newNickname - Sets new nickname for the user
	*/
	public void setNickname(String newNickname)
	{
		nickname = newNickname;
	}

	/**
	* @return Current value of nickname.
	*/
	public String getNickname()
	{
		return nickname;
	}
}
