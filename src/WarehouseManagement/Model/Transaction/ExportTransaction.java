package WarehouseManagement.Model.Transaction;

import java.time.LocalDate;

import WarehouseManagement.Model.Product.Product;

public class ExportTransaction extends WarehouseTransaction {
    public ExportTransaction(int transactionId, Product product, int quantity, LocalDate date) {
        super(transactionId, product, quantity, date);
    }

    @Override
    public double getTransactionValue() {
        return getQuantity() * getProduct().getPrice() * 1.10;
    }

    @Override
    public String toString() {
        return "[XUẤT KHO] " + super.toString() + String.format(" | Giá trị xuất: %,12.0f VNĐ", getTransactionValue());
    }
}