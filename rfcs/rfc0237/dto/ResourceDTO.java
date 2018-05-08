package org.osgi.service.onem2m.dto;
import java.util.*;

public class ResourceDTO extends org.osgi.dto.DTO{
	
	// Universal Attribute, which can be held by all resources.
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public ResourceType resourceType;
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public String resourceID;
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public String parentID;
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public String creationTime;
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public String lastModifiedTime;

	public String resourceName;

	// optional, Universal Attributes
	public List<String> labels;
	
	/**
	 * Non Universal Attribute.
	 * Value Part must be the types that are allowed for OSGi DTO.
	 */
	public Map<String, Object> attribute;
}
