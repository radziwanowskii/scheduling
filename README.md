# Vectum Scheduling program for the Extra Tutorials
# How to use the program
1) Download the response sheets for tutors and students in an Excel format.
2) Separate the sheets to have a sheet with tutors and students for each course.
3) Put the sheets in the folder where your program is and fill in their names
   in the Scheduling_IO.java file.
4) Adjust the hyperparameters at the top of the Scheduling_IO.java file.
5) Run the program and check the results
6) If everything is ok, copy the results and paste them in an Excel sheet.
   You can use the paste special option and set a semicolon as a separator.
7) Now you can do the same for the second course. If tutorials from both courses take
   place at the same time, check if there are no students having two tutorials at the same time.
   In this case you need to reschedule one course and exclude the given timeslot upfront.

# Comments on the program:
- This program was written to help Vectum treasurer find a feasible
schedule for the Extra Tutorial program in each period. It inputs
a response sheet of students who chose a given course and tutors teaching it.
The program is supposed to handle the signup forms in the form that was
used by Vectum in the last years. Any changes to the form will have to be
followed by the adjustment in the Read_Tutor_Data.java and Read_Student_data.java files.
- The aim of this program to provide maximally simple and flexible way of finding a feasible schedule.
All needed hyperparameters can be adjusted in the Scheduling_IO.java file with no need
of changing the algorithm file. 
- While the functions in the Read_Tutor_Data.java and Read_Student_data.java files might be seen as suboptimal, they are 
used on purpose to ensure the simplicity of the code as well as possible changes.
This is because treasurer of Vectum is only required to have basic knowledge of java.
- The results are printed in the console to allow for easy checks and quick adjustments of the hyperparameters.

# Index / time slot table
| Index | Day       | Time Slot     |
|-------|-----------|---------------|
| 0     | Monday    | 8:30-10:30    |
| 1     | Monday    | 11:00-13:00   |
| 2     | Monday    | 13:30-15:30   |
| 3     | Monday    | 16:00-18:00   |
| 4     | Tuesday   | 8:30-10:30    |
| 5     | Tuesday   | 11:00-13:00   |
| 6     | Tuesday   | 13:30-15:30   |
| 7     | Tuesday   | 16:00-18:00   |
| 8     | Wednesday | 8:30-10:30    |
| 9     | Wednesday | 11:00-13:00   |
| 10    | Wednesday | 13:30-15:30   |
| 11    | Wednesday | 16:00-18:00   |
| 12    | Thursday  | 8:30-10:30    |
| 13    | Thursday  | 11:00-13:00   |
| 14    | Thursday  | 13:30-15:30   |
| 15    | Thursday  | 16:00-18:00   |
| 16    | Friday    | 8:30-10:30    |
| 17    | Friday    | 11:00-13:00   |
| 18    | Friday    | 13:30-15:30   |
| 19    | Friday    | 16:00-18:00   |


