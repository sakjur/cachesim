package is.mjuk.cache;

/**
* Application main class
* <p>
* Creates {@link is.mjuk.cache.Controller} and {@link is.mjuk.cache.View}
* objects and passes the Controller to the view.
* <p>
* Does not have any return values or in parameters.
*/
public class CacheSimulator 
{

    /**
    * Application main
    * <p>
    * Method is called by commandline
    * Creates {@link is.mjuk.cache.Controller} and sends the controller as a
    * paramter to the creation of a {@link is.mjuk.cache.View}. 
    */
	public static void main(String[] args) 
	{
		Controller c = new Controller();
        View view = new View(c);
	}

}
