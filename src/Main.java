import java.util.*;
import java.io.*;
import java.nio.file.*;
class MainMenu
{
    public void menu()
    {
        System.out.println("        *******************************************");
        System.out.println("                 EMPLOYEE MANAGEMENT SYSTEM");
        System.out.println("        *******************************************");
        System.out.println("                     ~By Madiyar Tolen");
        System.out.println("\n\nPress 1 : Add an Employee Details");
        System.out.println("Press 2 : See an Employee Details ");
        System.out.println("Press 3 : Remove an Employee");
        System.out.println("Press 4 : Update Employee Details");
        System.out.println("Press 5 : Exit the EMS Portal");

    }
}
class CodeExit
{
    public void out()
    {
        System.out.println("\n*****************************************");
        System.out.println(" Thank You For Sharing your details :) ");
        System.out.println("*****************************************");
        System.exit(0);
    }
}
class Employee_Add
{
    public void createFile()
    {
        Scanner sc = new Scanner(System.in);

        EmployDetail employee = new EmployDetail();
        employee.getInfo();
        try{
            File f1 = new File("file"+employee.id+".txt");
            if(f1.createNewFile()){
                FileWriter myWriter = new FileWriter("file"+employee.id+".txt");
                myWriter.write("Employee ID : " + employee.id + "\nEmployee Name : " + employee.name +
                        "\nAge : " + employee.Age  + "\nEmployee Contact : " + employee.contact + "\nEmail Information :"
                        + employee.email + "\nEmployee position : " + employee.position + "\nEmployee Salary :" + employee.salary);
                myWriter.close();
                System.out.println("\nEmployee has been Added :)");

                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            }
            else {
                System.out.println("\nEmployee already exists :(");
                System.out.print("\nPress Enter to Continue...");
                sc.nextLine();
            }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
}


class EmployDetail
{
    String name;
    int Age;
    String email;
    String position;
    int id;
    float salary;
    long contact;
    public void getInfo()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Employee's name --------: ");
        name = sc.nextLine();
        System.out.print("Enter Employee's Age -: ");
        Age = sc.nextInt();
        System.out.print("Enter Employee's ID ----------: ");
        id = sc.nextInt();
        System.out.print("Enter Employee's Email ID ----: ");
        email=sc.next();
        System.out.print("Enter Employee's Position ----: ");
        position = sc.next();
        System.out.print("Enter Employee contact Info --: ");
        contact = sc.nextLong();
        System.out.print("Enter Employee's Salary ------: ");
        salary = sc.nextFloat();
    }
}
class Employee_Show
{
    public void viewFile(String s) throws Exception
    {
        File file = new File("file"+ s + ".txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
        {
            System.out.println(sc.nextLine());
        }
        sc.close();
    }
}
interface Remove{
    public void removeFile(int ID) throws IOException ;
}
class Employee_Remove implements Remove{
    @Override
    public void removeFile(int ID){
        File emp = new File("file" + ID + ".txt");
        if (emp.delete()) {
            System.out.println("\nEmployee has been removed Successfully");
        } else {
            System.out.println("\nEmployee does not exists :( ");
        }
    }

}

class Employee_Update {
    public void updateFile(String s, String o, String n) throws IOException
    {

        File FileEdit = new File("file" + s + ".txt");

        String oldline = "";

        BufferedReader reader = null;

        FileWriter writer = null;

        try {
            reader = new BufferedReader(new FileReader(FileEdit));
            String line = reader.readLine();
            while (line != null)
            {
                oldline = oldline + line + System.lineSeparator();//

                line = reader.readLine();
            }
            String newline = oldline.replaceAll(o, n);
            writer = new FileWriter(FileEdit);

            writer.write(newline);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}

class EmployManagementSystem
{

    public static void main(String arv[]) throws Exception {
        System.out.print("\033[H\033[2J");

        Scanner sc = new Scanner(System.in);
        Employee_Show epv =new Employee_Show();

        int choice = 0;

        MainMenu obj1 = new MainMenu();
        obj1.menu();
        while(choice <= 5)
        {

            System.out.print("\nPlease Enter choice :");
            choice = sc.nextInt();
            switch(choice)
            {
                case 1:
                {
                    Employee_Add ep = new Employee_Add();
                    ep.createFile();

                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }
                case 2:
                {
                    System.out.print("\nPlease Enter Employee's ID :");
                    String s = sc.next();
                    try
                    {
                        epv.viewFile(s);
                    }
                    catch(Exception e){
                        System.out.println(e);
                    }

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }

                case 3:
                {
                    System.out.print("\nPlease Enter Employee's ID :");
                    int ID = sc.nextInt();
                    Employee_Remove epr = new Employee_Remove();
                    epr.removeFile(ID);

                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }
                case 4:
                {
                    System.out.print("\nPlease Enter Employee's ID :");
                    String  ID = sc.next();
                    try {
                        epv.viewFile(ID);
                    }
                    catch(Exception e) {
                        System.out.println(e);
                    }
                    System.out.print("\nPlease Enter the detail you want to Update :");
                    System.out.print("\nFor Example :\n");
                    System.out.println("If you want to Change the Name, then Enter Current Name and Press Enter. Then write the new Name then Press Enter. It will Update the Name.\n");
                    String s = sc.next();
                    System.out.print("Please Enter the Updated Info :");
                    String n = sc.next();
                    Employee_Update epu = new Employee_Update();
                    epu.updateFile(ID, s, n);
                    if(ID.equals(s)){
                        File file = new File("file" + ID + ".txt");
                        file.renameTo(new File("file" + n + ".txt"));
                    }
                    System.out.print("\nPress Enter to Continue...");
                    sc.nextLine();
                    sc.nextLine();
                    System.out.print("\033[H\033[2J");
                    obj1.menu();
                    break;
                }
                case 5:
                {
                    CodeExit obj = new CodeExit();
                    obj.out();
                }
            }
        }
    }


}
