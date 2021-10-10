package com.onlineshop.helpdesk.utils;

import java.util.Comparator;

import com.onlineshop.helpdesk.entities.SupportTicket;

public class CustomSupportTicketsComparator implements Comparator<SupportTicket> {

	@Override
	public int compare(SupportTicket ticket1, SupportTicket ticket2) {
		if (ticket1 == null || ticket2 == null || ticket1.getPriority() == null || ticket2.getPriority() == null) {
			return 0;
		}
		int retVal = ticket2.getPriority().compareTo(ticket1.getPriority());
		if (retVal == 0) {
			retVal = ticket1.getSequentialNumber() - ticket2.getSequentialNumber();
		}
		return retVal;
	}

}
