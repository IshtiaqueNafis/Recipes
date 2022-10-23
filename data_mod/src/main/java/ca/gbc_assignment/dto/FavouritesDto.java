package ca.gbc_assignment.dto;

import ca.gbc_assignment.models.Recipe;
import ca.gbc_assignment.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FavouritesDto {
    private Long id;
    private User user;
    private Recipe recipe;

}
