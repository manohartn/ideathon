package com.soma.handlers;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;
import com.amazon.ask.request.Predicates;

import java.util.Optional;

public class CancelandStopIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(Predicates.intentName("AMAZON.StopIntent").or(Predicates.intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
        String speechText = "Thank you for using Corona Guide. Please stay healthy. \n " +
                "To combat COVID-19 pandemic, W.H.O has started the COVID-19 Solidarity Fund. " +
                "I have sent you a link to W.H.O COVID-19 Fund raiser to " +
                "your alexa app where you can learn more and donate as well.";
        final String whoSolidarityFundForCorona = "https://www.who.int/emergencies/diseases/novel-coronavirus-2019/donate";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Corona Donations", whoSolidarityFundForCorona)
                .withReprompt(speechText)
                .withShouldEndSession(Boolean.TRUE)
                .build();
    }

}
