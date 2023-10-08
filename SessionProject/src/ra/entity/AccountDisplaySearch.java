package ra.entity;

public class AccountDisplaySearch extends Account{
    private String em_Name ;

    @Override
    public void displayDataAccount() {
            String permissionValue = getPermission() ? "User" : "Admin";
            String accStatusValue = getAcc_Status()? "Active" : "Block";
            System.out.printf("| %-15d | %-30s | %-15s | %-30s | %-20s | %-20s | %-20s |\n", getAcc_Id(), getUser_Name(), getPassword(),
                    permissionValue, getEmp_Id(),this.em_Name, accStatusValue);

    }

    public String getEm_Name() {
        return em_Name;
    }

    public void setEm_Name(String em_Name) {
        this.em_Name = em_Name;
    }
}
