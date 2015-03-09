#import <Cordova/CDV.h>

@interface MyPlugin : CDVPlugin

- (void)sayHello:(CDVInvokedUrlCommand*)command;

-(void)checkKey:(CDVInvokedUrlCommand*)command;
-(void)saveKey:(CDVInvokedUrlCommand*)command;
-(void)getKey:(CDVInvokedUrlCommand*)command;
-(void)encodeByDES:(CDVInvokedUrlCommand*)command;
-(void)decodeByDES:(CDVInvokedUrlCommand*)command;


-(void)setValueToLocal:(CDVInvokedUrlCommand*)command;
-(void)getValueToLocal:(CDVInvokedUrlCommand*)command;

-(void)jumpToMainTab:(CDVInvokedUrlCommand*)command;

-(void)jumpToXGCQ:(CDVInvokedUrlCommand*)command;
-(void)jumpToBack:(CDVInvokedUrlCommand*)command;

-(void)clearAccount:(CDVInvokedUrlCommand*)command;
-(void)jumpToURL:(CDVInvokedUrlCommand*)command;

-(void)downloadFileAsync:(CDVInvokedUrlCommand*)command;
 


@end
