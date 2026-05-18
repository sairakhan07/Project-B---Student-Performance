public class Module //public means that this class can be accessed by any other class
{
    private String moduleCode;
    private String moduleName; //private means that only the class itself can access these variables

    public Module(String moduleCode, String moduleName) 
    {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

    public String getModuleCode() 
    {
        return moduleCode; 
    }
    
    public void setModuleCode(String moduleCode) 
    {
        this.moduleCode = moduleCode; 
    }
    
    public String getModuleName() 
    { 
        return moduleName; 
    }

    public void setModuleName(String moduleName) 
    { 
        this.moduleName = moduleName; 
    }

}

public class Student 
{
    private String studentId;
    private String name;
    private Map<Module, Integer> moduleMarks; // a 'map' storing the modules and their corresponding marks for the student

    public Student(String studentId, String name) //constructor to initialise the studentId, name, and map for module marks
    {
        this.studentId = studentId;
        this.name = name;
        this.moduleMarks = new HashMap<>(); 
    }   

    public String getStudentId() 
    {
        return studentId; 
    }

    public void setStudentId(String studentId) 
    {
        this.studentId = studentId; 
    }

    public String getName() 
    { 
        return name; 
    }

    public void setName(String name) 
    { 
        this.name = name; 
    }

    public Map<Module, Integer> getModuleMarks() 
    {
        return moduleMarks;
    }

    public void setMark(Module module, int mark) 
    {
        if (mark >= 0 && mark <= 100) 
        {
           moduleMarks.put(module, mark);
        } 
        else 
        {
           System.out.println("Invalid mark - must be between 0 and 100.");
        }
    }
}

public class Management
{
    private List<Student> students;
    private List<Module> modules;

    public Management() 
    {
        this.students = new ArrayList<>();
        this.modules = new ArrayList<>();
    }

    public void addStudent(String id, String name) 
    {
        Student newStudent = new Student(id, name);
        students.add(newStudent);
        System.out.println("Student '" + name + "'  has been added.");
    }

    public void addModule(String code, String name) 
    {
        Module newModule = new Module(code, name);
        modules.add(newModule);
        System.out.println("Module '" + name + "' registered successfully.");
    }

    public Student findStudent(String id) 
    {
        for (Student s : students) //s refers to the individual student in the list of students
        {
            if (s.getStudentId().equalsIgnoreCase(id)) //'equalsIgnoreCase' means to ignore letter case
            {
                return s; 
            }
        }
        return null; 
    }

    public Module findModule(String code) 
    {
        for (Module m : modules) //m refers to the individual module in the list of modules
        {
            if (m.getModuleCode().equalsIgnoreCase(code)) 
            {
                return m; 
            }
        }
        return null; 
    }

    public void displayPerformanceReport(String moduleCode) 
    {
        Module targetModule = findModule(moduleCode);
        if (targetModule == null) 
        {
            System.out.println("Error: Module code not found.");
            return;
        }//ensures module exists

        List<Integer> AllMarks = new ArrayList<>();
        
        int countA = 0, countB = 0, countC = 0, countD = 0, countF = 0;//counters for each grade category

        for (Student s : students) //iterate through all students to collect marks for the specific module
        {
            if (s.getModuleMarks().containsKey(targetModule)) //check if the student has a mark for the module
            {
                int mark = s.getModuleMarks().get(targetModule);//retrieve the mark for the module
                AllMarks.add(mark);//add the mark to the list of collected marks

                if (mark >= 70 && mark <= 100) 
                {
                    countA++;
                }
                else if (mark >= 60 && mark <= 69) 
                {
                    countB++;
                }
                else if (mark >= 50 && mark <= 59) 
                {
                    countC++;
                }
                else if (mark >= 40 && mark <= 49) 
                {
                    countD++;
                }
                else 
                {
                    countF++;
                }
                //each counter is incremented based on the mark's grade category
            }
        }

        if (AllMarks.isEmpty()) 
        {
            System.out.println("No marks have been recorded yet for: " + targetModule.getModuleName());
            return;
        }

        int min = AllMarks.get(0);//initialise min and max to the first mark in the list to start comparisons
        int max = AllMarks.get(0);
        double totalSum = 0;

        for (int mark : AllMarks) 
        {
            if (mark < min)//compare each mark to find the minimum
            {
               min = mark; //update min if a lower mark is found
            } 
            if (mark > max) 
            {
                max = mark; 
            }
            totalSum += mark;//accumulate the total sum of marks for mean calculation
        }
        
        double mean = totalSum / AllMarks.size();//calculate the mean by dividing the total sum by the number of marks collected

        // display report
        System.out.println("PERFORMANCE REPORT FOR: " + targetModule.getModuleName());
        System.out.println("------------------------------");
        System.out.printf("Mean Mark:    %.2f%%\n", mean);//"%.2f%%" means to format the mean to 2 decimal places followed by a percentage sign
        System.out.println("Minimum Mark: " + min + "%");
        System.out.println("Maximum Mark: " + max + "%");
        System.out.println("------------------------------");
        System.out.println("GRADE DISTRIBUTION:");
        
        int totalStudents = AllMarks.size();//total number of students who have marks for the module used for percentage calculations
        System.out.printf("Grade A (First Class):        %.1f%%\n", ((double) countA / totalStudents) * 100);//
        System.out.printf("Grade B (Upper Second Class): %.1f%%\n", ((double) countB / totalStudents) * 100);
        System.out.printf("Grade C (Lower Second Class): %.1f%%\n", ((double) countC / totalStudents) * 100);
        System.out.printf("Grade D (Third Class):        %.1f%%\n", ((double) countD / totalStudents) * 100);
        System.out.printf("Grade F (Fail):               %.1f%%\n", ((double) countF / totalStudents) * 100);
        System.out.println("------------------------------");
    }
}

public class Main 
{
    private static Management system = new Management(); //instance of the Management class to manage students and modules
    private static Scanner scanner = new Scanner(System.in); //scanner for reading user input from the terminal

    private static void inputSudent() 
    {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Student Full Name: ");
        String name = scanner.nextLine().trim();

        if (id.isEmpty() || name.isEmpty()) 
        {
            System.out.println("Error: Fields cannot be blank.");
            return;
        }
        system.addStudent(id, name);
    }

    private static void inputModule() 
    {
        System.out.print("Enter Module Code: ");
        String code = scanner.nextLine().trim();
        System.out.print("Enter Module Name: ");
        String title = scanner.nextLine().trim();

        if (code.isEmpty() || title.isEmpty()) 
        {
            System.out.println("Error: Fields cannot be blank.");
            return;
        }
        system.addModule(code, title);
    }

    private static void inputMarks() 
    {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine().trim();
        Student currentStudent = system.findStudent(id);

        if (currentStudent == null) 
        {
            System.out.println("Error: Student record not found.");
            return; 
        }

        System.out.print("Enter Module Code: ");
        String code = scanner.nextLine().trim();
        Module currentModule = system.findModule(code);

        if (currentModule == null) 
        {
            System.out.println("Error: Module code not found.");
            return;
        }

        System.out.print("Enter Mark (0-100): ");
        String markInput = scanner.nextLine().trim();
        
        try 
        {
            int numMark = Integer.parseInt(markInput);//converts the string input for the mark into an integer
            currentStudent.setMark(currentModule, numMark);//calls the 'setMark' method of the Student class to update the mark for the specified module
        } 
        catch (NumberFormatException e)//handles the case where the user input for the mark is not a valid integer
        {
            System.out.println("Error: Invalid entry. Mark must be a numeric whole number.");
        }
    }

    private static void moduleReport() 
    {
        System.out.print("Enter Module Code: ");
        String code = scanner.nextLine().trim();
        system.displayPerformanceReport(code);//
    }

    public static void main(String[] args) 
    {
        boolean run = true;
        System.out.println("Welcome to the Bolton University Student Performance App");

        while (run) 
        {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Add a new student");
            System.out.println("2. Register a new module");
            System.out.println("3. Input/Update student marks");
            System.out.println("4. Generate module performance analytics report");
            System.out.println("5. Exit system");
            System.out.print("Select an option (1-5): ");

            String choice = scanner.nextLine().trim();//read the user's menu choice and 'trim' any extra whitespace

            switch (choice) 
            {
                case "1"://if the user selects option 1, call the method to handle creating a new student
                    inputSudent();//call the method to handle creating a new student
                    break;
                case "2"://if the user selects option 2, call the method to handle registering a new module
                    inputModule();
                    break;
                case "3"://if the user selects option 3, call the method to handle inputting/updating student marks
                    inputMarks();
                    break;
                case "4"://if the user selects option 4, call the method to handle generating the module performance report
                    moduleReport();
                    break;
                case "5"://if the user selects option 5, print a message and set 'run' to false to exit the loop and end the application
                    System.out.println("Exiting application...");
                    run = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 5.");
            }
        }
    }
}
