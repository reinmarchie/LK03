// Abstract superclass untuk semua produk
public abstract class Product {
    private String productId;
    private String name;
    private double price;
    private int stockQuantity;

    // Default constructor
    public Product() {}
    
    // Parameterized constructor
    public Product(String productId, String name, double price, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    // Getters
    public String getProductId() { return productId; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }

    // Setter
    public void setPrice(double price) { this.price = price; }

    // Abstract method - wajib di-override tiap subclass
    public abstract double calculateDiscount();

    // Menampilkan info produk dasar
    public void getProductInfo() {
        System.out.println("Product ID    : " + productId);
        System.out.println("Name          : " + name);
        System.out.println("Price         : Rp." + price);
        System.out.println("Stock         : " + stockQuantity);
    }

    // Overload updateStock - tanpa alasan
    public void updateStock(int quantity) {
        if (stockQuantity + quantity < 0) {
            System.out.println("Insufficient stock. Cannot process the transaction.");
        } else {
            stockQuantity += quantity;
        }
    }

    // Overload updateStock - dengan alasan
    public void updateStock(int quantity, String reason) {
        if (stockQuantity + quantity < 0) {
            System.out.println("Insufficient stock. Cannot process the transaction.");
        } else {
            stockQuantity += quantity;
            System.out.println("Stock updated. Reason: " + reason);
        }
    }
}