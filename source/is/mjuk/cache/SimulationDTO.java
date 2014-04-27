package is.mjuk.cache;
import java.util.Date;

/**
* All the data from the simulation
*/
public class SimulationDTO {

    private double hitRate;
    private int loads;
    private int stores;
    private User user;

    private LayoutDTO layoutDTO;

    public SimulationDTO(){
    }

    public void setHitRate(double hitRate){
        this.hitRate = hitRate;
    }

    public double getHitrate(){
        return this.hitRate;
    }

    public double getMissrate(){
        return 1-this.hitRate;
    }

    public void setUser(User user){
        this.user = user;
    }

    public String getNickname(){
        return this.user.getNickname();
    }

    public Date getDateTime(){
        return this.user.getDateTime();
    }

    public void setStores(int stores){
        this.stores = stores;
    }

    public void setLoads(int loads){
        this.loads = loads;
    }

    public int getStores(){
        return this.stores;
    }

    public int getLoads(){
        return this.loads;
    }

    public void setLayoutDTO(LayoutDTO layoutDTO){
        this.layoutDTO = layoutDTO;
    }

    public LayoutDTO getLayoutDTO(){
        return this.layoutDTO;
    }
}