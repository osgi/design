package org.osgi.service.onem2m.dto;
import java.util.*;

public class PrimitiveContentDTO extends org.osgi.dto.DTO{
	ResourceDTO resource;
	ResourceWrapperDTO resourceWrapper;
	List<NotificationDTO> aggregatedNotification;
	SecurityInfoDTO securityInfo;
	ResponsePrimitiveDTO responsePrimitive;

	String debugInfo;
	
	List<String> listOfURIs;
	String uri;
	
	List<ResponsePrimitiveDTO> aggregatedResponse;
	List<ChildResourceRefDTO> childResourceRefList;
}
