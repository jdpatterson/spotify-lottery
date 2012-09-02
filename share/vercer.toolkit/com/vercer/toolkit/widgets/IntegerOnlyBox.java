package com.vercer.toolkit.widgets;


public class IntegerOnlyBox extends CharactersOnlyBox
{
	public IntegerOnlyBox()
	{
		addStyleName("IntegerOnlyBox");
	}
	
	protected boolean accept(char charCode)
	{
		return charCode < '0' || charCode > '9';
	}
}
