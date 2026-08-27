package WarehouseManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import WarehouseManagement.Model.Product.BookProduct;
import WarehouseManagement.Model.Product.ClothingProduct;
import WarehouseManagement.Model.Product.CosmeticProduct;
import WarehouseManagement.Model.Product.ElectronicProduct;
import WarehouseManagement.Model.Product.FoodProduct;
import WarehouseManagement.Model.Product.HouseholdProduct;
import WarehouseManagement.Model.Product.Product;
import WarehouseManagement.Model.Product.BulkyProduct.BulkyProduct;
import WarehouseManagement.Model.Product.BulkyProduct.ContainerProduct;
import WarehouseManagement.Model.Product.BulkyProduct.FurnitureProduct;
import WarehouseManagement.Model.Product.BulkyProduct.IndustrialMachineProduct;
import WarehouseManagement.Model.Product.BulkyProduct.VehicleProduct;
import WarehouseManagement.Model.Transaction.ExportTransaction;
import WarehouseManagement.Model.Transaction.ImportTransaction;
import WarehouseManagement.Model.Transaction.WarehouseTransaction;

public class Main {
    private static List<Product> products = new ArrayList<>();
    private static List<WarehouseTransaction> transactions = new ArrayList<>();

    // Đưa Scanner thành sc và đặt ở phạm vi class để dùng chung toàn bộ các hàm
    private static final Scanner sc = new Scanner(System.in);

    // Đưa colWidths thành hằng số hoặc biến dùng chung cho các hàm in bảng
    private static final int[] COL_WIDTHS = { 5, 35, 20, 15, 8, 25, 18, 19 };

    // Khai báo DateTimeFormatter toàn cục dùng chung cho toàn bộ dự án
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void main(String[] args) {
        initializeDefaultData();

        int choice = 0;

        do {
            printMenu();
            System.out.print("👉 Nhập lựa chọn của bạn: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
                System.out.println();

                switch (choice) {
                    // --- NHÓM 1: QUẢN LÝ SẢN PHẨM CƠ BẢN & TÌM KIẾM ---
                    case 1:
                        displayProductList();
                        break;
                    case 2:
                        addNewProductManual();
                        break;
                    case 3:
                        updateProductManual();
                        break;
                    case 4:
                        deleteProductManual();
                        break;
                    case 5:
                        searchProductById();
                        break;
                    case 6:
                        searchProductByName();
                        break;
                    case 7:
                        importProductsFromFile();
                        break;
                    case 8:
                        batchUpdateProductsFromFile();
                        break;
                    case 9:
                        batchDeleteProductsFromFile();
                        break;
                    case 10:
                        batchDeleteProductsByManualIdInput();
                        break;

                    // --- NHÓM 2: LỌC & HIỂN THỊ CHI TIẾT THEO DANH MỤC ---
                    case 11:
                        displayFoodProducts();
                        break;
                    case 12:
                        displayElectronicProducts();
                        break;
                    case 13:
                        displayClothingProducts();
                        break;
                    case 14:
                        displayBookProducts();
                        break;
                    case 15:
                        displayHouseholdProducts();
                        break;
                    case 16:
                        displayCosmeticProducts();
                        break;
                    case 17:
                        displayVehicleProducts();
                        break;
                    case 18:
                        displayIndustrialMachineProducts();
                        break;
                    case 19:
                        displayContainerProducts();
                        break;
                    case 20:
                        displayFurnitureProducts();
                        break;

                    // --- NHÓM 3: GIAO DỊCH VÀ LỊCH SỬ KHO ---
                    case 21:
                        importStockTransaction();
                        break;
                    case 22:
                        exportStockTransaction();
                        break;
                    case 23:
                        displayTransactionsByDay();
                        break;
                    case 24:
                        displayTransactionsByWeek();
                        break;
                    case 25:
                        displayTransactionsByMonth();
                        break;
                    case 26:
                        filterTransactionsByTimeRange();
                        break;
                    case 27:
                        filterTransactionsByTypeImport();
                        break;
                    case 28:
                        filterTransactionsByTypeExport();
                        break;
                    case 29:
                        sortTransactionsByTime();
                        break;
                    case 30:
                        sortTransactionsByQuantity();
                        break;
                    case 31:
                        filterTransactionsByProductId();
                        break;
                    case 32:
                        searchTransactionsByNote();
                        break;
                    case 33:
                        searchTransactionsByTransactionId();
                        break;
                    case 34:
                        searchTransactionsByMultipleConditions();
                        break;
                    case 35:
                        statisticsTransactionsByDay();
                        break;
                    case 36:
                        statisticsTransactionsByMonth();
                        break;

                    // --- NHÓM 4: THỐNG KÊ & SẮP XẾP SẢN PHẨM ---
                    case 37:
                        findHighestValueProduct();
                        break;
                    case 38:
                        searchKeywordInFiles();
                        break;
                    case 39:
                        sortProductsByPriceAscending();
                        break;
                    case 40:
                        sortProductsByPriceDescending();
                        break;
                    case 41:
                        sortProductsByInventoryQuantity();
                        break;
                    case 42:
                        checkLowStockProducts();
                        break;
                    case 43:
                        applyBatchDiscountByCategory();
                        break;

                    // --- NHÓM 5: ĐỌC/GHI FILE & XUẤT BÁO CÁO CHI TIẾT ---
                    case 44:
                        saveDataToFile();
                        break;
                    case 45:
                        loadDataFromFile();
                        break;
                    case 46:
                        displayInventoryReportConsole();
                        break;
                    case 47:
                        exportSummaryReportToFile();
                        break;
                    case 48:
                        exportFoodReportToFile();
                        break;
                    case 49:
                        exportElectronicReportToFile();
                        break;
                    case 50:
                        exportClothingReportToFile();
                        break;
                    case 51:
                        exportBookReportToFile();
                        break;
                    case 52:
                        exportHouseholdReportToFile();
                        break;
                    case 53:
                        exportCosmeticReportToFile();
                        break;
                    case 54:
                        exportBulkyReportToFile();
                        break;

                    // --- NHÓM 6: XỬ LÝ CHUYÊN BIỆT SẢN PHẨM CỒNG KỀNH ---
                    case 55:
                        checkBulkyProductSafetyAndFees();
                        break;
                    case 56:
                        calculateBulkyProductFees();
                        break;
                    case 57:
                        simulateBulkyProductActions();
                        break;

                    // --- NHÓM 7: BẢO TRÌ, DỮ LIỆU & HỆ THỐNG ---
                    case 58:
                        clearOldTransactionHistory();
                        break;
                    case 59:
                        initializeDefaultData();
                        break;
                    case 60:
                        backupSystemFiles();
                        break;
                    case 61:
                        restoreSystemFiles();
                        break;
                    case 62:
                        System.out.println("👋 Cảm ơn bạn đã sử dụng hệ thống. Hẹn gặp lại!");
                        break;
                    default:
                        System.out.println("❌ Lựa chọn không hợp lệ! Vui lòng chọn lại.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Lỗi: Vui lòng nhập vào một số nguyên hợp lệ!");
            }

        } while (choice != 62);

        sc.close();
    }

    private static void printMenu() {
        int[] menuColWidths = { 6, 68 };
        int menuTableWidth = calculateTableWidth(menuColWidths);
        String menuRowFormat = String.format("| %%-%ds | %%-%ds |\n", menuColWidths[0], menuColWidths[1]);

        printTableBorder(menuTableWidth);
        System.out.printf("| %-" + (menuTableWidth - 3) + "s |\n",
                centerText("WAREHOUSE MANAGEMENT SYSTEM (WMS)", menuTableWidth - 3));
        printTableBorder(menuTableWidth);

        // --- NHÓM 1: QUẢN LÝ SẢN PHẨM CƠ BẢN & TÌM KIẾM ---
        printMenuRow(menuRowFormat, "1", "Hiển thị danh sách sản phẩm (kèm ảnh & thời gian)");
        printMenuRow(menuRowFormat, "2", "Thêm mới sản phẩm thủ công (Có chọn ảnh)");
        printMenuRow(menuRowFormat, "3", "Cập nhật thông tin sản phẩm thủ công");
        printMenuRow(menuRowFormat, "4", "Xóa sản phẩm thủ công (Có Confirmation Prompt)");
        printMenuRow(menuRowFormat, "5", "Tìm kiếm sản phẩm theo ID");
        printMenuRow(menuRowFormat, "6", "Tìm kiếm sản phẩm theo tên");
        printMenuRow(menuRowFormat, "7", "Nhập danh sách sản phẩm từ file (TXT/CSV/XLSX)");
        printMenuRow(menuRowFormat, "8", "Cập nhật/Đồng bộ sản phẩm hàng loạt từ file");
        printMenuRow(menuRowFormat, "9", "Xóa sản phẩm hàng loạt bằng file danh sách ID");
        printMenuRow(menuRowFormat, "10", "Xóa sản phẩm hàng loạt bằng cách nhập tay danh sách ID");
        printTableBorder(menuTableWidth);

        // --- NHÓM 2: LỌC & HIỂN THỊ CHI TIẾT THEO DANH MỤC (Stream API) ---
        printMenuRow(menuRowFormat, "11", "Lọc & hiển thị: Thực phẩm (FoodProduct)");
        printMenuRow(menuRowFormat, "12", "Lọc & hiển thị: Điện tử (ElectronicProduct)");
        printMenuRow(menuRowFormat, "13", "Lọc & hiển thị: Thời trang (ClothingProduct)");
        printMenuRow(menuRowFormat, "14", "Lọc & hiển thị: Sách (BookProduct)");
        printMenuRow(menuRowFormat, "15", "Lọc & hiển thị: Đồ gia dụng (HouseholdProduct)");
        printMenuRow(menuRowFormat, "16", "Lọc & hiển thị: Mỹ phẩm (CosmeticProduct)");
        printMenuRow(menuRowFormat, "17", "Lọc & hiển thị: Phương tiện (VehicleProduct)");
        printMenuRow(menuRowFormat, "18", "Lọc & hiển thị: Máy công nghiệp (IndustrialMachineProduct)");
        printMenuRow(menuRowFormat, "19", "Lọc & hiển thị: Container (ContainerProduct)");
        printMenuRow(menuRowFormat, "20", "Lọc & hiển thị: Nội thất (FurnitureProduct)");
        printTableBorder(menuTableWidth);

        // --- NHÓM 3: GIAO DỊCH VÀ LỊCH SỬ KHO ---
        printMenuRow(menuRowFormat, "21", "Thực hiện giao dịch Nhập kho");
        printMenuRow(menuRowFormat, "22", "Thực hiện giao dịch Xuất kho");
        printMenuRow(menuRowFormat, "23", "Xem lịch sử giao dịch kho theo ngày");
        printMenuRow(menuRowFormat, "24", "Xem lịch sử giao dịch kho theo tuần");
        printMenuRow(menuRowFormat, "25", "Xem lịch sử giao dịch kho theo tháng");
        printMenuRow(menuRowFormat, "26", "Lọc lịch sử giao dịch theo khoảng thời gian");
        printMenuRow(menuRowFormat, "27", "Lọc lịch sử giao dịch theo loại Nhập kho");
        printMenuRow(menuRowFormat, "28", "Lọc lịch sử giao dịch theo loại Xuất kho");
        printMenuRow(menuRowFormat, "29", "Sắp xếp lịch sử giao dịch theo thời gian");
        printMenuRow(menuRowFormat, "30", "Sắp xếp lịch sử giao dịch theo số lượng");
        printMenuRow(menuRowFormat, "31", "Lọc lịch sử giao dịch theo mã sản phẩm");
        printMenuRow(menuRowFormat, "32", "Tìm kiếm lịch sử giao dịch theo ghi chú");
        printMenuRow(menuRowFormat, "33", "Tìm kiếm lịch sử giao dịch theo mã giao dịch");
        printMenuRow(menuRowFormat, "34", "Tìm kiếm lịch sử giao dịch theo nhiều điều kiện");
        printMenuRow(menuRowFormat, "35", "Thống kê số lượng giao dịch theo ngày");
        printMenuRow(menuRowFormat, "36", "Thống kê số lượng giao dịch theo tháng");
        printTableBorder(menuTableWidth);

        // --- NHÓM 4: THỐNG KÊ & SẮP XẾP SẢN PHẨM ---
        printMenuRow(menuRowFormat, "37", "Thống kê sản phẩm giá trị tồn kho cao nhất & Báo cáo danh mục");
        printMenuRow(menuRowFormat, "38", "Tìm kiếm từ khóa trong các file dữ liệu (Liệt kê file nào & dòng mấy)");
        printMenuRow(menuRowFormat, "39", "Sắp xếp danh sách sản phẩm theo giá tăng dần");
        printMenuRow(menuRowFormat, "40", "Sắp xếp danh sách sản phẩm theo giá giảm dần");
        printMenuRow(menuRowFormat, "41", "Sắp xếp danh sách sản phẩm theo số lượng tồn kho");
        printMenuRow(menuRowFormat, "42", "Kiểm tra sản phẩm sắp hết hàng (Low stock alert)");
        printMenuRow(menuRowFormat, "43", "Áp dụng giảm giá hàng loạt theo danh mục (Stream API)");
        printTableBorder(menuTableWidth);

        // --- NHÓM 5: ĐỌC/GHI FILE & XUẤT BÁO CÁO CHI TIẾT ---
        printMenuRow(menuRowFormat, "44", "Lưu dữ liệu sản phẩm & giao dịch vào file (TXT/CSV/Excel)");
        printMenuRow(menuRowFormat, "45", "Đọc dữ liệu sản phẩm & giao dịch từ file");
        printMenuRow(menuRowFormat, "46", "Xuất báo cáo kho hàng ra màn hình Console");
        printMenuRow(menuRowFormat, "47", "Xuất báo cáo tổng hợp ra file văn bản");
        printMenuRow(menuRowFormat, "48", "Xuất báo cáo riêng cho loại: Thực phẩm (Food) ra file");
        printMenuRow(menuRowFormat, "49", "Xuất báo cáo riêng cho loại: Điện tử (Electronic) ra file");
        printMenuRow(menuRowFormat, "50", "Xuất báo cáo riêng cho loại: Thời trang (Clothing) ra file");
        printMenuRow(menuRowFormat, "51", "Xuất báo cáo riêng cho loại: Sách (Book) ra file");
        printMenuRow(menuRowFormat, "52", "Xuất báo cáo riêng cho loại: Đồ gia dụng (Household) ra file");
        printMenuRow(menuRowFormat, "53", "Xuất báo cáo riêng cho loại: Mỹ phẩm (Cosmetic) ra file");
        printMenuRow(menuRowFormat, "54", "Xuất báo cáo riêng cho loại: Sản phẩm cồng kềnh (Bulky) ra file");
        printTableBorder(menuTableWidth);

        // --- NHÓM 6: XỬ LÝ CHUYÊN BIỆT SẢN PHẨM CỒNG KỀNH ---
        printMenuRow(menuRowFormat, "55", "Kiểm tra quy định an toàn chuyên biệt (PCCC, Tải trọng...)");
        printMenuRow(menuRowFormat, "56", "Tính toán phí vận chuyển/đăng kiểm/bảo quản sản phẩm cồng kềnh");
        printMenuRow(menuRowFormat, "57", "Chạy mô phỏng kiểm thử toàn bộ hành động sản phẩm cồng kềnh");
        printTableBorder(menuTableWidth);

        // --- NHÓM 7: BẢO TRÌ, DỮ LIỆU & HỆ THỐNG ---
        printMenuRow(menuRowFormat, "58", "Xóa toàn bộ dữ liệu lịch sử giao dịch cũ");
        printMenuRow(menuRowFormat, "59", "Tải lại dữ liệu mẫu mặc định (Reset Data)");
        printMenuRow(menuRowFormat, "60", "Sao lưu dữ liệu hệ thống (Backup Files)");
        printMenuRow(menuRowFormat, "61", "Khôi phục dữ liệu hệ thống (Restore Files)");
        printMenuRow(menuRowFormat, "62", "Thoát chương trình");

        printTableBorder(menuTableWidth);
    }

    private static void initializeDefaultData() {
        // --- 1. DANH SÁCH SẢN PHẨM (Tổng cộng đúng 55 sản phẩm) ---

        // --- Nhóm 1: Thực phẩm (Food) - 8 sản phẩm ---
        products.add(new FoodProduct(1, "Gạo ST25", 250_000, 100, "images/gao_st25_thumb.png",
                List.of("images/gao_st25_1.png"), LocalDate.of(2026, 12, 31)));
        products.add(new FoodProduct(2, "Sữa Vinamilk", 350_000, 80, "images/sua_vinamilk_thumb.png",
                List.of("images/sua_1.png"), LocalDate.of(2026, 10, 15)));
        products.add(new FoodProduct(3, "Bánh Oreo", 120_000, 150, "images/oreo_thumb.png",
                List.of("images/oreo_1.png"), LocalDate.of(2027, 1, 20)));
        products.add(
                new FoodProduct(4, "Cà phê Trung Nguyên Cà phê Trung Nguyên Cà phê Trung Nguyên Cà phê Trung Nguyên",
                        450_000, 60, "images/coffee_thumb.png",
                        List.of("images/coffee_1.png"), LocalDate.of(2027, 5, 10)));
        products.add(new FoodProduct(5, "Mì gói Hảo Hảo", 110_000, 300, "images/haohao_thumb.png",
                List.of("images/haohao_1.png"), LocalDate.of(2027, 3, 15)));
        products.add(new FoodProduct(6, "Dầu ăn Neptune", 280_000, 90, "images/neptune_thumb.png",
                List.of("images/neptune_1.png"), LocalDate.of(2027, 8, 20)));
        products.add(new FoodProduct(7, "Nước mắm Nam Ngư", 75_000, 120, "images/namngu_thumb.png",
                List.of("images/namngu_1.png"), LocalDate.of(2028, 1, 10)));
        products.add(new FoodProduct(8, "Yến sào Khánh Hòa", 1_500_000, 40, "images/yensao_thumb.png",
                List.of("images/yensao_1.png"), LocalDate.of(2028, 6, 15)));

        // --- Nhóm 2: Điện tử (Electronic) - 8 sản phẩm ---
        products.add(new ElectronicProduct(9, "Laptop Dell", 20_000_000, 20, "images/laptop_thumb.png",
                List.of("images/laptop_front.png"), 24));
        products.add(new ElectronicProduct(10, "iPhone 15", 18_000_000, 30, "images/ip15_thumb.png",
                List.of("images/ip15_black.png"), 12));
        products.add(new ElectronicProduct(11, "Tivi Samsung", 15_000_000, 15, "images/tivi_thumb.png",
                List.of("images/tivi_front.png"), 24));
        products.add(new ElectronicProduct(12, "Tai nghe Sony", 3_000_000, 50, "images/sony_thumb.png",
                List.of("images/sony_1.png"), 12));
        products.add(new ElectronicProduct(13, "Bàn phím cơ Logitech", 1_800_000, 45, "images/kb_thumb.png",
                List.of("images/kb_1.png"), 12));
        products.add(new ElectronicProduct(14, "Chuột không dây", 500_000, 70, "images/mouse_thumb.png",
                List.of("images/mouse_1.png"), 6));
        products.add(new ElectronicProduct(15, "Loa Bluetooth JBL", 2_500_000, 25, "images/jbl_thumb.png",
                List.of("images/jbl_1.png"), 12));
        products.add(new ElectronicProduct(16, "Máy chiếu thông minh", 8_500_000, 10, "images/projector_thumb.png",
                List.of("images/proj_1.png"), 12));

        // --- Nhóm 3: Thời trang (Clothing) - 8 sản phẩm ---
        products.add(new ClothingProduct(17, "Áo sơ mi", 500_000, 40, "images/shirt_thumb.png",
                List.of("images/shirt_front.png"), "L"));
        products.add(new ClothingProduct(18, "Quần jean", 800_000, 35, "images/jeans_thumb.png",
                List.of("images/jeans_1.png"), "32"));
        products.add(new ClothingProduct(19, "Áo khoác gió", 650_000, 55, "images/jacket_thumb.png",
                List.of("images/jacket_1.png"), "XL"));
        products.add(new ClothingProduct(20, "Giày thể thao Nike", 2_200_000, 25, "images/nike_thumb.png",
                List.of("images/nike_1.png"), "41"));
        products.add(new ClothingProduct(21, "Mũ lưỡi trai", 150_000, 80, "images/cap_thumb.png",
                List.of("images/cap_1.png"), "Free"));
        products.add(new ClothingProduct(22, "Áo thun Polo", 350_000, 90, "images/polo_thumb.png",
                List.of("images/polo_1.png"), "M"));
        products.add(new ClothingProduct(23, "Quần đùi thể thao", 200_000, 110, "images/short_thumb.png",
                List.of("images/short_1.png"), "L"));
        products.add(new ClothingProduct(24, "Túi xách da nam", 1_200_000, 20, "images/bag_thumb.png",
                List.of("images/bag_1.png"), "OneSize"));

        // --- Nhóm 4: Sách (BookProduct) - 8 sản phẩm ---
        products.add(new BookProduct(25, "Lập trình Java căn bản", 120_000, 50, "images/java_book_thumb.png",
                List.of("images/java_1.png"), "Nguyễn Văn A", "NXB Hồng Đức"));
        products.add(new BookProduct(26, "Đắc Nhân Tâm", 85_000, 70, "images/dct_thumb.png",
                List.of("images/dct_1.png"), "Dale Carnegie", "NXB Tổng Hợp"));
        products.add(new BookProduct(27, "Nhà Giả Kim", 79_000, 60, "images/alchemist_thumb.png",
                List.of("images/alchemist_1.png"), "Paulo Coelho", "NXB Văn Học"));
        products.add(new BookProduct(28, "Clean Code", 250_000, 30, "images/cleancode_thumb.png",
                List.of("images/cc_1.png"), "Robert C. Martin", "Prentice Hall"));
        products.add(new BookProduct(29, "Tôi thấy hoa vàng trên cỏ xanh", 110_000, 45, "images/hoavang_thumb.png",
                List.of("images/hv_1.png"), "Nguyen Nhat Anh", "NXB Trẻ"));
        products.add(new BookProduct(30, "Cấu trúc dữ liệu và giải thuật", 180_000, 40, "images/dsa_thumb.png",
                List.of("images/dsa_1.png"), "Đỗ Văn Hai", "NXB Khoa Học Kỹ Thuật"));
        products.add(new BookProduct(31, "Tư duy nhanh và chậm", 195_000, 35, "images/thinking_thumb.png",
                List.of("images/thinking_1.png"), "Daniel Kahneman", "NXB Thế Giới"));
        products.add(new BookProduct(32, "Design Patterns", 320_000, 25, "images/dp_thumb.png",
                List.of("images/dp_1.png"), "Erich Gamma", "Addison-Wesley"));

        // --- Nhóm 5: Đồ gia dụng (HouseholdProduct) - 8 sản phẩm ---
        products.add(new HouseholdProduct(33, "Nồi cơm điện tử", 1_200_000, 25, "images/rice_cooker_thumb.png",
                List.of("images/rc_1.png"), "Hợp kim nhôm", "30x30x35cm"));
        products.add(new HouseholdProduct(34, "Bếp điện từ", 2_500_000, 15, "images/induction_thumb.png",
                List.of("images/ind_1.png"), "Mặt kính Kanger", "60x40cm"));
        products.add(new HouseholdProduct(35, "Máy xay sinh tố", 850_000, 30, "images/blender_thumb.png",
                List.of("images/blender_1.png"), "Nhựa chịu lực", "20x20x40cm"));
        products.add(new HouseholdProduct(36, "Bộ nồi Inox 5 chiếc", 1_500_000, 20, "images/pots_thumb.png",
                List.of("images/pots_1.png"), "Inox 304", "Đa kích thước"));
        products.add(new HouseholdProduct(37, "Bình giữ nhiệt", 250_000, 60, "images/flask_thumb.png",
                List.of("images/flask_1.png"), "Inox 2 lớp", "500ml"));
        products.add(new HouseholdProduct(38, "Quạt máy đứng", 700_000, 40, "images/fan_thumb.png",
                List.of("images/fan_1.png"), "Nhựa và sắt", "40x40x120cm"));
        products.add(new HouseholdProduct(39, "Lò vi sóng", 2_100_000, 18, "images/microwave_thumb.png",
                List.of("images/mw_1.png"), "Thép sơn tĩnh điện", "45x35x30cm"));
        products.add(new HouseholdProduct(40, "Máy lọc nước RO", 4_500_000, 12, "images/filter_thumb.png",
                List.of("images/filter_1.png"), "Nhựa ABS", "40x30x90cm"));

        // --- Nhóm 6: Mỹ phẩm (CosmeticProduct) - 8 sản phẩm ---
        products.add(new CosmeticProduct(41, "Sữa rửa mặt Cetaphil", 320_000, 50, "images/cetaphil_thumb.png",
                List.of("images/ceta_1.png"), 250, LocalDate.of(2027, 8, 12)));
        products.add(new CosmeticProduct(42, "Kem chống nắng La Roche-Posay", 450_000, 60, "images/lrp_thumb.png",
                List.of("images/lrp_1.png"), 50, LocalDate.of(2027, 6, 15)));
        products.add(new CosmeticProduct(43, "Toner Klairs", 290_000, 45, "images/toner_thumb.png",
                List.of("images/toner_1.png"), 180, LocalDate.of(2026, 12, 30)));
        products.add(new CosmeticProduct(44, "Serum Vitamin C", 380_000, 35, "images/serum_thumb.png",
                List.of("images/serum_1.png"), 30, LocalDate.of(2027, 3, 20)));
        products.add(new CosmeticProduct(45, "Son dưỡng môi Dior", 750_000, 40, "images/dior_thumb.png",
                List.of("images/dior_1.png"), 5, LocalDate.of(2028, 5, 10)));
        products.add(new CosmeticProduct(46, "Tẩy trang Bioderma", 350_000, 55, "images/bio_thumb.png",
                List.of("images/bio_1.png"), 500, LocalDate.of(2027, 9, 15)));
        products.add(new CosmeticProduct(47, "Mặt nạ giấy Innisfree", 25_000, 200, "images/mask_thumb.png",
                List.of("images/mask_1.png"), 20, LocalDate.of(2026, 11, 20)));
        products.add(new CosmeticProduct(48, "Xịt khoáng Evoluderm", 180_000, 70, "images/spray_thumb.png",
                List.of("images/spray_1.png"), 400, LocalDate.of(2028, 2, 10)));

        // --- Nhóm 7: Sản phẩm cồng kềnh (BulkyProduct) - 7 sản phẩm ---
        products.add(new VehicleProduct(49, "Xe máy tay ga phân khối lớn", 45_000_000.0, 5, "images/vehicle_thumb.jpg",
                List.of("images/v1.jpg"), 130.5, 1.8, "150cc"));
        products.add(new VehicleProduct(50, "Xe đạp địa hình thể thao", 8_500_000.0, 10, "images/bike_thumb.jpg",
                List.of("images/b1.jpg"), 14.2, 0.5, "Không động cơ"));
        products.add(new IndustrialMachineProduct(51, "Máy tiện CNC công nghiệp tự động", 120_000_000.0, 2,
                "images/machine_thumb.jpg", List.of("images/m1.jpg"), 850.0, 4.5, 15));
        products.add(new IndustrialMachineProduct(52, "Máy phát điện công nghiệp", 65_000_000.0, 3,
                "images/generator_thumb.jpg", List.of("images/g1.jpg"), 420.0, 2.8, 30));
        products.add(new ContainerProduct(53, "Container lạnh chuyên dụng 40 feet", 95_000_000.0, 3,
                "images/container_thumb.jpg", List.of("images/c1.jpg"), 3_800.0, 67.5, "Lạnh", 28.5, true));
        products.add(new FurnitureProduct(54, "Bộ bàn ghế phòng khách gỗ tự nhiên", 35_000_000.0, 4,
                "images/furniture_thumb.jpg", List.of("images/f1.jpg"), 210.0, 3.2, "Gỗ Sồi", false, 3));
        products.add(new FurnitureProduct(55, "Tủ quần áo gỗ óc chó 4 cánh", 28_000_000.0, 6,
                "images/wardrobe_thumb.jpg", List.of("images/w1.jpg"), 180.0, 2.5, "Gỗ Óc Chó", true, 5));

        // --- 2. DANH SÁCH GIAO DỊCH (Tổng cộng đúng 40 giao dịch) ---
        // Giao dịch Nhập kho (20 giao dịch)
        for (int i = 0; i < 20; i++) {
            transactions.add(new ImportTransaction(i + 1, products.get(i % products.size()), 10 + i,
                    LocalDate.of(2026, 8, (i % 28) + 1)));
        }
        // Giao dịch Xuất kho (20 giao dịch)
        for (int i = 0; i < 20; i++) {
            transactions.add(new ExportTransaction(21 + i, products.get((i + 5) % products.size()), 5 + (i % 10),
                    LocalDate.of(2026, 8, (i % 28) + 1)));
        }

        System.out.println("✅ Đã khởi tạo thành công 55 sản phẩm và 40 giao dịch mẫu!\n");
    }

    // ==========================================
    // CÁC HÀM CHỨC NĂNG (PLACEHOLDERS & MỞ RỘNG)
    // ==========================================

    // ==========================================
    // IMPLEMENTATION: CHỨC NĂNG SỐ 1
    // ==========================================
    private static void displayProductList() {
        int tableWidth = calculateTableWidth(COL_WIDTHS);

        // Tạo chuỗi định dạng động từ COL_WIDTHS dùng chung
        String rowFormat = String.format("| %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds | %%-%ds |\n",
                COL_WIDTHS[0], COL_WIDTHS[1], COL_WIDTHS[2], COL_WIDTHS[3],
                COL_WIDTHS[4], COL_WIDTHS[5], COL_WIDTHS[6], COL_WIDTHS[7]);

        printTableBorder(tableWidth);
        System.out.printf(rowFormat,
                "ID", "Tên sản phẩm", "Danh mục", "Giá (VNĐ)", "SL", "Thumbnail", "Ảnh chi tiết", "Ngày tạo");
        printTableBorder(tableWidth);

        if (products.isEmpty()) {
            System.out.printf("| %-" + (tableWidth - 3) + "s |\n", "❌ Kho hàng hiện tại đang trống!");
        } else {

            for (Product p : products) {
                String category = getProductCategoryName(p);
                String thumb = Optional.ofNullable(p.getThumbnailImage()).orElse("Không có");
                int imageCount = Optional.ofNullable(p.getImages()).map(List::size).orElse(0);
                String createdStr = Optional.ofNullable(p.getCreatedAt())
                        .map(dt -> dt.format(DATE_TIME_FORMATTER))
                        .orElse("N/A");

                String rawName = Optional.ofNullable(p.getName()).orElse("Chưa có tên");

                // Áp dụng wrapText dựa theo chiều rộng cột Tên sản phẩm (COL_WIDTHS[1])
                List<String> nameLines = wrapText(rawName, COL_WIDTHS[1]);

                System.out.printf(rowFormat,
                        p.getId(),
                        nameLines.get(0),
                        category,
                        formatCurrency(p.getPrice()),
                        p.getQuantity(),
                        truncateTxt(thumb, COL_WIDTHS[5]),
                        imageCount + " ảnh",
                        createdStr);

                for (int i = 1; i < nameLines.size(); i++) {
                    System.out.printf(rowFormat,
                            "",
                            nameLines.get(i),
                            "", "", "", "", "", "");
                }
            }
        }
        printTableBorder(tableWidth);
        System.out.printf("📦 Tổng số loại sản phẩm trong kho: %d\n", products.size());

        promptReturnMenu();
    }

    private static String getProductCategoryName(Product p) {
        if (p instanceof FoodProduct) {
            return "Thực phẩm";
        } else if (p instanceof ElectronicProduct) {
            return "Điện tử";
        } else if (p instanceof ClothingProduct) {
            return "Thời trang";
        } else if (p instanceof BookProduct) {
            return "Sách";
        } else if (p instanceof HouseholdProduct) {
            return "Đồ gia dụng";
        } else if (p instanceof CosmeticProduct) {
            return "Mỹ phẩm";
        } else if (p instanceof VehicleProduct) {
            return "Phương tiện";
        } else if (p instanceof IndustrialMachineProduct) {
            return "Máy công nghiệp";
        } else if (p instanceof ContainerProduct) {
            return "Container";
        } else if (p instanceof FurnitureProduct) {
            return "Nội thất";
        } else if (p instanceof BulkyProduct) {
            return "Cồng kềnh khác";
        } else {
            return "Khác";
        }
    }

    private static String formatCurrency(double amount) {
        return String.format("%,.0f", amount);
    }

    // ==========================================
    // IMPLEMENTATION: CHỨC NĂNG SỐ 1
    // ==========================================
    private static void addNewProductManual() {
    }

    private static void updateProductManual() {
    }

    private static void deleteProductManual() {
    }

    private static void searchProductById() {
    }

    private static void searchProductByName() {
    }

    private static void importProductsFromFile() {
    }

    private static void batchUpdateProductsFromFile() {
    }

    private static void batchDeleteProductsFromFile() {
    }

    private static void batchDeleteProductsByManualIdInput() {
    }

    private static void displayFoodProducts() {
    }

    private static void displayElectronicProducts() {
    }

    private static void displayClothingProducts() {
    }

    private static void displayBookProducts() {
    }

    private static void displayHouseholdProducts() {
    }

    private static void displayCosmeticProducts() {
    }

    private static void displayVehicleProducts() {
    }

    private static void displayIndustrialMachineProducts() {
    }

    private static void displayContainerProducts() {
    }

    private static void displayFurnitureProducts() {
    }

    private static void importStockTransaction() {
    }

    private static void exportStockTransaction() {
    }

    private static void displayTransactionsByDay() {
    }

    private static void displayTransactionsByWeek() {
    }

    private static void displayTransactionsByMonth() {
    }

    private static void filterTransactionsByTimeRange() {
    }

    private static void filterTransactionsByTypeImport() {
    }

    private static void filterTransactionsByTypeExport() {
    }

    private static void sortTransactionsByTime() {
    }

    private static void sortTransactionsByQuantity() {
    }

    private static void filterTransactionsByProductId() {
    }

    private static void searchTransactionsByNote() {
    }

    private static void searchTransactionsByTransactionId() {
    }

    private static void searchTransactionsByMultipleConditions() {
    }

    private static void statisticsTransactionsByDay() {
    }

    private static void statisticsTransactionsByMonth() {
    }

    private static void findHighestValueProduct() {
    }

    private static void searchKeywordInFiles() {
    }

    private static void sortProductsByPriceAscending() {
    }

    private static void sortProductsByPriceDescending() {
    }

    private static void sortProductsByInventoryQuantity() {
    }

    private static void checkLowStockProducts() {
    }

    private static void applyBatchDiscountByCategory() {
    }

    private static void saveDataToFile() {
    }

    private static void loadDataFromFile() {
    }

    private static void displayInventoryReportConsole() {
    }

    private static void exportSummaryReportToFile() {
    }

    private static void exportFoodReportToFile() {
    }

    private static void exportElectronicReportToFile() {
    }

    private static void exportClothingReportToFile() {
    }

    private static void exportBookReportToFile() {
    }

    private static void exportHouseholdReportToFile() {
    }

    private static void exportCosmeticReportToFile() {
    }

    private static void exportBulkyReportToFile() {
    }

    private static void checkBulkyProductSafetyAndFees() {
    }

    private static void calculateBulkyProductFees() {
    }

    private static void simulateBulkyProductActions() {
    }

    private static void clearOldTransactionHistory() {
    }

    private static void backupSystemFiles() {
    }

    private static void restoreSystemFiles() {
    }

    private static int calculateTableWidth(int[] widths) {
        int total = 3;
        for (int w : widths) {
            total += w + 3;
        }
        return total;
    }

    private static void printTableBorder(int width) {
        System.out.println("-".repeat(width));
    }

    private static String centerText(String text, int width) {
        if (text.length() >= width)
            return text.substring(0, width);
        int padding = (width - text.length()) / 2;
        return " ".repeat(padding) + text;
    }

    private static void printMenuRow(String format, String key, String desc) {
        System.out.printf(format, key, desc);
    }

    private static List<String> wrapText(String text, int maxWidth) {
        List<String> lines = new ArrayList<>();
        if (text == null || text.isEmpty()) {
            lines.add("");
            return lines;
        }

        int length = text.length();
        for (int i = 0; i < length; i += maxWidth) {
            int end = Math.min(i + maxWidth, length);
            lines.add(text.substring(i, end));
        }
        return lines;
    }

    private static String truncateTxt(String str, int maxWidth) {
        if (str == null)
            return "";
        if (str.length() <= maxWidth)
            return str;
        return str.substring(0, maxWidth - 3) + "...";
    }

    // Hàm chung tạo hiệu ứng chờ 4 giây với thông báo tùy chỉnh
    private static void showLoadingDelay(String message) {
        System.out.print("\n⏳ " + message);
        for (int i = 0; i < 4; i++) {
            try {
                Thread.sleep(1000);
                System.out.print(".");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("\n");
    }

    // Hàm xóa console
    private static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }

    // ham hoi lai xu ly sau cung cua 1 chuc nang
    private static void promptReturnMenu() {
        System.out.println("\n----------------------------------------------------------------------");
        System.out.print(
                "👉 Bạn có muốn quay lại menu chính không? (Nhập 'y' để về menu, phím bất kỳ hoặc 'n' để thoát): ");
        String response = sc.nextLine().trim();
        if (response.equalsIgnoreCase("n")) {
            System.out.println("\n👋 Cảm ơn bạn đã sử dụng hệ thống. Hẹn gặp lại!");
            System.exit(0);
        } else if (response.equalsIgnoreCase("y")) {
            showLoadingDelay("Đang tải lại menu");
            clearConsole();
        }
    }

    // Hàm tạo độ trễ 4 giây và xóa màn hình console
    private static void executeWithDelayAndClear() {
        showLoadingDelay("Đang gọi hàm xử lý");
        clearConsole();
    }

}