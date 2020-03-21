package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class HelloWorldIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("HelpIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to CORONA GUIDE skill. You can ask me things like: \n " +
        "What is COVID-19, What is Corona Virus, What are it's symptoms, Preventive Measures, Total number corona reports across geographies, etc. \n";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Corona Guide", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
