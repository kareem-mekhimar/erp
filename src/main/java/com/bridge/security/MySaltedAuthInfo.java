package com.bridge.security;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;

public class MySaltedAuthInfo implements SaltedAuthenticationInfo {
    private String username ;
    
    private String password ;
    
    private String salt ;

    public MySaltedAuthInfo(String username, String password, String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }
    
    
    @Override
    public ByteSource getCredentialsSalt() {
        
        return new SimpleByteSource(salt) ;
    }

    @Override
    public PrincipalCollection getPrincipals() {

        return new SimplePrincipalCollection(username,"CustomRealm") ;
    }

    @Override
    public Object getCredentials() {

        return password ;
    }
}
