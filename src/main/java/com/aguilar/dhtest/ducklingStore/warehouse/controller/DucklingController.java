package com.aguilar.dhtest.ducklingStore.warehouse.controller;

import com.aguilar.dhtest.ducklingStore.common.customErrorType.CustomErrorType;
import com.aguilar.dhtest.ducklingStore.common.exception.NotDataFoundException;
import com.aguilar.dhtest.ducklingStore.common.exception.OperationException;
import com.aguilar.dhtest.ducklingStore.warehouse.dto.DucklingDTO;
import com.aguilar.dhtest.ducklingStore.warehouse.service.DucklingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/warehouse/duckling")
@AllArgsConstructor
@Slf4j
public class DucklingController {

    private DucklingService ducklingService;

    @GetMapping
    ResponseEntity<?> getAll() {
        List<DucklingDTO> ducklings = ducklingService.getAll();
        return ok(ducklings);
    }

    @PostMapping
    ResponseEntity<?> save(@RequestBody DucklingDTO ducklingDTO) {
        try {
            DucklingDTO response = ducklingService.save(ducklingDTO);
            return ok(response);
        } catch (OperationException e) {
            log.error("Ocurrió el error: [{}], al guardar el patito: [{}]", ducklingDTO.toString(), e.getMessage());
            return CustomErrorType.badRequest("Error de operación", e.getMessage());
        } catch (Exception e) {
            log.error("Ocurrió el error no controlado: [{}], al guardar el patito: [{}]", ducklingDTO.toString(), e.getMessage());
            return CustomErrorType.serverError("Error interno del servidor", e.getMessage());
        }
    }

    @PutMapping
    ResponseEntity<?> update(@RequestBody DucklingDTO ducklingDTO) {
        try {
            DucklingDTO response = ducklingService.update(ducklingDTO);
            return ok(response);
        } catch (NotDataFoundException e) {
            log.error("Ocurrió el error: [{}], al actualizar el patito: [{}]", ducklingDTO.toString(), e.getMessage());
            return CustomErrorType.notFound("No encontrado", e.getMessage());
        } catch (OperationException e) {
            log.error("Ocurrió el error: [{}], al actualizar el patito: [{}]", ducklingDTO.toString(), e.getMessage());
            return CustomErrorType.badRequest("Error de operación", e.getMessage());
        } catch (Exception e) {
            log.error("Ocurrió el error no controlado: [{}], al actualizar el patito: [{}]", ducklingDTO.toString(), e.getMessage());
            return CustomErrorType.serverError("Error interno del servidor", e.getMessage());
        }
    }

    @DeleteMapping("/{ducklingId}")
    ResponseEntity<?> delete(@PathVariable long ducklingId) {
        try {
            Boolean isDeleted = ducklingService.delete(ducklingId);
            return ok(isDeleted);
        } catch (NotDataFoundException e) {
            log.error("Ocurrió el error: [{}], al eliminar el patito: [{}]", ducklingId, e.getMessage());
            return CustomErrorType.notFound("No encontrado", e.getMessage());
        } catch (OperationException e) {
            log.error("Ocurrió el error: [{}], al eliminar el patito: [{}]", ducklingId, e.getMessage());
            return CustomErrorType.badRequest("Error de operación", e.getMessage());
        } catch (Exception e) {
            log.error("Ocurrió el error no controlado: [{}], al eliminar el patito: [{}]", ducklingId, e.getMessage());
            return CustomErrorType.serverError("Error interno del servidor", e.getMessage());
        }
    }
}
