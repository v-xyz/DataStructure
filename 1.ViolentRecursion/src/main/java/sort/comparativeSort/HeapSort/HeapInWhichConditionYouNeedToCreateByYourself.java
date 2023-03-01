package sort.comparativeSort.HeapSort;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapInWhichConditionYouNeedToCreateByYourself {
    public static class Student{
        public int classNo;
        public int age;
        public int id;

        public Student(int classNo, int age, int id) {
            this.classNo = classNo;
            this.age = age;
            this.id = id;
        }
    }
    //compare(o1,o2)返回正数 o1>o2, 返回负数 o1<o2 返回 0，相等
    public static class StudentComparator implements Comparator<Student> {
        @Override
        public int compare(Student o1, Student o2) {
            return o1.age - o2.age;
        }
    }
    public static void main(String[] args) {
        //以下例子显示在何种情况下，系统提供的堆不再适用

        Student s1 = new Student(2,50,11111);
        Student s2 = new Student(1,60,22222);
        Student s3 = new Student(6,10,33333);
        Student s4 = new Student(3,20,44444);
        Student s5 = new Student(7,72,55555);
        Student s6 = new Student(1,14,66666);
        //学生年龄的小根堆
        PriorityQueue<Student> heap = new PriorityQueue<>(new StudentComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        heap.add(s6);

        //此时如果中途修改值，则无法反映在已经排好序的 PriorityQueue中
        s2.age = 6;
        s4.age = 12;
        s5.age = 10;
        s6.age = 84;

        while (!heap.isEmpty()){
            Student cur = heap.poll();
            System.out.println(cur.classNo+","+cur.age+","+cur.id);
        }
    }
}
