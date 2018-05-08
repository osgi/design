package org.osgi.service.onem2m.dto;
import java.util.*;

public class FilterCriteriaDTO extends org.osgi.dto.DTO{
	public String createdBefore;
	public String createdAfter;
	public String modifiedSince;
	public String unmodifiedSince;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer stateTagSmaller;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer stateTagBigger;
	public String expireBefore;
	public String expireAfter;
	public List<String> labels;
	public List<Integer> resourceType;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer sizeAbove;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer sizeBelow;
	public List<String> contentType;
	public List<Map<String,Object>> attribute;// List<Attribute>
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public FilterUsage filterUsage;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer limit;
	public List<String> semanticsFilter;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public FilterOperation filterOperation;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer contentFilterSyntax;
	public String contentFilterQuery;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer level;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer offset;

	public static enum FilterOperation {
		AND(1), OR(2);

		private int value;

		private FilterOperation(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}
	}

	public static enum FilterUsage {
		DiscoveryCriteria(1), ConditionalRetrival(2), IPEOndemandDiscovery(3);

		private int value;

		private FilterUsage(int i) {
			value = i;
		}

		public int getValue() {
			return value;
		}

	}
}
