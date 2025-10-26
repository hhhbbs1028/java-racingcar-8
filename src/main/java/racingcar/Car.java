package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private final String name;
    private int position = 0;

    public Car(String name) {
        this.name = name;
    }

    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public void run(){
        if (dice()){
            this.position+=1;
        }
    }

    private boolean dice(){
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        return randomNumber >= 4;
    }
}
