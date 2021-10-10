package com.onlineshop.helpdesk.entities.impl;

import com.onlineshop.helpdesk.entities.Priority;
import com.onlineshop.helpdesk.entities.RequestType;
import com.onlineshop.helpdesk.entities.SupportTicket;

public class DefaultSupportTicket implements SupportTicket {

	private static int counter = 1;
	private int sequenceNumber;
	private RequestType requestType;

	{
		sequenceNumber = counter++;
	}

	public DefaultSupportTicket() {
	};

	public DefaultSupportTicket(RequestType requestType) {
		this.requestType = requestType;
	}

	@Override
	public int getSequentialNumber() {
		return this.sequenceNumber;
	}

	@Override
	public Priority getPriority() {
		if(requestType==null) {
			return null;
		}
		return this.requestType.getPriority();
	}

	@Override
	public RequestType getRequestType() {
		return this.requestType;
	}

}
