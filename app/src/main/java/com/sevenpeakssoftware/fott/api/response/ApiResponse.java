package com.sevenpeakssoftware.fott.api.response;

import com.google.gson.annotations.SerializedName;

/**
 * Created by razir on 1/3/2017.
 */

public class ApiResponse<T> {
    @SerializedName("content")
    T data;
    @SerializedName("status")
    String status;

    public T getData() {
        return data;
    }

    public String getStatus() {
        return status;
    }
}
