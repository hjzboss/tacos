package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import tacos.Taco;

import java.util.Date;
import java.util.List;

/*资源对象，继承了一个List对象的列表和管理链接列表的方法*/
public class TacoResource extends ResourceSupport {
    private static final IngredientResourceAssembler
            ingredientAssembler = new IngredientResourceAssembler();

    @Getter
    private final String name;

    @Getter
    private final Date createdAt;

    @Getter
    private final List<IngredientResource> ingredients;

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toResources(taco.getIngredients());
    }
}
