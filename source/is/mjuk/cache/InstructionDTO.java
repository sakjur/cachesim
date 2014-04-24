package is.mjuk.cache;

import java.lang.StringBuilder;

/**
* Stores the result, address and type of a single instruction
* <p> 
* InstructionDTO contains relevant data regarding a single execution of
* a {@link is.mjuk.cache.Instruction}-object.
*/
public class InstructionDTO {
    private boolean hit;
    private AddressDTO address;
    private InstructionType type;

    public InstructionDTO(boolean hit, AddressDTO address, 
        InstructionType type){
        this.hit = hit;
        this.address = address;
        this.type = type;
    }

    public InstructionDTO() {
        // Intentionally left empty.
    }

    public boolean getHit() {
        return this.hit;
    }

    public void setHit(boolean hit) {
        this.hit = hit;
    }

    public AddressDTO getAddress() {
        return this.address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public InstructionType getType() {
        return type;
    }

    public void setType(InstructionType type) {
        this.type = type;
    }

    public String toString() {
        StringBuilder rv = new StringBuilder();
        if (this.hit) {
            rv.append("Hit ");    
        } else {
            rv.append("Miss ");
        }

        if (this.type == InstructionType.LOAD) {
            rv.append("load ");
        } else if (this.type == InstructionType.STORE) {
            rv.append("store ");
        }

        rv.append("0x");
        rv.append(Long.toString(this.address.getTag(), 16));
        rv.append(" 0x");
        rv.append(Long.toString(this.address.getIndex(), 16));
        rv.append(" 0x");
        rv.append(Long.toString(this.address.getOffset(), 16));
        return rv.toString();
    }
}
