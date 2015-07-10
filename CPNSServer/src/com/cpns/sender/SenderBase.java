package com.cpns.sender;

import com.cpns.exception.CustomException;
import com.cpns.notification.NotificationBase;

public abstract class SenderBase {
	public abstract void sendNotification(NotificationBase notification) throws CustomException;
}
