public class Item {
	
	//properties
	private String symbol;
	private double price;
	private String bid_caller=null;
	private String security_name=null;
	private String market_category=null;
	private String test_issue=null;
	private String financial_status=null;
	private String round_lot_size=null;
	private String other=null;
	
	//getters and setters
	public String getSymbol() {
		return symbol;
	}
	public Double getPrice() {
		return price;
	}
	public String getBid_caller() {
		return bid_caller;
	}
	public String getSecurity_name() {
		return security_name;
	}
	public String getMarket_category() {
		return market_category;
	}
	public String getTest_issue() {
		return test_issue;
	}
	public String getFinancial_status() {
		return financial_status;
	}
	public String getRound_lot_size() {
		return round_lot_size;
	}
	public String getOther() {
		return other;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public void setBid_caller(String bid_caller) {
		this.bid_caller = bid_caller;
	}
	public void setSecurity_name(String security_name) {
		this.security_name = security_name;
	}
	public void setMarket_category(String market_categaory) {
		this.market_category = market_categaory;
	}
	public void setTest_issue(String test_issue) {
		this.test_issue = test_issue;
	}
	public void setFinancial_status(String financial_status) {
		this.financial_status = financial_status;
	}
	public void setRound_lot_size(String round_lot_size) {
		this.round_lot_size = round_lot_size;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	//method to display an object
	public void display() {	
		/*System.out.println("symbol:"+symbol+" security_name:"+security_name+" market_category:"+market_category+" test_issue:"
				+test_issue+" financial_status:"+financial_status+" "+" round_lot_size:"+round_lot_size+" other:"+other);*/
		
		System.out.println("symbol:"+symbol+" price:"+price+ " security_name:"+security_name);
		System.out.println("market category:"+market_category+"test_issue:"+test_issue);
		System.out.println(" financial_status:"+financial_status+"round_lot_size:"+round_lot_size+" other:"+other);		
	}
	
	
	
	
	
	
}
