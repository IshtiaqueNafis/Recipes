package ca.gbc_assignment.repository;

import ca.gbc_assignment.models.MealPlanner;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;

public interface MealPlannerRepository extends CrudRepository<MealPlanner, Long> {

    @Query(value = "select m from meal_planner m where  not (m.end< :from or m.start> :to)")
    List<MealPlanner> findBetween(@Param("from") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start, @Param("to") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end);
}