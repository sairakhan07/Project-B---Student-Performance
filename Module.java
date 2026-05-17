public class Module //public means that this class can be accessed by any other class
{
    private String moduleCode;
    private String moduleName; //private means that only the class itself can access these variables

    public Module(String moduleCode, String moduleName) 
    {
        this.moduleCode = moduleCode;
        this.moduleName = moduleName;
    }

}

public class Student 
{
    private String studentId;
    private String name;

    public Student(String studentId, String name) 
    {
        this.studentId = studentId;
        this.name = name;
    }   
}
