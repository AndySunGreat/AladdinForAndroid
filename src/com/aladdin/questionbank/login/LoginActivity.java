package com.aladdin.questionbank.login;

import java.util.ArrayList;
import java.util.List;

import com.aladdin.questionbank.R.id;
import com.aladdin.questionbank.R.layout;
import com.aladdin.questionbank.home.HomeActivity;
import com.aladdin.questionbank.util.Constants;
import com.aladdin.questionbank.util.Utility;
import com.loopj.android.http.*;
import com.aladdin.questionbank.R;
import cz.msebera.android.httpclient.Header;

import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {

	private EditText loginAccountNumber;
	private EditText loginPassword;
	private TextView registerError;
	private Button btnLogin;
	private Button btnLinkToRegisterScreen;
	private ProgressDialog progress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		loginAccountNumber = (EditText) findViewById(R.id.loginAccountNumber);
		loginPassword = (EditText) findViewById(R.id.loginPassword);
		registerError = (TextView) findViewById(R.id.register_error);
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLinkToRegisterScreen = (Button) findViewById(R.id.btnLinkToRegisterScreen);
		progress = new ProgressDialog(this);
		progress.setMessage("Please wait...");
		progress.setCancelable(false);
	}
	
	public void loginUser(View view){
		// Get Email Edit View Value
		String strLoginAccountNumber = loginAccountNumber.getText().toString();
		// Get Password  Edit View Value
		String strLoginPassword = loginPassword.getText().toString();		
		// Instantiate Http request params Object
		RequestParams params= new RequestParams();
		// When Email Edit View and Password Edit View have values other than Null
		if(Utility.isNotNull(strLoginAccountNumber)&&Utility.isNotNull(strLoginPassword)){
			if(Utility.validate(strLoginAccountNumber)){
				params.put("username", strLoginAccountNumber);
				params.put("password", strLoginPassword);
				// invoke RESTFUL web service with http parameters
				invokeWS(params);
			}else{
				Toast.makeText(getApplicationContext(), "Please enter valid email", Toast.LENGTH_LONG).show();
			}
		}else{
			Toast.makeText(getApplicationContext(), "Please fill the form, don't leave any field blank", Toast.LENGTH_LONG).show();
		}
		
	}
	
	/**
	 * Method that performs RESTful webservice invocations
	 * 
	 * @param params
	 */
	public void invokeWS(RequestParams params){
		// Show Progress Dialog
		progress.show();
		// Make RESTful webservice call using AsyncHttpClient object
		AsyncHttpClient client = new AsyncHttpClient();
		String url = Constants.restfulEndpoints + Constants.getCustomersById;
		Toast.makeText(this, "发送请求到服务器...", Toast.LENGTH_LONG).show(); 
		client.get(url, params,new JsonHttpResponseHandler(){
			@Override
			public void onStart(){
				// called before request is started
			}
			
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONArray jaobj) {
				super.onSuccess(statusCode, headers, jaobj);  
				// called when response HTTP status is "200 OK"
				progress.hide();
				if(statusCode ==200){
					// 存储数组变量
					 List<String> objects = new ArrayList<String>();  
	                    for (int i = 0; i < jaobj.length(); i++) {  
	                        try {  
	                            // 获取具体的一个JSONObject对象  
	                            JSONObject obj = jaobj.getJSONObject(i);  
	                            Log.i("LoginActivity-invokeWS-QuestionId:", obj.getInt("questionId") + "");
	                            objects.add(obj.getString("questionSubject"));  
	                        } catch (JSONException e) {  
	                            // TODO Auto-generated catch block  
	                            e.printStackTrace();  
	                        }  
	                    } 
	                    
				}
				//Navigate to Home Screen
				navigatetoHomeActivity();
			}
			// When the response returned by REST has Http response code '200'
			@Override
			public void onSuccess(int statusCode, Header[] headers, JSONObject obj) {
				// called when response HTTP status is "200 OK"
				progress.hide();
				try{
					if(obj.getInt("questionId")!=0){
						Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
						//Navigate to Home Screen
						navigatetoHomeActivity();
					}else{
						registerError.setText(obj.getString("questionSubject"));
                   	 	Toast.makeText(getApplicationContext(), obj.getString("questionSubject"), Toast.LENGTH_LONG).show();
					}
				}catch(JSONException e){
					 Toast.makeText(getApplicationContext(), "Error Occured [Server's JSON response might be invalid]!", Toast.LENGTH_LONG).show();
	                 e.printStackTrace();
				}
			}
			
			
			@Override
			public void onFailure(int statusCode, Header[] headers, String content,
					Throwable e) {
				// called when response HTTP status is "4XX" (eg. 401, 403, 404)
				 // Hide Progress Dialog 
				progress.hide();
                // When Http response code is '404'
                if(statusCode == 404){
                    Toast.makeText(getApplicationContext(), "Requested resource not found", Toast.LENGTH_LONG).show();
                } 
                // When Http response code is '500'
                else if(statusCode == 500){
                    Toast.makeText(getApplicationContext(), "Something went wrong at server end", Toast.LENGTH_LONG).show();
                } 
                // When Http response code other than 404, 500
                else{
                    Toast.makeText(getApplicationContext(), "Unexpected Error occcured! [Most common Error: Device might not be connected to Internet or remote server is not up and running]", Toast.LENGTH_LONG).show();
                }
			}

			public void navigatetoHomeActivity(){
				Intent homeIntent = new Intent(getApplicationContext(),HomeActivity.class);
				homeIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(homeIntent);
			}
			
			@Override
			public void onRetry(int retryNo) {
				// called when request is retried
			}
			/**
			 * Method gets triggered when Register button is clicked
			 * 
			 * @param view
			 */
			public void navigatetoRegisterActivity(View view){
				Intent loginIntent = new Intent(getApplicationContext(),RegisterActivity.class);
				loginIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(loginIntent);
			}

		});

	}
}
