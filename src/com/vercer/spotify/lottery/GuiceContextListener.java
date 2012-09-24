package com.vercer.spotify.lottery;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.google.inject.Guice;
import com.google.inject.Stage;

public class GuiceContextListener implements ServletContextListener
{
	@Override
	public void contextInitialized(ServletContextEvent arg0)
	{
		Guice.createInjector(Stage.PRODUCTION, new LotteryModule());
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0)
	{
	}
}
