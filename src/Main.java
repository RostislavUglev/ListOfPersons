import java.util.*;
import java.util.stream.Collectors;


public class Main {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }

        long count = persons.stream()
                .filter((Person age) -> age.getAge() >= 18)
                .count();
        System.out.println(count);

        String surnames = persons.stream()
                .filter((Person age) -> age.getAge() >= 18)
                .filter((Person age) -> age.getAge() <= 27)
                .filter((Person sex) -> sex.getSex() == Sex.MAN)
                .map(Person -> Person.getFamily())
                .collect(Collectors.toList()).toString();
        System.out.println(surnames);

        String workingPeople = persons.stream()
                .filter((Person education) -> education.getEducation() == Education.HIGHER)
                .filter((Person age) -> age.getAge() >= 18)
                .filter((Person) -> (Person.getSex() ==
                        Sex.WOMAN && Person.getAge() < 60) ||
                        (Person.getSex() == Sex.MAN && Person.getAge() < 65))
                .sorted(Comparator.comparing(Person::getFamily))
                .collect(Collectors.toList()).toString();
        System.out.println(workingPeople);


    }
}
