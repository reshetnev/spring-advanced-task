
package com.epam.reshetnev.spring.advanced.jaxws.event;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getByIdResponse", namespace = "http://event.impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByIdResponse", namespace = "http://event.impl.service.core.spring.reshetnev.epam.com/")
public class GetByIdResponse {

    @XmlElement(name = "return", namespace = "")
    private com.epam.reshetnev.spring.core.domain.Event _return;

    /**
     * 
     * @return
     *     returns Event
     */
    public com.epam.reshetnev.spring.core.domain.Event getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.epam.reshetnev.spring.core.domain.Event _return) {
        this._return = _return;
    }

}
