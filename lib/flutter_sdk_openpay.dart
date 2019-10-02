import 'dart:async';

import 'package:flutter/services.dart';

class FlutterSdkOpenpay {
  static const MethodChannel _channel =
      const MethodChannel('flutter_sdk_openpay');

  static Future<String> get platformVersion async {
    final String version = await _channel.invokeMethod('getPlatformVersion');
    return version;
  }

  static Future<String> get openpayTokenAndroid async {
    final  version = await _channel.invokeMethod('getcardTokenAndroid');
    return version;
  }
}
