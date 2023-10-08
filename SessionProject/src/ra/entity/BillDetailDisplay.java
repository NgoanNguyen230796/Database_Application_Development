package ra.entity;

public class BillDetailDisplay extends Bill_Detail{
    private String billCode;

    public String getBillCode() {
        return billCode;
    }

    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }

    @Override
    public void displayDataBillDetail() {
        System.out.printf("| %-20d | %-20s | %-15s | %-30d | %-20.1f |\n", getBill_Detail_Id(),this.billCode, getProduct_Id(), getQuantity(), getPrice());
    }
}
