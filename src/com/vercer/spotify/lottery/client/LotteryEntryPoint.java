package com.vercer.spotify.lottery.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.vercer.toolkit.widgets.IntegerOnlyBox;

public class LotteryEntryPoint implements EntryPoint, ChangeHandler, ClickHandler
{
	private CheckBox serverCheckBox;
	private IntegerOnlyBox ticketsBox;
	private IntegerOnlyBox friendsBox;
	private IntegerOnlyBox winnersBox;
	private IntegerOnlyBox limitBox;
	private Image busyImage;
	private Label resultLabel;
	
	@Override
	public void onModuleLoad()
	{
		RootPanel root = RootPanel.get("calculator");

		Grid grid = new Grid(2, 5);
		root.add(grid);

		serverCheckBox = new CheckBox("Run on server (delayed response)");
		serverCheckBox.setValue(true);
		serverCheckBox.addClickHandler(this);
		root.add(serverCheckBox);

		grid.setWidget(0, 0, new Label("Tickets sold (m)"));
		ticketsBox = new IntegerOnlyBox();
		ticketsBox.addChangeHandler(this);
		grid.setWidget(1, 0, ticketsBox);

		grid.setWidget(0, 1, new Label("Winners drawn (n)"));
		winnersBox = new IntegerOnlyBox();
		winnersBox.addChangeHandler(this);
		grid.setWidget(1, 1, winnersBox);
		
		grid.setWidget(0, 2, new Label("Tickets per winner (t)"));
		limitBox = new IntegerOnlyBox();
		limitBox.addChangeHandler(this);
		grid.setWidget(1, 2, limitBox);
		
		grid.setWidget(0, 3, new Label("Friends in group (p)"));
		friendsBox = new IntegerOnlyBox();
		friendsBox.addChangeHandler(this);
		grid.setWidget(1, 3, friendsBox);

		FlowPanel resultPanel = new FlowPanel();
		resultPanel.setStyleName("result");
		root.add(resultPanel);
		busyImage = new Image("busy.gif");
		busyImage.setVisible(false);
		resultPanel.add(busyImage);
		
		resultLabel = new Label();
		resultPanel.add(resultLabel);
		
		ticketsBox.setFocus(true);
	}

	@Override
	public void onChange(ChangeEvent event)
	{
		change();
	}

	@Override
	public void onClick(ClickEvent event)
	{
		change();
	}

	protected void change()
	{
		Integer tickets = ticketsBox.getValue();
		Integer friends = friendsBox.getValue();
		Integer winners = winnersBox.getValue();
		Integer limit = limitBox.getValue();
		
		if (validate(tickets, 1000, 1, ticketsBox) &&
			validate(winners, tickets, 1, winnersBox) &&
			validate(limit, 100, 1, limitBox) &&
			validate(friends, tickets, 1, friendsBox))
		{
			if (tickets != null && friends != null && winners != null && limit != null)
			{
				resultLabel.setText("");
				if (serverCheckBox.getValue())
				{
					GwtLotteryCalculatorAsync asyncCalculator = GWT.create(GwtLotteryCalculator.class);
					busyImage.setVisible(true);
					asyncCalculator.calculate(tickets, winners, friends, limit, new AsyncCallback<Double>()
					{
						@Override
						public void onFailure(Throwable caught)
						{
							Window.alert("Failed to communicate with server");
							busyImage.setVisible(false);
						}
	
						@Override
						public void onSuccess(Double result)
						{
							updateResult(result);
							busyImage.setVisible(false);
						}
					});
				}
				else
				{
					LotteryCalculator calculator = new StandardLotteryCalculator();
					double result = calculator.calculate(tickets, winners, friends, limit);
					updateResult(result);
				}
			}
		}
	}
	
	private boolean validate(Integer value, Integer maximum, int minimum, IntegerOnlyBox input)
	{
		if (value != null && (maximum != null && value > maximum || value < minimum))
		{
			input.setFocus(true);
			input.selectAll();
			input.addStyleName("error");
			return false;
		}
		else
		{
			input.removeStyleName("error");
			return true;
		}
	}

	protected void updateResult(Double result)
	{
		NumberFormat format = NumberFormat.getFormat("0.#########");
		resultLabel.setText(format.format(result));
	}

}
