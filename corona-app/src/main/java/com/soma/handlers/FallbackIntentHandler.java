package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

import java.util.Optional;

import static com.amazon.ask.request.Predicates.intentName;

public class FallbackIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.FallbackIntent"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Sorry, I had trouble understanding you but here are sample utterances you can make: \n " +
                "What is COVID-19, What is Corona Virus, What are it's symptoms, Preventive Measures, Total number corona reports across geographies, etc. " +
                "If you want to exit the skill just say bye!\n";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("FallbackResponse", speechText)
                .withReprompt(speechText)
                .build();
    }
}
