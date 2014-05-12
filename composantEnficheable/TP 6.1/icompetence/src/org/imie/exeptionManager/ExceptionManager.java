package org.imie.exeptionManager;

public class ExceptionManager {
	private static ExceptionManager instance;
	
	
	
	private ExceptionManager() {
		super();
	}


	public static synchronized ExceptionManager getInstance(){
		if (instance==null){
			instance = new ExceptionManager();
		}
		return instance;
	}
	
	
	public void manageException(Exception e){
		e.printStackTrace();
	}
	

}
