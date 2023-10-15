package ra.entity;

public class StatisticsForBill {
    private String date;
    private String statisticsValues;
    private String productName;

    private String dateFrom;
    private String dateTo;
    private int employeeStatus;
    private String statisticsEmployeeStatus;

    public StatisticsForBill() {
    }

    public StatisticsForBill(String date, String statisticsValues, String productName, String dateFrom, String dateTo, int employeeStatus, String statisticsEmployeeStatus) {
        this.date = date;
        this.statisticsValues = statisticsValues;
        this.productName = productName;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.employeeStatus = employeeStatus;
        this.statisticsEmployeeStatus = statisticsEmployeeStatus;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatisticsValues() {
        return statisticsValues;
    }

    public void setStatisticsValues(String statisticsValues) {
        this.statisticsValues = statisticsValues;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getEmployeeStatus() {
        return employeeStatus;
    }

    public void setEmployeeStatus(int employeeStatus) {
        this.employeeStatus = employeeStatus;
    }

    public String getStatisticsEmployeeStatus() {
        return statisticsEmployeeStatus;
    }

    public void setStatisticsEmployeeStatus(String statisticsEmployeeStatus) {
        this.statisticsEmployeeStatus = statisticsEmployeeStatus;
    }

    public void displayStatisticsValuesBill(){
        System.out.printf("| %-20s | %-20s |\n",this.date,this.statisticsValues);
    }
    public void displayStatisticsValuesEmployeeStatus(){
        String employeeStatusValues=this.employeeStatus==0?"Hoạt động":this.employeeStatus==1?"Nghỉ chế độ":"Nghỉ việc";
        System.out.printf("| %-20s | %-20s |\n",employeeStatusValues,this.statisticsEmployeeStatus);
    }

    public void statisticsMaxAndMinReceiptProductByMonthYear(){
        System.out.printf("| %-20s | %-30s | %-25s |\n",this.date,this.productName,this.statisticsValues);
    }


}
