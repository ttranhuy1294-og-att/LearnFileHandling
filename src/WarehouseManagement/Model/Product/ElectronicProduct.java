package WarehouseManagement.Model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class ElectronicProduct extends Product {
    private int warrantyMonths;

    public ElectronicProduct(int id, String name, double price, int quantity, String thumbnailImage,
            List<String> images, int warrantyMonths) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public double getTotalValue() {
        return (getPrice() * getQuantity()) + (getQuantity() * 100_000.0);
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" | Bảo hành: %-2d tháng | Loại: Điện tử", warrantyMonths);
    }

    public int getWarrantyMonths() {
        return warrantyMonths;
    }

    public void setWarrantyMonths(int warrantyMonths) {
        this.warrantyMonths = warrantyMonths;
        setUpdatedAt(LocalDateTime.now());
    }
}