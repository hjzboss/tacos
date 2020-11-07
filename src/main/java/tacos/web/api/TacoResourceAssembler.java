package tacos.web.api;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tacos.Taco;

/*资源装配器*/
public class TacoResourceAssembler extends ResourceAssemblerSupport<Taco, TacoResource> {
    //在创建TacoResource中的连接时将会使用DesignTacoController来确定所有URL的基础
    public TacoResourceAssembler() {
        super(DesignTacoController.class, TacoResource.class);
    }

    //创建对象，提供链接
    @Override
    public TacoResource toResource(Taco taco) {
        return createResourceWithId(taco.getId(), taco);
    }

    //基于给定的Taco实例化TacoResource
    @Override
    protected TacoResource instantiateResource(Taco entity) {
        return new TacoResource(entity);
    }
}
