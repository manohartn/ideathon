package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class LaunchRequestHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.requestType(LaunchRequest.class));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Welcome to Corona Guide skill. Feel free to ask me anything about COVID-19 or Corona Virus";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("CoronaGuideLaunch", speechText)
                .withReprompt(speechText)
                .build();
    }

}
