/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *  
 *   http://www.apache.org/licenses/LICENSE-2.0
 *  
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 *  
 */
package org.apache.wink.client.internal.handlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.wink.client.ClientAuthenticationException;
import org.apache.wink.client.ClientRequest;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.handlers.ClientHandler;
import org.apache.wink.client.handlers.HandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SecurityHandler for a client to perform http basic auth:
 * <p/>
 * <code>
 * Usage:<br/>
 * ClientConfig config = new ClientConfig();<br/>
 * config.handlers(new BasicAuthSecurityHandler());<br/>
 * // create the rest client instance<br/>
 * RestClient client = new RestClient(config);<br/>
 * // create the resource instance to interact with Resource<br/>
 * resource = client.resource("http://localhost:8080/path/to/resource");<br/>
 * </code>
 */
public class BasicAuthSecurityHandler implements ClientHandler {
    
    private static Logger logger = LoggerFactory.getLogger(BasicAuthSecurityHandler.class);

    final static String      PROPS_FILE_NAME = "wink.client.props";
    private Properties       clientProps     = null;
    private volatile boolean propsLoaded     = false;
    private volatile String  handlerUsername = null;
    private volatile String  handlerPassword = null;

    /**
     * Sets the username to use.
     * 
     * @param aUserName the user name
     */
    public void setUserName(String aUserName) {
        logger.debug("Setting the username to {}", aUserName);
        this.handlerUsername = aUserName;
    }

    /**
     * Sets the password to use.
     * 
     * @param aPassword the password to use
     */
    public void setPassword(String aPassword) {
        logger.debug("Setting the password");
        this.handlerPassword = aPassword;
    }

    /**
     * Performs basic HTTP authentication.
     * 
     * @param client request object
     * @param handler context object
     * @return a client response object that may contain an HTTP Authorization
     *         header
     */
    public ClientResponse handle(ClientRequest request, HandlerContext context) throws Exception {
        logger.debug("Entering BasicAuthSecurityHandler.doChain()");
        ClientResponse response = context.doChain(request);

        int statusCode = response.getStatusCode();
        logger.debug("Response status code was {}", statusCode);
        if (statusCode != 401) {
            logger.debug("Status code was not 401 so no need to re-issue request.");
            return response;
        } else {
            // read user id and password from a property
            // as a start we use java a command line property
            String userid = System.getProperty("user");
            String password = System.getProperty("password");
            if(logger.isDebugEnabled()) {
                logger.debug("The 'user' system property was set to: {}", userid);
                logger.debug("The 'password' system property was set: {}", password != null);
            }

            if (userid == null || userid.equals("") || password == null || password.equals("")) {
                // see if we can load credentials from a properties file
                String propsFileDir = System.getProperty("wink.client.props.dir");
                logger.debug("Could NOT get userid and password from system properties so attempting to look at properties file in {}", propsFileDir);
                if (propsFileDir != null && !propsFileDir.equals("")) {
                    if (!propsLoaded) {
                        clientProps = loadProps(propsFileDir + File.separator + PROPS_FILE_NAME);
                    }
                    userid = clientProps.getProperty("user");
                    password = clientProps.getProperty("password");
                } else {
                    logger.debug("Could NOT find properties file so checking variables assigned to handler itself", propsFileDir);
                    userid = handlerUsername;
                    password = handlerPassword;
                }
            }

            if (!(userid == null || userid.equals("") || password == null || password.equals(""))) {
                logger.debug("userid and password set so setting Authorization header");
                // we have a user credential
                String credential = userid + ":" + password;
                byte[] credBytes = credential.getBytes();
                byte[] encodedCredBytes =
                    org.apache.commons.codec.binary.Base64.encodeBase64(credBytes, false);
                // id and password needs to be base64 encoded
                String credEncodedString = "Basic " + new String(encodedCredBytes);
                request.getHeaders().putSingle("Authorization", credEncodedString);
                logger.debug("Issuing request again with Authorization header");
                response = context.doChain(request);
                if (response.getStatusCode() == 401) {
                    logger.debug("After sending request with Authorization header, still got 401 response");
                    throw new ClientAuthenticationException("Service failed to authenticate user: " + userid);
                } else {
                    logger.debug("Got a non-401 response, so returning response");
                    return response;
                }
            } else {
                logger.debug("userid and/or password were not set so throwing exception");
                // no user credential available
                throw new ClientAuthenticationException(
                                               "Missing client authentication credential for user: " + userid);
            }

        } // end if block
    } // end handle

    /**
     * Loads a properties file that contains user basic authentication
     * credential.
     * 
     * @param propsFileName
     * @return a Properties object
     */
    private synchronized Properties loadProps(String propsFileName) {
        Properties props = null;
        FileInputStream fis = null;
        try {
            File propsFile = new File(propsFileName);
            props = new Properties();
            fis = new FileInputStream(propsFile);
            props.load(fis);
            propsLoaded = true;
        } catch (IOException e) {
            props = null;
        } finally {
            try {
                if(fis != null) {
                    fis.close();
                }
            } catch (IOException e) {
                /* do nothing */
            }
        }
        return props;
    } // end loadProps

} // end class SecurityHandler

