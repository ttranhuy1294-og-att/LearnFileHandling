package WarehouseManagement.Model.Product.BulkyProduct;

import java.time.LocalDateTime;
import java.util.List;

public class IndustrialMachineProduct extends BulkyProduct {
    private int powerKw; // Công suất (kW)

    /**
     * Khởi tạo đối tượng IndustrialMachineProduct với đầy đủ thông tin chung và
     * công suất riêng.
     */
    public IndustrialMachineProduct(int id, String name, double price, int quantity, String thumbnailImage,
            List<String> images, double weightKg, double volumeCubicMeters, int powerKw) {
        super(id, name, price, quantity, thumbnailImage, images, weightKg, volumeCubicMeters);
        this.powerKw = powerKw;
    }

    /**
     * Tính tổng giá trị của máy móc công nghiệp bao gồm giá gốc, phí lưu kho đặc
     * biệt và phí cẩu hạ thiết bị.
     */
    @Override
    public double getTotalValue() {
        return (getPrice() * getQuantity()) + calculateSpecialStorageFee() + calculateLiftingCraneSurcharge();
    }

    /**
     * Tính phí lưu kho đặc biệt dành riêng cho máy móc (kho điện cao thế / chịu lực
     * tốt).
     */
    @Override
    public double calculateSpecialStorageFee() {
        return getVolumeCubicMeters() * 800_000.0;
    }

    /**
     * Cung cấp quy định an toàn vận hành riêng cho máy móc công nghiệp.
     */
    @Override
    public String getSafetyRegulations() {
        return String.format(
                "⚡ [AN TOÀN ĐIỆN] Quy định: Yêu cầu thợ điện có chứng chỉ vận hành máy [%s] (%d kW), trang bị đồ bảo hộ cách điện.",
                getName(), powerKw);
    }

    // --- 2 PHƯƠNG THỨC HÀNH ĐỘNG RIÊNG (DÙNG PRINTF) ---
    /**
     * Thực hiện quy trình hiệu chuẩn điện áp cho máy móc công nghiệp.
     */
    public void runVoltageCalibration() {
        System.out.printf("⚡ [HIỆU CHUẨN] Đang thực hiện hiệu chuẩn điện áp cho máy móc công nghiệp: %s%n", getName());
    }

    /**
     * Kiểm tra tải trọng chịu lực thực tế với mức thử nghiệm đầu vào.
     * 
     * @param loadTestKg Mức tải trọng thử nghiệm tính bằng kg
     */
    public void testLoadCapacity(double loadTestKg) {
        if (loadTestKg <= 0) {
            System.err.println("⚠️ [CẢNH BÁO] Mức tải trọng kiểm tra không hợp lệ!");
            return;
        }
        System.out.printf("🏋️ [KIỂM TRA TẢI] Đang kiểm tra chịu lực với mức thử nghiệm %.1f kg cho máy: %s%n",
                loadTestKg, getName());
    }

    // --- 2 PHƯƠNG THỨC TÍNH TOÁN RIÊNG ---
    /**
     * 1. Tính chi phí điện năng vận hành thử nghiệm hàng tháng (VNĐ).
     * 
     * @param hoursPerDay Số giờ vận hành mỗi ngày
     * @return Tổng chi phí điện năng ước tính
     */
    public double calculateMonthlyPowerConsumptionCost(int hoursPerDay) {
        if (hoursPerDay < 0 || hoursPerDay > 24) {
            System.err.println("⚠️ [CẢNH BÁO] Số giờ vận hành trong ngày không hợp lệ. Mặc định tính cho 8 giờ/ngày.");
            hoursPerDay = 8;
        }
        double electricityRatePerKwh = 3_000.0;
        return powerKw * hoursPerDay * 30 * electricityRatePerKwh * getQuantity();
    }

    /**
     * 2. Tính phí dịch vụ cẩu lắp thiết bị nặng dựa trên sự kết hợp giữa trọng
     * lượng chung và công suất riêng.
     * 
     * @return Phí cẩu hạ thiết bị tính bằng VNĐ
     */
    public double calculateLiftingCraneSurcharge() {
        return (getWeightKg() * 1_000.0) + (powerKw * 50_000.0);
    }

    @Override
    public String toString() {
        return "[MÁY CÔNG NGHIỆP] " + super.toString() + String.format(" | Công suất: %-3d kW", powerKw);
    }

    public int getPowerKw() {
        return powerKw;
    }

    public void setPowerKw(int powerKw) {
        this.powerKw = powerKw;
        setUpdatedAt(LocalDateTime.now());
    }
}