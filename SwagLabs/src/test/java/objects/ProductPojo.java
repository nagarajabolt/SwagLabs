package objects;

public class ProductPojo {
	private String productName;
	private double price;
	
	public ProductPojo (String productName, double d)
	{
		this.productName = productName;
		this.price = d;
	}
	
	public String getProductName()
	{
		return productName;
	}
	public double getPrice()
	{
		return price;
	}
	
	
}


