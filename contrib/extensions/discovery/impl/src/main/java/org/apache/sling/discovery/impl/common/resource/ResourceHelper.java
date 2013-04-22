/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.sling.discovery.impl.common.resource;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ValueMap;

/**
 * Some helper methods surrounding resources
 */
public class ResourceHelper {

    /** Get or create a resource at the given path **/
    public static Resource getOrCreateResource(
            final ResourceResolver resourceResolver, final String path)
            throws RepositoryException {
        Resource resource = resourceResolver.getResource(path);
        if (resource == null) {
            resource = createResource(resourceResolver, path);
        }
        return resource;
    }

    /** Create a resource at the given path **/
    public static Resource createResource(final ResourceResolver resourceResolver,
            final String path) throws RepositoryException {
        final Session session = resourceResolver.adaptTo(Session.class);

        final StringTokenizer st = new StringTokenizer(path, "/");
        Node node = session.getRootNode();
        while (st.hasMoreTokens()) {
            String elem = st.nextToken();
            if (!node.hasNode(elem)) {
                node = node.addNode(elem, "nt:unstructured");
            } else {
                node = node.getNode(elem);
            }
        }
        session.save();
        return resourceResolver.getResource(path);
    }

    /** Compile a stringbuffer containing the properties of a resource - used for logging **/
    public static StringBuffer getPropertiesForLogging(final Resource resource) {
        final ValueMap valueMap = resource.adaptTo(ValueMap.class);
        final Set<Entry<String, Object>> entrySet = valueMap.entrySet();
        final StringBuffer sb = new StringBuffer();
        for (Iterator<Entry<String, Object>> it = entrySet.iterator(); it
                .hasNext();) {
            Entry<String, Object> entry = it.next();
            sb.append(" ");
            sb.append(entry.getKey());
            sb.append("=");
            sb.append(entry.getValue());
        }
        return sb;
    }

}
