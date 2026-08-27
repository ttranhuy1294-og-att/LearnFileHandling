package WarehouseManagement.Model.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class CosmeticProduct extends Product {
    private int volumeMl;
    private LocalDate expiryDate;

    public CosmeticProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            int volumeMl, LocalDate expiryDate) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.volumeMl = volumeMl;
        this.expiryDate = expiryDate;
    }

    @Override
    public double getTotalValue() {
        return getPrice() * getQuantity();
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Dung tích: %-4d ml | HSD: %-10s | Loại: Mỹ phẩm", volumeMl, expiryDate);
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(int volumeMl) {
        this.volumeMl = volumeMl;
        setUpdatedAt(LocalDateTime.now());
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
        setUpdatedAt(LocalDateTime.now());
    }
}