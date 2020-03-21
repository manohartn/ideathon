package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class CoronaReportsHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("CoronaReports"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "\"Here is a high level details on Corona virus reports as on 18th March 2020:\n" +
                "Mainland China has the largest reports with over 80 thousand cases. The other countries in Asia with most reports include Iran with over 17,000 cases, South Korea with over 8500 cases. " +
                "The outbreak in Europe is high with Italy reporting over 35 thousand cases, France reporting over 9000 cases, Spain with 13,000 cases. In North America United States has reports over 8000. Overall, 114 countries across the globe are identified with Corona reports" +
                "\n Please stay tuned while my developers are actually working hard to get the live reports";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("What is Covid 19", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
