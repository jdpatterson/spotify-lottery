package com.vercer.spotify.lottery.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("calculator")
public interface GwtLotteryCalculator extends RemoteService, LotteryCalculator
{
}
