package org.imie.mvc;

public class Controller {

	private Model model;
	private Vue vue;

	/**
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * @param model
	 *            the model to set
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * @return the vue
	 */
	public Vue getVue() {
		return vue;
	}

	/**
	 * @param vue
	 *            the vue to set
	 */
	public void setVue(Vue vue) {
		this.vue = vue;
	}

	public void incrementSecond() {
		model.incrementSecond();
	}

	public void incrementMinute() {
		model.incrementMinute();
	}

	public void incrementHeure() {
		model.incrementHeure();
	}

	public void decrementSecond() {
		model.decrementSecond();
	}

	public void decrementMinute() {
		model.decrementMinute();
	}

	public void decrementHeure() {
		model.decrementHeure();
	}
	
	public void stop(){
		model.stop();
	}

}
