package hu.bme.mit.spaceship;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TorpedoStoreTest {

    private TorpedoStore store;

    @BeforeEach
    void setUp() {
        store = new TorpedoStore(10, 0.0);
    }

    @Test
    void testGetTorpedoCount_Initial() {
        assertEquals(10, store.getTorpedoCount());
    }

    @Test
    void testIsEmpty_FalseWhenNotEmpty() {
        assertFalse(store.isEmpty());
    }

    @Test
    void testIsEmpty_TrueWhenEmpty() {
        TorpedoStore emptyStore = new TorpedoStore(0, 0.0);
        assertTrue(emptyStore.isEmpty());
    }

    @Test
    void testFire_Success() {
        boolean result = store.fire(1);
        assertTrue(result);
        assertEquals(9, store.getTorpedoCount());
    }

    @Test
    void testFire_AlwaysFailsWithFailureRateOne() {
        TorpedoStore faultyStore = new TorpedoStore(5, 1.0);
        boolean result = faultyStore.fire(1);
        assertFalse(result);
    }

    @Test
    void testFire_ThrowsExceptionWhenCountIsInvalid() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(0));
    }

    @Test
    void testFire_ThrowsExceptionWhenFiringMoreThanAvailable() {
        assertThrows(IllegalArgumentException.class, () -> store.fire(11));
    }
}