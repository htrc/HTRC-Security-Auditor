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
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# -----------------------------------------------------------------
#
# Project: auditor
# File:  NullAuditor.java
# Description:  
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
 * @author Yiming Sun
 *
 */
public class NullAuditor extends AbstractAuditor {

    public NullAuditor(Map<String, List<String>> contextMap) {
        super(contextMap);
    }
    /**
     * @see edu.indiana.d2i.htrc.audit.AbstractAuditor#audit(java.lang.String, java.lang.String, java.lang.String[])
     */
    @Override
    public void audit(String action, String volumeID, String... pageSequences) {
        // do nothing in null auditor

    }

    /**
     * @see edu.indiana.d2i.htrc.audit.AbstractAuditor#log(java.lang.String, java.lang.String[])
     */
    @Override
    public void log(String action, String... messages) {
        // do nothing in null auditor
        
    }
    /**
     * @see edu.indiana.d2i.htrc.audit.AbstractAuditor#error(java.lang.String, java.lang.String[])
     */
    @Override
    public void error(String errorType, String... messages) {
        // do nothing in null auditor
        
    }

}

