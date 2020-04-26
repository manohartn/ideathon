package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.soma.dependencies.AlexaHttpClient;
import com.soma.dependencies.CovidApiHttpClient;
import com.soma.utilities.ContextInfo;
import com.soma.utilities.CountryCodeInfo;
import com.soma.utilities.CovidApiResult;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;


public class CoronaReportsHandler implements RequestHandler {

    final static Date today = new Date();
    final static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    final static String currentDateStr = dateFormat.format(today);

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("CoronaReports"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "\"According to W.H.O, here is a high level details on COVID-19 reports as on 22nd March 2020:\n" +
                "Mainland China has the largest reports with over 80 thousand cases. The other countries in Asia with most reports include Iran with over 21,000 cases, South Korea with over 8500 cases. " +
                "The outbreak in Europe is high with Italy reporting over 59 thousand cases, France reporting over 16,000 cases, Spain with 28,000 cases. In North America, United States has reports over 34,000. Overall, 193 countries across the globe are identified with Corona reports" +
                "\n Please stay tuned while my developers are actually working hard to get the live reports";


        final String address = getCountryFromDeviceAddress(input);
        String countryCode = null;

        if (address != null) {
            System.out.println("Address in %s " + address);
            countryCode = getCountryFromAddress(address);
            System.out.println("Country code extracted from user address:" + countryCode);
        }


        final String three_letter_country_code =
                countryCode != null ?
                CountryCodeInfo.two_to_three_letter_ISO_mapping.containsKey(countryCode)
                        ? CountryCodeInfo.two_to_three_letter_ISO_mapping.get(countryCode) : "USA"
                        : "USA";
        final String countryName = CountryCodeInfo.three_lettter_ISO_countryName_mapping.get(three_letter_country_code);

        System.out.println("countryCode passed to API is " + three_letter_country_code);
        final String result = CovidApiHttpClient.builder()
                .three_letter_country_code(three_letter_country_code)
                //.currentDate(currentDateStr)
                .build()
                .execute();

        CovidApiResult covidApiResult = null;

        if (result != null) {
            covidApiResult = parseCovidApiResult(result);
        }

        if (covidApiResult != null) {
            speechText = String.format(
                    "As on %s in %s, the total number of confirmed covid-19 cases is %s. " +
                            " Number of Deaths from covid-19 is %s. " +
                            "Total number of recovered cases from covid-19 is %s",
                    covidApiResult.getLastReportedDate(),
                    countryName,
                    covidApiResult.getConfirmedCovid19Cases().toString(),
                    covidApiResult.getDeathsFromCovid19(),
                    covidApiResult.getRecoveredCasesFromCovid19());
        }

        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CoronaReports", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }

    private String getCountryFromDeviceAddress(HandlerInput input) {
        final String deviceId = ContextInfo.GET_DEVICE_ID(input);
        final String apiAccessToken = ContextInfo.GET_API_ACCESS_TOKEN(input);

        System.out.println(String.format("Device Id of my device is %s \n", deviceId));
        System.out.println(String.format("API Access token is %s \n", apiAccessToken));

        // Query alexa endpoint to get the address of the customer

        final AlexaHttpClient alexaHttpClient = AlexaHttpClient.builder()
                .apiAccessToken(apiAccessToken)
                .deviceId(deviceId)
                .build();

        return alexaHttpClient.execute();
    }

    private CovidApiResult parseCovidApiResult(final String result) {
        JsonElement jsonTree = JsonParser.parseString(result);

        if (jsonTree.isJsonObject()) {
            JsonObject jsonObject = jsonTree.getAsJsonObject();

            JsonElement jsonElementResult = jsonObject.get("result");

            if (jsonElementResult.isJsonObject()) {
                JsonObject jsonObjectDates = jsonElementResult.getAsJsonObject();
                Set<String> dateStrSet = jsonObjectDates.keySet();
                final TreeSet<String> dateStrTreeSet = new TreeSet<>();
                dateStrTreeSet.addAll(dateStrSet);
                final String lastDate = dateStrTreeSet.last();

                LocalDate lastLocalDate = LocalDate.parse(lastDate);
                final String formattedLastDate = lastLocalDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG));
                JsonElement dateElement = jsonObjectDates.get(lastDate);

                JsonObject subObject = dateElement.getAsJsonObject();
                JsonElement confirmedElementResult = subObject.get("confirmed");
                JsonElement deathsFromCovid = subObject.get("deaths");
                JsonElement recoveryFromCovid = subObject.get("recovered");

                BigInteger confirmedCoronaCases = confirmedElementResult.getAsBigInteger();
                BigInteger deathsFromCovid19 = deathsFromCovid.getAsBigInteger();
                BigInteger recoveryFromCovid19 = recoveryFromCovid.getAsBigInteger();

                System.out.println("confirmed: " + confirmedCoronaCases.toString());
                System.out.println("deaths: " + deathsFromCovid19.toString());
                System.out.println("recovery: " + recoveryFromCovid19.toString());
                System.out.println("lastReportedDate" + formattedLastDate);

                return CovidApiResult.builder()
                        .confirmedCovid19Cases(confirmedCoronaCases)
                        .deathsFromCovid19(deathsFromCovid19)
                        .recoveredCasesFromCovid19(recoveryFromCovid19)
                        .lastReportedDate(formattedLastDate)
                        .build();
            }
        }

        return null;
    }

    private String getCountryFromAddress(final String address) {
        JsonElement jsonElement = JsonParser.parseString(address);

        if (jsonElement.isJsonObject()) {
            JsonElement country = jsonElement.getAsJsonObject().get("countryCode");

            System.out.println("2 e" + country.getAsString());
            return country.getAsString();
        }

        return null;
    }
}
