package com.virtualprojectfactory;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class Selection extends Activity
{
	
	String[] user_info = new String[4];
	String username;
	String password;
	String hash_password;
	//ArrayList<String> USERDATA = new ArrayList<String>();
	int offset;
	//Tags
	final String SERVER_TAG = "http://vpf.cise.ufl.edu/VirtualPeopleFactory/virtual_patient_mvc/View/web_service.php?";
	final String USERDATA_TAG = "com.virtualprojectfactory.userdata";
	

	
	 /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
   
    
    	/*
    	 * Retrieve username and password from VirtualTestProjectActivity using Intent
    	 */
    user_info = getIntent().getStringArrayExtra(USERDATA_TAG);
    username = user_info[0];
    password = user_info[1];
    	super.onCreate(savedInstanceState);
    	setContentView(R.layout.scriptselect);
    	
    	//Create Widgets and Button Identifiers
    	final Spinner select_script = (Spinner) findViewById(R.id.select_script);
    	final ImageView picture = (ImageView) findViewById(R.id.imageView1);
    	final TextView script_narrative = (TextView) findViewById(R.id.script_narrative);	
    	final Button finish_selection = (Button) findViewById(R.id.finish_select_script);
    	
    	
    	MD5 HashHandler = new MD5();
    	hash_password = HashHandler.MD5_Hash(password);
    	
    	//Log.d(USERDATA_TAG, user_info[0]);
    	System.out.println("Username: " + user_info[0]);
    	System.out.println("Password: " + password);

	 
    	

    	
    	
    		try
    		{
    			URL url = new URL(SERVER_TAG + "model=Script&method_username=" + user_info[0] + "&method=getScripts_by_username&encoding=xml&username=" + username + "&password=" + password);
    			 Log.d(SERVER_TAG, url.toString());
    			 
    			 /* Get a SAXParser from the SAXPArserFactory. */
        		 
	              SAXParserFactory spf = SAXParserFactory.newInstance();
	              
	            
	              SAXParser sp = spf.newSAXParser();

	              /* Get the XMLReader of the SAXParser we created. */
	           
                  XMLReader xr = sp.getXMLReader();
              
                /* Create a new ContentHandler and apply it to the XML-Reader*/
                  

                  /* Create a new ContentHandler and apply it to the XML-Reader*/
 	              
                  ParserHelper ParserHolder = new ParserHelper();
           
                                   xr.setContentHandler(ParserHolder);
           
                                   
                                   /* Parse the xml-data from our URL. */
                                   
                                  xr.parse(new InputSource(url.openStream()));
                                   
                                                           /* Parsing has finished. */
                                  
                                  /* Our ExampleHandler now provides the parsed data to us. */
                                  System.out.println("ERROR1??");
                                  
                                  Script parsedData = ParserHolder.getParsedData();  
                                  System.out.println("ERROR2??");
                                                     
                                  offset = parsedData.Number_of_Items();
                                  
                                  System.out.println("Number: " + offset);
                                  
                                  
                                  ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this,
                                	        android.R.layout.simple_spinner_item,
                                	           parsedData.getCharacterNames());
                                  
                                 
                               
                                  select_script.setAdapter(spinnerArrayAdapter);

                               
                                  
                                  
                                 select_script.setOnItemSelectedListener(new SpinnerItemSelect());
                                 
                                 
                                 
                                  			
                                 finish_selection.setOnClickListener(new View.OnClickListener() {
                                     public void onClick(View v) 
                                     {
                                     		
                                     		Intent TranscriptIntent = new Intent(v.getContext(), Transcript.class);
                                     		
                                     		TranscriptIntent.putExtra(USERDATA_TAG, user_info);
                                     		System.out.println("USER: " + user_info[0]);
                                     		System.out.println("PASS: " + user_info[1]);
                                     		System.out.println("NAME: " + user_info[3]);
                                     		//user_info[2] = "802";
                                     		System.out.println("KEY: " + user_info[2]);
                                     		System.out.println("SearchError?");
                                     		startActivity(TranscriptIntent); //startActivityForResult(SearchIntent, SearchResultCode);
                                     		
                                     }
                                
                                 
                                 
                                 });
                                
          
                                 
             
             	
    		}
    		
    		catch(Exception e)
    		{
    			
    		}
    		
    }
    
   

    public class SpinnerItemSelect implements OnItemSelectedListener 
	{
		
		
		 public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) 
		 {
			 final TextView narrative = (TextView) findViewById(R.id.script_narrative);
			
			 try
	    		{
	    			URL url = new URL(SERVER_TAG + "model=Script&method_username=" + user_info[0] + "&method=getScripts_by_username&encoding=xml&username=" + username + "&password=" + password);
	    			 Log.d(SERVER_TAG, url.toString());
	    			 
	    			 XmlPullFeed xpf = new XmlPullFeed();
	    			 
	    			Script s =  xpf.getParsedData(url.toString(),false);
	    		
	    			
	    			 
	    			System.out.println("NARRATIVE: " + s.getCharacterNarrative(pos));
	    		
	    			 //getAllXML(url.toString());
	    			 
	    			 /* Get a SAXParser from the SAXPArserFactory. */
	        		 
		          //    SAXParserFactory spf = SAXParserFactory.newInstance();
		              
		            
		            //  SAXParser sp = spf.newSAXParser();

		              /* Get the XMLReader of the SAXParser we created. */
		           
	                  //XMLReader xr = sp.getXMLReader();
	              
	                /* Create a new ContentHandler and apply it to the XML-Reader*/
	                  

	                  /* Create a new ContentHandler and apply it to the XML-Reader*/
	 	              
	                  //ParserHelper ParserHolder = new ParserHelper();
	           
	                    //               xr.setContentHandler(ParserHolder);
	           
	                                   
	                                   /* Parse the xml-data from our URL. */
	                                   
	                      //            xr.parse(new InputSource(url.openStream()));
	                                   
	                                                           /* Parsing has finished. */
	                                  
	                                  /* Our ExampleHandler now provides the parsed data to us. */
	                                  
	                        //          Script parsedData = ParserHolder.getParsedData();  
	                              
	                                // narrative.setText(Html.fromHtml(parsedData.getCharacterNarrative()));
	                          //       System.out.println("Narrative: " + parsedData.getCharacterBackground(0));
	
	    		//	System.out.println("KEY: " );
	    			
		narrative.setText(Html.fromHtml(s.getCharacterNarrative(pos)), TextView.BufferType.SPANNABLE);
		user_info[2] = s.getPrimaryKeyValue(pos);
		user_info[3] = s.getCharacterNames(pos);
		//System.out.println("KEY: " +user_info[2] );	
		 }
			 
		catch(Exception e)
		{
			
		}
			
		 }
		 public void onNothingSelected(AdapterView parent) {
			      // Do nothing.
			    }


	}
    
    


}   
