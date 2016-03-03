
package com.epam.reshetnev.spring.advanced.jaxws.user;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getByEmailResponse", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getByEmailResponse", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
public class GetByEmailResponse {

    @XmlElement(name = "return", namespace = "")
    private com.epam.reshetnev.spring.core.domain.User _return;

    /**
     * 
     * @return
     *     returns User
     */
    public com.epam.reshetnev.spring.core.domain.User getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.epam.reshetnev.spring.core.domain.User _return) {
        this._return = _return;
    }

}
