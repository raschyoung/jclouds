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
package org.jclouds.blobstore.util;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.IOException;
import java.io.InputStream;

import org.jclouds.blobstore.domain.Blob;
import org.jclouds.blobstore.domain.BlobMetadata;
import org.jclouds.blobstore.domain.Key;
import org.jclouds.util.Utils;

/**
 * Encryption, Hashing, and IO Utilities needed to sign and verify S3 requests and responses.
 * 
 * @author Adrian Cole
 */
public class BlobStoreUtils {

   public static Key parseKey(Key key) {

      if (key.getKey().indexOf('/') != -1) {
         String container = key.getContainer() + '/'
                  + key.getKey().substring(0, key.getKey().lastIndexOf('/'));
         String newKey = key.getKey().substring(key.getKey().lastIndexOf('/') + 1);
         key = new Key(container.replaceAll("//", "/"), newKey);
      }
      return key;
   }

   public static String getContentAsStringAndClose(Blob<? extends BlobMetadata> object)
            throws IOException {
      checkNotNull(object, "s3Object");
      checkNotNull(object.getData(), "s3Object.content");
      Object o = object.getData();

      if (o instanceof InputStream) {
         String returnVal = Utils.toStringAndClose((InputStream) o);
         if (object.getMetadata().getContentType().indexOf("xml") >= 0) {

         }
         return returnVal;
      } else {
         throw new IllegalArgumentException("Object type not supported: " + o.getClass().getName());
      }
   }
}