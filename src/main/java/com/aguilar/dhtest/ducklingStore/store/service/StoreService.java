package com.aguilar.dhtest.ducklingStore.store.service;


import com.aguilar.dhtest.ducklingStore.common.exception.OperationException;
import com.aguilar.dhtest.ducklingStore.store.dto.StoreOrderDTO;
import com.aguilar.dhtest.ducklingStore.store.dto.StoreResponseOrderDTO;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostI;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostImpl;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.CostUtil;
import com.aguilar.dhtest.ducklingStore.warehouse.entity.DucklingEntity;
import com.aguilar.dhtest.ducklingStore.warehouse.repository.DucklingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class StoreService {

    private final DucklingRepository ducklingRepository;

    public StoreResponseOrderDTO processOrder(StoreOrderDTO storeOrderDTO) {

        StoreResponseOrderDTO responseOrderDTO = new StoreResponseOrderDTO();
        DucklingEntity stockDuckling = this.ducklingRepository.findByColorAndSizeAndIsDeleted(storeOrderDTO.getColor(),
                storeOrderDTO.getSize(), false);

        List<String> invalidFields = storeOrderDTO.getInvalidFields();
        if (invalidFields.size() > 0) {
            throw new OperationException("Los siguientes campos son invalidos: " + String.join(", ", invalidFields));
        }

        if (stockDuckling == null) {
            throw new OperationException("Orden no completada, no se cuenta con el tamaño y cantidad de patitos solicitados");
        }

        if (stockDuckling.getQuantity() < storeOrderDTO.getDucklingQuantity()) {
            throw new OperationException("Orden no completada, la cantidad de patitos solicitados es mayor al stock actual: " +
                    stockDuckling.getQuantity());
        }

        responseOrderDTO.setPackageTypeBySize(storeOrderDTO.getSize());
        responseOrderDTO.setProtectionTypeByShippingMethodAndPackageType(storeOrderDTO.getShippingMethod(),
                responseOrderDTO.getPackageType());

        CostImpl defaultCost = new CostImpl();
        defaultCost.setPrice(storeOrderDTO.getDucklingQuantity() * stockDuckling.getPrice());
        defaultCost.setQuantity(storeOrderDTO.getDucklingQuantity());

        CostI costCalc = CostUtil.getPackageTypeCost(defaultCost, responseOrderDTO.getPackageType());
        costCalc = CostUtil.getDestinationCountryTypeCost(costCalc, storeOrderDTO.getDestinationCountry());
        costCalc = CostUtil.getShippingMethodCost(costCalc, storeOrderDTO.getShippingMethod());

        responseOrderDTO.setTotalToPaid(costCalc.getTotalPrice());
        responseOrderDTO.setDetailList(Arrays.asList(costCalc.getDetail().split("\\|")));


        return responseOrderDTO;
    }

}
