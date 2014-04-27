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

    /**
     * Emty constructor
     */
    public InstructionDTO() {
        // Intentionally left empty.
    }

    /**
     * Get hit
     */
    public boolean getHit() {
        return this.hit;
    }

    /**
     * Set if it was a hit
     *
     * @param hit true if it was a hit
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }

    /**
     * Return this instructions address
     */
    public AddressDTO getAddress() {
        return this.address;
    }

    /**
     * Set this instructions address
     *
     * @param address {@link is.mjuk.cache.AddressDTO} for this instruction
     */
    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    /**
     * Get the {@link is.mjuk.cache.InstructionType} of this instruction
     */
    public InstructionType getType() {
        return type;
    }

    /**
     * Set the {@link is.mjuk.cache.InstructionType} of this instryction
     */
    public void setType(InstructionType type) {
        this.type = type;
    }

    /**
     * Converts this DTO to a string
     */
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
