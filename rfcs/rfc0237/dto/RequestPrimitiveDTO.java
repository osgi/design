package org.osgi.service.onem2m.dto;

import java.util.*;

public class RequestPrimitiveDTO extends org.osgi.dto.DTO {
	@javax.xml.bind.annotation.XmlElement(required = true)
	public Operation operation;
	@javax.xml.bind.annotation.XmlElement(required = true)
	public String to;
	public String from;
	@javax.xml.bind.annotation.XmlElement(required = true)
	public String requestIdentifier;
	@javax.xml.bind.annotation.XmlElement(required = false)
	public Integer resourceType;
	public PrimitiveContentDTO primitiveContent;
	public List<String> roleIDs;
	public String originatingTimestamp;
	public String requestExpirationTimestamp;
	public String resultExpirationTimestamp;
	public String operationExecutionTime;
	public ResponseTypeInfoDTO responseType;
	public String resultPersistence;
	@javax.xml.bind.annotation.XmlElement(required = false)
	public ResultContent resultContent;
	public String eventCategory;
	@javax.xml.bind.annotation.XmlElement(required = false)
	public Boolean deliveryAggregation;
	public String groupRequestIdentifier;
	public FilterCriteriaDTO filterCriteria;
	@javax.xml.bind.annotation.XmlElement(required = false)
	public DiscoveryResultType discoveryResultType;
	public String tokens;
	public String tokenIDs;
	public List<String> localTokenIDs;
	@javax.xml.bind.annotation.XmlElement(required = false)
	public Boolean tokenReqIndicator;

	public static enum DiscoveryResultType {
		structured(1), unstructured(2);

		int value;

		private DiscoveryResultType(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}
	}

	public static enum ResultContent {
		nothing(1), attributes(2), hierarchicalAddress(3), hierarchicalAddressAndAttributes(
				4), attributesAndChildResources(5), attributesAndChildResourceReferences(
						6), childResourceReferences(7), originalResource(8), childResources(9);

		int value;

		private ResultContent(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}
	}

	public static enum Operation {
		Create(1), Retrieve(2), Update(3), Delete(4), Notify(5);

		int value;

		private Operation(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}
	}

}
