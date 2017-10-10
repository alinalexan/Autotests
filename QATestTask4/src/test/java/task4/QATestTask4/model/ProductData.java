package task4.QATestTask4.model;

import java.util.Random;

/**
 * Hold Product information that is used among tests.
 */

public class ProductData {

	enum Dress{Sweater,Jacket,Shirt,Skirt,Stockings,Laces,Pants};
	
	private String name;
    private int qty;
    private String price;

    public ProductData(String name, int qty, String price) {
        this.name = name;
        this.qty = qty;
        this.price = price;
    }
    
    public ProductData() {
		
	}

	public ProductData generate() {
        return new ProductData(
        		generateName(),generateQuantity(),generatePrice() 
        );
    }
    
    public String generateName(){
    	Dress item = Dress.values()[(int) new Random().nextInt(Dress.values().length)];
		return item.toString();
    }
    
    public int generateQuantity(){
    	Integer quantity = (int) (Math.random()*10);
    	if (quantity == 0) ++quantity;
    	return quantity;
    }

    public String generatePrice(){
    	float rawPrice = (float) (Math.random()*100);
    	if (rawPrice < 0.1) rawPrice += 0.1;
    	String price = String.format("%.2f",rawPrice );
    	return price; 
    }

    public String getName() {
        return name;
    }

    public Integer getQty() {
        return qty;
    }

    public String getPrice() {
    	return price;
    }
}
