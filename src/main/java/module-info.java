module dk.easv.easvticketgui {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires com.microsoft.sqlserver.jdbc;
    requires java.sql;
    requires java.naming;
    requires bcrypt;


    opens dk.easv.easvticket to javafx.fxml;
    exports dk.easv.easvticket;
    exports dk.easv.easvticket.be;
    opens dk.easv.easvticket.be to javafx.fxml;
}