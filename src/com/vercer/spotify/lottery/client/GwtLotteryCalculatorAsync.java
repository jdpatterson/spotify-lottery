package com.vercer.spotify.lottery.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GwtLotteryCalculatorAsync
{
	public abstract void calculate(int tickets, int drawn, int friends, int limit, AsyncCallback<Double> callback);
}
