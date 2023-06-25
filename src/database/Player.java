package database;

public class Player {
    private String Name;
    private String Country;
    private int Age;
    private double Height;
    private String Club;
    private String Position;
    private int Number;
    private double Weekly_Salary;

    public Player(){

    }
    public Player(String Name,String Country,int Age,double Height,String Club,String Position,int Number,double WeeklySalary){
        this.Name=Name;
        this.Country=Country;
        this.Age=Age;
        this.Height=Height;
        this.Club=Club;
        this.Position=Position;
        this.Number=Number;
        this.Weekly_Salary=WeeklySalary;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getName() {
        return Name;
    }

    public void setAge(int age) {
        Age = age;
    }

    public int getAge() {
        return Age;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getCountry() {
        return Country;
    }

    public void setClub(String club) {
        Club = club;
    }

    public String getClub() {
        return Club;
    }

    public void setHeight(double height) {
        Height = height;
    }

    public double getHeight() {
        return Height;
    }

    public void setPosition(String position) {
        Position = position;
    }

    public String getPosition() {
        return Position;
    }

    public void setNumber(int number) {
        Number = number;
    }

    public int getNumber() {
        return Number;
    }

    public void setWeekly_Salary(double weekly_Salary) {
        Weekly_Salary = weekly_Salary;
    }

    public double getWeekly_Salary() {
        return Weekly_Salary;
    }

}
