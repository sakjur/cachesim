package is.mjuk.cache;

import java.util.ArrayList;
import java.util.Date;

/**
 * Templete for storage of data
 * <p>
 * Stores different variables for logging and
 * storage to database or likewise.
 * @author  Emil Tullstedt <emiltu@kth.se>
 */
public class Storage {
    private static final Storage storage = new Storage();
    private ArrayList<InstructionDTO> instructionStore;
    private LayoutDTO layoutStore;
    private String nickname;
    private Date datetime;
    private double hitrate;

    private Storage() {
        this.instructionStore = new ArrayList<InstructionDTO>();
    }

    /**
     * Gets the saved storage
     */
    public static Storage getStorage() {
        return storage;
    }

    /**
     * Adds an {@link is.mjuk.cache.InstructionDTO} to the
     * list of instructions.
     *
     * @param instruction {@link is.mjuk.cache.InstructionDTO} to be stored.
     */
    public void addInstructionDTO (InstructionDTO instruction) {
        this.instructionStore.add(instruction);
    }

    /**
     * Get one {@link is.mjuk.cache.InstructionDTO} from the
     * list of saved InstructionDTOs.
     *
     * @param count The index of the DTO to be retrived
     */
    public InstructionDTO getInstructionDTO (int count) {
        return instructionStore.get(count);
    }

    /**
     * Sets the {@link is.mjuk.cache.LayoutDTO} of the data
     *
     * @param layout {@link is.mjuk.cache.LayoutDTO} to be stored.
     */
    public void storeLayoutDTO(LayoutDTO layout) {
        this.layoutStore = layout;
    }

    /**
     * Get the saved {@link is.mjuk.cache.LayoutDTO}
     */
    public LayoutDTO getLayoutDTO() {
        return this.layoutStore;
    }

    /**
     * Set the date to store
     *
     * @param datetime The date to set to
     */
    public void storeDateTime(Date datetime) {
        this.datetime = datetime;
    }

    /**
     * Get the stored Date
     */
    public Date getDateTime() {
        return this.datetime;
    }

    /**
     * Set hitrate to store
     *
     * @param hitrate The hitrate to store
     */
    public void storeHitrate(double hitrate) {
        this.hitrate = hitrate;
    }

    /**
     * Get the stored date
     */
    public double getHitrate() {
        return this.hitrate;
    }

    /**
     * Set nickname to store
     */
    public void storeNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Get the stored nickname
     */
    public String getNickname() {
        return this.nickname;
    }

    /**
     * Creates a {@link is.mjuk.cache.SimulationDTO} from all saved data
     */
    public SimulationDTO createDTO() {
        return new SimulationDTO (hitrate, nickname, datetime, layoutStore);
    }

    /**
     * Reset all variables to emty instances 
     */
    public void clean() {
        instructionStore = new ArrayList<InstructionDTO>();
        layoutStore = new LayoutDTO();
        datetime = new Date();
        nickname = new String();
        hitrate = Double.NaN;
    }
}
