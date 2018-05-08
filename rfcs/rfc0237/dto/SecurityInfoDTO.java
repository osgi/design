package org.osgi.service.onem2m.dto;
import java.util.*;
public class SecurityInfoDTO extends org.osgi.dto.DTO{
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer securityInfoType;
	public Map<String,Object>  dasRequest;// DynAuthDasRequestDTO
	public Map<String,Object>  dasResponse;//DynAuthDasResponseDTO
	public Map<String,Object> esprimRandObject;//ReceiverESPrimRandObjectDTO
	public String esprimObject;
	public byte[] escertkeMessage;
}
