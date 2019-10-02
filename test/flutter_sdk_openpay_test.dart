import 'package:flutter/services.dart';
import 'package:flutter_test/flutter_test.dart';
import 'package:flutter_sdk_openpay/flutter_sdk_openpay.dart';

void main() {
  const MethodChannel channel = MethodChannel('flutter_sdk_openpay');

  setUp(() {
    channel.setMockMethodCallHandler((MethodCall methodCall) async {
      return '42';
    });
  });

  tearDown(() {
    channel.setMockMethodCallHandler(null);
  });

  test('getPlatformVersion', () async {
    expect(await FlutterSdkOpenpay.platformVersion, '42');
  });
}
