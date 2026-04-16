package dk.easv.easvticket.dal;

import dk.easv.easvticket.be.Event;

import java.util.List;

public interface IEvent {
    List<Event> getAllEvent() throws Exception;

    Event createEvent(Event event) throws Exception;

    void updateEvent(Event event) throws Exception;

    void deleteEvent(Event event) throws Exception;

}
