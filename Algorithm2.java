package org.scheduling;
import java.util.*;
import ilog.concert.*;
import ilog.cplex.*;
public class Algorithm2 {


    public Algorithm2(ArrayList<Tutor> tutorList, ArrayList<Student> studentList,boolean exclude_16_18, int[] excluded_slots,
                      int[] tutorials_per_tutor, int min_students, int max_students) {
        try {
            IloCplex cplex = new IloCplex();
            cplex.setOut(null);

// get the amount of students and tutors
            int no_tutors = tutorList.size();
            int no_students = studentList.size();
//Decision variable x[tutor][student][time], x[1][4][0] = 1 means, that student 4 is in the group of tutor 1 and his tutorial happens at monday 8:30
//20 is hardcoded as we have 20 time slots in each week (four a day)
            IloNumVar[][][] x = new IloNumVar[no_tutors][no_students][20];
//introducing the objective function
            IloLinearNumExpr obj = cplex.linearNumExpr();

            //initialize the decision variable
            for (int i = 0; i < no_tutors; i++) {
                for (int j = 0; j < no_students; j++) {
                    x[i][j] = cplex.numVarArray(20, 0, 1, IloNumVarType.Int);
                }
            }
//y is the variable telling if the tutorial is taking place at time t
            IloNumVar[][] y = new IloNumVar[no_tutors][20];
            for(int i=0; i < no_tutors; i++) {
                y[i] = cplex.numVarArray(20, 0, 1, IloNumVarType.Int);
            }

//adding whatever obj value so that cplex finds solution
            for (int i = 0; i < no_tutors; i++) {
                for (int j = 0; j < no_students; j++) {
                    // IloLinearNumExpr expr = cplex.linearNumExpr();
                    for (int t = 0; t < 20; t++) {
                        obj.addTerm(x[i][j][t], 1);
                    }
                }
            }
            cplex.addMaximize(obj);

            //Constraint 1, each student has 1 tutorial per week
            for(int j=0;j<studentList.size();j++){
                IloLinearNumExpr expr = cplex.linearNumExpr();

                for(int t=0;t<20;t++) {
                    for(int i = 0; i< no_tutors; i++){
                        expr.addTerm(x[i][j][t], 1);
                    }
                }

                cplex.addEq(expr,1);
            }

            //Constraint, for each tutor and time slot, sum of students taking part must be smaller equal
            // no of students * y, which is the binary variable if tutorial is taking place
            for(int i=0;i<no_tutors;i++) {
                for (int t = 0; t < 20; t++) {
                    IloLinearNumExpr expr = cplex.linearNumExpr();
                    for (int j = 0; j < studentList.size(); j++) {
                        expr.addTerm(x[i][j][t], 1);
                    }
                    //Here give the min and max students per group
                    cplex.addLe(expr, cplex.prod(max_students,y[i][t]));
                    cplex.addGe(expr, cplex.prod(min_students,y[i][t]));
                }
            }

//Constraint, there are x tutorials per week
            for(int i=0;i<no_tutors;i++) {
                IloLinearNumExpr expr1 = cplex.linearNumExpr();
                for (int t = 0; t < 20; t++) {
                    expr1.addTerm(y[i][t], 1);
                }
                //Here give how many tutorials each can tutor have, may be conditional as well
                if(tutorials_per_tutor.length > 0)
                    cplex.addEq(expr1, tutorials_per_tutor[i]);
                else {
                    Tutor tutor = tutorList.get(i);
                    cplex.addGe(expr1, 1);
                    cplex.addLe(expr1, tutor.getMaxTutorials());
                }
            }

            // Constraint 3, unavailability for students
            for(int j=0;j< no_students;j++){
                Student student = studentList.get(j);
                int[] time = student.getTime();
                IloLinearNumExpr expr = cplex.linearNumExpr();
                for(int t=0;t<20;t++){
                    //maybe change this later not to do it manually
                    if(time[t]==0){
                        for(int i = 0; i < no_tutors; i++) {
                            expr.addTerm(x[i][j][t], 1);
                        }
                    }
                }
                cplex.addEq(expr,0);
            }

            // Constraint 4, unavailability for tutors
            for(int i=0;i < no_tutors;i++){
                Tutor tutor = tutorList.get(i);
                //System.out.println(tutor.getName());
                int[] time = tutor.getTime();
                IloLinearNumExpr expr = cplex.linearNumExpr();
                for(int t=0;t<20;t++){
                    if(time[t]==0){
                        //System.out.println(t);
                        for(int j=0;j< studentList.size();j++) {
                            expr.addTerm(x[i][j][t],1);
                        }
                    }
                }
                cplex.addEq(expr,0);
            }
            /*
            // Days excluded from the consideration
                if(exclude_16_18 == true) {
                    //Days when I don't want tutorials - Every day 16-18 + customs
                    for (int i = 0; i < no_tutors; i++) {
                        Tutor tutor = tutorList.get(i);
                        //System.out.println(tutor.getName());
                        int[] time = tutor.getTime();
                        IloLinearNumExpr expr = cplex.linearNumExpr();

                        //System.out.println(t);
                        for (int j = 0; j < studentList.size(); j++) {
                            expr.addTerm(x[i][j][3], 1);
                        }
                        for (int j = 0; j < studentList.size(); j++) {
                            expr.addTerm(x[i][j][7], 1);
                        }
                        for (int j = 0; j < studentList.size(); j++) {
                            expr.addTerm(x[i][j][11], 1);
                        }
                        for (int j = 0; j < studentList.size(); j++) {
                            expr.addTerm(x[i][j][15], 1);
                        }
                        for (int j = 0; j < studentList.size(); j++) {
                            expr.addTerm(x[i][j][19], 1);
                        }
                        cplex.addEq(expr, 0);
                    }
                }

             */
            // Days excluded from the consideration
            if(exclude_16_18 == true) {
                //Days when I don't want tutorials - Every day 16-18 + customs
                for (int i = 0; i < no_tutors; i++) {
                    Tutor tutor = tutorList.get(i);
                    //System.out.println(tutor.getName());
                    int[] time = tutor.getTime();
                    IloLinearNumExpr expr = cplex.linearNumExpr();

                    expr.addTerm(y[i][3], 1);
                    expr.addTerm(y[i][11], 1);
                    expr.addTerm(y[i][15], 1);
                    expr.addTerm(y[i][19], 1);

                    cplex.addEq(expr, 0);
                }
            }
                if(excluded_slots.length > 0){
                    for(int k = 0; k < excluded_slots.length; k++){
                        for (int i = 0; i < no_tutors; i++) {
                            Tutor tutor = tutorList.get(i);
                            //System.out.println(tutor.getName());
                            int[] time = tutor.getTime();
                            IloLinearNumExpr expr = cplex.linearNumExpr();

                            for (int j = 0; j < studentList.size(); j++) {
                                expr.addTerm(x[i][j][excluded_slots[k]], 1);
                            }
                            cplex.addEq(expr, 0);
                        }
                    }
                }

            cplex.solve();

// print the results
            for(int i=0;i<no_tutors;i++){
                for(int j=0;j<no_students;j++){
                    for(int t=0;t<20;t++){
                        if(cplex.getValue(x[i][j][t])==1) {
                            System.out.println(tutorList.get(i).getName() + " ; " +
                                    studentList.get(j).getName() + " ; " + t);
                        }
                    }
                }
            }



        }
        catch (IloException e) {
            e.printStackTrace();
        }
    }
}
