package com.uncoverpc.searchProcess.Computer;

public class Components {

	//Class Variables
	private Component relativeComponent;
	private Component similarComponent;
	
	/**
	 * Constructor
	 * @param relativeComponent, Component
	 * @param similarComponent, Component
	 */
	public Components(Component relativeComponent, Component similarComponent) {
		this.relativeComponent = relativeComponent;
		this.similarComponent = similarComponent;
	}

	
	//Getters and Setters
	/**
	 * 
	 * @return relativeComponent
	 */
	public Component getRelativeComponent() {
		return relativeComponent;
	}

	/**
	 * 
	 * @0return similarComponent
	 */
	public Component getSimilarComponent() {
		return similarComponent;
	}

	
	
}
