package com.psy7758.util.dbConnector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector {
   private static final String MYSQL_DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
   private static final String MARIA_DRIVER_PATH = "org.mariadb.jdbc.Driver";
   private static final String MYSQL_DBMS_ACCESS_INFO = "jdbc:mysql://";
   private static final String MARIA_DBMS_ACCESS_INFO = "jdbc:mariadb://";
   
   private DBconnector(){}
   
   public static Connection getInstance(String dbms, String hostName_ip, int portNum, String serviceName_dbName, String userName, String psw) throws ClassNotFoundException, SQLException, NullPointerException {
      String dbmsDriver = null;       // 해당 DBMS 드라이버 패키지 경로.
      String url;                // 해당 DBMS 접속 URL.
      String assemble_dbms_access_info = null;
      
      switch (dbms) {
      case "mysql":
         dbmsDriver = MYSQL_DRIVER_PATH;
         assemble_dbms_access_info = MYSQL_DBMS_ACCESS_INFO;
         break;
      case "maria":
         dbmsDriver = MARIA_DRIVER_PATH;
         assemble_dbms_access_info = MARIA_DBMS_ACCESS_INFO;
         break;
      default:
         System.err.println("구성되어 있는 DBMS 와 일치하지 않습니다.");
         return null;
      }
      
      url = String.format("%s%s:%d/%s", assemble_dbms_access_info, hostName_ip, portNum, serviceName_dbName);
      
      Class.forName(dbmsDriver);
      
      return DriverManager.getConnection(url, userName, psw);
   }
}