package pl.hackyeah.bumboxqueue.converter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public <D, T> D map(T source, Class<D> outClass) {
        return modelMapper.map(source, outClass);
    }

    public <D, T> void map(T source, D destination) {
        modelMapper.map(source, destination);
    }

    public <D, T> List<D> mapToList(List<T> sources, Class<D> outClass) {
        return sources.stream().map(source -> map(source, outClass))
                .collect(Collectors.toList());
    }
}
