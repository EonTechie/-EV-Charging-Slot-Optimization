// OccupiedTimeSlotTest - Unit tests for the OccupiedTimeSlot class.

package model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

/**
 * Unit tests for the OccupiedTimeSlot class to ensure proper functionality
 * and validation of occupied time slot operations.
 */
public class OccupiedTimeSlotTest {

    /**
     * Tests successful creation of an OccupiedTimeSlot instance with valid start and end times.
     */
    @Test
    void testValidOccupiedTimeSlotCreation() {
        OccupiedTimeSlot slot = new OccupiedTimeSlot(9, 17);

        assertNotNull(slot, "OccupiedTimeSlot object should not be null");
        assertEquals(9, slot.getStartTime(), "Start time should be 9");
        assertEquals(17, slot.getEndTime(), "End time should be 17");
    }

    /**
     * Tests the constructor with an invalid start time greater than or equal to the end time,
     * expecting an IllegalArgumentException to be thrown.
     */
    @Test
    void testInvalidOccupiedTimeSlotCreation() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new OccupiedTimeSlot(18, 16);
        });

        assertEquals("Start time must be less than end time", exception.getMessage(),
                "Exception message should indicate invalid time input");
    }

    /**
     * Tests whether the getStartTime() method returns the correct start time value.
     */
    @Test
    void testGetStartTime() {
        OccupiedTimeSlot slot = new OccupiedTimeSlot(10, 15);
        assertEquals(10, slot.getStartTime(), "Start time should be 10");
    }

    /**
     * Tests whether the getEndTime() method returns the correct end time value.
     */
    @Test
    void testGetEndTime() {
        OccupiedTimeSlot slot = new OccupiedTimeSlot(8, 12);
        assertEquals(12, slot.getEndTime(), "End time should be 12");
    }

    /**
     * Tests the string representation of the occupied time slot to ensure correct formatting.
     */
    @Test
    void testToStringMethod() {
        OccupiedTimeSlot slot = new OccupiedTimeSlot(9, 17);
        assertEquals("(9 - 17)", slot.toString(), "String representation should match expected format");
    }
}
