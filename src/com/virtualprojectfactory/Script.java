package com.virtualprojectfactory;

import java.util.ArrayList;

public class Script 
{
	

	private int ScriptItems = 0; //amount of scripts available
	private int line = 1; //default line starts at 1
	private int auth = 0;
	private ArrayList<String> scriptid_array = new ArrayList<String>();
	private ArrayList<String> charactername_array = new ArrayList<String>();
	private ArrayList<String> characterbackground_array = new ArrayList<String>();
	private ArrayList<String> narrative = new ArrayList<String>();
	private ArrayList<String> primary_key_value = new ArrayList<String>();
	private String speech = null;
	
	public void addItem(int offset)
	{
		ScriptItems = ScriptItems + offset;
	}
	
	public int Number_of_Items()
	{
		return ScriptItems;
	}
	
	public void pushScriptID(String id)
	{

			scriptid_array.add(id);
	}
	
	public String getScriptID(int index)
	{
		
			return scriptid_array.get(index);
	}
	
	
	public void pushCharacterName(String name)
	{
			charactername_array.add(name);
	}
	
	public ArrayList<String> getCharacterNames()
	{
			return charactername_array;
	}
	
	public String getCharacterNames(int index)
	{
		
			return charactername_array.get(index);
	}
	
	public void pushCharacterBackground(String background)
	{
			characterbackground_array.add(background);
	}
	
	public String getCharacterBackground(int index)
	{
			return characterbackground_array.get(index);
	}
	
	public void pushCharacterNarrative(String N)
	{
		   narrative.add(N);
	}
	
	public String getCharacterNarrative(int index)
	{
		return narrative.get(index);
	}
	
	public void pushPrimaryKeyValue(String key)
	{
		primary_key_value.add(key);
	}
	
	public String getPrimaryKeyValue(int index)
	{
		return primary_key_value.get(index);
	}
	
	public int getLine()
	{
		 return line;
	}
	
	public void setCurrentLine(int x)
	{
		 line = x;
	}

	public void setSpeechText(String nextText) 
	{
		
		speech = nextText;
		
	}
	
	public String getSpeechText()
	{
		return speech;
	}

	public void setAuth(int i) 
	{
		
			auth = i;
			
	}
	
	public int getAuth()
	{
		 return auth;
	}
};




