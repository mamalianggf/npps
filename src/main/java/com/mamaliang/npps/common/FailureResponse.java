package com.mamaliang.npps.common;


import java.util.Map;


public record FailureResponse(String id, String apiVersion, FailureAdapter error) {

    public record FailureAdapter(String id, Integer code, String message, Failure[] errors) {
    }

    public record Failure(String domain, String message, String reason, String recoveryOptions,
                          Map<String, Object> diagnoseInfo, String extendedHelper, String sendReport) {
    }
}