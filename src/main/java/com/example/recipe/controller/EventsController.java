package com.example.recipe.controller;

import com.example.recipe.models.Events;
import com.example.recipe.services.EventService;
import com.example.recipe.services.RecipeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/events")
public class EventsController {
    private EventService eventService;
    private RecipeService recipeService;

    @GetMapping(value = {"/eventForm", "/eventForm/{id}"})
    public String EventForm(Model model, @PathVariable(value = "id", required = false) Long id) {

        try {

            System.out.println(id);
            model.addAttribute("event", eventService.findEventsById(id));
            model.addAttribute("recipes", recipeService.getRecipesForEvents());
            return "registered/eventForm";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    @PostMapping(value = "/submitEvent")
    public String createOrUpdateRecipe(@Valid @ModelAttribute("event") Events event, BindingResult result, Model model) {

        if (result.hasErrors()) {
            model.addAttribute("event", event);
            model.addAttribute("recipes", recipeService.getRecipesForEvents());
            return "registered/eventForm";
        }

        eventService.submitEvent(event);


        return "registered/eventForm";
    }


    @GetMapping("/delete/{eventId}")
    public String deleteEvent(@PathVariable(value = "eventId") Long eventId) {

        eventService.deleteEvent(eventId);
        return "home";

    }

    @GetMapping("join/{eventId}")
    public String joinEvent(@PathVariable(value = "eventId")Long eventId){
        eventService.joinEvent(eventId);
        return "home";
    }

    @GetMapping("unjoin/{eventId}")
    public String UnjoinEvent(@PathVariable(value = "eventId")Long eventId){
        eventService.unJoinEvent(eventId);
        return "home";
    }


}
