package com.sdl.webapp.common.api.model;

import java.util.Map;

/**
 * Data for the MVC framework to determine how a view model object should be handled.
 */
public interface MvcData {

    String getControllerAreaName();

    String getControllerName();

    String getActionName();

    String getAreaName();

    String getViewName();

    String getRegionAreaName();

    String getRegionName();

    Map<String, String> getRouteValues();

    MvcData deepCopy();

    /**
     * Returns a map of metadata for current MvcData.
     *
     * @return a {@link java.util.Map} object with metadata
     */
    Map<String, Object> getMetadata();

    void addMetadataValue(String key, Object obj);
}
