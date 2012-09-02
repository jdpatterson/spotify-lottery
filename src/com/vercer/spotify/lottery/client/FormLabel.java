package com.vercer.spotify.lottery.client;

import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.LabelElement;
import com.google.gwt.user.client.ui.Widget;

public class FormLabel extends Widget
{
	public FormLabel(Widget widget, String id, String text)
	{
		setElement(Document.get().createLabelElement());
		widget.getElement().setId(id);
		((LabelElement) getElement().cast()).setHtmlFor(id);
		getElement().setInnerText(text);
	}
}
