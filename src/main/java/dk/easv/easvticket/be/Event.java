package dk.easv.easvticket.be;

import java.time.LocalDateTime;

public class Event {
    private int id;
    private String name;
    private LocalDateTime time;
    private int locationId;
    private String note;
    private LocalDateTime end;
    private String arrivalMethod;

    public Event(int id, String name, LocalDateTime time, int locationId, String note, LocalDateTime end, String arrivalMethod) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.locationId = locationId;
        this.note = note;
        this.end = end;
        this.arrivalMethod = arrivalMethod;
    }

    public Event(String name, LocalDateTime time, int locationId, String note, LocalDateTime end, String arrivalMethod) {
        this(-1, name, time, locationId, note, end, arrivalMethod);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public int getLocationId() {
        return locationId;
    }

    public String getNote() {
        return note;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public String getArrivalMethod() {
        return arrivalMethod;
    }
}
