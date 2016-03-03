
package com.epam.reshetnev.spring.advanced.jaxws.event;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAllNextEventsResponse", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAllNextEventsResponse", namespace = "http://impl.service.core.spring.reshetnev.epam.com/")
public class GetAllNextEventsResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.epam.reshetnev.spring.core.domain.Event> _return;

    /**
     * 
     * @return
     *     returns List<Event>
     */
    public List<com.epam.reshetnev.spring.core.domain.Event> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.epam.reshetnev.spring.core.domain.Event> _return) {
        this._return = _return;
    }

}
