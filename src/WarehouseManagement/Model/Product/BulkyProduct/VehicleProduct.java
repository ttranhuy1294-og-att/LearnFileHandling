package WarehouseManagement.Model.Product.BulkyProduct;

import java.time.LocalDateTime;
import java.util.List;

public class VehicleProduct extends BulkyProduct {
    private String engineCapacity; // Ví dụ: "150cc", "2.0L"

    /**
     * Khởi tạo đối tượng VehicleProduct với đầy đủ thông tin chung và riêng.
     */
    public VehicleProduct(int id, String name, double price, int quantity, String thumbnailImage, List<String> images,
            double weightKg, double volumeCubicMeters, String engineCapacity) {
        super(id, name, price, quantity, thumbnailImage, images, weightKg, volumeCubicMeters);
        this.engineCapacity = engineCapacity;
    }

    /**
     * Tính tổng giá trị của phương tiện bao gồm giá gốc, phí lưu kho đặc biệt và
     * phí đăng kiểm.
     */
    @Override
    public double getTotalValue() {
        return (getPrice() * getQuantity()) + calculateSpecialStorageFee() + calculateRegistrationFee();
    }

    /**
     * Tính phí lưu kho đặc biệt dành riêng cho phương tiện (bãi giữ xe rộng).
     */
    @Override
    public double calculateSpecialStorageFee() {
        return getQuantity() * 500_000.0;
    }

    /**
     * Cung cấp quy định an toàn phòng cháy chữa cháy riêng cho phương tiện.
     */
    @Override
    public String getSafetyRegulations() {
        return String.format(
                "🔥 [PCCC] Quy định: Rút hết nhiên liệu của xe [%s] trước khi đưa vào kho, cấm lửa tuyệt đối.",
                getName());
    }

    // --- 2 PHƯƠNG THỨC HÀNH ĐỘNG RIÊNG (DÙNG PRINTF) ---
    /**
     * Lên lịch bảo dưỡng định kỳ cho phương tiện dựa trên số tháng.
     * 
     * @param monthsInterval Chu kỳ tháng bảo dưỡng
     */
    public void scheduleMaintenanceCheck(int monthsInterval) {
        System.out.printf("🔧 [BẢO DƯỠNG] Lên lịch định kỳ mỗi %d tháng cho phương tiện: %s%n", monthsInterval,
                getName());
    }

    /**
     * Tiến hành đăng ký kiểm định số khung số máy cho xe.
     */
    public void registerVehicleLicense() {
        System.out.printf("📝 [ĐĂNG KIỂM] Đang tiến hành làm thủ tục đăng ký kiểm định số khung/số máy cho xe: %s%n",
                getName());
    }

    // --- 2 PHƯƠNG THỨC TÍNH TOÁN RIÊNG ---
    /**
     * 1. Tính phí đăng kiểm/trước bạ dựa trên giá sản phẩm và dung tích xi-lanh
     * trích xuất.
     * 
     * @return Tổng chi phí đăng kiểm tính bằng VNĐ
     */
    public double calculateRegistrationFee() {
        double ccMultiplier = parseEngineCc() > 1000 ? 0.10 : 0.05;
        return getPrice() * ccMultiplier * getQuantity();
    }

    /**
     * 2. Tính chi phí nhiên liệu dự kiến cho một quãng đường vận chuyển (đơn vị:
     * VNĐ).
     * Kết hợp giữa trọng lượng chung (kg) và dung tích động cơ quy đổi.
     * 
     * @param distanceKm Quãng đường vận chuyển tính bằng km
     * @return Chi phí nhiên liệu ước tính
     */
    public double estimateTransportFuelCost(double distanceKm) {
        double baseRatePerKg = 150.0;
        double engineFactor = parseEngineCc() > 1000 ? 1.5 : 1.0;
        return getWeightKg() * baseRatePerKg * distanceKm * engineFactor;
    }

    /**
     * Hàm phụ trợ: Trích xuất và quy đổi chuỗi dung tích động cơ (engineCapacity)
     * sang định dạng chuẩn số cc.
     * Hỗ trợ đọc các định dạng như: "150cc", "150 CC", "2.0L", "2.5 l".
     * 
     * @return Giá trị dung tích động cơ tính bằng cc (cubic centimeters)
     */
    private double parseEngineCc() {
        if (engineCapacity == null || engineCapacity.trim().isEmpty()) {
            return 150.0;
        }

        try {
            String clean = engineCapacity.toLowerCase().trim();

            if (clean.contains("l")) {
                String numericPart = clean.replace("l", "").trim();
                return Double.parseDouble(numericPart) * 1000.0;
            } else if (clean.contains("cc")) {
                String numericPart = clean.replace("cc", "").trim();
                return Double.parseDouble(numericPart);
            } else {
                return Double.parseDouble(clean);
            }
        } catch (NumberFormatException e) {
            System.err.printf(
                    "⚠️ [CẢNH BÁO] Không thể phân tích dung tích động cơ từ chuỗi '%s'. Sử dụng mặc định 150cc.%n",
                    engineCapacity);
            return 150.0;
        }
    }

    @Override
    public String toString() {
        return "[XE/PHƯƠNG TIỆN] " + super.toString() + String.format(" | Động cơ: %-8s", engineCapacity);
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
        setUpdatedAt(LocalDateTime.now());
    }
}