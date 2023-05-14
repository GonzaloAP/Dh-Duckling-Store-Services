package com.aguilar.dhtest.ducklingStore.warehouse.service;

import com.aguilar.dhtest.ducklingStore.common.exception.NotDataFoundException;
import com.aguilar.dhtest.ducklingStore.common.exception.OperationException;
import com.aguilar.dhtest.ducklingStore.common.modelMapper.ModelMapperConfig;
import com.aguilar.dhtest.ducklingStore.warehouse.dto.DucklingDTO;
import com.aguilar.dhtest.ducklingStore.warehouse.entity.DucklingEntity;
import com.aguilar.dhtest.ducklingStore.warehouse.repository.DucklingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class DucklingService {

    private final DucklingRepository ducklingRepository;
    private final boolean isDeleted = false;

    public List<DucklingDTO> getAll() {
        List<DucklingEntity> list = ducklingRepository.findAllByIsDeleted(isDeleted);

        return list.stream().map(o -> new ModelMapperConfig().getStrictModelMapper().map(o, DucklingDTO.class))
                .collect(Collectors.toList());
    }

    public DucklingDTO getById(Long id) throws NotDataFoundException {
        DucklingEntity entity = ducklingRepository.findByIdAndIsDeleted(id, isDeleted).orElseThrow(
                () -> new NotDataFoundException("No se encontró al patito")
        );

        return new ModelMapperConfig().getStrictModelMapper().map(entity, DucklingDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {OperationException.class, Exception.class})
    public DucklingDTO save(DucklingDTO ducklingDTO) {

        List<String> invalidFields = ducklingDTO.getInvalidFields();
        if (invalidFields.size() > 0) {
            throw new OperationException("Los siguientes campos son invalidos: " + String.join(", ", invalidFields));
        }

        DucklingEntity ducklingEntity = this.validateIfDucklingExist(ducklingDTO);
        ducklingEntity = ducklingRepository.save(ducklingEntity);
        return new ModelMapperConfig().getStrictModelMapper().map(ducklingEntity, DucklingDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {OperationException.class, Exception.class})
    public DucklingDTO update(DucklingDTO ducklingDTO) {

        List<String> invalidFields = ducklingDTO.getInvalidFieldsWithId();
        if (invalidFields.size() > 0) {
            throw new OperationException("Los siguientes campos son invalidos: " + String.join(", ", invalidFields));
        }

        this.getById(ducklingDTO.getId());
        DucklingEntity ducklingEntity = new ModelMapperConfig().getStrictModelMapper().map(ducklingDTO, DucklingEntity.class);
        ducklingEntity = ducklingRepository.save(ducklingEntity);
        return new ModelMapperConfig().getStrictModelMapper().map(ducklingEntity, DucklingDTO.class);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {NotDataFoundException.class,
            OperationException.class, Exception.class})
    public Boolean delete(Long id) {

        DucklingDTO existentDucklingDTO = this.getById(id);

        DucklingEntity ducklingEntity = new ModelMapperConfig().getStrictModelMapper().map(existentDucklingDTO, DucklingEntity.class);
        ducklingEntity.setIsDeleted(true);
        return ducklingRepository.save(ducklingEntity).getIsDeleted();
    }

    private DucklingEntity validateIfDucklingExist(DucklingDTO ducklingDTO) {

        DucklingEntity existentDuckling = ducklingRepository.findByPriceAndColorAndSizeAndIsDeleted(
                ducklingDTO.getPrice(), ducklingDTO.getColor(), ducklingDTO.getSize(), isDeleted
        );

        if (existentDuckling != null) {
            existentDuckling.setQuantity(existentDuckling.getQuantity() + ducklingDTO.getQuantity());
            return existentDuckling;
        }

        return new ModelMapperConfig().getStrictModelMapper().map(ducklingDTO, DucklingEntity.class);
    }

}
