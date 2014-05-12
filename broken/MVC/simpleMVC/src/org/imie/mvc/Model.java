package org.imie.mvc;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Model {

	public class IncrementThread implements Runnable {
		private Boolean activ = true;
		
		public void stop(){
			activ=false;
		}
		@Override
		public void run() {
			while (activ) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
				incrementSecond();
			}
			System.out.println("End IncrementThread");
		}
	}

	private Integer heure;
	private Integer minute;
	private Integer second;

	private IncrementThread thread = new IncrementThread();

	public Model(Integer heure, Integer minute, Integer second) {
		super();
		startTimer(heure, minute, second);
	}

	/**
	 * @param heure
	 * @param minute
	 * @param second
	 */
	private void startTimer(Integer heure, Integer minute, Integer second) {
		this.heure = heure;
		this.minute = minute;
		this.second = second;
		Thread t = new Thread(thread);
		t.start();
	}

	public Model() {
		super();
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(new Date()); // assigns calendar to given date

		startTimer(calendar.get(Calendar.HOUR_OF_DAY),
				calendar.get(Calendar.MINUTE), calendar.get(Calendar.SECOND));
	}
	
	public void stop(){
		thread.stop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#finalize()
	 */
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
		thread.activ = false;
	}

	public void incrementSecond() {
		second++;
		if (second >= 60) {
			second -= 60;
			incrementMinute();
		}
	}

	public void incrementMinute() {
		minute++;
		if (minute >= 60) {
			minute -= 60;
			incrementHeure();
		}
	}

	public void incrementHeure() {
		heure++;
		if (heure >= 24) {
			heure -= 24;
		}
	}

	public void decrementSecond() {
		second--;
		if (second < 0) {
			second += 60;
			decrementMinute();
		}
	}

	public void decrementMinute() {
		minute--;
		if (minute < 0) {
			minute += 60;
			decrementHeure();
		}
	}

	public void decrementHeure() {
		heure--;
		if (heure < 0) {
			heure += 24;
		}
	}

	public Integer getHeure() {
		return heure;
	}

	public Integer getMinute() {
		return minute;
	}

	public Integer getSecond() {
		return second;
	}

}
