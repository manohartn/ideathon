package com.soma.coronafaq;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.soma.handlers.CancelandStopIntentHandler;
import com.soma.handlers.ContractedCoronaHandler;
import com.soma.handlers.CoronaProtectionHandler;
import com.soma.handlers.CoronaReportsHandler;
import com.soma.handlers.CoronaSpreadHandler;
import com.soma.handlers.CoronaSymptomsHandler;
import com.soma.handlers.CoronaTreatmentHandler;
import com.soma.handlers.FallbackIntentHandler;
import com.soma.handlers.HelloWorldIntentHandler;
import com.soma.handlers.HelpIntentHandler;
import com.soma.handlers.LaunchRequestHandler;
import com.soma.handlers.SessionEndedRequestHandler;
import com.soma.handlers.WhatIsCoronaHandler;
import com.soma.handlers.WhatIsCovid19Handler;

public class CoronaGuideHandler extends SkillStreamHandler {

    private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new HelloWorldIntentHandler(),
                        new WhatIsCoronaHandler(),
                        new WhatIsCovid19Handler(),
                        new CoronaSpreadHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new CoronaSymptomsHandler(),
                        new CoronaProtectionHandler(),
                        new CoronaTreatmentHandler(),
                        new ContractedCoronaHandler(),
                        new FallbackIntentHandler(),
                        new CoronaReportsHandler(),
                        new SessionEndedRequestHandler())
                .build();
    }

    public CoronaGuideHandler() {
        super(getSkill());
    }

}
