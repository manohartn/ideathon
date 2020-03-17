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
        String speechText = "Welcome to SOMA Company!, So cool to build your first skill. Hey Sohana, This is just an example but we will add more FAQ's on Corona like" +
                "What is COVID-19, What are it's symptoms, preventive measures, total number corona reports across geographies, etc";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", speechText)
                .build();
    }
}
