package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

public class Car {
    private static final int NAME_MAX_LEN = 5;
    private static final int MOVE_THRESHOLD = 4;

    private final String name;
    private int position = 0;

    public Car(String name) {
        String trimmedName = name == null ? "" : name.trim();
        if (trimmedName.isEmpty() || trimmedName.length() > NAME_MAX_LEN) {
            throw new IllegalArgumentException("자동차 이름은 1~5자 이상");
        }
        this.name = trimmedName;
    }

    public int getPosition(){
        return position;
    }

    public String getName(){
        return name;
    }

    public void run(){
        if (movable()){
            this.position+=1;
        }
    }

    private boolean movable(){
        int randomNumber = Randoms.pickNumberInRange(0, 9);
        return randomNumber >= MOVE_THRESHOLD;
    }
}
