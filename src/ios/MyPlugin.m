#import "MyPlugin.h"
#import <Cordova/CDV.h>
#import "JoDes.h"
#import "MainViewController.h"


@implementation MyPlugin

- (void)sayHello:(CDVInvokedUrlCommand*)command
{
    
    NSString* myarg = [command.arguments objectAtIndex:0];
   // NSString *result=[a stringByAppendingString: b];
  //  NSString* result=
   // NSString *str=[NSString init :@"%@,%@" , a , b];
  CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:[[NSString alloc]  initWithFormat:@"Hello - %@ - that's your plugin :) 很不爽" , myarg]];
  [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
  
}

-(void)checkKey:(CDVInvokedUrlCommand*)command
{
    NSString* accessTokenStr=[[NSUserDefaults standardUserDefaults]objectForKey:@"access_token"];
    CDVPluginResult* pluginResult;
    
    if ([accessTokenStr length]==0 )
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"0"];
    }else
    {
        pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:@"1"];
    }
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}

-(void)saveKey:(CDVInvokedUrlCommand*)command
{
    NSString* myarg = [command.arguments objectAtIndex:0];
    
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    [defaults setObject:myarg forKey:@"access_token"];
    [defaults synchronize];
}

-(void)getKey:(CDVInvokedUrlCommand*)command
{
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    NSString *accessToken = [defaults objectForKey:@"access_token"];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:accessToken];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}



-(void)encodeByDES:(CDVInvokedUrlCommand*)command
{
    NSString* myarg = [command.arguments objectAtIndex:0];
    NSString* result;
    @try
    {
        result=[JoDes encode:myarg key:@"huodongy"];
    }@catch(NSException* exception){
        result=@"0";
    }
    NSArray* theMessage=[NSArray arrayWithObjects:result,myarg, nil];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsMultipart:(NSArray *)theMessage];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}
-(void)decodeByDES:(CDVInvokedUrlCommand*)command
{
    NSString* myarg = [command.arguments objectAtIndex:0];
    NSString* result;
    @try
    {
        result=[JoDes decode:myarg key:@"huodongyou"];
    }@catch(NSException* exception){
        result=@"0";
    }
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:result];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
}



-(void)setValueToLocal:(CDVInvokedUrlCommand*)command
{
    NSString* saveKey = [command.arguments objectAtIndex:0];
    NSString* saveValue=[command.arguments objectAtIndex:1];
//    NSString* saveIsEncrypt=[command.arguments objectAtIndex:2];
//    
//    if ([saveIsEncrypt isEqualToString:@"1"]) {
//        saveValue=[JoDes decode:saveValue key:@"huodongy"];
//    }
   //  NSArray* theMessage=[NSArray arrayWithObjects:result,myarg, nil];
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    [defaults setObject:saveValue forKey:saveKey];
    [defaults synchronize];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsMultipart:command.arguments ];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}
-(void)getValueToLocal:(CDVInvokedUrlCommand*)command
{
    NSString* saveKey = [command.arguments objectAtIndex:0];
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    NSString *result = [defaults objectForKey:saveKey];
     NSArray* theMessage=[NSArray arrayWithObjects:saveKey,result, nil];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsMultipart:theMessage ];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];
    
}

-(void)jumpToMainTab:(CDVInvokedUrlCommand *)command
{
    UIStoryboard *storyboard = [UIStoryboard storyboardWithName:@"MainTab" bundle:nil];
    self.webView.window.rootViewController = [storyboard instantiateInitialViewController];
   
}

-(void)jumpToXGCQ:(CDVInvokedUrlCommand*)command
{
    NSString* xgcid = [command.arguments objectAtIndex:0];
  //  NSString* xgcname = [command.arguments objectAtIndex:1];
    
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    [defaults setObject:xgcid forKey:@"xgcIdStr"];
    [defaults synchronize];
    //[defaults setObject:xgcname forKey:@"xgcNameStr"];
    
   UIStoryboard *mainStoryboard=[UIStoryboard storyboardWithName:@"MainTab" bundle:nil];
   UIViewController *xgc=[mainStoryboard instantiateViewControllerWithIdentifier:@"xgcq"];
   [self.viewController  presentViewController:xgc animated:YES completion:nil];
    
   // [self.viewController performSegueWithIdentifier:@"xgcSegue" sender:self.viewController];
    
  
   
    
    
    //[[NSNotificationCenter defaultCenter]postNotificationName:@"xgcid" object:xgcid];
}


-(void)jumpToBack:(CDVInvokedUrlCommand*)command{
    [self.viewController dismissViewControllerAnimated:YES completion:nil];
   // NSLog(@"ffffffff-------");
}
 

-(void)clearAccount:(CDVInvokedUrlCommand*)command{
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    [defaults removeObjectForKey:@"access_token"];
    [defaults removeObjectForKey:@"isProfileComplete"];
   // [defaults removeObjectForKey:@"xgcIdStr"];
    [self.viewController dismissViewControllerAnimated:YES completion:nil];
    
 
    
    MainViewController *controller=[[MainViewController alloc] init];
    NSString *startURL=[NSString stringWithFormat:@"%@%@%@",[[NSUserDefaults standardUserDefaults] objectForKey:@"isDeveloper"]  , @"firstLogin.html?path=",[self urlEncode:[NSString stringWithFormat:@"%@/NoCloud/imagecache",[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0]]]];
    controller.startPage=startURL;
    self.webView.window.rootViewController = controller;
}
                        
  -(NSString *)urlEncode:(NSString*)str
    {
        //    NSMutableString* encodeStr = [NSMutableString stringWithString:[str stringByAddingPercentEscapesUsingEncoding:NSUTF8StringEncoding]];
        //
        //    return encodeStr;
        
        
        NSString *escapedString = [str stringByAddingPercentEncodingWithAllowedCharacters:[NSCharacterSet URLHostAllowedCharacterSet]];
        // NSLog(@"escapedString: %@", escapedString);
        return  escapedString;
    }

-(void)jumpToURL:(CDVInvokedUrlCommand*)command{
     NSString* url = [command.arguments objectAtIndex:0];
    NSString* isSys = [command.arguments objectAtIndex:1];
    
    NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
    [defaults setObject:url forKey:@"scanURL"];
    [defaults synchronize];
    
    UIStoryboard *mainStoryboard=[UIStoryboard storyboardWithName:@"MainTab" bundle:nil];

    if ([isSys isEqualToString:@"1"]) {
        UIViewController *xgc=[mainStoryboard instantiateViewControllerWithIdentifier:@"sysAppURL"];
        [self.viewController  presentViewController:xgc animated:YES completion:nil];
    }else{
        UIViewController *xgc=[mainStoryboard instantiateViewControllerWithIdentifier:@"scanURL"];
        [self.viewController  presentViewController:xgc animated:YES completion:nil];

    }
    
    
}


-(void)downloadFileAsync:(CDVInvokedUrlCommand*)command
{
   // NSString* url = [command.arguments objectAtIndex:0];
    
    NSString *remoteURL=[command.arguments objectAtIndex:0];
    NSString *filePath=[command.arguments objectAtIndex:1];
    MKNetworkEngine *engin=[[MKNetworkEngine alloc] init];
    MKNetworkOperation *op=[engin operationWithURLString:remoteURL];

    [op addCompletionHandler:^(MKNetworkOperation *completedOperation) {
        NSLog(@"------ %@",filePath);
        NSLog(@"donwnload file finished");
    } errorHandler:^(MKNetworkOperation *completedOperation, NSError *error) {
        NSLog(@"donwnload file error");
    }];
    
    [engin enqueueOperation:op];

    [op addDownloadStream:[NSOutputStream  outputStreamToFileAtPath:filePath append:NO]];

}

-(void)computeCacheSize:(CDVInvokedUrlCommand*)command
{
   // NSString* saveKey = [command.arguments objectAtIndex:0];
  //  NSUserDefaults *defaults =[NSUserDefaults standardUserDefaults];
  //  NSString *result = [defaults objectForKey:saveKey];
    
    NSString *folder=[NSString stringWithFormat:@"%@/NoCloud/",[NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0]];
   // NSLog(@"oooooooooo = %@",folder);
    
    float  theSize=[self folderSizeAtPath:folder];
    CDVPluginResult* pluginResult = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDouble:theSize ];
    [self.commandDelegate sendPluginResult:pluginResult callbackId:command.callbackId];

}


//单个文件的大小

- (long long) fileSizeAtPath:(NSString*) filePath{
    
    NSFileManager* manager = [NSFileManager defaultManager];
    
    if ([manager fileExistsAtPath:filePath]){
        
        return [[manager attributesOfItemAtPath:filePath error:nil] fileSize];
    }
    return 0;
}


- (float ) folderSizeAtPath:(NSString*) folderPath{
    
    NSFileManager* manager = [NSFileManager defaultManager];
    
    if (![manager fileExistsAtPath:folderPath]) return 0;
    
    NSEnumerator *childFilesEnumerator = [[manager subpathsAtPath:folderPath] objectEnumerator];
    
    NSString* fileName;
    
    long long folderSize = 0;
    
    while ((fileName = [childFilesEnumerator nextObject]) != nil){
        
        NSString* fileAbsolutePath = [folderPath stringByAppendingPathComponent:fileName];
        
        folderSize += [self fileSizeAtPath:fileAbsolutePath];
        
    }
    
    return folderSize/(1024.0*1024.0);
    
}



@end
