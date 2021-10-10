package com.onlineshop.helpdesk.entities;

import static com.onlineshop.helpdesk.entities.Priority.*;

public enum RequestType {
	OTHER(LOW), CHANGE_ACCOUNT_DETAILS(LOW), CAN_NOT_LOGIN(MEDIUM),
	ACCOUNT_IS_BLOCKED(MEDIUM), COOPERATION(MEDIUM), ACCOUNT_IS_HACKED(HIGH),
	CAN_NOT_COMPLETE_PURCHASE(HIGH), ORDER_IS_NOT_RECEIVED(HIGH);

	private Priority priority;

	RequestType(Priority priority) {
		this.priority = priority;
	}

	public Priority getPriority() {
		return this.priority;
	}
}
