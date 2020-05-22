package com.soma.utilities;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class CountryCodeInfo {

    public static Map<String, String> two_to_three_letter_ISO_mapping =
            ImmutableMap.<String, String>builder()
                    .put("US", "USA")
                    .put("GB", "GBR")
                    .put("IN", "IND")
                    .build();

    public static Map<String, String> country_to_three_letter_ISO_mapping =
            ImmutableMap.<String, String>builder()
                    .put("United States", "USA")
                    .put("United States of America", "USA")
                    .put("America", "USA")
                    .put("USA", "USA")
                    .put("United Kingdom", "GBR")
                    .put("Great Britain", "GBR")
                    .put("England", "GBR")
                    .put("India", "IND")
                    .put("Hindustan", "IND")
                    .put("Bharat", "IND")
                    .build();

    public static Map<String, String> three_lettter_ISO_countryName_mapping =
            ImmutableMap.<String, String>builder()
                    .put("USA", "United States of America")
                    .put("IND", "India")
                    .put("GBR", "Great Britain")
                    .build();

}
