package pl.epoint.otto.servletsample;

import pl.epoint.otto.servletsample.helper.Link;

public class SiteAppenders {

	public static String renderLogout() {
		return "<br>" + Link.of("?action=logout", "Logout");
	}
}
