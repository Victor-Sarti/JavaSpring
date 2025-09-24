package br.com.sarti.JavaSpring.mapper;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

import java.util.ArrayList;
import java.util.List;

//serve para copiar os dados de um objeto (ex: sua entidade Person) para outro objeto (ex: um DTO PersonDTO), sem precisar ficar escrevendo get e set manualmente.

public class ObjectMapper {
    private static Mapper mapper = DozerBeanMapperBuilder.buildDefault();

    public static <O, D > D parseObject( O origin, Class <D> destination){
        return mapper.map(origin, destination);
    }
    public static <O, D > List<D> parseListObjects(List <O> origin, Class <D> destination){
      List<D> destinationObjects = new ArrayList<D>();
      for(Object o : origin){
          destinationObjects.add(mapper.map(o, destination));
      }
        return destinationObjects;
    }
}
