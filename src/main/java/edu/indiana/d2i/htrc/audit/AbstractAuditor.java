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
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either expressed or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: auditor
# File:  AbstractAuditor.java
# Description: this abstract class implements the Auditor interface 
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

/**
 * this abstract class implements the Auditor interface
 * 
 * @author Yiming Sun
 *
 */
public abstract class AbstractAuditor implements Auditor {
    
    protected final Map<String, List<String>> contextMap;

    /**
     * Constructor
     * @param contextMap a Map object containing request context information
     */
    public AbstractAuditor(Map<String, List<String>> contextMap) {
        this.contextMap = contextMap;
    }
    /**
     * @see edu.indiana.d2i.htrc.audit.Auditor#audit(java.lang.String, java.lang.String, java.lang.String[])
     */
    public abstract void audit(String action, String volumeID, String... pageSequences);
    
    /**
     * @see edu.indiana.d2i.htrc.audit.Auditor#log(java.lang.String, java.lang.String[])
     */
    public abstract void log(String action, String... messages);

    /**
     * @see edu.indiana.d2i.htrc.audit.Auditor#error(java.lang.String, java.lang.String[])
     */
    public abstract void error(String errorType, String... messages);

}

