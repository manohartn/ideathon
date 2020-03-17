package com.soma.coronafaq;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.soma.handlers.CancelandStopIntentHandler;
import com.soma.handlers.HelloWorldIntentHandler;
import com.soma.handlers.HelpIntentHandler;
import com.soma.handlers.LaunchRequestHandler;
import com.soma.handlers.SessionEndedRequestHandler;
import com.soma.handlers.WhatIsCoronaHandler;

public class HelloWorldStreamHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        //new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new WhatIsCoronaHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedRequestHandler())
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}
