public class ShopManagementSystem {
    public static void main(String[] args) {

        Product[] catalog = new Product[6];

        catalog[0] = new ClothingProduct("C001", "T-Shirt",   200000, 50, "M",  "Uniqlo");
        catalog[1] = new ClothingProduct("C002", "Hoodie",    300000, 30, "XL", "Zara");
        catalog[2] = new ElectronicProduct("E001", "Smartphone", 600000,  20, "2 years");
        catalog[3] = new ElectronicProduct("E002", "Laptop",    1500000, 10, "3 years");
        catalog[4] = new FoodProduct("F001", "Chocolate", 50000, 10, "2026-12-31");
        catalog[5] = new FoodProduct("F002", "Chips",     30000, 15, "2026-11-30");

       // TAMPILKAN INFO & DISKON SEMUA PRODUK (polymorphism)
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║            PRODUCT CATALOG           ║");
        System.out.println("╚══════════════════════════════════════╝");
        for (Product item : catalog) {
            item.getProductInfo();
            System.out.println("Discount      : Rp." + item.calculateDiscount());
            System.out.println("Final Price   : Rp." + (item.getPrice() - item.calculateDiscount()));
            System.out.println("--------------------------------------");
        }

        // TRANSAKSI 1
        Transaction trans1 = new Transaction("T001");
        trans1.addItem(catalog[5], 3); // Chips x3
        trans1.addItem(catalog[2], 4);    // Smartphone x1
        trans1.addItem(catalog[0]);    // T-Shirt x1

        double totalTrans1 = trans1.processSale();
        printReceipt(trans1, totalTrans1);

        // TRANSAKSI 2
        Transaction trans2 = new Transaction("T002");
        trans2.addItem(catalog[1], 2); // Hoodie x2
        trans2.addItem(catalog[3]);    // Laptop x1
        trans2.addItem(catalog[4]);    // Chocolate x1

        double totalTrans2 = trans2.processSale();
        printReceipt(trans2, totalTrans2);

        // LAPORAN AKHIR
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           LAPORAN PENJUALAN          ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Total Penjualan T001 : Rp." + totalTrans1);
        System.out.println("Total Penjualan T002 : Rp." + totalTrans2);
        System.out.println("Total Keseluruhan    : Rp." + (totalTrans1 + totalTrans2));
        System.out.println("--------------------------------------");

        // Cari produk terlaris (paling banyak terjual)
        Product terlaris = findBestSeller(trans1, trans2);
        System.out.println("Produk Terlaris      : " + terlaris.getName());
        System.out.println("======================================");
    }

    // Helper - cetak struk transaksi
    static void printReceipt(Transaction trans, double total) {
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║          FILKOM MART RECEIPT         ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("Transaction ID : " + trans.getTransactionId());
        System.out.println("Items Purchased:");
        for (int i = 0; i < trans.getTotalItems(); i++) {
            Product item = trans.getItems()[i];
            double finalPrice = item.getPrice() - item.calculateDiscount();
            System.out.println("  - " + item.getName() + " | Rp." + finalPrice);
        }
        System.out.println("--------------------------------------");
        System.out.println("TOTAL : Rp." + total);
        System.out.println("======================================\n");
    }

    // Helper - cari produk terlaris dari semua transaksi
    static Product findBestSeller(Transaction... transactions) {
        String[] ids = new String[200];
        int[] counts = new int[200];
        int uniqueCount = 0;

        for (Transaction trans : transactions) {
            for (int i = 0; i < trans.getTotalItems(); i++) {
                String pid = trans.getItems()[i].getProductId();
                int idx = -1;
                for (int j = 0; j < uniqueCount; j++) {
                    if (ids[j].equals(pid)) {
                        idx = j;
                        break;
                    }
                }
                if (idx != -1) {
                    counts[idx]++;
                } else {
                    ids[uniqueCount] = pid;
                    counts[uniqueCount] = 1;
                    uniqueCount++;
                }
            }
        }

        // Cari yang terbanyak
        int maxIdx = 0;
        for (int i = 1; i < uniqueCount; i++) {
            if (counts[i] > counts[maxIdx]) maxIdx = i;
        }

        // Return produk yang cocok
        String bestId = ids[maxIdx];
        for (Transaction trans : transactions) {
            for (int i = 0; i < trans.getTotalItems(); i++) {
                if (trans.getItems()[i].getProductId().equals(bestId)) {
                    return trans.getItems()[i];
                }
            }
        }
        return transactions[0].getItems()[0]; // fallback
    }
}