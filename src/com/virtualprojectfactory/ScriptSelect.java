package com.virtualprojectfactory;

import java.net.URL;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ScriptSelect extends ListActivity
{
	
	final String SERVER_TAG = "http://vpf.cise.ufl.edu/VirtualPeopleFactory/virtual_patient_mvc/View/web_service.php?";
	final String USERDATA_TAG = "com.virtualprojectfactory.userdata";
	final String LIST_TAG = "com.virtualprojectfactory.userdata.list";
	String[] user_info = new String[4];
	
	String username;
	String password;
	String hash_password;
	int list_item;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    user_info = getIntent().getStringArrayExtra(USERDATA_TAG);
	   
	   
	   
	    	username = user_info[0];
	    	password = user_info[1];
	    
	    System.out.println("000: " + user_info[0]);
	  super.onCreate(savedInstanceState);
		setContentView(R.layout.scriptlist);
	    
		
		ListView list = (ListView) findViewById(android.R.id.list);
	  
	  
	  setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, user_info));
		
	  
	 
		///	URL url = new URL(SERVER_TAG + "model=Script&method_username=" + user_info[0] + "&method=getScripts_by_username&encoding=xml&username=" + username + "&password=" + password);
			
		//	 XmlPullFeed xpf = new XmlPullFeed();
			 
 		//	final Script s =  xpf.getParsedData(url.toString(),false);
	  // setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, s.getCharacterNames()));
	
 			// scriptlist.setTextFilterEnabled(true);
 			 
 			 list.setOnItemSelectedListener(new OnItemSelectedListener() {
 	
 				 public void onItemSelected(AdapterView<?> parent, View view,
						int position, long id) {
 					 
 					 
 					 System.out.println("PUSH");
 						Intent SelectionIntent = new Intent(view.getContext(), Selection.class);
 			 		SelectionIntent.putExtra(LIST_TAG, position);
 			 		 setResult(RESULT_OK, SelectionIntent);
 			 		 finish();
 			 		
                   
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> parent) {
		
					
				}
 				 
 			 });
		}
	  
	
	 
	
	

}
