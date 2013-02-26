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
# File:  Auditor.java
# Description: This is the interface definition of Auditor
#
# -----------------------------------------------------------------
# 
*/



/**
 * 
 */
package edu.indiana.d2i.htrc.audit;

/**
 * This is the interface definition of Auditor
 * @author Yiming Sun
 *
 */
public interface Auditor {
    
    public static final String KEY_REMOTE_USER = "remoteuser";
    public static final String KEY_REMOTE_ADDR = "remoteaddr";
    public static final String KEY_X_FORWARDED_FOR = "x-forwarded-for";
    
    /**
     * Method to log audit information regarding corpus data
     * @param action action taken on the corpus data
     * @param volumeID volumeID of the corpus data
     * @param pageSequences an array of page sequence numbers identifying the pages of the volume
     */
    public void audit(String action, String volumeID, String... pageSequences);
    
    /**
     * Method to log more generic audit information
     * @param action action to be logged
     * @param messages an array of messages to be logged
     */
    public void log(String action, String... messages);
    
    /**
     * Method to audit log errors
     * @param errorType type of error
     * @param messages an array of message to be logged
     */
    public void error(String errorType, String... messages);
}

