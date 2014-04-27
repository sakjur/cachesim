package is.mjuk.cache;
import java.util.Date;

/**
* Collection of data related to the simulation
*/
public class SimulationDTO {

    private double hitRate;
    private int loads;
    private int stores;
    private String nickname;
    private Date dateTime;

    private LayoutDTO layoutDTO;

    /**
    * Constructs an empty DTO for simulation data.
    */
    public SimulationDTO(){
    }

    /**
    * Constructs a DTO with certain data from initialization.
    * @param hitRate Hitrate value for the new DTO
    * @param nickname Nickname value for the new DTO
    * @param dateTime Date and time for attaching the new DTO
    * @param layoutDTO Layout DTO to store in the new DTO
    */
    public SimulationDTO(double hitRate, String nickname,
        Date dateTime, LayoutDTO layoutDTO) {
        this.hitRate = hitRate;
        this.nickname = nickname;
        this.dateTime = dateTime;
        this.layoutDTO = layoutDTO;
    }

    /**
    * Updates the hitrate value for the DTO
    * @param hitRate New hitrate value for the DTO
    */
    public void setHitRate(double hitRate){
        this.hitRate = hitRate;
    }

    /**
    * Getter for the hitrate property
    * @return Hitrate property of the DTO
    */
    public double getHitrate(){
        return this.hitRate;
    }

    /**
    * Calculates the hitrate from one and returns the value.
    * @return 1 - {@link is.mjuk.cache.SimulationDTO#getHitrate()}
    */
    public double getMissrate(){
        return 1-this.hitRate;
    }

    /**
    * Extracts datetime and nickname properties from a user object and stores.
    * @param user User to update nickname and datetime from.
    */
    public void setUser(User user){
        this.nickname = user.getNickname();
        this.dateTime = user.getDateTime();
    }

    /**
    * Getter for the nickname property.
    */
    public String getNickname(){
        return this.nickname;
    }

    /**
    * Getter for the datetime property.
    */
    public Date getDateTime(){
        return this.dateTime;
    }

    /**
    * Setter for the stores property
    */
    public void setStores(int stores){
        this.stores = stores;
    }

    /**
    * Setter for the loads property
    */
    public void setLoads(int loads){
        this.loads = loads;
    }

    /**
    * Getter for the stores property
    */
    public int getStores(){
        return this.stores;
    }

    /**
    * Getter for the loads property
    */
    public int getLoads(){
        return this.loads;
    }

    /**
    * Sets the attached LayoutDTO.
    * @param layoutDTO New {@link is.mjuk.cache.LayoutDTO} to store
    */
    public void setLayoutDTO(LayoutDTO layoutDTO){
        this.layoutDTO = layoutDTO;
    }

    /**
    * Getter for the stored layoutDTO.
    */
    public LayoutDTO getLayoutDTO(){
        return this.layoutDTO;
    }
}