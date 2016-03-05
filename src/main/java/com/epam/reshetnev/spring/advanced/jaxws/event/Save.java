
package com.epam.reshetnev.spring.advanced.jaxws.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "save", namespace = "http://event.impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "save", namespace = "http://event.impl.service.core.spring.reshetnev.epam.com/")
public class Save {

    @XmlElement(name = "arg0", namespace = "")
    private com.epam.reshetnev.spring.core.domain.Event arg0;

    /**
     * 
     * @return
     *     returns Event
     */
    public com.epam.reshetnev.spring.core.domain.Event getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(com.epam.reshetnev.spring.core.domain.Event arg0) {
        this.arg0 = arg0;
    }

}
