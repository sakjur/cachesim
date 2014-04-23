package is.mjuk.cache;

public class InstructionDTO {
    public boolean hit;
    public AddressDTO address;
    public String type; // FIXME shouldn't be a string

    public InstructionDTO(boolean hit, AddressDTO address, String type){
        this.hit = hit;
        this.address = address;
        this.type = type;
    }

    public InstructionDTO() {
        
    }
}