package com.vercer.toolkit.widgets;

import com.google.gwt.event.dom.client.KeyPressEvent;
import com.google.gwt.event.dom.client.KeyPressHandler;
import com.google.gwt.user.client.ui.IntegerBox;

public abstract class CharactersOnlyBox extends IntegerBox implements KeyPressHandler
{
	public CharactersOnlyBox()
	{
		addKeyPressHandler(this);
	}

	@Override
	public void onKeyPress(KeyPressEvent event)
	{
		char charCode = event.getCharCode();
		if (accept(charCode))
		{
			event.preventDefault();
		}
	}
	
	protected abstract boolean accept(char c);
}