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
}
