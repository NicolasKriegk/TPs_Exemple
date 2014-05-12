package org.imie;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;


@WebService
public class BasicService {
	@WebMethod
	public List<String> executeService(){
		List<String> retour = new ArrayList<String>();
		retour.add("un");
		retour.add("deux");
		return retour;
	}

}
