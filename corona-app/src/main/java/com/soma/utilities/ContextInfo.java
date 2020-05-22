package com.soma.utilities;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

public class ContextInfo {

    public static String GET_DEVICE_ID (final HandlerInput input) {
        return input.getRequestEnvelope().getContext().getSystem().getDevice().getDeviceId();
    }

    public static String GET_API_ACCESS_TOKEN (final HandlerInput input) {
        return input.getRequestEnvelope().getContext().getSystem().getApiAccessToken();
    }
}
