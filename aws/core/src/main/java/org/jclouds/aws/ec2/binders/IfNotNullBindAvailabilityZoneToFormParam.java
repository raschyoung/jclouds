/**
 *
 * Copyright (C) 2009 Cloud Conscious, LLC. <info@cloudconscious.com>
 *
 * ====================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ====================================================================
 */
package org.jclouds.aws.ec2.binders;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import javax.inject.Singleton;

import org.jclouds.aws.ec2.domain.AvailabilityZone;
import org.jclouds.http.HttpRequest;
import org.jclouds.rest.Binder;
import org.jclouds.rest.internal.GeneratedHttpRequest;

/**
 * Binds the AvailabilityZone to a form parameter if set.
 * 
 * @author Adrian Cole
 */
@Singleton
public class IfNotNullBindAvailabilityZoneToFormParam implements Binder {

   @SuppressWarnings("unchecked")
   public void bindToRequest(HttpRequest request, Object input) {
      if (input != null) {
         checkArgument(checkNotNull(request, "request") instanceof GeneratedHttpRequest,
                  "this binder is only valid for GeneratedHttpRequests!");
         checkArgument(input instanceof AvailabilityZone,
                  "this binder is only valid for AvailabilityZone!");
         ((GeneratedHttpRequest<?>) request).addFormParam("Placement.AvailabilityZone",
                  ((AvailabilityZone) input).value());
      }
   }

}