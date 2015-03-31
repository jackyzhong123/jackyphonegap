package cn.jackyzhong.myplugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.apache.cordova.App;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.pengyouqiang.pengyouqiang.InternalCordova;
import com.pengyouqiang.pengyouqiang.MainActivity;
import com.pengyouqiang.uti.AsyncDownload;
import com.pengyouqiang.uti.DESUti;
import com.pengyouqiang.uti.DeleteFileUtil;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.rtp.RtpStream;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import com.pengyouqiang.pengyouqiang.*;
import android.app.Activity;

public class MyPlugin extends CordovaPlugin {
	public static final String ACTION_ADD_CALENDAR_ENTRY = "sayHello";

	@Override
	public boolean execute(String action, JSONArray args,
			CallbackContext callbackContext) throws JSONException {

		try {
			switch (action) {
			case "sayHello":
				callbackContext.success("Jacky" + args.getString(0));
				return true;
			case "encodeByDES":
				String resultString;
				try {
					resultString = DESUti.encrypt(args.getString(0),
							DESUti.DES_KEY_STRING);
				} catch (Exception e) {
					// TODO: handle exception
					resultString = "0";
				}
				JSONArray jsonStrs = new JSONArray();
				jsonStrs.put(0, args.getString(0));
				jsonStrs.put(1, resultString);
				callbackContext.success(jsonStrs);
				return true;
			case "jumpToMainTab":
				Intent intent = new Intent(cordova.getActivity(),
						TabsActivity.class);
				cordova.getActivity().startActivity(intent);
				cordova.getActivity().finish();
				return true;				
			case "jumpToInternalCordova":
				jumpToInternalCordova(args, callbackContext);
				callbackContext.success();
				return true;	 
			case "jumpToInternalCordovaWithDetail":
				jumpToInternalCordovaWithDetail(args, callbackContext);
				callbackContext.success();
				return true;	
		 			
			case "setValueToLocal":
				setValueToLocal(args, callbackContext);
				return true;
			case "getValueToLocal":
				getValueToLocal(args, callbackContext);
				return true;
			case "checkCacheExisit":
				SharedPreferences spPreferences = cordova.getActivity()
						.getSharedPreferences("user", Context.MODE_PRIVATE);
				checkCacheExisit(args,
						spPreferences.getString("PATH_NoCloud", ""),
						callbackContext);
				return true;
			
			case "jumpToBack":
				if(args.getBoolean(0))
				{
					cordova.getActivity().setResult(0);
				} 
				cordova.getActivity().finish();
				callbackContext.success();
				return true;
		 
			case "jumpToURL":
				jumpToURL(args, callbackContext);
				callbackContext.success();
				return true;
			case "clearAccount":
				clearAccount(args, callbackContext);
				callbackContext.success();
				return true;
			case "computeCacheSize":
				double size= computeCacheSize();
			//	callbackContext.success();
				  callbackContext.success(String.valueOf(size));
				return true;
			case "showMenuTabs":
				showMenuTabs(args, callbackContext);
				return true;	
				case "injectDetailWebView":
					injectDetailWebView(args, callbackContext);
				return true;
			case "clearimagecache":
			 	String folderString= cordova.getActivity().getApplicationContext().getFilesDir().getAbsolutePath()+"/NoCloud"+"/imagecache";
			//	 DeleteFileUtil.doDeleteEmptyDir(folderString);        
			  
				  DeleteFileUtil.DeleteFile(folderString); 
				 double size1= computeCacheSize(); 
				 callbackContext.success(String.valueOf(size1));
				
				return true;
			default:
				break;
			}

			callbackContext.error("错误 action");
			return false;
		} catch (Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		}
	}
	
	private void showMenuTabs(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		TabsActivity t= (TabsActivity) cordova.getActivity().getParent();				 
		Message message = new Message();
		Bundle bundle=new Bundle();
		Boolean showTabs = args.getBoolean(0); 
		bundle.putBoolean("show", showTabs);
		message.setData(bundle);
		//写逻辑
		t.showHandler.sendMessage(message);
	
		
		callbackContext.success();
	}
	
	private void injectDetailWebView(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		InternalCordovaWithDetail t= (InternalCordovaWithDetail) cordova.getActivity();				 
		Message message = new Message();
		Bundle bundle=new Bundle();
		String  data = args.getString(0); 
		bundle.putString("data", data);
		message.setData(bundle);
		//写逻辑
		t.showHandler.sendMessage(message);		
		callbackContext.success();
	}
	
	private double computeCacheSize()
	{
		
		double filesize=0;
		File file=new File(cordova.getActivity().getApplicationContext().getFilesDir().getAbsolutePath()+"/NoCloud"+"/imagecache" );
	 
		 if (!file.isFile()) {  
             //获取文件大小  
             File[] fl = file.listFiles();  
         
             double ss = 0;  
             for (File f : fl)  
                 ss +=  f.length()/1024/1024;
             
             filesize=ss;
            // callbackContext.success(String.valueOf(ss));
		 }
		 return filesize;
		// callbackContext.success(0);
         
	}
	
	
 
	

	private void jumpToInternalCordova(JSONArray args, CallbackContext callbackContext)
			throws JSONException { 
		String url = args.getString(0); 
		String data = args.getString(1);
		boolean needResult=args.getBoolean(2);
			SharedPreferences preferences = cordova.getActivity()
					.getSharedPreferences("user", Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			editor.putString("JumpUrl", url); 
			editor.putString("JumpData", data);
			editor.commit();
			Intent intent = new Intent(cordova.getActivity(),
					InternalCordova.class);
			if(needResult)
			{
				cordova.getActivity().startActivityForResult(intent, 0); 
			}else {				
				cordova.getActivity().startActivity(intent); 
			} 
	}
	
	
	private void jumpToInternalCordovaWithDetail(JSONArray args, CallbackContext callbackContext)
			throws JSONException { 
		String url = args.getString(0); 
		String data = args.getString(1);
		boolean needResult=args.getBoolean(2);
			SharedPreferences preferences = cordova.getActivity()
					.getSharedPreferences("user", Context.MODE_PRIVATE);
			Editor editor = preferences.edit();
			editor.putString("JumpUrl", url); 
			editor.putString("JumpData", data);
			editor.commit();
			Intent intent = new Intent(cordova.getActivity(),
				 InternalCordovaWithDetail.class);
			if(needResult)
			{
				cordova.getActivity().startActivityForResult(intent, 0); 
			}else {				
				cordova.getActivity().startActivity(intent); 
			} 
	}
	
	

	private void jumpToURL(JSONArray args, CallbackContext callbackContext)
			throws JSONException {
		SharedPreferences preferences = cordova.getActivity()
				.getSharedPreferences("user", Context.MODE_PRIVATE);
		String url = args.getString(0);
		Editor editor = preferences.edit();
		editor.putString("JumpUrl", url);
		editor.commit();
		Intent intent = new Intent(cordova.getActivity(),
				ExternalActivity.class);
		cordova.getActivity().startActivity(intent);
	}

	private void clearAccount(JSONArray args, CallbackContext callbackContext)
			throws JSONException {
		SharedPreferences preferences = cordova.getActivity()
				.getSharedPreferences("user", Context.MODE_PRIVATE);
		// String url = args.getString(0);
		Editor editor = preferences.edit();
		// editor.putString("JumpUrl", url);
		editor.remove("access_token");
		editor.putString("JumpUrl", "firstLogin.html"); 
		editor.putString("JumpData", "");

		editor.commit();
		Intent intent = new Intent(cordova.getActivity(),
				InternalCordova.class);
		cordova.getActivity().startActivity(intent);
		cordova.getActivity().finish();
	}

	private void getValueToLocal(JSONArray args, CallbackContext callbackContext)
			throws Exception {
		SharedPreferences SPgetValueToLocal = cordova.getActivity()
				.getSharedPreferences("user", Context.MODE_PRIVATE);

		JSONArray JSONgetValueToLocal = new JSONArray();
		JSONgetValueToLocal.put(0, args.getString(0));
		JSONgetValueToLocal.put(1,
				SPgetValueToLocal.getString(args.getString(0), ""));

		callbackContext.success(JSONgetValueToLocal);
	}

	private void checkCacheExisit(JSONArray args, String NoCloudPath,
			CallbackContext callbackContext) throws Exception {
		ArrayList<String> existArr = new ArrayList<String>();
		ArrayList<String> nonExistArr = new ArrayList<String>();

		for (int i = 0; i < args.length(); i++) {
			File file = new File(NoCloudPath + "/imagecache/"
					+ args.getString(i));
			if (file.exists()) {
				existArr.add(args.getString(i));
			} else {

				nonExistArr.add(args.getString(i));
			}
		}
		JSONObject result = new JSONObject();
		JSONArray jsonExist = new JSONArray();
		JSONArray jsonNoExist = new JSONArray();
		Iterator<String> it = existArr.iterator();
		while (it.hasNext()) {
			jsonExist.put(it.next());
		}
		it = nonExistArr.iterator();
		while (it.hasNext()) {
			jsonNoExist.put(it.next());
		}
		result.put("exsist", jsonExist);
		result.put("nonexsist", jsonNoExist);
		callbackContext.success(result);
		// downloadFileAsync(nonExistArr);
		AsyncDownload download = new AsyncDownload();
		download.execute(nonExistArr, NoCloudPath + "/imagecache/");
		return;

	}

	private void setValueToLocal(JSONArray args, CallbackContext callbackContext)
			throws Exception {
		SharedPreferences SPsetValueToLocal = cordova.getActivity()
				.getSharedPreferences("user", Context.MODE_PRIVATE);
		Editor EsetValueToLocal = SPsetValueToLocal.edit();
		EsetValueToLocal.putString(args.getString(0), args.getString(1));
		EsetValueToLocal.commit();
		JSONArray JSONsetValueToLocal = new JSONArray();
		JSONsetValueToLocal.put(0, args.getString(0));
		JSONsetValueToLocal.put(1, args.getString(1));
		callbackContext.success(JSONsetValueToLocal);
	}

}
