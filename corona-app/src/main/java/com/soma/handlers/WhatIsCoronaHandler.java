package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class WhatIsCoronaHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("WhatCorona"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Coronaviruses (CoV) are a large family of viruses that cause illness " +
                "ranging from the common cold to more severe diseases such as Middle East Respiratory Syndrome  and Severe Acute Respiratory Syndrome ";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("What's Corona", speechText)
                .build();
    }
}
