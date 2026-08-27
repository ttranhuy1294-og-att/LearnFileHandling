package WarehouseManagement.Model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FoodProduct extends Product {
    private LocalDate expiryDate;

    public FoodProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            LocalDate expiryDate) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.expiryDate = expiryDate;
    }

    @Override
    public double getTotalValue() {
        return getPrice() * getQuantity();
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | HSD: %-10s | Loại: Thực phẩm", expiryDate);
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        setUpdatedAt(LocalDateTime.now());
    }
}