package com.vercer.spotify.lottery;

import static org.junit.Assert.*;

import org.junit.Test;

import com.vercer.spotify.lottery.client.StandardLotteryCalculator;

public class StandardLotteryCalculatorTest
{
	private StandardLotteryCalculator calculator = new StandardLotteryCalculator();
	
	@Test
	public void testCalculate()
	{
		assertTrue(Math.abs(calculator.calculate(100, 10, 2, 2) - 0.1909090909) < 10e-9);
		assertTrue(Math.abs(calculator.calculate(100, 10, 1, 2) - 0.1) < 10e-9);
	}

	@Test
	public void testRequired()
	{
		assertEquals(2, calculator.required(10, 5));
		assertEquals(3, calculator.required(11, 5));
	}

	@Test
	public void testCombination()
	{
		assertEquals(2, (int) calculator.combination(2, 1));
		assertEquals(210, (int) calculator.combination(10, 4));
	}

}
