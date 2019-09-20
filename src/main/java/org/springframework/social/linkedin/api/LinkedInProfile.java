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
package org.springframework.social.linkedin.api;

import java.io.Serializable;

import org.springframework.social.linkedin.api.impl.mapped.FirstName;
import org.springframework.social.linkedin.api.impl.mapped.LastName;
import org.springframework.social.linkedin.api.impl.mapped.ProfilePicture;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Model class containing a user's LinkedIn profile information.
 * 
 * @author Craig Walls
 */
public class LinkedInProfile extends LinkedInObject implements Serializable {

	private static final long serialVersionUID = 1L;

	private final String id;
	
	@JsonProperty("firstName")
	private final FirstName firstName;
	
	@JsonProperty("lastName")
	private final LastName lastName;
	
	private final String headline;
	
	private final String industry;

	private String emailAddress;
	
	private String localizedLastName;
	private String localizedFirstName;
	
	private final String publicProfileUrl;
	
	private final ProfilePicture profilePicture;
	private String summary;
	
	public LinkedInProfile() {
		this.id = "dummy";
		this.firstName = null;
		this.lastName = null;
		this.headline = "dummy";
		this.industry = "dummy";
		this.publicProfileUrl = "dummy";
		this.profilePicture = null;		
	}
	
	public LinkedInProfile(String id, FirstName firstName, LastName lastName, String headline, String industry,
			String publicProfileUrl, ProfilePicture profilePicture) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = null;
		this.headline = headline;
		this.industry = industry;
		this.publicProfileUrl = publicProfileUrl;
		this.profilePicture = profilePicture;
	}

	/**
	 * The user's LinkedIn profile ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * The user's first name
	 */
	public FirstName getFirstName() {
		return firstName;
	}

	/**
	 * The user's last name
	 */
	public LastName getLastName() {
		return lastName;
	}

	/**
	 * The user's email address (if available).
	 * Requires "r_emailaddress" scope; will be null if "r_emailaddress" scope is not authorized on the connection.
	 */
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * The user's headline
	 */
	public String getHeadline() {
		return headline;
	}

	/**
	 * The user's industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * A URL to the user's public profile. The content shown at this profile is intended for public display and is determined by the user's privacy settings.
	 * May be null if the user's profile isn't public.
	 */
	public String getPublicProfileUrl() {
		return publicProfileUrl;
	}
	
	/**
	 * A URL to the user's profile picture.
	 */
	public ProfilePicture getProfilePicture() {
		return profilePicture;
	}
	
	/**
	 * The user's summary.
	 */
	public String getSummary() {
		return summary;
	}

	public String getLocalizedLastName() {
		return localizedLastName;
	}

	public String getLocalizedFirstName() {
		return localizedFirstName;
	}
	


}
