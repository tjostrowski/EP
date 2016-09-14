package pl.epoint.otto.servletsample.helper;

public class TableBuilder {
	private StringBuffer tableStr = new StringBuffer();
	
	public TableBuilder() {
		tableStr.append("<table>");		
	}
	
	public TableBuilder addHeader(String... headers) {
		tableStr.append("<tr>");
		for (String header : headers) {
			tableStr.append("<th>").append(header).append("</th>");			
		}
		tableStr.append("</tr>");
		return this;		
	}
	
	public TableBuilder addRow(String... columns) {
		tableStr.append("<tr>");
		for (String col : columns) {
			tableStr.append("<td>").append(col).append("</td>");			
		}
		tableStr.append("</tr>");
		return this;
	}	
	
	public TableBuilder finish() {
		tableStr.append("</table>");
		return this;
	}
	
	public String build() {
		return tableStr.toString();
	}

}
