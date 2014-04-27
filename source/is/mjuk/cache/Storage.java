package is.mjuk.cache;

import java.util.ArrayList;
import java.util.Date;

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

    public static Storage getStorage() {
        return storage;
    }

    public void addInstructionDTO (InstructionDTO instruction) {
        this.instructionStore.add(instruction);
    }

    public InstructionDTO getInstructionDTO (int count) {
        return instructionStore.get(count);
    }

    public void storeLayoutDTO(LayoutDTO layout) {
        this.layoutStore = layout;
    }

    public LayoutDTO getLayoutDTO() {
        return this.layoutStore;
    }

    public void storeDateTime(Date datetime) {
        this.datetime = datetime;
    }

    public Date getDateTime() {
        return this.datetime;
    }

    public void storeHitrate(double hitrate) {
        this.hitrate = hitrate;
    }

    public double getHitrate() {
        return this.hitrate;
    }

    public void storeNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return this.nickname;
    }

    public SimulationDTO createDTO() {
        return new SimulationDTO (hitrate, nickname, datetime, layoutStore);
    }

    public void clean() {
        instructionStore = new ArrayList<InstructionDTO>();
        layoutStore = new LayoutDTO();
        datetime = new Date();
        nickname = new String();
        hitrate = Double.NaN;
    }
}