package com.vercer.spotify.lottery;

import com.google.inject.servlet.ServletModule;
import com.vercer.spotify.lottery.server.LotteryRemoteServlet;

public class LotteryModule extends ServletModule
{
	@Override
	protected void configureServlets()
	{
		serve("/lottery/*").with(new LotteryRemoteServlet());
	}
}
