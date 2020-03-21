package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class WhatIsCovid19Handler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("CovidNineteen"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Coronavirus disease abbreviated as COVID-19 is a new corona virus strain that was discovered in 2019 and has not been previously identified in humans\n";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("What is Covid 19", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
