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
        String speechText = "Thank you for using Cornoa Guide To combat COVID-19 pandemic, W H O has started the COVID-19 Solidarity Fund " +
                "Response. I have sent you a link to W H O COVID-19 Fund raiser to " +
                "your alexa app  here you can learn more and donate as well. Donations support efforts to track and understand the spread of the virus; " +
                "to ensure patients get the care they need";
        final String covid19Link = "https://www.facebook.com/donate/1564752357011737/10163057684300177/";
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard(covid19Link, speechText)
                .withReprompt(speechText)
                .build();
    }

}
