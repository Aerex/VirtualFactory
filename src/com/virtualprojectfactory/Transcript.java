package com.virtualprojectfactory;



import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Transcript extends Activity
{
	/**
	 * General fields for class Transcript
	 */
	
		String[] userinfo;
		String input;
		String newURL;
		String newinput;
		String oldinput;
		String username;
		String password;
		String hash_password;
		String charactername;
		String primary_key_value;
		final String USERDATA_TAG = "com.virtualprojectfactory.userdata";
		final String SERVER_TAG = "http://vpf.cise.ufl.edu/VirtualPeopleFactory/virtual_patient_mvc/View/web_service.php?";
		final String CHARACTER_TAG ="com.virtualprojectfactory.speech";
    	boolean firstword = true;
			
	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
    	
    	Bundle extra;
    	
    	extra = getIntent().getExtras();
    	System.out.println("EXTRA: " + extra.toString());
     
    	userinfo = getIntent().getStringArrayExtra(USERDATA_TAG);
    	username = userinfo[0];
    	password = userinfo[1];
    	primary_key_value = userinfo[2];
    	charactername = userinfo[3];
    	

    	
    	MD5 HashHandler = new MD5();
    	hash_password = HashHandler.MD5_Hash(password);
    	
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.transcript);
    	
    	final EditText userinput = (EditText) findViewById(R.id.userinput);
    	final Button submit = (Button) findViewById(R.id.submit);
    	final Button exit = (Button) findViewById(R.id.exit);
    	final TextView transcript = (TextView) findViewById(R.id.transcript);
    	final Toast error = Toast.makeText(getApplicationContext(), "Sorry, the avatar does not understand your statement or question. Please try again.", Toast.LENGTH_SHORT);
    	
    	submit.setOnClickListener(new View.OnClickListener() 
    	{
    		  public void onClick(View v) 
              {
    			  
    			 newinput =  "You: " + userinput.getText().toString();
    			 oldinput = transcript.getText().toString();
    			 input = userinput.getText().toString();
    			 

    			 if(firstword)
    			 {
    				transcript.setText(newinput + "\n"); 
    				firstword = false;
    			 }
    			 
    			 else
    			 {
    				 transcript.setText(oldinput + "\n" + newinput + "\n");
    			 }
    			 		try
    			 		{
    			 			URL url = new URL(SERVER_TAG + "model=FindResponseScript&primary_key_value=" +primary_key_value + "&userInput=" + input + "&method=FindMostRelevantAndroidResponses&encoding=xml&username=" + username + "&password_md5=" + hash_password);
    		    			 newURL = url.toString().replace(" ", "%20");
    			 			Log.d(SERVER_TAG, newURL);
    		    			 
    		    			 XmlPullFeed xpf = new XmlPullFeed();
    		    			 
    			    			Script s =  xpf.getParsedData(newURL,false);
    			    			System.out.println("ERROR MAN COME ON :(: " + s.getAuth());
    			    			
    			    				if(s.getAuth() == 0 || newinput.equals("NO RESULT") )
    			    				{
    			    					error.show();
    			    				}
    			    				
    			    				else
    			    				{
    			    					oldinput = transcript.getText().toString();
    			    					newinput = charactername + ": " + s.getSpeechText();
    			    					Log.d("CHARACTER_TAG", newinput);
    			    					transcript.setText(oldinput + newinput + "\n");
    			    				}
    			    	}
    			    			
    			    			 
    		    			
    			 		
    			 		
    			 		catch(Exception e)
    			 		{
    			 			
    			 		}
              				
              }
         
          
          
          });
    	}
    	
    	
    }


