package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;

public class Application {
    private static void checkCarName(String carName) throws Exception{
        if(carName.length()>5){
            throw new IllegalArgumentException();
        }
    }
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String userInputCars = Console.readLine();
        String[] userInputSplit = userInputCars.split(",");
        List<String> carNames = Arrays.asList(userInputSplit);
        carNames.forEach(c -> {
            try {
                checkCarName(c);
            } catch (Exception e) {
                throw new IllegalArgumentException(e);
            }
        });
        ArrayList<Car> cars = new ArrayList<>();

        for (String carName : carNames){
            Car car = new Car(carName);
            cars.add(car);
        }
    }
}
