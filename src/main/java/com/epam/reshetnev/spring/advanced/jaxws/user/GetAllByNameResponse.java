
package com.epam.reshetnev.spring.advanced.jaxws.user;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllByNameResponse", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllByNameResponse", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
public class GetAllByNameResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.epam.reshetnev.spring.core.domain.User> _return;

    /**
     * 
     * @return
     *     returns List<User>
     */
    public List<com.epam.reshetnev.spring.core.domain.User> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.epam.reshetnev.spring.core.domain.User> _return) {
        this._return = _return;
    }

}
