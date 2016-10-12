package server.home.service;


import com.pi4j.io.gpio.*;
import server.home.model.Lights;

public class LightsService {

	/*final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pinKichent = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, "Kichent", PinState.LOW);
    final GpioPinPwmOutput pinBedroom = gpio.provisionPwmOutputPin(RaspiPin.GPIO_26, "Bedroom", 0);*/

	public LightsService() {
		super();
	}


	public Lights kitchenLight() {
		/*if(pinKichent.isLow()){
			pinKichent.high();
		}else{
			pinKichent.low();
		}
		return this.statusLights();*/
		return null;
	}

	public Lights bedroomLight(int power) {
		//pinBedroom.setPwm(power);
		return this.statusLights();

	}

	public Lights statusLights(){
		//return new Lights(pinKichent.getState().toString(), pinBedroom.getPwm())  ;
		return null;
	}


}
