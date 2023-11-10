# School Management System

This School Management System is a command-line application designed to manage students, teachers, and courses with various functionalities.

### How the Application Works

1. Provide a name for the school.
2. Specify the number of teachers to be created and enter details for each.
3. Specify the number of courses to be created (without specifying the teacher yet) and enter details for each.
4. Specify the number of students to be created (without specifying the course yet) and enter details for each.
5. Execute commands to perform various actions in the system.

### Commands

- `ENROLL [STUDENT_ID] [COURSE_ID]`: Enroll the student in the corresponding course, updating the money earned by that course.
- `ASSIGN [TEACHER_ID] [COURSE_ID]`: Assign the teacher to the corresponding course.
- `SHOW COURSES`: Display a list of all courses.
- `LOOKUP COURSE [COURSE_ID]`: Display the full details of the specified course.
- `SHOW STUDENTS`: Display a list of all students.
- `LOOKUP STUDENT [STUDENT_ID]`: Display the full details of the specified student.
- `SHOW TEACHERS`: Display a list of all teachers.
- `LOOKUP TEACHER [TEACHER_ID]`: Display the full details of the specified teacher.
- `SHOW PROFIT`: Calculate and display the total profit.
