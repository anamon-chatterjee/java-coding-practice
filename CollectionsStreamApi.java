import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
class Main {
    public static void main(String[] args) {
        // String input = "tree";
        // String sentence = "I am learning Java streams API";
        // System.out.println("maxLenWord : " + maxLenWord(sentence));
        // Map<Character, Long> result = getFrequencyMap(input);

        // System.out.println("maxLenWord : " + maxLenWord(sentence));

        // result.forEach((key, value) ->
        //         System.out.println(key + " -> " + value));

        // String sentence = "abcdfhebcaak";
        // Character ch = findFirstNonRepeatingChar(sentence);
        // System.out.println("FirstNonRepeatingChar : " + ch);

        // System.out.println("Firstduplicate : " + findFirstduplicate(sentence));

        String str = "pxkkaapccpcxxxzz";
        Map<Character, Long> map = sortByFreqThenAlphabet(str);
        map.forEach((key, value) ->
                System.out.println(key + " -> " + value));
    }

    public static Map<Character, Long> sortByFreqThenAlphabet(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting()
                ))
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<Character, Long>comparingByValue().reversed()
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public static Character findFirstduplicate(String input) {
        Set<Character> set = new HashSet<>();
        return input.chars()
                .mapToObj(c -> (char) c)
                .filter(c -> !set.add(c))
                .findFirst()
                .get();
    }

    public static Character findFirstNonRepeatingChar(String input) {
        return input.chars()
                .mapToObj(c -> (char) c)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                LinkedHashMap::new,
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                .findFirst()
                .get();
    }

    public static String maxLenWord(String input) {
        return Arrays.stream(
                        input.split(" ")
                )
                .max(Comparator.comparingInt(s -> s.length()))
                .get();
    }

    public static Map<Character, Long> getFrequencyMap(String input) {
        return input.chars()
                .mapToObj(c -> (char)c)
                .collect(
                        Collectors.groupingBy(
                                Function.identity(),
                                Collectors.counting()
                        )
                )
                .entrySet()
                .stream()
                .sorted(
                        Map.Entry.<Character, Long>comparingByValue().reversed()
                )
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (e1, e2) -> e1,
                                LinkedHashMap::new
                        )
                );
    }
}