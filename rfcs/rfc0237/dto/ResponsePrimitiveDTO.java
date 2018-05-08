package org.osgi.service.onem2m.dto;
import java.util.*;

public class ResponsePrimitiveDTO extends org.osgi.dto.DTO{
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public Integer responseStatusCode;
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public String requestIdentifier;
	public PrimitiveContentDTO primitiveContent;
	public String to;
	public String from;
	public String originatingTimestamp;
	public String resultExpirationTimestamp;
	public String eventCategory;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer contentStatus;
	@javax.xml.bind.annotation.XmlElement( required  = false)
	public Integer contentOffset;
	public DynAuthLocalTokenIdAssignmentsDTO assignedTokenIdentifiers;//Map<String,Object>
	public DynAuthTokenReqInfoDTO tokenReqInfo;//DynAuthTokenReqInfoDTO
	

}
