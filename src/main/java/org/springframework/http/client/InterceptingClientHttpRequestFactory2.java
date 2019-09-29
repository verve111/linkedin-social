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

package org.springframework.http.client;

import java.net.URI;
import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpMethod;

/**
 * Wrapper for a {@link ClientHttpRequestFactory2} that has support for {@link ClientHttpRequestInterceptor2}s.
 *
 * @author Arjen Poutsma
 * @since 3.1
 */
public class InterceptingClientHttpRequestFactory2 extends AbstractClientHttpRequestFactoryWrapper2 {

	private final List<ClientHttpRequestInterceptor2> interceptors;

	/**
	 * Creates a new instance of the {@code InterceptingClientHttpRequestFactory} with the given parameters.
	 *
	 * @param requestFactory the request factory to wrap
	 * @param interceptors the interceptors that are to be applied. Can be {@code null}.
	 */
	public InterceptingClientHttpRequestFactory2(ClientHttpRequestFactory2 requestFactory,
			List<ClientHttpRequestInterceptor2> interceptors) {
		super(requestFactory);
		this.interceptors = interceptors != null ? interceptors : Collections.<ClientHttpRequestInterceptor2>emptyList();
	}

	@Override
	protected ClientHttpRequest2 createRequest(URI uri, HttpMethod httpMethod, ClientHttpRequestFactory2 requestFactory) {
		InterceptingClientHttpRequest2 i = new InterceptingClientHttpRequest2(requestFactory, interceptors, uri, httpMethod);
		return i;
	}
}
