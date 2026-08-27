package WarehouseManagement.Model.Product;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;

    private String thumbnailImage; // Ảnh đại diện chính (Cover/Thumbnail)
    private List<String> images; // Danh sách nhiều hình ảnh chi tiết của sản phẩm

    private LocalDateTime createdAt; // Thời gian tạo
    private LocalDateTime updatedAt; // Thời gian cập nhật gần nhất

    public Product(int id, String name, double price, int quantity, String thumbnailImage, List<String> images) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.thumbnailImage = thumbnailImage;
        this.images = (images != null) ? images : new ArrayList<>();
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public abstract double getTotalValue();

    // --- Các phương thức hỗ trợ quản lý danh sách ảnh ---
    public void addImage(String imageUrl) {
        if (imageUrl != null && !imageUrl.trim().isEmpty()) {
            this.images.add(imageUrl);
            this.updatedAt = LocalDateTime.now();
        }
    }

    public void removeImage(String imageUrl) {
        this.images.remove(imageUrl);
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format(
                "ID: %-3d | Tên: %-20s | Giá: %,12.0f VNĐ | Tồn: %-5d | Thumbnail: %-25s | Ảnh chi tiết: %-15s | Tạo lúc: %-19s | Sửa lúc: %-19s",
                id, name, price, quantity,
                (thumbnailImage != null ? thumbnailImage : "Không có"),
                (images != null ? images.size() + " ảnh" : "0 ảnh"),
                createdAt.format(formatter),
                updatedAt.format(formatter));
    }

    // --- Getters và Setters ---
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
        this.updatedAt = LocalDateTime.now();
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        this.updatedAt = LocalDateTime.now();
    }

    public String getThumbnailImage() {
        return thumbnailImage;
    }

    public void setThumbnailImage(String thumbnailImage) {
        this.thumbnailImage = thumbnailImage;
        this.updatedAt = LocalDateTime.now();
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
        this.updatedAt = LocalDateTime.now();
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}