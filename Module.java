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
  

    public Student(String studentId, String name) 
    {
        this.studentId = studentId;
        this.name = name;
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
}
