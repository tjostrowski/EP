package pl.epoint.otto.servletsample.helper;

public class Link {
	
	public static String of(String url, String name) {
		StringBuffer link = new StringBuffer();
		link.append("<a href=\"").append(url).append("\">")
			.append(name).append("</a>");
		return link.toString();
	}
	
}
