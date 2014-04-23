package is.mjuk.cache;

public class InstructionDTO {
    public boolean hit;
    public AddressDTO address;
    public Instruction.InstructionType type;

    public InstructionDTO(boolean hit, AddressDTO address, Instruction.InstructionType type){
        this.hit = hit;
        this.address = address;
        this.type = type;
    }

    public InstructionDTO() {
        
    }
}
