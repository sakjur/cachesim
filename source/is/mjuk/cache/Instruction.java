package is.mjuk.cache;

/**
* A single cache instruction.
* <p>
* The class is handling execution of a single instruction to be
* performed on the inputted {@link is.mjuk.cache.DataCache}.
*/
public class Instruction {
    private DataCache dataCache;
    private InstructionType type;
    private AddressDTO address;

    /**
    * Parses address and prepares the instruction for execution.
    *
    * @param dataCache {@link is.mjuk.cache.DataCache} to be used for
    * executing instruction.
    * @param addressLayout {@link is.mjuk.cache.AddressLayout} for 
    * parsing the address parameter in order to be able to successfully
    * select the correct block in the cache.
    * @param type Type of the instruction to be executed on the cache.
    * See {@link is.mjuk.cache.InstructionType} for available types.
    * @param address Address of the destinated memory block.
    * @throws IllegalAddressException Thrown for unparseable addresses.
    */
    public Instruction(DataCache dataCache, AddressLayout addressLayout, 
        InstructionType type, long address)
    throws IllegalAddressException {
        this.type = type;

        this.address = addressLayout.parseAddress(address);

        this.dataCache = dataCache;
    }

    /**
    * Executes the instruction
    * <p>
    * Creates a call to the datacache requesting the data for
    * the specified address.
    *
    * @return {@link is.mjuk.cache.InstructionDTO}
    */
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
