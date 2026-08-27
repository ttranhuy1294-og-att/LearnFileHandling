package WarehouseManagement.Model.Product.BulkyProduct;

import java.time.LocalDateTime;
import java.util.List;

public class ContainerProduct extends BulkyProduct {
    private String containerType; // Loại container (Lạnh, Khô, Hở mái)
    private double maxPayloadTons; // Tải trọng tối đa (Tấn)
    private boolean isRefrigerated; // Có làm lạnh hay không

    /**
     * Khởi tạo đối tượng ContainerProduct với thông số thể tích, trọng lượng chung
     * và các thuộc tính chứa chuyên biệt.
     */
    public ContainerProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            double weightKg, double volumeCubicMeters, String containerType, double maxPayloadTons,
            boolean isRefrigerated) {
        super(id, name, price, quantity, thumbnailImage, images, weightKg, volumeCubicMeters);
        this.containerType = containerType;
        this.maxPayloadTons = maxPayloadTons;
        this.isRefrigerated = isRefrigerated;
    }

    /**
     * Tính tổng giá trị của container bao gồm giá gốc và phí lưu kho đặc biệt.
     */
    @Override
    public double getTotalValue() {
        return (getPrice() * getQuantity()) + calculateSpecialStorageFee();
    }

    /**
     * Tính phí lưu kho đặc biệt: Container lạnh sẽ chịu chi phí bảo quản điện lạnh
     * cao hơn container khô.
     */
    @Override
    public double calculateSpecialStorageFee() {
        return getQuantity() * (isRefrigerated ? 1_500_000.0 : 600_000.0);
    }

    /**
     * Cung cấp quy định an toàn cẩu hàng và xếp dỡ riêng cho container.
     */
    @Override
    public String getSafetyRegulations() {
        return String.format("🏗️ [CẨU HÀNG] Quy định: Kiểm tra chốt khóa an toàn cho container [%s], tuyệt đối không vượt quá tải trọng %.1f tấn.", getName(), maxPayloadTons);
    }

    // --- 3 PHƯƠNG THỨC HÀNH ĐỘNG RIÊNG (DÙNG PRINTF) ---
    /**
     * Kiểm tra tình trạng nhiệt độ buồng lạnh của container (nếu là loại lạnh).
     */
    public void checkCoolingTemperature() {
        System.out.printf("❄️ [KIỂM TRA LẠNH] Kiểm tra nhiệt độ buồng lạnh cho container: %s (Trạng thái lạnh: %b)%n",
                getName(), isRefrigerated);
    }

    /**
     * Thực hiện niêm phong kẹp chì khóa an toàn cho container trước khi xuất bến.
     */
    public void sealContainerLock() {
        System.out.printf("🔒 [NIÊM PHONG] Đang niêm phong kẹp chì khóa an toàn cho container: %s%n", getName());
    }

    /**
     * Kiểm tra tình trạng chống thấm nước của lớp vỏ container.
     */
    public void inspectWaterproofStatus() {
        System.out.printf("💧 [CHỐNG THẤM] Đang kiểm tra tình trạng chống thấm nước của vỏ container: %s%n", getName());
    }

    // --- 2 PHƯƠNG THỨC TÍNH TOÁN RIÊNG ---
    /**
     * 1. Tính giá trị bảo hiểm hàng hóa tối đa chứa trong container (VNĐ).
     * 
     * @return Giá trị bảo hiểm ước tính
     */
    public double calculateMaxCargoInsuranceValue() {
        double baseValuePerKg = 250_000.0;
        double refrigerationMultiplier = isRefrigerated ? 1.4 : 1.0;
        return (maxPayloadTons * 1000.0) * baseValuePerKg * refrigerationMultiplier * getQuantity();
    }

    /**
     * 2. Tính chi phí điện năng tiêu thụ chạy máy lạnh của container trong X ngày
     * lưu kho.
     * 
     * @param storageDays Số ngày lưu kho thực tế
     * @return Chi phí điện năng làm lạnh
     */
    public double estimateRefrigerationEnergyCost(int storageDays) {
        if (!isRefrigerated || storageDays <= 0) {
            return 0.0;
        }
        double dailyPowerKwh = getVolumeCubicMeters() * 15.0;
        double electricityRate = 2_800.0;
        return dailyPowerKwh * storageDays * electricityRate * getQuantity();
    }

    @Override
    public String toString() {
        return "[CONTAINER] " + super.toString() + String.format(" | Loại: %-8s | Tải trọng: %.1f tấn | Lạnh: %s",
                containerType, maxPayloadTons, isRefrigerated);
    }

    public String getContainerType() {
        return containerType;
    }

    public void setContainerType(String containerType) {
        this.containerType = containerType;
        setUpdatedAt(LocalDateTime.now());
    }

    public double getMaxPayloadTons() {
        return maxPayloadTons;
    }

    public void setMaxPayloadTons(double maxPayloadTons) {
        this.maxPayloadTons = maxPayloadTons;
        setUpdatedAt(LocalDateTime.now());
    }

    public boolean isRefrigerated() {
        return isRefrigerated;
    }

    public void setRefrigerated(boolean refrigerated) {
        isRefrigerated = refrigerated;
        setUpdatedAt(LocalDateTime.now());
    }
}