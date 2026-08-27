package WarehouseManagement.Model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ClothingProduct extends Product {
    private String size;

    public ClothingProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            String size) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.size = size;
    }

    @Override
    public double getTotalValue() {
        double baseValue = getPrice() * getQuantity();
        if (getQuantity() >= 10) {
            return baseValue * 0.95;
        }
        return baseValue;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Size: %-5s | Loại: Thời trang", size);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
        setUpdatedAt(LocalDateTime.now());
    }
}