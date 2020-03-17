package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HelloWorldIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("HelloWorldIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "So cool to build CORONA GUIDE skill. This is just an example but we will add more FAQ's on Corona like " +
        "What is COVID-19, What are it's symptoms, preventive measures, total number corona reports across geographies, etc. \n" +
                " Please stay tuned!";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Corona Guide", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
