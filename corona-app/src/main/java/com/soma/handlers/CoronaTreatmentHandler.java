package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class CoronaTreatmentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("CoronaTreatment"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "To date, there is no vaccine and no specific antiviral medicine to prevent or treat COVID-2019. " +
                "Possible vaccines and some specific drug treatments are under investigation. They are being tested through clinical trials. W.H.O is coordinating efforts to develop " +
                "vaccines and medicines to prevent and treat COVID-19.";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CoronaTreatment", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
