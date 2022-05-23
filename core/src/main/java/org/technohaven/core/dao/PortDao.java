package org.technohaven.core.dao;

import org.technohaven.core.entities.Port;

import javax.annotation.Nonnull;
import java.util.List;

public interface PortDao {

    @Nonnull
    Port save(@Nonnull Port port);

    @Nonnull
    List<Port> readAllPort();

    /**
     * Retrieve a subset of all Ports
     *
     * @param limit the maximum number of results, defaults to 20
     * @param offset the starting point in the record set, defaults to 0
     * @return
     */
    @Nonnull
    public List<Port> readPorts(@Nonnull int limit, @Nonnull int offset);

    @Nonnull
    public List<Port> readPortsByName(@Nonnull String portName, int limit, int offset);
    
}
