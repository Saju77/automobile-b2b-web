package org.technohaven.api.services;

import org.technohaven.core.entities.Port;

import java.util.List;

public interface PortService {

    List<Port> getAllPort();

    List<Port> findPorts(int limit, int offset);

    /**
     * Retrieve a list of {@code Port} instances based on the search criteria
     *
     * @param portName the name of the Port to search by
     * @param limit the maximum number of results to return
     * @param offset the starting point of the records to return
     * @return a list of Port instances that match the search criteria
     */
    List<Port> findPortsByName(String portName, int limit, int offset);

}
