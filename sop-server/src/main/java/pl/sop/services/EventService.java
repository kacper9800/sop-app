package pl.sop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import pl.sop.dao.repository.EventRepository;

@Transactional
public class EventService {

    @Autowired
    private EventRepository eventRepository;


}
