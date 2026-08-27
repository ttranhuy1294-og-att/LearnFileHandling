package WarehouseManagement.Model.Product.BulkyProduct;

import java.time.LocalDateTime;
import java.util.List;

import WarehouseManagement.Model.Product.Product;

public abstract class BulkyProduct extends Product {
    private double weightKg; // Trọng lượng (kg)
    private double volumeCubicMeters; // Thể tích (m3)

    public BulkyProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            double weightKg, double volumeCubicMeters) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.weightKg = weightKg;
        this.volumeCubicMeters = volumeCubicMeters;
    }

    // --- CÁC ABSTRACT METHOD RIÊNG CỦA TẦNG BULKY PRODUCT ---
    /**
     * Bắt buộc các lớp con cồng kềnh phải định nghĩa cách tính phí lưu kho đặc biệt
     */
    public abstract double calculateSpecialStorageFee();

    /**
     * Bắt buộc các lớp con cồng kềnh phải cung cấp quy định an toàn riêng
     */
    public abstract String getSafetyRegulations();

    // Phương thức thông thường hỗ trợ chung cho hàng cồng kềnh
    public double calculateBaseShippingCost() {
        return volumeCubicMeters * 200_000.0;
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Nặng: %-6.1f kg | Thể tích: %-4.1f m3", weightKg, volumeCubicMeters);
    }

    // Getters và Setters
    public double getWeightKg() {
        return weightKg;
    }

    public void setWeightKg(double weightKg) {
        this.weightKg = weightKg;
        setUpdatedAt(LocalDateTime.now());
    }

    public double getVolumeCubicMeters() {
        return volumeCubicMeters;
    }

    public void setVolumeCubicMeters(double volumeCubicMeters) {
        this.volumeCubicMeters = volumeCubicMeters;
        setUpdatedAt(LocalDateTime.now());
    }
}
