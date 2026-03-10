public class ElectronicProduct extends Product {
    private String warrantyPeriod;

    public ElectronicProduct() {}

    public ElectronicProduct(String productId, String name, double price, int stockQuantity, String warrantyPeriod) {
        super(productId, name, price, stockQuantity);
        this.warrantyPeriod = warrantyPeriod;
    }

    public String getWarrantyPeriod() { return warrantyPeriod; }

    // Diskon 5%, bonus 2% jika harga asli > 500.000
    @Override
    public double calculateDiscount() {
        double discount = getPrice() * 0.05;
        if (getPrice() > 500000) {
            discount += getPrice() * 0.02;
        }
        return discount;
    }

    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.println("Warranty      : " + warrantyPeriod);
    }
}