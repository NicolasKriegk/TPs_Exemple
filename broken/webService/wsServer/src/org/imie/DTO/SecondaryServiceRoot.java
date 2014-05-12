package org.imie.DTO;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="ListOfBeans")
@XmlAccessorType(XmlAccessType.FIELD)
public class SecondaryServiceRoot {
	@XmlElement(name = "secondServiceBean")
	//@XmlElementWrapper(name = "secondServiceBean")  
	private List<SecondServiceBean> secondServiceBeans=new ArrayList<SecondServiceBean>();

	public List<SecondServiceBean> getSecondServiceBeans() {
		return secondServiceBeans;
	}

	public void setSecondServiceBeans(List<SecondServiceBean> secondServiceBeans) {
		this.secondServiceBeans = secondServiceBeans;
	}
	

}
