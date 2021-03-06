package com.hoiio.sdk.objects.account;

/*
Copyright (C) 2012 Hoiio Pte Ltd (http://www.hoiio.com)

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without
restriction, including without limitation the rights to use,
copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the
Software is furnished to do so, subject to the following
conditions:

The above copyright notice and this permission notice shall be
included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR
OTHER DEALINGS IN THE SOFTWARE.
*/

import net.sf.json.JSONObject;

import com.hoiio.sdk.objects.HoiioResponse;
import com.hoiio.sdk.objects.enums.Currency;
import com.hoiio.sdk.util.StringUtil;

public class Balance extends HoiioResponse {
	
	private static enum Params {
		CURRENCY, BALANCE, POINTS, BONUS;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private Currency currency;
	private Double balance;
	private Double points;
	private Double bonus;
	
	/**
	 * Constructs a new {@code Balance} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Balance(JSONObject output) {
		response = output.toString();
		
		currency = Currency.fromString(StringUtil.getStringFromJSON(output, Params.CURRENCY.toString()));
		balance = output.getDouble(Params.BALANCE.toString());
		points = output.getDouble(Params.POINTS.toString());
		bonus = output.getDouble(Params.BONUS.toString());
	}

	/** 
	 * Gets the total balance of this account
	 * @return The total available credit balance for this account. This is the sum of your Hoiio Points and Hoiio Bonus Points.
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * Gets the Bonus Points credit balance of this account
	 * @return Your Hoiio Bonus Points credit balance. Hoiio Bonus Points cannot be transferred. 
	 */
	public double getBonus() {
		return bonus;
	}

	/**
	 * Gets the currency used for this account
	 * @return Currency used for this account. 
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Gets the Hoiio Points credit balance of this account
	 * @return Your Hoiio Points credit balance. Hoiio Points can be transferred to another account.
	 */
	public double getPoints() {
		return points;
	}
	
}
