package com.hoiio.sdk.objects.voice;

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

import java.util.Date;

import net.sf.json.JSONObject;

import com.hoiio.sdk.exception.HoiioException;
import com.hoiio.sdk.objects.HoiioResponse;
import com.hoiio.sdk.objects.enums.CallStatus;
import com.hoiio.sdk.objects.enums.Currency;
import com.hoiio.sdk.util.DateUtil;
import com.hoiio.sdk.util.StringUtil;

public class Call extends HoiioResponse {
	
	private static enum Params {
		TXN_REF, TAG, DEST1, DEST2, CALL_STATUS_DEST1, RATE,
		CALL_STATUS_DEST2, DATE, DURATION, CURRENCY, DEBIT;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String txnRef;
	private String tag;
	private String dest1;
	private String dest2;
	private CallStatus callStatusDest1;
	private CallStatus callStatusDest2;
	private Date date;
	private Integer duration;
	private Currency currency;
	private Double rate;
	private Double debit;
	
	/**
	 * Constructs a new {@code Call} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 */
	public Call(JSONObject output) throws HoiioException {
		response = output.toString();
		
		txnRef = StringUtil.getStringFromJSON(output, Params.TXN_REF.toString());
		tag = StringUtil.getStringFromJSON(output, Params.TAG.toString());
		dest1 = StringUtil.getStringFromJSON(output, Params.DEST1.toString());
		dest2 = StringUtil.getStringFromJSON(output, Params.DEST2.toString());
		callStatusDest1 = CallStatus.fromString(StringUtil.getStringFromJSON(output, Params.CALL_STATUS_DEST1.toString()));
		callStatusDest2 = CallStatus.fromString(StringUtil.getStringFromJSON(output, Params.CALL_STATUS_DEST2.toString()));
		date = DateUtil.stringToDateTime(StringUtil.getStringFromJSON(output, Params.DATE.toString()));
		currency = Currency.fromString(StringUtil.getStringFromJSON(output, Params.CURRENCY.toString()));
		rate = StringUtil.getDoubleFromJSON(output, Params.RATE.toString());
		
		// When a call is still ongoing, the duration and debit are not returned.
		if (!callStatusDest1.toString().equals(CallStatus.ONGOING.toString())) {
			duration = output.getInt(Params.DURATION.toString());
			debit = output.getDouble(Params.DEBIT.toString());
		}
	}

	/**
	 * Gets the status of the call to dest1
	 * @return Dial status of the call to dest1.
	 */
	public CallStatus getCallStatusDest1() {
		return callStatusDest1;
	}

	/**
	 * Gets the status of the call to dest2
	 * @return Dial status of the call to dest2.
	 */
	public CallStatus getCallStatusDest2() {
		return callStatusDest2;
	}

	/**
	 * Gets the currency used for this transaction
	 * @return Currency used for this transaction. 
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Gets the Date/time of this transaction
	 * @return Date/time (GMT+8) of the call in "YYYY-MM-DD HH:MM:SS" format.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the total amount billed for this transaction
	 * @return Total amount billed for this transaction. If the call is still ongoing, this parameter will be returned as null.
	 */
	public double getDebit() {
		return debit;
	}

	/**
	 * Gets the first number dialed in this transaction
	 * @return The first number dialed in this call. Phone numbers start with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getDest1() {
		return dest1;
	}

	/**
	 * Gets the second number dialed in this transaction
	 * @return The second number dialed in this call. Phone numbers start with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getDest2() {
		return dest2;
	}

	/**
	 * Gets the duration of the call in minutes
	 * @return Duration of the call in minutes. If the call is still ongoing, this parameter will be returned as null.
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Gets the per-minute charges for this transaction
	 * @return Per-minute charges for this call transaction.
	 */
	public double getRate() {
		return rate;
	}	

	/**
	 * Gets your own reference ID submitted in the initial Call request
	 * @return Your own reference ID submitted in the initial MakeCalls request. This parameter will not be present if it wasn't included in the initial request.
	 */
	public String getTag() {
		return tag;
	}
	
	/**
	 * Gets the unique reference ID for this transaction
	 * @return The unique reference ID for this transaction.
	 */
	public String getTxnRef() {
		return txnRef;
	}
}
