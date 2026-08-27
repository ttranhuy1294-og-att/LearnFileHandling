package WarehouseManagement.Model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class BookProduct extends Product {
    private String author;
    private String publisher;

    public BookProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images, String author, String publisher) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.author = author;
        this.publisher = publisher;
    }

    @Override
    public double getTotalValue() {
        double baseValue = getPrice() * getQuantity();
        return (getQuantity() >= 20) ? baseValue * 0.90 : baseValue; // Chiết khấu 10% nếu số lượng >= 20
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Tác giả: %-15s | NXB: %-10s | Loại: Sách", author, publisher);
    }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; setUpdatedAt(LocalDateTime.now()); }

    public String getPublisher() { return publisher; }
    public void setPublisher(String publisher) { this.publisher = publisher; setUpdatedAt(LocalDateTime.now()); }
}