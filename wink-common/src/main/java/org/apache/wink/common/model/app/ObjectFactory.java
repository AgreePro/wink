/*******************************************************************************
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
 *******************************************************************************/
//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.1.1-b02-fcs 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2008.09.15 at 12:57:27 PM IDT 
//

package org.apache.wink.common.model.app;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the org.apache.wink.common.model.app package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Accept_QNAME     =
                                                     new QName("http://www.w3.org/2007/app", //$NON-NLS-1$
                                                               "accept"); //$NON-NLS-1$
    private final static QName _Workspace_QNAME  =
                                                     new QName("http://www.w3.org/2007/app", //$NON-NLS-1$
                                                               "workspace"); //$NON-NLS-1$
    private final static QName _Categories_QNAME =
                                                     new QName("http://www.w3.org/2007/app", //$NON-NLS-1$
                                                               "categories"); //$NON-NLS-1$
    private final static QName _Service_QNAME    =
                                                     new QName("http://www.w3.org/2007/app", //$NON-NLS-1$
                                                               "service"); //$NON-NLS-1$
    private final static QName _Collection_QNAME =
                                                     new QName("http://www.w3.org/2007/app", //$NON-NLS-1$
                                                               "collection"); //$NON-NLS-1$

    /**
     * Create a new ObjectFactory that can be used to create new instances of
     * schema derived classes for package: org.apache.wink.common.model.app
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AppAccept }
     */
    public AppAccept createAppAccept() {
        return new AppAccept();
    }

    /**
     * Create an instance of {@link AppCollection }
     */
    public AppCollection createAppCollection() {
        return new AppCollection();
    }

    /**
     * Create an instance of {@link AppWorkspace }
     */
    public AppWorkspace createAppWorkspace() {
        return new AppWorkspace();
    }

    /**
     * Create an instance of {@link AppCategories }
     */
    public AppCategories createAppCategories() {
        return new AppCategories();
    }

    /**
     * Create an instance of {@link AppService }
     */
    public AppService createAppService() {
        return new AppService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppAccept }
     * {@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2007/app", name = "accept")
    public JAXBElement<AppAccept> createAccept(AppAccept value) {
        return new JAXBElement<AppAccept>(_Accept_QNAME, AppAccept.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppWorkspace }
     * {@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2007/app", name = "workspace")
    public JAXBElement<AppWorkspace> createWorkspace(AppWorkspace value) {
        return new JAXBElement<AppWorkspace>(_Workspace_QNAME, AppWorkspace.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppCategories }
     * {@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2007/app", name = "categories")
    public JAXBElement<AppCategories> createCategories(AppCategories value) {
        return new JAXBElement<AppCategories>(_Categories_QNAME, AppCategories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppService }
     * {@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2007/app", name = "service")
    public JAXBElement<AppService> createService(AppService value) {
        return new JAXBElement<AppService>(_Service_QNAME, AppService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AppCollection }
     * {@code >}
     */
    @XmlElementDecl(namespace = "http://www.w3.org/2007/app", name = "collection")
    public JAXBElement<AppCollection> createCollection(AppCollection value) {
        return new JAXBElement<AppCollection>(_Collection_QNAME, AppCollection.class, null, value);
    }

}
