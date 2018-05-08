package org.osgi.service.onem2m.dto;

import java.util.*;

public class ResponseTypeInfoDTO extends org.osgi.dto.DTO {
	@javax.xml.bind.annotation.XmlElement(required = true)
	public java.lang.Integer responseTypeValue;
	@javax.xml.bind.annotation.XmlElement(required = true)
	public List<java.lang.String> notificationURI;

	public static enum ResponseType {
		nonBlockingRequestSynch(1), nonBlockingRequestAsynch(2), blockingRequest(3), flexBlocking(4);

		private int value;

		private ResponseType(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}
	}
}
