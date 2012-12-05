package com.hoiio.sdk.objects.fax;

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
import com.hoiio.sdk.objects.enums.Currency;
import com.hoiio.sdk.objects.enums.FaxStatus;
import com.hoiio.sdk.util.DateUtil;
import com.hoiio.sdk.util.StringUtil;

public class Fax extends HoiioResponse {
	
	private static enum Params {
		TXN_REF, TAG, SRC, DEST, FAX_STATUS, FAX_PAGES,
		FAX_URL, DATE, CURRENCY, RATE, DEBIT;
		
		public String toString() {
			return this.name().toLowerCase();
		}
	}
	
	private String txnRef;
	private String tag;
	private String src;
	private String dest;
	private FaxStatus faxStatus;
	private Integer faxPages;
	private String faxUrl;
	private Date date;
	private Currency currency;
	private Double rate;
	private Double debit; 
	
	/**
	 * Constructs a new {@code Fax} object by decoding the {@code JSONObject} as a response from the HTTP Request 
	 * @param output The response of the HTTP Request
	 * @throws HoiioException
	 */
	public Fax(JSONObject output) throws HoiioException {
		response = output.toString();
		
		txnRef = StringUtil.getStringFromJSON(output, Params.TXN_REF.toString());
		tag = StringUtil.getStringFromJSON(output, Params.TAG.toString());
		src = StringUtil.getStringFromJSON(output, Params.SRC.toString());
		dest = StringUtil.getStringFromJSON(output, Params.DEST.toString());
		faxStatus = FaxStatus.fromString(StringUtil.getStringFromJSON(output, Params.FAX_STATUS.toString()));
		faxPages = StringUtil.getIntFromJSON(output, Params.FAX_PAGES.toString());
		if (faxPages == null) {
			faxPages = 0;
		}
		faxUrl = StringUtil.getStringFromJSON(output, Params.FAX_URL.toString());
		date = DateUtil.stringToDateTime(StringUtil.getStringFromJSON(output, Params.DATE.toString()));
		currency = Currency.fromString(StringUtil.getStringFromJSON(output, Params.CURRENCY.toString()));
		rate = StringUtil.getDoubleFromJSON(output, Params.RATE.toString());
		debit = StringUtil.getDoubleFromJSON(output, Params.DEBIT.toString());
	}

	/**
	 * Gets the currency used for this transaction
	 * @return Currency used for this transaction. 
	 */
	public Currency getCurrency() {
		return currency;
	}

	/**
	 * Gets the date of this transaction
	 * @return Date/time (GMT+8) of the fax in "YYYY-MM-DD HH:MM:SS" format.
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Gets the total amount billed for this transaction
	 * @return Total amount billed for this transaction.
	 */
	public double getDebit() {
		return debit;
	}

	/**
	 * Gets the destination that the fax is sent to
	 * @return The destination of the fax transaction. Phone numbers start with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getDest() {
		return dest;
	}

	/**
	 * Gets the number of fax pages sent
	 * @return The number of fax pages sent.
	 */
	public int getFaxPages() {
		return faxPages;
	}

	/**
	 * Gets the dial status of the fax
	 * @return Dial status of the fax. 
	 */
	public FaxStatus getFaxStatus() {
		return faxStatus;
	}

	/**
	 * Gets the link to download the PDF file
	 * @return Link to download the PDF file of the fax.
	 */
	public String getFaxUrl() {
		return faxUrl;
	}

	/**
	 * Gets the per-minute charges for this fax transaction
	 * @return Per-minute charges for this fax transaction.
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * Gets the source number of the fax transaction
	 * @return The source of the fax transaction. For outgoing fax this value is empty. Phone numbers start with a "+" and country code (E.164 format), e.g. +6511111111.
	 */
	public String getSrc() {
		return src;
	}

	/**
	 * Gets the reference ID submmited in the initial fax request
	 * @return Your own reference ID submitted in the initial fax Send request. This parameter will not be present if it wasn't included in the initial request.
	 */
	public String getTag() {
		return tag;
	}

	/**
	 * Gets the unique reference iD for this transaction
	 * @return The unique reference ID for this transaction.
	 */
	public String getTxnRef() {
		return txnRef;
	}
	
}
