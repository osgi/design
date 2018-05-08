package org.osgi.service.onem2m.dto;
import java.util.*;
public class DynAuthTokenReqInfoDTO extends org.osgi.dto.DTO{
	@javax.xml.bind.annotation.XmlElement( required  = true)
	public List<Map> dasInfo;//Map : DasInfo
}
