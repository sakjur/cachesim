package is.mjuk.cache;

public class Instruction {
    private DataCache dataCache;
    private String type; // FIXME Not properly a string
    private AddressDTO address;

    public Instruction(DataCache dataCache, AddressLayout addressLayout, 
        String type, long address) {
        if (type.toLowerCase().equals("store") || type.toLowerCase().equals("load")) {
            this.type = type;
        }

        this.address = addressLayout.parseAddress(address);

        this.dataCache = dataCache;
    }

    public InstructionDTO executeInstruction() {
        return new InstructionDTO();
    }


}