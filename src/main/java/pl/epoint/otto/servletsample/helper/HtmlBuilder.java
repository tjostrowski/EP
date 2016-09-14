package pl.epoint.otto.servletsample.helper;

public class HtmlBuilder {
	private StringBuffer html = new StringBuffer();	
	
	public HtmlBuilder() {
		html.append("<html>");
	}
	
	public HtmlBuilder withHead(String head) {
		if (head != null) {
			html.append("<head>").append(head).append("</head>");
		}
		return this;
	}
	
	public HtmlBuilder withBody(String body) {
		if (body != null) {
			html.append("<body>").append(body).append("</body>");
		}
		return this;		
	}
			
	public HtmlBuilder finish() {
		html.append("</html>");
		return this;
	}
	
	public String build() {
		return html.toString();
	}
}
