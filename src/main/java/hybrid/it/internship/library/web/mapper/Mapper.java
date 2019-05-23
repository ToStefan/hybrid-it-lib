package hybrid.it.internship.library.web.mapper;

import java.util.Collection;
import java.util.List;

public interface Mapper<E, DTO> {

    DTO toDTO(E entity);
    List<DTO> toDTO(Collection<E> entities);
    E toEntity(DTO dto);
    List<E> toEntity(Collection<DTO> dtos);

}
