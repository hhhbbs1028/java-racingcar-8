package racingcar;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        List<String> carNames = parseNames(Console.readLine());

        for (String carName : carNames){
            Car car = new Car(carName);
            cars.add(car);
        }

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

    private static List<String> parseNames(String raw) {
        if (raw == null) throw new IllegalArgumentException("자동차 이름 공백");
        List<String> names = Arrays.stream(raw.split(","))
                .map(String::trim)
                .peek(name -> {
                    if (name.isEmpty()) {
                        throw new IllegalArgumentException("자동차 이름 공백 불가");
                    }
                })
                .collect(Collectors.toList());
        if (names.isEmpty()) throw new IllegalArgumentException();

        Set<String> dupCheck = new HashSet<>();
        for (String name : names) {
            if (!dupCheck.add(name)) {
                throw new IllegalArgumentException("자동차 이름 중복");
            }
        }
        return names;
    }

    private static int parseCount(String s) {
        try {
            int count = Integer.parseInt(s.trim());
            if (count <= 0) throw new IllegalArgumentException("시도 횟수 음수");
            return count;
        } catch (Exception e) {
            throw new IllegalArgumentException("시도 횟수는 1 이상의 정수여야 함");
        }
    }
}
