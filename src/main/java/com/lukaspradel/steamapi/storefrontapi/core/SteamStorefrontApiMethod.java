package com.lukaspradel.steamapi.storefrontapi.core;

import com.lukaspradel.steamapi.data.json.appdetails.Appdetails;
import com.lukaspradel.steamapi.data.json.appuserdetails.AppUserdetails;
import com.lukaspradel.steamapi.data.json.featured.Featured;
import com.lukaspradel.steamapi.data.json.featuredcategories.FeaturedCategories;
import com.lukaspradel.steamapi.data.json.packagedetails.Packagedetails;

public enum SteamStorefrontApiMethod {

	FEATURED("featured", Featured.class), FEATURED_CATEGORIES(
			"featuredcategories", FeaturedCategories.class), APP_DETAILS(
			"appdetails", Appdetails.class), APP_USER_DETAILS("appuserdetails",
			AppUserdetails.class), MESSAGES("messages", Object.class), PACKAGE_DETAILS(
			"packagedetails", Packagedetails.class), SALE_PAGE("salepage",
			Object.class);

	private final String method;

	private final Class<?> responseType;

	private SteamStorefrontApiMethod(String method, Class<?> responseType) {

		this.method = method;
		this.responseType = responseType;
	}

	public Class<?> getReponseType() {
		return responseType;
	}

	@Override
	public String toString() {
		return method;
	}
}
