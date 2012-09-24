package com.vercer.spotify.lottery.client;

public interface LotteryCalculator
{
	/**
	 * @param tickets The total number of tickets sold in the lottery
	 * @param drawn How many winners are drawn
	 * @param friends Number of people in our group
	 * @param limit The number of tickets each winner can buy
	 * @return The probability that the whole group will get tickets
	 */
	public abstract double calculate(int tickets, int drawn, int friends, int limit);
}
