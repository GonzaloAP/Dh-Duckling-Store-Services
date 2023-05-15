package com.aguilar.dhtest.ducklingStore.store.controller;

import com.aguilar.dhtest.ducklingStore.common.customErrorType.CustomErrorType;
import com.aguilar.dhtest.ducklingStore.common.exception.OperationException;
import com.aguilar.dhtest.ducklingStore.store.dto.StoreOrderDTO;
import com.aguilar.dhtest.ducklingStore.store.dto.StoreResponseOrderDTO;
import com.aguilar.dhtest.ducklingStore.store.service.StoreService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/store/duckling")
@AllArgsConstructor
@Slf4j
public class StoreController {

    private StoreService storeService;

    @PostMapping("/processOrder")
    ResponseEntity<?> processOrder(@RequestBody StoreOrderDTO storeOrderDTO) {
        try {
            StoreResponseOrderDTO response = storeService.processOrder(storeOrderDTO);
            return ok(response);
        } catch (OperationException e) {
            log.error("Ocurrió el error: [{}], al procesar la orden: [{}]", storeOrderDTO.toString(), e.getMessage());
            return CustomErrorType.badRequest("Error de operación", e.getMessage());
        } catch (Exception e) {
            log.error("Ocurrió el error no controlado: [{}], al guardar el patito: [{}]", storeOrderDTO.toString(), e.getMessage());
            return CustomErrorType.serverError("Error interno del servidor", e.getMessage());
        }
    }
}
