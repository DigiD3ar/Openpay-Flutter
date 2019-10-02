package com.example.flutter_sdk_openpay;

public class OpenpayRespuesta {

    String Device_id;
    String Token;

    OpenpayRespuesta(String Token,String device_id){
        this.Token = Token;
        this.Device_id = device_id;
    }
}
