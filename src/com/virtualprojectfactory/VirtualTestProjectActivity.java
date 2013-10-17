package com.virtualprojectfactory;



import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class VirtualTestProjectActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        
    	final String[] USERDATA = new String[4];

  
    	super.onCreate(savedInstanceState);
    	 setContentView(R.layout.login);
         
    	final String USERDATA_TAG = "com.virtualprojectfactory.userdata";
    	final String SERVER_TAG = "http://vpf.cise.ufl.edu/VirtualPeopleFactory/virtual_patient_mvc/View/web_service.php?";

        	
        
        
        final ActiveAccount User = new ActiveAccount();
        
        final EditText username = (EditText) findViewById(R.id.username);
        final EditText password = (EditText) findViewById(R.id.password);
        final Button finish_login = (Button) findViewById(R.id.finish_login);
    	final Toast error = Toast.makeText(getApplicationContext(), "There was a problem with your username and/or password. ", Toast.LENGTH_SHORT);
        
        
        finish_login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	
            	//Request XML stuff
            	
            	User.setUsername(username.getText().toString().trim());
            	User.setPassword(password.getText().toString().trim());
            	
            	USERDATA[0] = User.getUsername();
            	USERDATA[1] = User.getPassword();
            	
            	try
            	{
            		
            		URL url = new URL(SERVER_TAG + "model=Script&method_username=" + USERDATA[0] + "&method=getScripts_by_username&encoding=xml&username=" + USERDATA[0] + "&password=" + USERDATA[1]);
        			System.out.println(url.toString());
            		XmlPullFeed s = new XmlPullFeed	();
        			
        			Script script = s.getParsedData(url.toString(),false);
        			
        			if(script.getAuth() == 0)
        			{
        				error.show();
        			}
        				
        			else
        			{
            		 Intent SelectionIntent = new Intent(v.getContext(),Selection.class);
                	 SelectionIntent.putExtra(USERDATA_TAG, USERDATA);
                     startActivity(SelectionIntent);
        			}
               

            	}
            	
            	catch(MalformedURLException e)
            	{
            		error.show();
            		
            	}
            	
            	
            	
            
            	
}
});
        
}
}    