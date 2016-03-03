
package com.epam.reshetnev.spring.advanced.jaxws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "update", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "update", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
public class Update {

    @XmlElement(name = "arg0", namespace = "")
    private com.epam.reshetnev.spring.core.domain.User arg0;

    /**
     * 
     * @return
     *     returns User
     */
    public com.epam.reshetnev.spring.core.domain.User getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.epam.reshetnev.spring.core.domain.User arg0) {
        this.arg0 = arg0;
    }

}
