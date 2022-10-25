package com.example.KTS.Controller.wrapper;

public class Response<T> {

    private T data;

    public Response() {
    }

    public Response(T data) {
        this.data = data;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}