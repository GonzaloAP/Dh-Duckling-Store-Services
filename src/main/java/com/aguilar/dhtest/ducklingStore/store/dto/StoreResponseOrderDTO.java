package com.aguilar.dhtest.ducklingStore.store.dto;

import com.aguilar.dhtest.ducklingStore.common.exception.OperationException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StoreResponseOrderDTO {
    private String packageType;
    private List<String> protectionType;
    private Double totalToPaid;
    private List<String> detailList;

    public void setPackageTypeBySize(String size) {
        String sizeAux = size.trim().toUpperCase();
        switch (sizeAux) {
            case "XLARGE":
            case "LARGE":
                this.packageType = "Madera";
                break;
            case "XSMALL":
            case "SMALL":
                this.packageType = "Plastico";
                break;
            case "MEDIUM":
                this.packageType = "Carton";
                break;
        }
        if (this.packageType == null || this.packageType.isEmpty()) {
            throw new OperationException("Orden no completada, el tamaño: " + size + ", no es válido");
        }
    }
    public void setProtectionTypeByShippingMethodAndPackageType(String shippingMethod, String packageType) {
        String shippingMethodAux = shippingMethod.trim().toUpperCase();
        String packageTypeAux = packageType.trim().toUpperCase();
        this.protectionType = new ArrayList<>();

        if(shippingMethodAux.equals("AIRE") && (packageTypeAux.equals("MADERA") || packageTypeAux.equals("CARTON"))){
            this.protectionType.add("Bolitas de plastoformo");
        }

        if(shippingMethodAux.equals("AIRE") && packageTypeAux.equals("PLASTICO")){
            this.protectionType.add("Bolsas con burbujas");
        }

        if(shippingMethodAux.equals("TIERRA")){
            this.protectionType.add("Bolitas de plastoformo");
        }

        if(shippingMethodAux.equals("MAR")){
            this.protectionType.add("Bolitas absorbentes de humedad");
            this.protectionType.add("Bolsas con burbujas");
        }


        if (this.protectionType.isEmpty()) {
            throw new OperationException("Orden no completada, el método de envío: " + shippingMethod + ", o " +
                    "tipo de paquete: " + packageType + ", no son validos");
        }
    }

}
