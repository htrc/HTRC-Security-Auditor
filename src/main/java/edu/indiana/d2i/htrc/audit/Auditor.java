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
# Description:  
#
# -----------------------------------------------------------------
# 
*/



/**
 * 
 */
package edu.indiana.d2i.htrc.audit;

/**
 * @author Yiming Sun
 *
 */
public interface Auditor {
    
    public static final String KEY_REMOTE_USER = "remoteuser";
    public static final String KEY_REMOTE_ADDR = "remoteaddr";
    public static final String KEY_X_FORWARDED_FOR = "x-forwarded-for";
    
    public void audit(String action, String volumeID, String... pageSequences);
    public void log(String action, String... messages);
    public void error(String errorType, String... messages);
}

