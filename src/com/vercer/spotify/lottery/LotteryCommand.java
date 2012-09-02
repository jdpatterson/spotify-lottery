package com.vercer.spotify.lottery;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.NumberFormat;

import com.vercer.spotify.lottery.client.LotteryCalculator;
import com.vercer.spotify.lottery.client.StandardLotteryCalculator;

public class LotteryCommand
{
	public static void main(String[] args) throws IOException
	{
		LotteryCalculator calculator = new StandardLotteryCalculator();

		// read parameters from standard input
		String inputs = new BufferedReader(new InputStreamReader(System.in)).readLine();
		
		String[] split = inputs.split(" ");
		
		// should use a command line argument parsing library
		int people = Integer.parseInt(split[0]);
		int winners = Integer.parseInt(split[1]);
		int tickets = Integer.parseInt(split[2]);
		int friends = Integer.parseInt(split[3]);
		
		double result = calculator.calculate(people, winners, friends, tickets);
		
		// write formatted result to standard output
		NumberFormat format = NumberFormat.getInstance();
		format.setMaximumFractionDigits(10);
		System.out.println(format.format(result));
	}
}
