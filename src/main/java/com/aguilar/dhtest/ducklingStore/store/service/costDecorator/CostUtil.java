package com.aguilar.dhtest.ducklingStore.store.service.costDecorator;

import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.destinationCountry.BoliviaCountry;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.destinationCountry.IndiaCountry;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.destinationCountry.OtherCountry;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.destinationCountry.UnitedStatesCountry;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.packageType.CartonPackage;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.packageType.PlasticPackage;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.packageType.WoodPackage;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.shippingMethod.AirPlane;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.shippingMethod.Land;
import com.aguilar.dhtest.ducklingStore.store.service.costDecorator.shippingMethod.Sea;

public class CostUtil {

    public static CostI getPackageTypeCost(CostI cost, String packageType) {
        String auxPackageType = packageType.toUpperCase().trim();
        CostI aux = cost;

        switch (auxPackageType) {
            case "MADERA":
                aux = new WoodPackage(aux);
                break;
            case "PLASTICO":
                aux = new PlasticPackage(aux);
                break;
            case "CARTON":
                aux = new CartonPackage(aux);
                break;
        }
        return aux;
    }

    public static CostI getDestinationCountryTypeCost(CostI cost, String destinationCountry) {
        String auxDestinationType = destinationCountry.toUpperCase().trim();
        CostI aux = cost;

        switch (auxDestinationType) {
            case "USA":
                aux = new UnitedStatesCountry(aux);
                break;
            case "BOLIVIA":
                aux = new BoliviaCountry(aux);
                break;
            case "INDIA":
                aux = new IndiaCountry(aux);
                break;
            default:
                aux = new OtherCountry(aux);
                break;
        }
        return aux;
    }

    public static CostI getShippingMethodCost(CostI cost, String shippingMethod) {
        String auxDestinationType = shippingMethod.toUpperCase().trim();
        CostI aux = cost;

        switch (auxDestinationType) {
            case "MAR":
                aux = new Sea(aux);
                break;
            case "TIERRA":
                aux = new Land(aux);
                break;
            case "AVION":
                aux = new AirPlane(aux);
                break;
        }
        return aux;
    }

}
