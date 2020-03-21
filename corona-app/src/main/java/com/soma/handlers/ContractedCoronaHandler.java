package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class ContractedCoronaHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("ContractedCorona"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Call the office of your health care provider immediately. \n" +
                "Tell them about your travel or contact and your symptoms. \n" +
                "Discuss whether if you need to be tested for COVID-19. \n" +
                "They should be able to give you instructions on how to get care without exposing other people to your illness.\n" +
                "While sick, avoid contact with others, don’t go out in public and delay any travel to reduce the possibility of spreading illness to others\n";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Corona Contraction", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
