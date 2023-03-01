package sort.comparativeSort;

import java.util.Arrays;
import java.util.Comparator;

public class ComparatorPractice {
    public static class Student{
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }
    public static class IdAscendingComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.id - o2.id;
        }
    }

    public static class AgeAscendingComparator implements Comparator<Student>{
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }
    public static void main(String[] args) {
        Student[] students = new Student[]{
                new Student("A",2,20),
                new Student("B",3,21),
                new Student("C",1,22),
        };
        Arrays.sort(students,new IdAscendingComparator());
        for (int i = 0; i < students.length; i++) {
            System.out.println(
                    "id:"+students[i].id
                            +" name:"+students[i].name
                            +" age:"+students[i].age);
        }
        System.out.println("=================================");
        Arrays.sort(students,new AgeAscendingComparator());
        for (int i = 0; i < students.length; i++) {
            System.out.println(
                    "id:"+students[i].id
                            +" name:"+students[i].name
                            +" age:"+students[i].age);
        }
    }
}
