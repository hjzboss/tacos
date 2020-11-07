package tacos.web.api;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tacos.Taco;
import tacos.data.TacoRepository;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController//返回json，相当于Controller上加了个ResponseBody
@RequestMapping(path = "/design",
        produces = "application/json")//只会处理Accept头部包含"application/json"的请求
@CrossOrigin(origins = "*")//允许跨域请求
public class DesignTacoController {
    private final TacoRepository tacoRepository;
    final EntityLinks entityLinks;

    public DesignTacoController(TacoRepository tacoRepository, EntityLinks entityLinks) {
        this.tacoRepository = tacoRepository;
        this.entityLinks = entityLinks;
    }

    /*返回最近12条订单信息，按时间降序排列*/
    @GetMapping("/recent")
    public Resources<TacoResource> recentTacos() {
        PageRequest page = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        List<Taco> content = tacoRepository.findAll(page).getContent();

        List<TacoResource> tacoResources = new TacoResourceAssembler().toResources(content);
        Resources<TacoResource> recentResources = new Resources<>(tacoResources);

        recentResources.add(
            linkTo(methodOn(DesignTacoController.class).recentTacos())
        .withRel("recents"));

        return recentResources;
    }

    /*按id查找，没有就返回notfound*/
    @GetMapping("/{id}")
    public ResponseEntity<Taco> tacoById(@PathVariable("id") Long id) {
        Optional<Taco> optionalTaco = tacoRepository.findById(id);
        if (optionalTaco.isPresent()) {
            return new ResponseEntity<>(optionalTaco.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    /*保存taco*/
    @PostMapping(consumes = "application/json")//指定请求输入为json
    @ResponseStatus(HttpStatus.CREATED)
    public Taco postTaco(@RequestBody Taco taco){//表明请求应该转换为一个Taco对象，而不是请求参数
        return tacoRepository.save(taco);
    }

}
