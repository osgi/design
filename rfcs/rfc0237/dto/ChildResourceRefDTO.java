package org.osgi.service.onem2m.dto;
public class ChildResourceRefDTO extends org.osgi.dto.DTO{
	public String uri;
	public String name;
	public Integer type;//Resource Type    TODO: should be int ?
	public String specializationID;// any URI, optional
}
