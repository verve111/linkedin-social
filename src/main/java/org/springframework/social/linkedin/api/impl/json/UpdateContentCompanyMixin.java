/*
 * Copyright 2014 the original author or authors.
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
package org.springframework.social.linkedin.api.impl.json;

import java.io.IOException;

import org.springframework.social.linkedin.api.Company;
import org.springframework.social.linkedin.api.CompanyJobUpdate;
import org.springframework.social.linkedin.api.Share;
import org.springframework.social.linkedin.api.UrlResource;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
abstract class UpdateContentCompanyMixin extends LinkedInObjectMixin {

	@JsonCreator
	UpdateContentCompanyMixin (
		@JsonProperty("id") String id, 
		@JsonProperty("firstName") String firstName, 
		@JsonProperty("lastName") String lastName, 
		@JsonProperty("headline") String headline, 
		@JsonProperty("industry") String industry, 
		@JsonProperty("publicProfileUrl") String publicProfileUrl, 
		@JsonProperty("siteStandardProfileRequest") UrlResource siteStandardProfileRequest, 
		@JsonProperty("pictureUrl") String profilePictureUrl) {}
	
	@JsonProperty("company")
	Company company;
	
	@JsonProperty("companyStatusUpdate")
	@JsonDeserialize(using = CompanyStatusUpdateDeserializer.class)
	Share companyStatusUpdate;
	
	@JsonProperty("companyJobUpdate")
	CompanyJobUpdate companyJobUpdate;
	
	private static final class CompanyStatusUpdateDeserializer extends JsonDeserializer<Share> {
		@Override
		public Share deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new LinkedInModule());
			jp.setCodec(mapper);
			
			JsonNode node = (JsonNode) jp.readValueAs(JsonNode.class);
			return mapper.reader(new TypeReference<Share>() {}).readValue(node.get("share"));
		}
		
	}

}
