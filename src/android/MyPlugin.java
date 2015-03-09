
package cn.jackyzhong.myplugin;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MyPlugin extends CordovaPlugin {
	public static final String ACTION_ADD_CALENDAR_ENTRY = "sayHello"; 
	
	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		try {
			if (ACTION_ADD_CALENDAR_ENTRY.equals(action)) { 
		 
			   callbackContext.success("Jacky"+ args.getString(0));
			   return true;
			}
			callbackContext.error("Invalid action");
			return false;
		} catch(Exception e) {
			System.err.println("Exception: " + e.getMessage());
			callbackContext.error(e.getMessage());
			return false;
		} 
	}
}
