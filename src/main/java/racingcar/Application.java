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
        List<String> carNames = parseName(Console.readLine());

        System.out.println("시도할 횟수는 몇 회인가요?");
        int count = parseCount(Console.readLine());

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

    private static List<String> parseName(String userInput) {
        if (userInput == null) throw new IllegalArgumentException("빈 이름 입력");
        List<String> names = Arrays.stream(userInput.split(","))
                .map(String::trim)
                .peek(name -> {
                    if(name.isEmpty()){
                        throw new IllegalArgumentException();
                    }
                })
                .collect(Collectors.toList());
        if (names.isEmpty()) throw new IllegalArgumentException();

        Set<String> dupCheck = new HashSet<>();
        for (String name : names) {
            if (!dupCheck.add(name)) {
                throw new IllegalArgumentException("중복 자동차 이름");
            }
        }

        return names;
    }

    private static int parseCount(String rawNumber) {
        try {
            int count = Integer.parseInt(rawNumber.trim());
            if (count <= 0) throw new IllegalArgumentException("음수 횟수");
            return count;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
