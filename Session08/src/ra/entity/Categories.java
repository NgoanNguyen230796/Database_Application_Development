package ra.entity;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private int priority;
    private Boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, int priority, Boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.priority = priority;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Boolean getCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(Boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputDataCategories(Scanner sc) {
        System.out.println("Nhập vào tên danh mục:");
        this.catalogName = sc.nextLine();
        System.out.println("Nhập vào độ ưu tiên:");
        this.priority = Integer.parseInt(sc.nextLine());
        System.out.println("Nhập vào trạng thái danh mục:");
        this.catalogStatus = Boolean.parseBoolean( sc.nextLine());
    }

    public void displayDataCategories() {
        String statusValue = this.catalogStatus ? "Active" : "InActive";
        System.out.printf("%-20d%-20s%-20s%-20s\n", this.catalogId, this.catalogName, this.priority, statusValue);
    }
}
