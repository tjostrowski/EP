package pl.epoint.otto.servletsample.helper;

public class FormBuilder {
	private StringBuilder formStr = new StringBuilder();
	
	public FormBuilder(String action, String method) {
		formStr.append("<form");
		if (action != null) {
			formStr.append(" action=\"").append(action).append("\"");
		}
		if (method != null) {
			formStr.append(" method=\"").append(method).append("\"");
		}		
		formStr.append(">");		
	}
	
	public FormBuilder(String action) {
		this(action, null);
	}
	
	public FormBuilder() {
		this(null, null);
	}
	
	public FormBuilder withTextInput(String label, String name, String value) {
		formStr.append(label).append(":<br>")
			.append("<input type=\"text\" name=\"" + name + "\" value=\"" + value + "\"><br>");
		return this;
	}
	
	public FormBuilder withSubmit(String value) {
		formStr.append("<input type=\"submit\" value=\"" + value + "\"><br>");
		return this;
	}
		
	public FormBuilder withCancel(String action, String cancelText) {
		String value = (cancelText != null) ? cancelText : "Cancel";
		formStr.append("<button><a href=\"" + action + "\">" + value + "</a></button>");
//		formStr.append("<input type=\"button\" value=\"" + value + "\"><br>");
		return this;		
	}
	
	public FormBuilder finish() {
		formStr.append("</form>");
		return this;
	}
	
	public String build() {
		return formStr.toString();
	}

}
