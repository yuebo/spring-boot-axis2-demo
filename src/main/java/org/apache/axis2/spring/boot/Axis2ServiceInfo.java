package org.apache.axis2.spring.boot;

import org.springframework.core.io.Resource;

/**
 * Created by yuebo on 2017/12/14.
 */
public class Axis2ServiceInfo {
    private String name;
    private Resource resource;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }
}
