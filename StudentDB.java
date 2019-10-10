//Fraol Dechasa
//StudentDB.java

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class StudentDB {
    //Desc:     Maintains a database of Student records.  The database is the text file Student.txt.
    //Input:    User enters commands from keyboard to manipulate database.
    //    The database Student.txt, which contains student information as follows:
    //    id
    //    name
    //    age
    //    id
    //    name
    //    age
    //    …
    //Output:Various messages.
//    Database Student.txt updated as directed by user.
    public static Scanner keyboard=new Scanner(System.in);
    public static void main(String[] args) throws IOException
    {   Scanner keyboard = new Scanner(System.in);
        Vector<Student> v=new Vector<Student>();
        File f=new File("Student.txt");
        if (f.exists()) loadStudent(v);
        int choice=5;
        do {
            System.out.println("\t1. Add a Student record");
            System.out.println("\t2. Remove a Student record");
            System.out.println("\t3. Print a Student record");
            System.out.println("\t4. Print all Student records");
            System.out.println("\t5. Update a student record");
            System.out.println("\t6. Quit");
            choice= keyboard.nextInt();
            switch (choice)
            {
                case 1: addStudent(v); break;
                case 2: removeStudent(v); break;
                case 3: printStudent(v); break;
                case 4: printAllStudent(v); break;
                case 5: update(v); break;
                default:  break;
            }
        } while (choice!=6);
        storeStudent(v);
    }
    //Input:     Text file Student.txt must exist and contains student records.
    //Post:     All records in Student.txt loaded into vector v.
    public static void loadStudent(Vector<Student> v) throws FileNotFoundException
    {
        System.out.println("Loading database ...");
        Scanner f=new Scanner(new File("Student.txt"));
        while(f.hasNext())
        {
            Student stu = new Student();
            stu.read(f);
            v.add(stu);
        }

        f.close();
    }
    //Input: user enters an integer (id), a string (name), an integer (age) from the
    //keyboard all on separate lines
    //Post: The input record added to v if id does not exist
    //Output: various prompts as well as "Student added" or "Add failed: Student already exists"
    //printed on the screen accordingly
    public static void addStudent(Vector<Student> v)
    {
        Student stu=new Student();
        System.out.print("Student ID:");
        stu.setId(keyboard.nextInt());
        keyboard.nextLine();
        System.out.print("Student name:");
        stu.setName(keyboard.nextLine());
        System.out.print("Student Age:");
        stu.setAge(keyboard.nextInt());
        keyboard.nextLine();
        System.out.print("Student Major: ");
        stu.setMajor(keyboard.nextLine());
        int index = v.indexOf(stu);
        if(index==-1)
        {
            v.add(stu);
            System.out.println("Student Added");
        }
        else System.out.println("Add failed: student already exists");


    }
    //Input:     user enters an integer (id) from the keyboard
    //Post:     The record in v whose id field matches the input removed from v.
    //Output: various prompts as well as "Student removed" or "Remove failed: Student does not
//    exist" printed on the screen accordingly
    public static void removeStudent(Vector<Student> v)
    {
        System.out.print("Student ID:");
        int id= keyboard.nextInt();
        Student stu = new Student(id,"", 0,"");
        if(v.remove(stu)) System.out.println("Student removed");
        else System.out.println("Remove failed: Student does not exist");

    }
    //Input:     user enters an integer (id) from the keyboard
    //Output: various prompts as well as the record in v whose id field matches the input printed on
//    the screen or "Print failed: Student does not exist" printed on the screen accordingly
    public static void printStudent(Vector<Student> v)
    {
        System.out.print("Student ID:");
        int id = keyboard.nextInt();
        Student stu = new Student(id,"",0,"");
        int index = v.indexOf(stu);
        if(index!=-1)
        {
            stu=v.get(index);
            System.out.println(stu);
        }
        else System.out.println("No such Student");


    }
    //Output: All records in v printed on the screen.
    public static void printAllStudent(Vector<Student> v)
    {
        for(int j=0; j<v.size(); ++j)
            System.out.println(v.get(j));

    }
    //Input: user enters an integer (id), a string (name and Major), an integer (age) from the
    //keyboard all on separate lines
    //Post: Updates the previous records in vector by the new input records.

    //Output: various prompts as well as "Finished Update" or "No such student", prompts age, major and name
    //printed on the screen accordingly

    public static void update(Vector<Student>v){
        Scanner keyboard = new Scanner(System.in);
        System.out.print("Student ID: ");
        int id = keyboard.nextInt();
        Student stu = new Student(id,"",0,"");
        int index = v.indexOf(stu);
        if (index!=-1){
            Student st =v.get(index);
            System.out.print("Enter age: ");
            st.setAge(keyboard.nextInt());
            keyboard.nextLine();
            System.out.print("Enter Major: ");
            st.setMajor(keyboard.nextLine());
            System.out.print("Enter Name: ");
            st.setName(keyboard.next());
            System.out.println("Finished update");}
        else System.out.println("No such Student");

    }
    //Output: All records in v written to text file Student.txt.
    public static void storeStudent(Vector<Student> v) throws FileNotFoundException
    {
        System.out.println("Saving database ...");
        PrintWriter f = new PrintWriter("Student.txt");
        for (int j=0; j<v.size(); ++j)
        {    Student stu =v.get(j);
             stu.write(f);

        }

        f.close();
    }


}
