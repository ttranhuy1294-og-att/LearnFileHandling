package WarehouseManagement.Model.Transaction;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import WarehouseManagement.Model.Product.Product;

public abstract class WarehouseTransaction {
    private int transactionId;
    private Product product;
    private int quantity;
    private LocalDate date;
    private LocalDateTime createdAt;

    public WarehouseTransaction(int transactionId, Product product, int quantity, LocalDate date) {
        this.transactionId = transactionId;
        this.product = product;
        this.quantity = quantity;
        this.date = date;
        this.createdAt = LocalDateTime.now();
    }

    public abstract double getTransactionValue();

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String productName = (product != null) ? product.getName() : "Sản phẩm không tồn tại";
        return String.format(
                "Mã GD: %-3d | Sản phẩm: %-20s | Số lượng: %-5d | Ngày giao dịch: %-10s | Thời điểm tạo: %-19s",
                transactionId, productName, quantity, date, createdAt.format(formatter));
    }

    // Getters
    public int getTransactionId() {
        return transactionId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}