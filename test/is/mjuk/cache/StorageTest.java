package is.mjuk.cache;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class StorageTest {
    Storage store = Storage.getStorage();

    @Test
    public void addInstructionDTO() {
        store.clean();

        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        DataCache dataCache = cacheLayout.getDataCache();
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        Instruction instruction = new Instruction(dataCache, addressLayout,
            InstructionType.LOAD, 0xABAD1DEA);
        InstructionDTO instructionDTO = instruction.executeInstruction();
        store.addInstructionDTO(instructionDTO);
        assertFalse(store.getInstructionDTO(0).getHit());

        instruction = new Instruction(dataCache, addressLayout,
            InstructionType.LOAD, 0xABAD1DEA);
        instructionDTO = instruction.executeInstruction();
        store.addInstructionDTO(instructionDTO);
        assertTrue(store.getInstructionDTO(1).getHit());

        store.clean();
    }

    @Test
    public void clean() {
        store.clean();

        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        DataCache dataCache = cacheLayout.getDataCache();
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        Instruction instruction = new Instruction(dataCache, addressLayout,
            InstructionType.LOAD, 0xABAD1DEA);
        InstructionDTO instructionDTO = instruction.executeInstruction();
        store.addInstructionDTO(instructionDTO);
        assertEquals("The type of the instruction saved should be LOAD",
            InstructionType.LOAD, store.getInstructionDTO(0).getType());

        store.clean();

        instruction = new Instruction(dataCache, addressLayout,
            InstructionType.STORE, 0xABAD1DEA);
        instructionDTO = instruction.executeInstruction();
        store.addInstructionDTO(instructionDTO);
        assertEquals("The type of the instruction saved should be STORE",
            InstructionType.STORE, store.getInstructionDTO(0).getType());

        store.clean();
    }

    @Test
    public void storeLayout() {
        CacheLayout cacheLayout = new CacheLayout(8, 4, 2);

        store.storeLayoutDTO(cacheLayout.generateLayoutDTO());

        LayoutDTO layoutDTO = store.getLayoutDTO();

        assertEquals("BlockSize should be 8", 8, layoutDTO.getBlockSize());
        assertEquals("Index should be 2", 2, layoutDTO.getIndexSize());
    }

    @Test
    public void storeDateTime() {
        User u = new User();

        store.storeDateTime(u.getDateTime());
        assertEquals("Date in Storage should be same as date from user",
            u.getDateTime(), store.getDateTime());

        u.updateDateTime(1234567890000L);
        store.storeDateTime(u.getDateTime());
        assertEquals("Date in storage should be same as date from user",
            u.getDateTime(), store.getDateTime());
    }

    @Test
    public void storeHitrate() {
        CacheLayout cacheLayout = new CacheLayout(16, 16, 1);
        DataCache dataCache = cacheLayout.getDataCache();
        AddressLayout addressLayout = cacheLayout.getAddressLayout();

        store.storeHitrate(dataCache.getHitrate());
        assertEquals("Stored hitrate should be same as in the datacache",
            dataCache.getHitrate(), store.getHitrate(), 0.01);

        dataCache.loadData(addressLayout.parseAddress(0xABAD1DEA));
        dataCache.loadData(addressLayout.parseAddress(0xABAD1DEA));
        dataCache.storeData(addressLayout.parseAddress(0xABAD1DEA));
        dataCache.storeData(addressLayout.parseAddress(0xABAD1DCA));
        dataCache.storeData(addressLayout.parseAddress(0xAAAABBEA));
        dataCache.loadData(addressLayout.parseAddress(0xABAD1DEA));

        store.storeHitrate(dataCache.getHitrate());
        assertEquals("Stored hitrate should be same as in the datacache",
            dataCache.getHitrate(), store.getHitrate(), 0.01);
    }

    @Test
    public void storeNickname() {
        User u = new User();
        u.setNickname("Huxley");

        store.storeNickname(u.getNickname());
        assertEquals("Name in storage should be same as name in user object",
            u.getNickname(), store.getNickname());
    }

}