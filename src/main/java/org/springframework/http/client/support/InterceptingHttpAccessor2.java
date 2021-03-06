/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.http.client.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.client.ClientHttpRequestFactory2;
import org.springframework.http.client.ClientHttpRequestInterceptor2;
import org.springframework.http.client.InterceptingClientHttpRequestFactory2;
import org.springframework.util.CollectionUtils;

/**
 * Base class for {@link org.springframework.web.client.RestTemplateExt} and other HTTP accessing gateway helpers, adding
 * interceptor-related properties to {@link HttpAccessor2}'s common properties.
 *
 * <p>Not intended to be used directly. See {@link org.springframework.web.client.RestTemplateExt}.
 *
 * @author Arjen Poutsma
 */
public abstract class InterceptingHttpAccessor2 extends HttpAccessor2 {

	private List<ClientHttpRequestInterceptor2> interceptors = new ArrayList<ClientHttpRequestInterceptor2>();

	/**
	 * Sets the request interceptors that this accessor should use.
	 */
	public void setInterceptors(List<ClientHttpRequestInterceptor2> interceptors) {
		this.interceptors = interceptors;
	}

	/**
	 * Return the request interceptor that this accessor uses.
	 */
	public List<ClientHttpRequestInterceptor2> getInterceptors() {
		return interceptors;
	}

	@Override
	public ClientHttpRequestFactory2 getRequestFactory() {
		ClientHttpRequestFactory2 delegate = super.getRequestFactory();
		if (!CollectionUtils.isEmpty(getInterceptors())) {
			return new InterceptingClientHttpRequestFactory2(delegate, getInterceptors());
		}
		else {
			return delegate;
		}
	}

}
