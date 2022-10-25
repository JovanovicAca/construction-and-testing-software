package com.example.KTS.constants;

import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class ControllerConstants {

    public static MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
}
