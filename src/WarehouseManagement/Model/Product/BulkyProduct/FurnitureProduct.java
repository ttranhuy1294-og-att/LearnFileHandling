package WarehouseManagement.Model.Product.BulkyProduct;

import java.time.LocalDateTime;
import java.util.List;

public class FurnitureProduct extends BulkyProduct {
    private String woodMaterial; // Chất liệu gỗ / vật liệu chính
    private boolean isAssembled; // Đã lắp ráp sẵn hay hàng rời
    private int warrantyYears; // Số năm bảo hành

    /**
     * Khởi tạo đối tượng FurnitureProduct với thông số thể tích, trọng lượng chung
     * và thuộc tính nội thất chuyên biệt.
     */
    public FurnitureProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            double weightKg, double volumeCubicMeters, String woodMaterial, boolean isAssembled, int warrantyYears) {
        super(id, name, price, quantity, thumbnailImage, images, weightKg, volumeCubicMeters);
        this.woodMaterial = woodMaterial;
        this.isAssembled = isAssembled;
        this.warrantyYears = warrantyYears;
    }

    /**
     * Tính tổng giá trị của nội thất bao gồm giá gốc, phí lưu kho và phí xử lý sấy
     * chống ẩm đặc biệt.
     */
    @Override
    public double getTotalValue() {
        return (getPrice() * getQuantity()) + calculateSpecialStorageFee() + calculateSpecialMoistureProtectionFee();
    }

    /**
     * Tính phí lưu kho đặc biệt dành riêng cho nội thất (kho bảo quản chống ẩm
     * mốc).
     */
    @Override
    public double calculateSpecialStorageFee() {
        return getQuantity() * 300_000.0;
    }

    /**
     * Cung cấp quy định bảo quản an toàn chống mối mọt, cong vênh riêng cho nội
     * thất.
     */
    @Override
    public String getSafetyRegulations() {
        return String.format(
                "🪑 [BẢO QUẢN] Quy định: Kho chứa nội thất [%s] chất liệu [%s] phải khô ráo, độ ẩm dưới 65%% để tránh mối mọt và cong vênh.",
                getName(), woodMaterial);
    }

    // --- 3 PHƯƠNG THỨC HÀNH ĐỘNG RIÊNG (DÙNG PRINTF) ---
    /**
     * Phun hóa chất chuyên dụng xử lý chống mối mọt cho sản phẩm nội thất gỗ.
     */
    public void applyAntiTermiteTreatment() {
        System.out.printf("🐜 [CHỐNG MỐI MỌT] Đang phun thuốc xử lý cho sản phẩm nội thất: %s%n", getName());
    }

    /**
     * Đánh bóng và bảo dưỡng lớp sơn phủ bề mặt cho sản phẩm nội thất.
     */
    public void polishSurfaceFinish() {
        System.out.printf("✨ [ĐÁNH BÓNG] Đang bảo dưỡng bề mặt sơn cho nội thất: %s%n", getName());
    }

    /**
     * Tháo rời các linh kiện bộ phận để chuẩn bị vận chuyển an toàn qua đường dài.
     */
    public void disassembleForTransport() {
        System.out.printf("🛠️ [THÁO RỜI] Đang tháo rời các bộ phận để chuẩn bị vận chuyển an toàn cho: %s%n",
                getName());
    }

    // --- 2 PHƯƠNG THỨC TÍNH TOÁN RIÊNG ---
    /**
     * 1. Tính quỹ dự phòng bảo hành mở rộng cho sản phẩm nội thất (VNĐ).
     * 
     * @return Chi phí dự phòng bảo hành
     */
    public double calculateExtendedWarrantyCost() {
        if (warrantyYears <= 0)
            return 0.0;
        double annualRate = 0.03;
        return getPrice() * warrantyYears * annualRate * getQuantity();
    }

    /**
     * 2. Tính phí xử lý sấy chống ẩm đặc biệt dựa trên sự kết hợp giữa thể tích
     * cồng kềnh và loại chất liệu gỗ.
     * 
     * @return Phí xử lý chống ẩm tính bằng VNĐ
     */
    public double calculateSpecialMoistureProtectionFee() {
        String materialCheck = (woodMaterial != null) ? woodMaterial.toLowerCase() : "";
        double materialFactor = (materialCheck.contains("óc chó") || materialCheck.contains("sồi")) ? 1.8 : 1.0;
        double ratePerCubicMeter = 100_000.0;
        return getVolumeCubicMeters() * ratePerCubicMeter * materialFactor * getQuantity();
    }

    @Override
    public String toString() {
        return "[NỘI THẤT] " + super.toString() + String.format(" | Chất liệu: %-10s | Đã lắp ráp: %s | BH: %d năm",
                woodMaterial, isAssembled, warrantyYears);
    }

    public String getWoodMaterial() {
        return woodMaterial;
    }

    public void setWoodMaterial(String woodMaterial) {
        this.woodMaterial = woodMaterial;
        setUpdatedAt(LocalDateTime.now());
    }

    public boolean isAssembled() {
        return isAssembled;
    }

    public void setAssembled(boolean assembled) {
        this.isAssembled = assembled;
        setUpdatedAt(LocalDateTime.now());
    }

    public int getWarrantyYears() {
        return warrantyYears;
    }

    public void setWarrantyYears(int warrantyYears) {
        this.warrantyYears = warrantyYears;
        setUpdatedAt(LocalDateTime.now());
    }
}