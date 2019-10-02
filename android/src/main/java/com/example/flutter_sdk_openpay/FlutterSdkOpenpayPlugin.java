package com.example.flutter_sdk_openpay;

import android.app.Activity;

import java.util.List;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry.Registrar;
import mx.openpay.android.Openpay;
import mx.openpay.android.OperationResult;
import mx.openpay.android.exceptions.OpenpayServiceException;
import mx.openpay.android.exceptions.ServiceUnavailableException;
import mx.openpay.android.model.Card;
import mx.openpay.android.OperationCallBack;
import mx.openpay.android.model.Token;

/** FlutterSdkOpenpayPlugin */
public class FlutterSdkOpenpayPlugin implements MethodCallHandler {

  private final Activity activity;

  private FlutterSdkOpenpayPlugin(Activity activity) {
    this.activity = activity;
  }


  /** Plugin registration. */
  public static void registerWith(Registrar registrar) {
    final MethodChannel channel = new MethodChannel(registrar.messenger(), "flutter_sdk_openpay");
    channel.setMethodCallHandler(new FlutterSdkOpenpayPlugin(registrar.activity()));
  }

  @Override
  public void onMethodCall(MethodCall call, Result result) {
    if (call.method.equals("getPlatformVersion")) {
      result.success("Android " + android.os.Build.VERSION.RELEASE);
    }else if(call.method.equals("getcardTokenAndroid")){
        String mid = call.argument("mid");
        String opk = call.argument("opk");
        String holdername = call.argument("holder");
        String card = call.argument("card");
        int month = call.argument("month");
        int year = call.argument("year");
        String cvv = call.argument("cvv");


        pay(result,this.activity,mid,opk,false,holdername,card,month,year,cvv);
    } else {
      result.notImplemented();
    }
  }


  private void pay(Result result, final Activity action,String mid,String opk,Boolean prod,String holder,String cardNumber, int month,int year, String cvv){
    final Result res = result;
    Openpay openpay = new Openpay(mid,opk,false);
    final String  device_id = openpay.getDeviceCollectorDefaultImpl().setup(action);
    Card card = new Card();
    card.holderName(holder);
    card.cardNumber(cardNumber);
    card.expirationMonth(month);
    card.expirationYear(year);
    card.cvv2(cvv);

    openpay.createToken(card, new OperationCallBack<Token>() {


      @Override
      public void onError(OpenpayServiceException e) {
        res.success( new OpenpayRespuesta("",""));
      }

      @Override
      public void onCommunicationError(ServiceUnavailableException e) {
        res.success( new OpenpayRespuesta("",""));

      }

      @Override
      public void onSuccess(OperationResult<Token> operationResult) {
        Token tok = operationResult.getResult();
        res.success( new OpenpayRespuesta(tok.getId(),device_id));
      }
    });

  }


}

