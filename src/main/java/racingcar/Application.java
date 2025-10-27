package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;

public class Application {
    private static void checkCarName(String carName) throws Exception{
        if(carName.length()>5 || carName.trim().isEmpty()){
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

        System.out.println("시도할 횟수는 몇 회인가요?");
        String userInputCount = Console.readLine();
        int count = 0;
        try{
            count = Integer.parseInt(userInputCount);
        } catch (Exception e){
            throw new IllegalArgumentException();
        }

        System.out.println("실행 결과");
        for (int i=0; i<count; i++){
            for (Car car : cars){
                car.run();
                System.out.println(car.getName()+" : " + "-".repeat(car.getPosition()));
            }
            System.out.println("\n");
        }

        HashMap<String, Integer> carNamesPositions = new HashMap<>();
        for (Car car : cars){
            int carPosition = car.getPosition();
            String carName = car.getName();
            carNamesPositions.put(carName, carPosition);
        }

        Collection<Integer> values = carNamesPositions.values();
        Integer maxPosition = Collections.max(values);

        Iterator<Map.Entry<String, Integer>> entry = carNamesPositions.entrySet().iterator();

        ArrayList<String> winners = new ArrayList<String>();
        while (entry.hasNext()){
            Map.Entry<String, Integer> car = entry.next();
            if (car.getValue()>=maxPosition){
                winners.add(car.getKey());
            }
        }

        System.out.println("최종 우승자 : "+String.join(", ", winners));
    }
}
