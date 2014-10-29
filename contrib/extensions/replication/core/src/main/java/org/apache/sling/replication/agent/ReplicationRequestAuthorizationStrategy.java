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
package org.apache.sling.replication.agent;

import javax.annotation.Nonnull;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.replication.communication.ReplicationRequest;
import org.apache.sling.replication.component.ReplicationComponent;

/**
 * Strategy for authorizing requests
 */
public interface ReplicationRequestAuthorizationStrategy extends ReplicationComponent {

    /**
     * @param resourceResolver   a {@link org.apache.sling.api.resource.ResourceResolver} representing the calling 'user'
     * @param replicationRequest a request bringing metadata for getting {@link org.apache.sling.replication.packaging.ReplicationPackage}s
     *                           to be exported
     * @throws ReplicationRequestAuthorizationException if the {@link org.apache.sling.api.resource.ResourceResolver} is
     *                                                  not authorized to execute the given {@link org.apache.sling.replication.communication.ReplicationRequest}
     */
    void checkPermission(@Nonnull ResourceResolver resourceResolver, @Nonnull ReplicationRequest replicationRequest) throws ReplicationRequestAuthorizationException;

}
