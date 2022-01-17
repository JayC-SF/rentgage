module com.mycompany.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    opens com.mycompany.finalproject to javafx.fxml;
    opens com.mycompany.finalproject.bankList to javafx.fxml;
    opens com.mycompany.finalproject.contractorList to javafx.fxml;
    opens com.mycompany.finalproject.homePage to javafx.fxml;
    opens com.mycompany.finalproject.leaseList to javafx.fxml;
    opens com.mycompany.finalproject.maintenanceList to javafx.fxml;
    opens com.mycompany.finalproject.model to javafx.fxml;
    opens com.mycompany.finalproject.propertyList to javafx.fxml;
    opens com.mycompany.finalproject.tenantList to javafx.fxml;
    opens com.mycompany.finalproject.plexList to javafx.fxml;
    opens com.mycompany.finalproject.condoList to javafx.fxml;
    opens com.mycompany.finalproject.houseList to javafx.fxml;
    opens com.mycompany.finalproject.unitList to javafx.fxml;
    opens com.mycompany.finalproject.mortgageList to javafx.fxml;
    exports com.mycompany.finalproject;
    exports com.mycompany.finalproject.model;

    requires junit;
}
