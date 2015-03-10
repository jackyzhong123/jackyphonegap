package cn.jackyzhong.myplugin;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.cordova.App;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;

import com.pengyouqiang.pengyouqiang.InternalActivity;
import com.pengyouqiang.pengyouqiang.MainActivity;
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
	public JSONArray _args;

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
				jsonStrs.put(0, resultString);
				jsonStrs.put(1, args.getString(0));
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
				checkCacheExisit(args, callbackContext);

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

	private void checkCacheExisit(JSONArray args,
			CallbackContext callbackContext) throws Exception {

		File file = new File(args.getString(0));
		if (file.exists()) {
			callbackContext.success(1);
		} else {
			callbackContext.success(0);
			downloadFileAsync(args);
		}
	}

	private void downloadFileAsync(JSONArray args,
			CallbackContext callbackContext) throws Exception {
		File file = new File(args.getString(0));
		_args = args;
		if (file.exists()) {
			callbackContext.success(1);
		} else {
			callbackContext.success(0);
			new Thread(new Runnable() {
				public void run() {
					try {
						String sourceUrl = _args.getString(0); // 获取下载地址
						URL url = new URL(sourceUrl); // 创建下载地址对应的URL对象
						HttpURLConnection urlConn = (HttpURLConnection) url
								.openConnection(); // 创建一个连接
						InputStream is = urlConn.getInputStream(); // 获取输入流对象
						if (is != null) {
							String expandName = sourceUrl.substring(
									sourceUrl.lastIndexOf(".") + 1,
									sourceUrl.length()).toLowerCase(); // 获取文件的扩展名
							String fileName = _args.getString(1);
							File file = new File(fileName); // 在SD卡上创建文件

							FileOutputStream fos = new FileOutputStream(file); // 创建一个文件输出流对象
							byte buf[] = new byte[128];// 创建一个字节数组
							// 读取文件到输出流对象中
							while (true) {
								int numread = is.read(buf);
								if (numread <= 0) {
									break;
								} else {
									fos.write(buf, 0, numread);
								}
							}
						}
						is.close(); // 关闭输入流对象
						urlConn.disconnect(); // 关闭连接

					} catch (MalformedURLException e) {
						e.printStackTrace(); // 输出异常信息

					} catch (IOException e) {
						e.printStackTrace(); // 输出异常信息

					}
					// Message m = handler.obtainMessage(); // 获取一个Message
					// handler.sendMessage(m); // 发送消息
					catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start(); // 开启线程
		}
	}

	private void downloadFileAsync(JSONArray args) throws Exception {
		File file = new File(args.getString(0));
		_args = args;
		new Thread(new Runnable() {
			public void run() {
				try {
					String sourceUrl = _args.getString(0); // 获取下载地址
					URL url = new URL(sourceUrl); // 创建下载地址对应的URL对象
					HttpURLConnection urlConn = (HttpURLConnection) url
							.openConnection(); // 创建一个连接
					InputStream is = urlConn.getInputStream(); // 获取输入流对象
					if (is != null) {
					 
						String fileName = _args.getString(1);
						File file = new File(fileName); // 在SD卡上创建文件

						FileOutputStream fos = new FileOutputStream(file); // 创建一个文件输出流对象
						byte buf[] = new byte[128];// 创建一个字节数组
						// 读取文件到输出流对象中
						while (true) {
							int numread = is.read(buf);
							if (numread <= 0) {
								break;
							} else {
								fos.write(buf, 0, numread);
							}
						}
						fos.close();
					}
					is.close(); // 关闭输入流对象
					
					urlConn.disconnect(); // 关闭连接

				} catch (MalformedURLException e) {
					e.printStackTrace(); // 输出异常信息
					

				} catch (IOException e) {
					e.printStackTrace(); // 输出异常信息

				}
				// Message m = handler.obtainMessage(); // 获取一个Message
				// handler.sendMessage(m); // 发送消息
				catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally{
				 
				}
			}
		}).start(); // 开启线程
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
