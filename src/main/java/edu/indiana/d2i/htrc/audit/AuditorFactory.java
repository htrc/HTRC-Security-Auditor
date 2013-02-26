/*
#
# Copyright 2013 The Trustees of Indiana University
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: auditor
# File:  AuditorFactory.java
# Description: This class is the AuditorFactory for initializing and creating Auditor instances
#
# -----------------------------------------------------------------
# 
*/



/**
 * 
 */
package edu.indiana.d2i.htrc.audit;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * This class is the AuditorFactory for initializing and creating Auditor instances
 * @author Yiming Sun
 *
 */
public final class AuditorFactory {
    private static Logger log = Logger.getLogger(AuditorFactory.class);
    public static final String DEFAULT_AUDITOR_CLASS_NAME = "edu.indiana.d2i.htrc.access.audit.NullAuditor";
    private static String auditorClassName = DEFAULT_AUDITOR_CLASS_NAME;
    
    /**
     * Method to create Auditor instances
     * @param contextMap a Map object containing request context information
     * @return an Auditor object
     */
    public static Auditor getAuditor(Map<String, List<String>> contextMap) {
        Auditor auditor = null;
        try {
            Class<?> auditorClass = Class.forName(auditorClassName);
            Constructor<?> constructor = auditorClass.getConstructor(Map.class);
            Object instance = constructor.newInstance(contextMap);
            if (instance != null) {
                auditor = (Auditor)instance;
            }
        } catch (ClassNotFoundException e) {
            log.error("ClassNotFoundException", e);
        } catch (NoSuchMethodException e) {
            log.error("NoSuchMethodException", e);
        } catch (InvocationTargetException e) {
            log.error("InvocationTargetException", e);
        } catch (IllegalAccessException e) {
            log.error("IllegalAccessException", e);
        } catch (InstantiationException e) {
            log.error("InstantiationException", e);
        }
        
        if (auditor == null) {
            auditor = new NullAuditor(contextMap);
        }
        
        
        return auditor;
    }
    
    /**
     * Method to initialize the AuditorFactory to produce a specific implementation of the Auditor interface
     * @param auditorClass a fully qualified name of the specific Auditor class implementation
     */
    public static void init(String auditorClass) {
        if (auditorClass != null) {
            String localAuditorClassName = auditorClass.trim();
            if (!"".equals(localAuditorClassName)) {
                AuditorFactory.auditorClassName = localAuditorClassName;
                log.info("Using auditor class: " + localAuditorClassName);
            } else {
                log.info("Using default auditor class: " + AuditorFactory.auditorClassName);
            }
        } else {
            log.info("Using default auditor class: " + AuditorFactory.auditorClassName);
        }
       
    }

}

