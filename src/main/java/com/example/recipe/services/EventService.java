package com.example.recipe.services;

import com.example.recipe.models.Events;

public interface EventService {
    void createEvent(Events events);

    void updateEvent(Events events);

    void deleteEvent(Long id);

    void joinEvent(Long id);

    void unJoinEvent(Long id);

    Object findEventsById(Long id);

    void submitEvent(Events event);



}
