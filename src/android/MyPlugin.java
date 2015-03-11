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

import com.pengyouqiang.pengyouqiang.InternalActivity;
import com.pengyouqiang.pengyouqiang.MainActivity;
import com.pengyouqiang.uti.AsyncDownload;
import com.pengyouqiang.uti.DESUti;

import android.R.string;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;
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
				jsonStrs.put(0,  args.getString(0));
				jsonStrs.put(1,resultString);
				callbackContext.success(jsonStrs);
				return true;
			case "jumpToMainTab":
				Intent intent = new Intent(cordova.getActivity(),
						TabsActivity.class);
				cordova.getActivity().startActivity(intent);
				cordova.getActivity().finish();
				return true;

			case "setValueToLocal":
				setValueToLocal(args, callbackContext);
				return true;
			case "getValueToLocal":
				getValueToLocal(args, callbackContext);
				return true;
			case "checkCacheExisit":
				SharedPreferences spPreferences = cordova.getActivity().getSharedPreferences(
						"user", Context.MODE_PRIVATE);
				 
				checkCacheExisit(args,spPreferences.getString("PATH_NoCloud", ""), callbackContext);
				return true;
			case "JumpToView":
				JumpToView(args, callbackContext);
				callbackContext.success();
				return true;
			case "jumpToBack":
				cordova.getActivity().finish();
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
	
	private void JumpToView(JSONArray args, CallbackContext callbackContext) throws JSONException
	{
		 String isInternal = args.getString(0);
	     String url = args.getString(1);
	     String page = args.getString(2);
	     String data =  args.getString(3);
	     if(isInternal.equals("internal"))
	     {
	    	
				SharedPreferences preferences = cordova.getActivity().getSharedPreferences("user",
						Context.MODE_PRIVATE);
				Editor editor = preferences.edit();

				editor.putString("JumpUrl", url);
				editor.putString("JumpPage",page);
				editor.putString("JumpData", data);
				editor.commit();
				 Intent intent = new Intent(cordova.getActivity(),
							InternalActivity.class);
					cordova.getActivity().startActivity(intent) ;
			 
			//		cordova.getActivity().finish();
				
	     }else {
			
		}
	     
	     
	     
	}
	
	

	private void getValueToLocal(JSONArray args, CallbackContext callbackContext)
			throws Exception {
		SharedPreferences SPgetValueToLocal = cordova.getActivity()
				.getSharedPreferences("user", Context.MODE_PRIVATE);

		JSONArray JSONgetValueToLocal = new JSONArray();
		JSONgetValueToLocal.put(0,
				SPgetValueToLocal.getString(args.getString(0), ""));
		JSONgetValueToLocal.put(1, args.getString(0));
		callbackContext.success(JSONgetValueToLocal);
	}

	private void checkCacheExisit(JSONArray args,String NoCloudPath,
			CallbackContext callbackContext) throws Exception {
		ArrayList<String>  existArr = new ArrayList<String> (); 
		ArrayList<String>  nonExistArr = new ArrayList<String> (); 
		 
		for(int i=0;i<args.length();i++)
		{
			File file = new File(NoCloudPath+"/imagecache/"+ args.getString(i));
			if(file.exists())
			{
				existArr.add(args.getString(i));
			}else {
				
				nonExistArr.add(args.getString(i));
			}
		}
		JSONObject result = new JSONObject();
		JSONArray jsonExist = new JSONArray();  
		JSONArray jsonNoExist = new JSONArray();
		Iterator<String> it=existArr.iterator();
		while(it.hasNext()){
			jsonExist.put(it.next());			
		}
		it=nonExistArr.iterator();
		while(it.hasNext()){
			jsonNoExist.put(it.next());			
		}
		result.put("exsist", jsonExist);
		result.put("nonexsist", jsonNoExist);
		callbackContext.success(result);
		//downloadFileAsync(nonExistArr);
		AsyncDownload download=new AsyncDownload();
		download.execute(nonExistArr,NoCloudPath+"/imagecache/");
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
