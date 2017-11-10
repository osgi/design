package org.osgi.cdi.examples.dto;

import java.util.Collection;

import javax.inject.Inject;

import org.osgi.service.cdi.annotations.Reference;
import org.osgi.service.cdi.dto.model.ContainerModelDTO;
import org.osgi.service.cdi.runtime.CdiRuntime;

public class D1 {

	@Inject
	void model(@Reference CdiRuntime runtime) {
		Collection<ContainerModelDTO> containerModelDTOs = runtime.getContainerModelDTOs();
	}

}
