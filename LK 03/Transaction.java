// Class untuk mengelola satu transaksi penjualan
public class Transaction {
    private String transactionId;
    private Product[] items;
    private int totalItems;

    public Transaction() {}

    public Transaction(String transactionId) {
        this.transactionId = transactionId;
        this.items = new Product[100]; // maksimal 100 item per transaksi
        this.totalItems = 0;
    }

    public String getTransactionId() { return transactionId; }
    public Product[] getItems() { return items; }
    public int getTotalItems() { return totalItems; }

    // Overload addItem - tambah 1 produk
    public void addItem(Product product) {
        items[totalItems] = product;
        totalItems++;
    }

    // Overload addItem - tambah produk sejumlah quantity
    public void addItem(Product product, int quantity) {
        for (int i = 0; i < quantity; i++) {
            items[totalItems] = product;
            totalItems++;
        }
    }

    // Hitung total harga setelah diskon dan kurangi stok
    public double processSale() {
        double total = 0;
        for (int i = 0; i < totalItems; i++) {
            Product item = items[i];
            double priceAfterDiscount = item.getPrice() - item.calculateDiscount();
            total += priceAfterDiscount;
            item.updateStock(-1);
        }
        return total;
    }
}