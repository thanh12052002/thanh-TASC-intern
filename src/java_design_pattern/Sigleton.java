package java_design_pattern;

//B1. Create constructor private
//B2. Create variable static --> luu instance tao ra -> tra ve khi method goi new instance sau khi da tao.
//B3. create method static --> tao new instance chi cho lan dau tien
class StudentManager {
    private int count;
    private static volatile StudentManager studentManager;

    //
    private StudentManager() {
        this.count = 1;
    }

    //
    public static StudentManager getInstance() {
        if (studentManager == null)
            synchronized (StudentManager.class) {
                if (studentManager == null) {
                    studentManager = new StudentManager();
                }
            }
        return studentManager;
    }

    //
    public int getCount() {
        return count;
    }

    //
    public int add() {
        return this.count++;
    }
}

//
public class Sigleton {
    public static void main(String args[]) {
        StudentManager s1Manager = StudentManager.getInstance();
        StudentManager s2Manager = StudentManager.getInstance();
        //
        System.out.println("Count s1: " + s1Manager.getCount());
        System.out.println("Count s2: " + s2Manager.getCount());
        s1Manager.add();
        System.out.println("Count s2 sau khi add s1: " + s2Manager.getCount());
        s2Manager.add();
        System.out.println("Count s1 sau khi add s2: " + s1Manager.getCount());
    }
}
