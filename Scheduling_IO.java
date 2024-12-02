package org.scheduling;
import java.util.ArrayList;


public class Scheduling_IO {

    public static void main(String[] args) {
        //Do you want to exclude the 16-18 tutorials?
        boolean exclude_16_18 = true;
        // Do you want to exclude any other time slots? If none, leave the brackets empty {}
        int[] excluded_slots = {};
        // Minimum amount of students in a tutorial (default 3)
        int min_students = 3;
        // Maximal amount of students in a tutorial (default 5)
        int max_students = 6;
        // how many tutorials should each tutor have? length of this array should be equal to the number of tutors
        // if you leave this array empty, by default each tutor is supposed to have at least one tutorial
        int[] tutorials_per_tutor = {};

        //load the tutors data
        Read_Tutor_Data instance_tutor = new Read_Tutor_Data("tutors_la_1.xlsx");
        //Load the student data
        Read_Student_Data instance_student = new Read_Student_Data("students_la.xlsx");

        ArrayList<Tutor> list_tutor = new ArrayList<Tutor>();
        ArrayList<Student> list_student = new ArrayList<Student>();
        list_tutor = instance_tutor.returnList();
        list_student = instance_student.returnList();

        //Algorithm algorithm = new Algorithm(list_tutor,list_student);
        Algorithm2 algorithm2 = new Algorithm2(list_tutor,list_student,exclude_16_18,excluded_slots,
         tutorials_per_tutor,min_students,max_students);

    }
}
