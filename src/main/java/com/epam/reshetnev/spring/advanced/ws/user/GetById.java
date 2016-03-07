
package com.epam.reshetnev.spring.advanced.ws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getById", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getById", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
public class GetById {

    @XmlElement(name = "arg0", namespace = "")
    private Integer arg0;

    /**
     * 
     * @return
     *     returns Integer
     */
    public Integer getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(Integer arg0) {
        this.arg0 = arg0;
    }

}
