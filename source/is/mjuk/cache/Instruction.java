package is.mjuk.cache;

public class Instruction {
    public enum InstructionType {
		LOAD,
		STORE
	}
	
	private DataCache dataCache;
    private InstructionType type;
    private AddressDTO address;

    public Instruction(DataCache dataCache, AddressLayout addressLayout, 
        InstructionType type, long address) {
        this.type = type;

        this.address = addressLayout.parseAddress(address);

        this.dataCache = dataCache;
    }

    public InstructionDTO executeInstruction() {
        boolean hit = false;

        if (this.type == InstructionType.LOAD) {
            hit = this.dataCache.loadData(this.address);
        } else if (this.type == InstructionType.STORE) {
            hit = this.dataCache.storeData(this.address);
        }

        InstructionDTO rv = new InstructionDTO(hit, this.address, this.type);
        
        return rv;
    }


}
