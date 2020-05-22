package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class CoronaSpreadHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("CoronaSpread"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Coronavirus disease spreads primarily through contact with an infected person when they cough or sneeze.\n " +
                "It also spreads when a person touches a surface or object that has the virus on it, then touches their eyes, nose, or mouth. ";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Corona Spread", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
