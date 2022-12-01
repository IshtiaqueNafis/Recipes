package com.example.recipe.services.impl;

import com.example.recipe.models.EventAttends;
import com.example.recipe.models.EventAttendsRepository;
import com.example.recipe.models.Events;
import com.example.recipe.models.User;
import com.example.recipe.repository.EventsRepository;
import com.example.recipe.repository.RecipeRepository;
import com.example.recipe.repository.UserRepository;
import com.example.recipe.services.EventService;
import com.example.recipe.utils.SecurityUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@AllArgsConstructor
@Service
public class EventServiceImpl implements EventService {

    private RecipeRepository recipeRepository;
    private UserRepository userRepository;

    private EventAttendsRepository eventAttendsRepository;

    private EventsRepository eventRepository;

    @Override
    public void createEvent(Events events) {

        String email = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUsername();
        User user = userRepository.findByEmail(email);
        eventRepository.save(events);
        EventAttends eventAttends = new EventAttends();
        eventAttends.setHost(true);
        eventAttends.setUser(user);
        eventAttends.setEvent(events);
        eventAttendsRepository.save(eventAttends);


    }

    @Override
    public void updateEvent(Events events) {
        eventRepository.save(events);
    }

    @Override
    public void deleteEvent(Long id) {
        eventRepository.deleteById(id);
    }

    @Override
    public void joinEvent(Long id) {
        Events events = eventRepository.findById(id).get();
        String email = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUsername();
        User user = userRepository.findByEmail(email);

        var userInEvent = eventAttendsRepository.existsByEventAndUserAndIsHostFalse(events, user);

        if (userInEvent) {
            return;
        } else {
            EventAttends eventAttends = new EventAttends();

            eventAttends.setUser(user);
            eventAttends.setEvent(events);
            eventAttendsRepository.save(eventAttends);
        }


    }


    @Override
    public void unJoinEvent(Long id) {
        String email = Objects.requireNonNull(SecurityUtils.getCurrentUser()).getUsername();
        User user = userRepository.findByEmail(email);
        eventAttendsRepository.unAttendEvent(id, user.getId());


    }

    @Override
    public Events findEventsById(Long id) {
        if (id == null) {
            return new Events();
        } else {
            return eventRepository.findById(id).get();
        }
    }

    @Override
    public void submitEvent(Events event) {
        if (event.getId() == null) {
            createEvent(event);
        } else {
            updateEvent(event);
        }
    }


}
