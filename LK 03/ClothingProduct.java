public class ClothingProduct extends Product {
    private String size;
    private String brand;

    public ClothingProduct() {}

    public ClothingProduct(String productId, String name, double price, int stockQuantity, String size, String brand) {
        super(productId, name, price, stockQuantity);
        this.size = size;
        this.brand = brand;
    }

    public String getSize() { return size; }
    public String getBrand() { return brand; }

    // Diskon 15% untuk ukuran L atau XL
    @Override
    public double calculateDiscount() {
        if (size.equalsIgnoreCase("L") || size.equalsIgnoreCase("XL")) {
            return getPrice() * 0.15;
        }
        return 0;
    }

    @Override
    public void getProductInfo() {
        super.getProductInfo();
        System.out.println("Size          : " + size);
        System.out.println("Brand         : " + brand);
    }
}