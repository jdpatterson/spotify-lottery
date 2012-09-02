package com.vercer.spotify.lottery.server;

import javax.inject.Singleton;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.vercer.spotify.lottery.client.LotteryCalculator;
import com.vercer.spotify.lottery.client.StandardLotteryCalculator;

@Singleton
public class LotteryRemoteServlet extends RemoteServiceServlet implements LotteryCalculator
{
	private static final long serialVersionUID = 1L;

	private StandardLotteryCalculator calculator = new StandardLotteryCalculator();
	
	@Override
	public double calculate(int tickets, int drawn, int friends, int limit)
	{
		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			throw new RuntimeException(e);
		}
		return calculator.calculate(tickets, drawn, friends, limit);
	}
}
