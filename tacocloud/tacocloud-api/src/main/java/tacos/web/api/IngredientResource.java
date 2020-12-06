package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import tacos.Ingredient;

public class IngredientResource extends ResourceSupport {
    @Getter
    private final String name;

    @Getter
    private final Ingredient.Type type;

    public IngredientResource(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
