public class Developer extends Employee{
    private String programmingLang;

    public Developer(String name, int id, int salary, String programmingLang){
        super(name,id,salary);
        this.programmingLang = programmingLang;
    }

    @Override
    public void introduce(){
        super.introduce();
        System.out.printf("%-15s\n", programmingLang);
        System.out.println("------------------------------------------------------");
    }

    public String getProgrammingLang() {
        return programmingLang;
    }

    public void setProgrammingLang(String programmingLang) {
        this.programmingLang = programmingLang;
    }

    @Override
    public String toString() {
        return "Developer{" +
                "programmingLang='" + programmingLang + '\'' +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", salary=" + salary +
                '}';
    }
}
