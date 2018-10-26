package com.dasn.web;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PreDestroy;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.derby.jdbc.EmbeddedConnectionPoolDataSource;
import org.apache.derby.jdbc.EmbeddedDataSource;

@ApplicationScoped
public class AppConfig {

    private static final Logger Log=Logger.getLogger(AppConfig.class.getName());
    private DataSource ds=null;
        
    @Produces
    public DataSource getDataSource() throws NamingException, IOException {
        String dbpath="/tmp/notes";
        if (ds == null) {
            EmbeddedConnectionPoolDataSource dbds= new EmbeddedConnectionPoolDataSource();
            
            dbds.setDatabaseName(dbpath);
            
            File dbLoc = new File(dbpath);
            if(!dbLoc.exists()) {
                dbds.setCreateDatabase("create");
            }                        
            ds=dbds;
        }

        return ds;
    }   
    
    @PreDestroy
    private void shutdownDB ()  {
        try {
            //Close embedded database (remove db locks)
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            Log.log(Level.SEVERE, e.getMessage(), e);
        }
    }
 
}