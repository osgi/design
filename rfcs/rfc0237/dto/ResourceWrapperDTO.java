package org.osgi.service.onem2m.dto;
public class ResourceWrapperDTO extends org.osgi.dto.DTO{
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public String uri;
	
	ResourceDTO resource;
}
