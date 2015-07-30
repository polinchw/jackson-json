package com.bpe.model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a User.  It will be used by
 * the Jackson library to marshal/unmarshal with JSON. 
 *
 */
public class User {
	 
	private int age;
	private String name;
	private List<String> messages = new ArrayList<String>() {
		/**
		 * 
		 */
		private static final long serialVersionUID = 7174980653058432030L;

		{
			add("msg 1");
			add("msg 2");
			add("msg 3");
		}
	};		
 
	//getter and setter methods 
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMessages() {
		return messages;
	}    
}