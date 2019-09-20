package org.springframework.social.linkedin.api.impl.mapped;

public class FirstName {

	private Localized localized;

	private PreferredLocale preferredLocale;

	public Localized getLocalized() {
		return localized;
	}

	public void setLocalized(Localized localized) {
		this.localized = localized;
	}

	public PreferredLocale getPreferredLocale() {
		return preferredLocale;
	}

	public void setPreferredLocale(PreferredLocale preferredLocale) {
		this.preferredLocale = preferredLocale;
	}
}
