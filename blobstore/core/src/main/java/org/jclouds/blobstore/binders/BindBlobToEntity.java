/**
 *
 * Copyright (C) 2009 Global Cloud Specialists, Inc. <info@globalcloudspecialists.com>
 *
 * ====================================================================
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
 * ====================================================================
 */
package org.jclouds.blobstore.binders;

import static com.google.common.base.Preconditions.checkNotNull;

import javax.ws.rs.core.HttpHeaders;

import org.jclouds.blobstore.domain.Blob;
import org.jclouds.http.HttpRequest;
import org.jclouds.http.HttpUtils;
import org.jclouds.rest.Binder;

public class BindBlobToEntity implements Binder {

   public void bindToRequest(HttpRequest request, Object entity) {
      Blob<?> object = (Blob<?>) entity;

      request.setEntity(checkNotNull(object.getData(), "object.getContent()"));
      request.getHeaders()
               .put(
                        HttpHeaders.CONTENT_TYPE,
                        checkNotNull(object.getMetadata().getContentType(),
                                 "object.metadata.contentType()"));

      request.getHeaders().put(HttpHeaders.CONTENT_LENGTH, object.getMetadata().getSize() + "");

      if (object.getMetadata().getContentMD5() != null) {
         request.getHeaders().put("Content-MD5",
                  HttpUtils.toBase64String(object.getMetadata().getContentMD5()));
      }
   }
}