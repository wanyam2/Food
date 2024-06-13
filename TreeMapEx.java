
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class TreeMapEx {
    public static void main(String[] args) {
        TreeMap<String, Integer> map = new TreeMap<>();

        // 유통기한을 관리하고싶은 식료품과 유통기한을 적어주세요.//
        
        map.put("cheese", 20240712);
        map.put("milk", 20240620);
        map.put("milk2", 20240616);
        map.put("yogurt", 20240624);
        map.put("cream", 20240730);
        map.put("butter", 20240918);
        map.put("cheese2", 20240720);

        //printAll(map.entrySet());
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("원하는 옵션을 골라주세요!");
        System.out.println("1. 검색해서 유통기한 찾기");
        System.out.println("2. 유통기한이 긴 순서대로 정리하기");
        int choice = scanner.nextInt();
        scanner.nextLine();
        
        System.out.println("--------------------------");
        
        switch (choice) {
        case 1:
        	System.out.print("유통기한을 알고싶은 식료품을 입력하세요 : ");
            String userInput = scanner.nextLine();

            // 사용자 입력과 일치하는 데이터 출력
            if (map.containsKey(userInput)) {
                System.out.println(userInput + " = " + map.get(userInput) + " 입니다.");
            } else {
                System.out.println(userInput + "는(은) 없습니다.");
            }
            break;
        case 2:
            // 값을 기준으로 내림차순으로 정렬하여 데이터 출력
        	System.out.println("유통기한이 긴 순서대로 정리했습니다^^");
        	List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
            Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
                @Override
                public int compare(Map.Entry<String, Integer> e1, Map.Entry<String, Integer> e2) {
                    return e2.getValue().compareTo(e1.getValue());
                }
            });

            for (Map.Entry<String, Integer> entry : list) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
            break;
        


        }
        // 유통기한이 많이 남은 식료품부터 차례대로 출력
//        NavigableMap<String, Integer> descendingMap = map.descendingMap();
//        printAll(descendingMap.entrySet());

//        System.out.println("--------------------------");

        // 범위 검색
//        NavigableMap<String, Integer> subMap = map.subMap("c", true, "h", false);
//        printAll(subMap.entrySet());
        System.out.println("--------------------------");

        Map.Entry<String, Integer> firstEntry = map.firstEntry();
        System.out.println(firstEntry.getKey() + "-" + firstEntry.getValue());

        Map.Entry<String, Integer> lastEntry = map.lastEntry();
        System.out.println(lastEntry.getKey() + "-" + lastEntry.getValue());
    }
    

    private static void printAll(Set<Map.Entry<String, Integer>> map) {
        for (Map.Entry<String, Integer> entry : map) {
            System.out.println(entry.getKey() + "-" + entry.getValue());
        }
    }
}