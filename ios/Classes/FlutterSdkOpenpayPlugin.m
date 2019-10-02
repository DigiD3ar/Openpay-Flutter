#import "FlutterSdkOpenpayPlugin.h"
#import <flutter_sdk_openpay/flutter_sdk_openpay-Swift.h>

@implementation FlutterSdkOpenpayPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterSdkOpenpayPlugin registerWithRegistrar:registrar];
}
@end
