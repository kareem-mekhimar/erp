/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bridge.services.exceptions;

import javax.ejb.ApplicationException;

/**
 *
 * @author Bridge
 */

@ApplicationException(rollback = true)
public class StaleTransferException extends Exception{
    
}
