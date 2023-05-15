package com.aguilar.dhtest.ducklingStore.common.utils;


import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ValueList {

    public static Set<String> validColors = Stream.of("ROJO", "VERDE", "AMARILLO", "NEGRO").collect(Collectors.toSet());
    public static Set<String> validSizes = Stream.of("XLARGE", "LARGE", "MEDIUM", "SMALL", "XSMALL").collect(Collectors.toSet());
    public static Set<String> validShippingMethods = Stream.of("TIERRA", "AIRE", "MAR").collect(Collectors.toSet());
}
