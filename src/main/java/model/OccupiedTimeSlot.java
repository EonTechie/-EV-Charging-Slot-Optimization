// OccupiedTimeSlot - Represents a time slot during which a charging station is unavailable

package model;

public class OccupiedTimeSlot {

    private final int startTime;  // Start time of the occupied slot (in hours)
    private final int endTime;    // End time of the occupied slot (in hours)

    /**
     * Constructor to create an occupied time slot with specified start and end times.
     * 
     * @param startTime The start time of the occupied period (must be less than endTime)
     * @param endTime The end time of the occupied period
     * @throws IllegalArgumentException if startTime is greater than or equal to endTime
     */
    public OccupiedTimeSlot(int startTime, int endTime) {
        if (startTime >= endTime) {
            throw new IllegalArgumentException("Start time must be less than end time");
        }
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Gets the start time of the occupied time slot.
     * 
     * @return Start time in hours
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Gets the end time of the occupied time slot.
     * 
     * @return End time in hours
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Provides a string representation of the occupied time slot.
     * 
     * @return Formatted string representing the time slot
     */
    @Override
    public String toString() {
        return "(" + startTime + " - " + endTime + ")";
    }
}
