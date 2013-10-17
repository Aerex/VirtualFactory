package com.virtualprojectfactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import android.util.Log;
import android.util.Xml;

public class XmlPullFeed 
{

	
		String ATTRIBUTE_TAG = "com.virtualfactor.tags";
		private Script DataSet;
		String json;
		
	public Script getParsedData(String url, boolean auth)
	{
		
		URLConnection feedUrl = null;
		boolean isauth = false;
		try {
			  feedUrl = new URL(url).openConnection();
			} catch (MalformedURLException e) {
			  Log.v("ERROR","MALFORMED URL EXCEPTION");
			} catch (IOException e) {
			  e.printStackTrace();
			}
			try {
			  InputStream in = feedUrl.getInputStream();
			  json = convertStreamToString(in);
			}
			catch(IOException e)
			{
				
			}
		 XmlPullParser parser = Xml.newPullParser();
	        try {
	            // auto-detect the encoding from the stream
	            parser.setInput(new StringReader(json)); //source feed of the xml file
	            System.out.println(json);
	            int eventType = parser.getEventType(); //either START_DOCUMENT, START_TAG, TEXT, END_TAG, OR END_DOCUMENT
	            boolean done = false;
	            
	            while(!done && eventType != XmlPullParser.END_DOCUMENT)
	            {
	            	switch(eventType)
	            	{
	            	
	            		case XmlPullParser.START_DOCUMENT:
	            			DataSet = new Script();
	            			Log.d(ATTRIBUTE_TAG,"Location: Start Document");
	            			//parser.next();
	            			//System.out.println("NEXT TAG:" + parser.nextText());
	            			break;
	            		case XmlPullParser.START_TAG:
	            			if(!auth)
	            			{
	            				isauth = true;
	            			}
	            			String tag = parser.getName();
	            		//	System.out.println("TAG: " + tag);
	            			
	            				if(tag.equals("narrative"))
	            				{
	            						DataSet.pushCharacterNarrative(parser.nextText());	
	            					//	Log.d(ATTRIBUTE_TAG, "NARRATIVE TAG");
	            				}
	            				if(tag.equals("character_name"))
	            				{
	            					DataSet.pushCharacterName(parser.nextText());
	            					Log.d(ATTRIBUTE_TAG, "CHARACTERNAME");
	            				}
	            				
	            				if(tag.equals("primary_key_value"))
	            				{
	            						DataSet.pushPrimaryKeyValue(parser.nextText());
	            						
	            						//Log.d(ATTRIBUTE_TAG, "PRIMARY KEY");
	            				}
	            				
	            				if(tag.equals("speech_text") || tag.equals("string"))
	            				{
	            						DataSet.setSpeechText(parser.nextText());
	            						//Log.d(ATTRIBUTE_TAG, "SPEECH TEXT");
	            				}
	            			break;
	            	    case XmlPullParser.END_DOCUMENT:
	            	    	done = true;
	            	    	break;
	            }
	            	
	            	eventType = parser.next();
	        
	            			
	            //loop until done and end of file
	            }
	            
	            
	        }
	        
	       catch(IOException e)
	       {
	       
	       }
	        	
	        catch(XmlPullParserException e)
	        {
	        		System.out.println("EXCEPTION THROWN!!");
	        }
	            
	            	
	   if(isauth)
	   {
		   DataSet.setAuth(1);
	   }
	   
	   else
	   {
		   DataSet.setAuth(0);
	   }
	        	return this.DataSet;
		
		
	}
	
	private static String convertStreamToString(InputStream is) throws UnsupportedEncodingException {
		  BufferedReader reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
		  StringBuilder sb = new StringBuilder();
		  String line = null;
		  try {
		    while ((line = reader.readLine()) != null) {
		      sb.append(line);
		    }
		  } catch (IOException e) {
		    e.printStackTrace();
		  } finally {
		    try {
		      is.close();
		  } catch (IOException e) {
		    e.printStackTrace();
		  }
		}
		return sb.toString();

	
	
	


}
}
