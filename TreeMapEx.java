package food;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class TreeMapEx {
    public static void main(String[] args) {

        System.out.print("๑ᵔ⤙ᵔ๑ 자료구조 3조 식료품 유통기한 프로그램 ๑´ސު'๑\n");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        TreeMap<String, LocalDate> map = new TreeMap<>();

        Scanner scanner = new Scanner(System.in);

        // 초기 식료품을 추가하는 기능
        boolean addingItems = true;
        while (addingItems) {
            System.out.print("추가할 식료품의 이름을 입력하세요 (없을 경우 X 를 눌러주세요): ");
            String foodName = scanner.nextLine();
            if (foodName.equalsIgnoreCase("X")) {
                addingItems = false;
                continue;
            }
            System.out.print("추가할 식료품의 유통기한을 입력하세요 (yyyyMMdd): ");
            String expiryDateString = scanner.nextLine();
            LocalDate expiryDate = LocalDate.parse(expiryDateString, formatter);
            map.put(foodName, expiryDate);

        }

        // 식료품을 삭제하는 기능
        boolean deletingItems = true;
        while (deletingItems) {
            System.out.print("삭제할 식료품의 이름을 입력하세요 (없을 경우 X 를 눌러주세요): ");
            String removeFood = scanner.nextLine();
            if (removeFood.equalsIgnoreCase("X")) {
                deletingItems = false;
                continue;
            }
            if (map.containsKey(removeFood)) {
                map.remove(removeFood);
                System.out.println(removeFood + "이(가) 삭제되었습니다.");
            } else {
                System.out.println(removeFood + "는(은) 목록에 없습니다 🙅‍♀️");
            }

        }

        System.out.println("원하는 옵션을 골라주세요!");
        System.out.println("1. 검색해서 유통기한 찾기");
        System.out.println("2. 유통기한이 긴 순서대로 정리하기");
        System.out.println("3. 유통기한이 짧은 순서대로 정리하기");
        System.out.println("4. 현재 날짜로부터 남은 유통기한 계산하기");

        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.println("--------------------------");

        switch (choice) {
            case 1:
                System.out.println("*✧. °·‿︵‿୨ 유통기한 검색 ୧‿︵‿·°✧*");
                System.out.print("유통기한을 알고싶은 식료품을 입력하세요: ");
                String userInput = scanner.nextLine();

                // 사용자 입력과 일치하는 데이터 출력
                if (map.containsKey(userInput)) {
                    System.out.println(userInput + " = " + map.get(userInput).format(formatter) + " 입니다 🍎");
                } else {
                    System.out.println(userInput + "는(은) 없습니다.");
                }
                break;

            case 2:
                // 값을 기준으로 내림차순으로 정렬하여 데이터 출력
                System.out.println("*✧. °·‿︵‿୨ 유통기한 긴 순서대로 정렬 ୧‿︵‿·°✧*");
                List<Map.Entry<String, LocalDate>> list = new ArrayList<>(map.entrySet());
                Collections.sort(list, new Comparator<Map.Entry<String, LocalDate>>() {
                    @Override
                    public int compare(Map.Entry<String, LocalDate> e1, Map.Entry<String, LocalDate> e2) {
                        return e2.getValue().compareTo(e1.getValue());
                    }
                });

                for (Map.Entry<String, LocalDate> entry : list) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().format(formatter));
                }
                break;

            case 3:
                // 값을 기준으로 오름차순으로 정렬하여 데이터 출력
                System.out.println("*✧. °·‿︵‿୨ 유통기한 짧은 순서대로 정렬 ୧‿︵‿·°✧*");
                list = new ArrayList<>(map.entrySet());

                list.sort(new Comparator<Map.Entry<String, LocalDate>>() {
                    @Override
                    public int compare(Map.Entry<String, LocalDate> e1, Map.Entry<String, LocalDate> e2) {
                        return e1.getValue().compareTo(e2.getValue());
                    }
                });

                for (Map.Entry<String, LocalDate> entry : list) {
                    System.out.println(entry.getKey() + ": " + entry.getValue().format(formatter));
                }
                break;

            case 4:
                LocalDate currentDate = LocalDate.now();
                System.out.println("*✧. °·‿︵‿୨ 현재로부터 유통기한까지의 날짜는 다음과 같습니다 ୧‿︵‿·°✧*");

                for (Map.Entry<String, LocalDate> entry : map.entrySet()) {
                    LocalDate expiryDate = entry.getValue();
                    if (currentDate.isAfter(expiryDate)) {
                        System.out.println(entry.getKey() + ": 유통기한이 지났습니다 🤦‍♀️");
                    } else {
                        int daysLeft = (int) java.time.temporal.ChronoUnit.DAYS.between(currentDate, expiryDate);
                        System.out.println(entry.getKey() + ": " + daysLeft + "일 남았습니다 🍎");
                    }
                }
                break;

            default:
                System.out.println("잘못된 선택입니다 🙅‍♀️");
                break;
        }

        System.out.println("--------------------------");

    }

    private static void printAll(Set<Map.Entry<String, LocalDate>> map) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        for (Map.Entry<String, LocalDate> entry : map) {
            System.out.println(entry.getKey() + " - " + entry.getValue().format(formatter));
        }
    }
}
