package WarehouseManagement.Model.Product;

import java.time.LocalDateTime;
import java.util.List;

public class HouseholdProduct extends Product {
    private String material;
    private String dimensions;

    public HouseholdProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            String material, String dimensions) {
        super(id, name, price, quantity, thumbnailImage, images);
        this.material = material;
        this.dimensions = dimensions;
    }

    @Override
    public double getTotalValue() {
        // Hàng gia dụng cồng kềnh cộng thêm phụ phí lưu kho 50.000 VNĐ / sản phẩm
        return (getPrice() * getQuantity()) + (getQuantity() * 50_000.0);
    }

    @Override
    public String toString() {
        return super.toString()
                + String.format(" | Chất liệu: %-10s | Kích thước: %-10s | Loại: Gia dụng", material, dimensions);
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
        setUpdatedAt(LocalDateTime.now());
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
        setUpdatedAt(LocalDateTime.now());
    }
}
