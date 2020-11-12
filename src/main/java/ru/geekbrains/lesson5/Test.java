package ru.geekbrains.lesson5;

public class Test {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5];
        employees[0] = new Employee("Egor1", "director", "123@mail.ru", "88005553535", 300, 18);
        employees[1] = new Employee("Egor2", "director", "123@mail.ru", "88005553535", 300, 50);
        employees[2] = new Employee("Egor3", "director", "123@mail.ru", "88005553535", 300, 18);
        employees[3] = new Employee("Egor4", "director", "123@mail.ru", "88005553535", 300, 50);
        employees[4] = new Employee("Egor5", "director", "123@mail.ru", "88005553535", 300, 44);

        for (Employee employee : employees) {
            if (employee.getAge() >= 40) {
                employee.print();
            }
        }
    }
}
