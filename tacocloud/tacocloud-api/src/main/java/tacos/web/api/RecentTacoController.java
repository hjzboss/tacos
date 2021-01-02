package tacos.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RepositoryRestController
public class RecentTacoController {
    private final TacoRepository tacoRepository;

    public RecentTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    public Resources<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(0, 6, Sort.by("createdAt").descending());
        List<Taco> content = tacoRepository.findAll(page).getContent();

        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(content);
        Resources<TacoResource> recentResources = new Resources<>(tacoResources);

        recentResources.add(
                linkTo(methodOn(DesignTacoController1.class).recentTacos())
                        .withRel("recent"));

        return recentResources;
    }
}
