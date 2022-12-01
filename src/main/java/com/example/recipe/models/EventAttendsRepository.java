package com.example.recipe.models;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface EventAttendsRepository extends JpaRepository<EventAttends, Long> {
    long deleteByEvent(Events event);

    boolean existsByEventAndUserAndIsHostFalse(Events event, User user);

    @Modifying
    @Transactional
    @Query(value = "DELETE from event_attendes  where event_attendes.user_id =:userId and event_attendes.event_id=:eventId", nativeQuery = true)
    void unAttendEvent(Long eventId, long userId);






    
    
    

   


}