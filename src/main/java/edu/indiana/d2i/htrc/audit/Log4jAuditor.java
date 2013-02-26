/*
#
# Copyright 2012 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or areed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: data-api
# File:  Log4jAuditer.java
# Description: This class extends the AbstractAuditor class and uses Log4j to record audit information
#
# -----------------------------------------------------------------
# 
*/



/**
 * 
 */
package edu.indiana.d2i.htrc.audit;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * This class extends the AbstractAuditor class and uses Log4j to record audit information
 * 
 * @author Yiming Sun
 *
 */
public final class Log4jAuditor extends AbstractAuditor {
    
    private static final Logger auditLogger = Logger.getLogger("audit");
    private static Logger log = Logger.getLogger(Log4jAuditor.class);
    
    private static final String TAB = "\t";
    private static final String COMMA = ",";
    private static final String UNKNOWN = "UNKNOWN";
    private static final String VIA = "->";
    
    private String userIdentity = null;
   
    /**
     * Constructor that extracts request context information from the context map to populate and harden some fields for better performance
     * @param contextMap a Map object containing request context information
     */
    public Log4jAuditor(Map<String, List<String>> contextMap) {
        super(contextMap);
        String userID = UNKNOWN;
        String userIP = UNKNOWN;

        List<String> context = contextMap.get(KEY_REMOTE_USER);
        userID = listToString(context);
        if (log.isDebugEnabled()) log.debug("remoteUser: " + userID);
        
        context = contextMap.get(KEY_REMOTE_ADDR);
        String remoteAddr = listToString(context);
        if (log.isDebugEnabled()) log.debug("remoteAddr:" + remoteAddr);
        
        context = contextMap.get(KEY_X_FORWARDED_FOR);
       
        String forwardedFor = listToString(context);
        
        if (log.isDebugEnabled()) log.debug("X-Forwarded-For:" + forwardedFor);
        
        if (UNKNOWN.equals(forwardedFor)) {
            userIP = remoteAddr;
        } else {
            userIP = forwardedFor + VIA + remoteAddr;
        }
        if (log.isDebugEnabled()) log.debug("userIP: " + userIP);
        
        StringBuilder builder  = new StringBuilder(userID);
        builder.append(TAB).append(userIP).append(TAB);
        userIdentity = builder.toString();
        
        if (log.isDebugEnabled()) log.debug("userIdentity: " + userIdentity);
    }
  
    /**
     * @see edu.indiana.d2i.htrc.access.Auditor#audit(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String[])
     */
    @Override
    public void audit(String action, String volumeID, String... pageSequences) {

    	StringBuilder builder = new StringBuilder(userIdentity);
    	builder.append(action).append(TAB).append(volumeID);
    	
        if (pageSequences != null) {
            int length = pageSequences.length;
            if (length > 0) {
                builder.append(TAB).append(pageSequences[0]);
                for (int i = 1; i < length; i++) {
                    builder.append(COMMA).append(pageSequences[i]);
                }
            }
        }
        auditLogger.info(builder.toString());
    }
    
    
    /**
     * Method that converts a list of String objects into a single comma-separated String object
     * @param list a List of String objects
     * @return a String object containing items from the List separated by comma
     */
    protected String listToString(List<String> list) {
        StringBuilder builder = new StringBuilder();
        
        if (list != null && !list.isEmpty()) {
            builder.append(list.get(0));
            for (int i = 1; i < list.size(); i++) {
                builder.append(COMMA).append(list.get(i));
            }
        } else {
            builder.append(UNKNOWN);
        }
        return builder.toString();
    }

    /**
     * @see edu.indiana.d2i.htrc.audit.AbstractAuditor#log(java.lang.String, java.lang.String[])
     */
    @Override
    public void log(String action, String... messages) {
        StringBuilder builder = new StringBuilder(userIdentity);
        builder.append(action);
        
        if (messages != null) {
            for (String message : messages) {
                builder.append(TAB).append(message);
            }
        }
        auditLogger.info(builder.toString());
    }

    /**
     * @see edu.indiana.d2i.htrc.audit.AbstractAuditor#error(java.lang.String, java.lang.String[])
     */
    @Override
    public void error(String errorType, String... messages) {
        StringBuilder builder = new StringBuilder(userIdentity);
        builder.append(errorType);
        
        if (messages != null) {
            for (String message : messages) {
                builder.append(TAB).append(message);
            }
        }
        auditLogger.error(builder.toString());
    }
    
   

}

