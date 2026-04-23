package bean;

public class Subject {
    private String cd; // 科目コード
    private String name; // 科目名
    private School School; // 学校名

    public String getCd() { return cd; }
    public void setCd(String cd) { this.cd = cd; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public School getSchool() { return School; }
    public void setSchool(School school) { School = school; }
}
