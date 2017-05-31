/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;

/**
 *
 * @author Bridge
 */

public class SecurityUtils {

    public static String[] digest(String password) {
       
        RandomNumberGenerator generator = new SecureRandomNumberGenerator() ;
        
        String salt = generator.nextBytes(32).toBase64() ;
        
        password = new Sha256Hash(password, salt, 1024).toBase64() ;     
        
        return new String[]{ password ,salt} ; 
    }
    
}
