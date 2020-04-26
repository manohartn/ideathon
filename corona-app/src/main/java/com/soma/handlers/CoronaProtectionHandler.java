package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class CoronaProtectionHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {

        return input.matches(Predicates.intentName("CoronaProtection"));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Stay aware of the latest information on the COVID-19 outbreak. \n " +
                "Recommended Measures to protect from COVID- 19 are : Regularly and thoroughly clean your hands " +
                "with an alcohol-based hand rub or wash them with soap and water. " +
                "\n To help stop Corona Virus, these are the FIVE DO's recommended by W.H.O : " +
                "One. Wash hands Often. " +
                "Two. Elbow Cough into it. " +
                "Three. Don't touch your face or nose. " +
                "Four. Maintain 6 feet or more distance from other people. " +
                "Five. If you feel sick, please stay home.\n";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Corona Protection", speechText)
                .withShouldEndSession(Boolean.FALSE)
                .build();
    }
}
