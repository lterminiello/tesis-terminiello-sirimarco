package server.home.model;

/**
 * Created by lterminiello on 12/10/16.
 */
public enum PinRaspberry {
    GPIO_1(1,"GPIO 1"),
    GPIO_2(2,"GPIO 2"),
    GPIO_3(3,"GPIO 3"),
    GPIO_4(4,"GPIO 4"),
    GPIO_5(5,"GPIO 5"),
    GPIO_6(6,"GPIO 6"),
    GPIO_7(7,"GPIO 7"),
    GPIO_8(8,"GPIO 8"),
    GPIO_9(9,"GPIO 9"),
    GPIO_10(10,"GPIO 10"),
    GPIO_11(11,"GPIO 11"),
    GPIO_12(12,"GPIO 12"),
    GPIO_13(13,"GPIO 13"),
    GPIO_14(14,"GPIO 14"),
    GPIO_15(15,"GPIO 15"),
    GPIO_16(16,"GPIO 16"),
    GPIO_17(17,"GPIO 17"),
    GPIO_18(18,"GPIO 18"),
    GPIO_19(19,"GPIO 19"),
    GPIO_20(20,"GPIO 20"),
    GPIO_21(21,"GPIO 21"),
    GPIO_22(22,"GPIO 22"),
    GPIO_23(23,"GPIO 23"),
    GPIO_24(24,"GPIO 24"),
    GPIO_25(25,"GPIO 25"),
    GPIO_26(26,"GPIO 26"),
    GPIO_27(27,"GPIO 27"),
    GPIO_28(28,"GPIO 28"),
    GPIO_29(29,"GPIO 29");

    private int numberPin;
    private String namePin;

    PinRaspberry(int numberPin, String namePin) {
        this.numberPin = numberPin;
        this.namePin = namePin;
    }

    public int getNumberPin() {
        return numberPin;
    }

    public void setNumberPin(int numberPin) {
        this.numberPin = numberPin;
    }

    public String getNamePin() {
        return namePin;
    }

    public void setNamePin(String namePin) {
        this.namePin = namePin;
    }
}
