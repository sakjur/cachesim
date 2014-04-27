package is.mjuk.cache;
import java.util.Date;

/**
* All the data from the simulation
*/
public class SimulationDTO {

    private double hitRate;
    private int loads;
    private int stores;
    private String nickname;
    private Date dateTime;

    private LayoutDTO layoutDTO;

    public SimulationDTO(){
    }

    public SimulationDTO(double hitRate, String nickname,
        Date dateTime, LayoutDTO layoutDTO) {
        this.hitRate = hitRate;
        this.nickname = nickname;
        this.dateTime = dateTime;
        this.layoutDTO = layoutDTO;
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
        this.nickname = user.getNickname();
        this.dateTime = user.getDateTime();
    }

    public String getNickname(){
        return this.nickname;
    }

    public Date getDateTime(){
        return this.dateTime;
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