package com.onlineshop.helpdesk.facades.impl;

import java.util.PriorityQueue;

import java.util.Queue;

import com.onlineshop.helpdesk.entities.SupportTicket;
import com.onlineshop.helpdesk.facades.HelpDeskFacade;
import com.onlineshop.helpdesk.utils.CustomSupportTicketsComparator;

public class DefaultHelpDeskFacade implements HelpDeskFacade {

	private Queue<SupportTicket> tickets;
	
	{
		tickets = new PriorityQueue<>(new CustomSupportTicketsComparator());
	}
	
	@Override
	public int getNumberOfTickets() {
		return tickets.size();
	}

	@Override
	public void addNewSupportTicket(SupportTicket supportTicket) {
		tickets.offer(supportTicket);
		
	}

	@Override
	public SupportTicket getNextSupportTicket() {
		return tickets.poll();
	}

}
