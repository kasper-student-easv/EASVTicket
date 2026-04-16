package dk.easv.easvticket.bll;

import dk.easv.easvticket.be.Event;
import dk.easv.easvticket.dal.EventDAO;
import dk.easv.easvticket.dal.IEvent;

import java.util.List;

public class EventManger {
    private final IEvent eventDAO = new EventDAO();

    public List<Event> getAllEvent() throws Exception{
        return eventDAO.getAllEvent();
    }
    public Event createEvent(Event event) throws Exception {
        return eventDAO.createEvent(event);
    }
    public void deleteEvent(Event event) throws Exception {
        eventDAO.deleteEvent(event);
    }
    public void updateEvent(Event event) throws Exception {
        eventDAO.updateEvent(event);
    }
}
