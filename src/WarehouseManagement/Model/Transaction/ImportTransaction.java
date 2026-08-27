package WarehouseManagement.Model.Transaction;

import java.time.LocalDate;

import WarehouseManagement.Model.Product.Product;

public class ImportTransaction extends WarehouseTransaction {
    public ImportTransaction(int transactionId, Product product, int quantity, LocalDate date) {
        super(transactionId, product, quantity, date);
    }

    @Override
    public double getTransactionValue() {
        return getQuantity() * getProduct().getPrice();
    }

    @Override
    public String toString() {
        return "[NHẬP KHO] " + super.toString() + String.format(" | Giá trị nhập: %,12.0f VNĐ", getTransactionValue());
    }
}
