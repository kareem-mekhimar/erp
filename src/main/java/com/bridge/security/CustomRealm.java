package com.bridge.security;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;


public class CustomRealm extends JdbcRealm {
  
    public CustomRealm() {
    
        setSaltStyle(SaltStyle.COLUMN);
        
        
    }

    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        
        UsernamePasswordToken passwordToken = (UsernamePasswordToken) token ;
        
        try {

            MySaltedAuthInfo info = null ;
            
            DataSource ds =  (DataSource) new InitialContext().lookup("java:/BridgeDS") ;
            
            Connection conn = ds.getConnection();
            
            PreparedStatement ps =  conn.prepareStatement("SELECT user_name,password,salt FROM users_accounts " +
                " WHERE user_name = ?") ;

            ps.setString(1, passwordToken.getUsername()) ;
            
            ResultSet rs =  ps.executeQuery();
            
            if(rs.next()) {

                info =  new MySaltedAuthInfo(rs.getString(1), rs.getString(2), rs.getString(3)) ;
            }
            
            conn.close() ;
            
            if(info == null)
                
                throw new AuthenticationException("Unknown User or Pass") ;
            
            return info ;
            
        } catch (NamingException ex) {
        
            Logger.getLogger(CustomRealm.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
