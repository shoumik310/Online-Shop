package com.onlineshop.helpdesk.facades;

import com.onlineshop.helpdesk.entities.SupportTicket;

public interface HelpDeskFacade {

	void addNewSupportTicket(SupportTicket supportTicket);

	SupportTicket getNextSupportTicket();

		/**
	 * @return amount of tickets that are not processed
	 */
	int getNumberOfTickets();

}
