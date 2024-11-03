package com.mamaliang.npps.common;


public record SuccessResponse<T>(String id, String apiVersion, T data) {
}