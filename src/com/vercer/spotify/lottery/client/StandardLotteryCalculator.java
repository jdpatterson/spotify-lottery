package com.vercer.spotify.lottery.client;

public class StandardLotteryCalculator implements LotteryCalculator
{
	@Override
	public double calculate(int tickets, int drawn, int friends, int limit)
	{
		// how many tickets do we need to win
		int required = required(friends, limit);
		
		// add odds for each number of winning tickets above required amount
		double totalOddsForAllWinning = 0;
		double totalCombos = combination(tickets, drawn);
		for (int winning = required; winning <= friends; winning++)
		{
			totalOddsForAllWinning += oddsForNumberOfWins(tickets, drawn, friends, winning, totalCombos);;
		}
		
		return totalOddsForAllWinning;
	}

	/**
	 * The number of winning tickets needed to allow all friends to enter
	 */
	public int required(int friends, int limit)
	{
		return (int) Math.ceil((double) friends / limit);
	}
	
	/**
	 * The total number of combinations of selecting 'choose' values out of 'from' values regardless of order 
	 */
	public double combination(int from, int choose)
	{
		double result = 1;
		for (int i = 0; i < choose; i++)
		{
			result = result * (from - i);
			result = result / (choose - i);
		}
		return result;
	}
	
	/**
	 * The odds of choosing 'required' winning tickets from 'drawn' given 'tickets' possible values
	 */
	public double oddsForNumberOfWins(int tickets, int drawn, int friends, int required, double totalCombos)
	{
		// number of ways can choose required number of winners from our friends tickets
		double winningCombos = combination(friends, required);
		
		// number of ways we can choose losers from strangers tickets 
		double losingCombos = combination(tickets - friends, drawn - required);
		
		return (losingCombos * winningCombos) / totalCombos;
	}
}
