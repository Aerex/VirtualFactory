package com.virtualprojectfactory;

import org.xml.sax.Attributes;
  
import org.xml.sax.SAXException;
 
import org.xml.sax.helpers.DefaultHandler;

import android.util.Log;
   
public class ParserHelper extends DefaultHandler
{
   

			  private boolean in_arraytag = false;
	
              private boolean in_arrayitemtag = false; //array item
              
              private boolean in_scriptidtag = false; //script id
              
              private boolean in_characternametag = false; //character name
              
              private boolean in_charbackgroundtag = false; //character background
              
              private boolean in_charnarrative = false; // character narrative
               
              
              final String LOCALNAME_TAG = "LocalName";
             
  
              private Script DataSet = new Script();
  
              /**Returns the object holding the parsed information
               * 
               * @return the object 
               */
              public Script getParsedData() 
              {
  
                      return this.DataSet;
              }
       
              @Override
  
              public void startDocument() throws SAXException 
              {
  
                      DataSet = new Script();
                      System.out.println("StartDocument: " + DataSet);
  
              }
       
              @Override
              public void endDocument() throws SAXException {
  
                      System.out.println("EndDocument" + DataSet);
                      
              }
  
       
  
              /** Gets be called on opening tags like:
  
               * <tag>
  
               * Can provide attribute(s), when xml was like:
  
               * <tag attribute="attributeValue">
               * 
               * Script: String stringName = att atts.getValue("attributeValue)"*/
              
  
              @Override
  
              public void startElement(String namespaceURI, String localName,
  
                              String qName, Attributes atts) throws SAXException {
  
            	  	  if(localName.equals("array"))
            	  	  {
            	  		  this.in_arraytag = true;
            	  	//	  Log.d(LOCALNAME_TAG, "ARRAY attribute");
            	  		  
            	  	  }
            	  
                      if (localName.equals("array_item")) 
                      {
 
                            this.in_arrayitemtag= true;
                      //      Log.d(LOCALNAME_TAG, "ARRAY ITEM attribute");
                          
                      }
                      else if (localName.equals("script_id")) 
                      {
  
                              this.in_scriptidtag = true;
                        //      Log.d(LOCALNAME_TAG, "SCRIPTID attribute");
  
                      }
                      else if (localName.equals("character_name")) 
                      {
 
                               this.in_characternametag = true;
                          //     Log.d(LOCALNAME_TAG, "CHARACTERNAME attribute");
  
                      }
                      else if (localName.equals("char_background")) 
                      {
  
                    	  		this.in_charbackgroundtag = true;
                    	  	//	Log.d(LOCALNAME_TAG, "CHARACTER BACKGROUND attribute");
                      }
                      
                      else if (localName.equals("narrative"))
                      {
                    	  
                    	  	this.in_charnarrative = true;
                    	//  	Log.d(LOCALNAME_TAG, "CHARACTER NARRATIVE attribute");
                      }
                      else
                      {
                    	 // System.out.println("Error: unknown attribute");
                      }
  
              }
  
              /** Gets be called on closing tags like:
  
               * </tag> */
  
              @Override
  
              public void endElement(String namespaceURI, String localName, String qName)
  
                              throws SAXException {
  
            	   if (localName.equals("array"))
            	   {
            		   	this.in_arraytag = false;
            		   		   	
            		   
            	   }
            	   if (localName.equals("array_item")) 
                   {

                         this.in_arrayitemtag= false;
                     	DataSet.addItem(1); 
                         //Log.d(LOCALNAME_TAG, "ARRAY ITEM Attribute End");

                   }
                   else if (localName.equals("script_id")) 
                   {

                           this.in_scriptidtag = false;

                   }
                   else if (localName.equals("character_name")) 
                   {

                            this.in_characternametag = false;

                   }
                   else if (localName.equals("char_background")) 
                   {

                 	  		this.in_charbackgroundtag = false;

                   }
            	   
                   else if(localName.equals("narrative"))
                   {
                	   	this.in_charnarrative = false;
                   }
            	   else
                   {
                 	 // System.out.println("Error: unknown attribute");
                   }

              }
              /** Gets be called on the following structure:
  
               * <tag>characters</tag> */
  
              @Override
  
          public void characters(char ch[], int start, int length) 
          {
  
                      if(in_arrayitemtag)
                      {  
                    	  
                    	  	
                    	  if(in_scriptidtag)
                    	  {
                    		  DataSet.pushScriptID(new String(ch, start, length));  
                    		  
                    	  }
                      
                      	  if(in_characternametag)
                      	  {
                      		  DataSet.pushCharacterName(new String(ch, start, length));
                      	  }
                      	  
                      	  if(in_charbackgroundtag)
                      	  {
                      		  DataSet.pushCharacterBackground(new String (ch, start, length));
                      	  }
                      	  
                      	  if(in_charnarrative)
                      	  {
                      		  DataSet.pushCharacterNarrative(new String(ch, start, length));
                      	  }
                      	  
                      		  
                      	  
                      	  
                      
                      }
                      
  
          }
  
 }

