import java.util.ArrayList;
import java.util.List;

public class Employee {
    // Поля класса
    private String name; // Имя сотрудника
    private int totalWorkedHours; // Общее количество отработанных часов
    private int idleHours; // Количество простаиваемых часов
    private List<Task> tasks; // Список задач, назначенных сотруднику

    // Конструктор класса
    public Employee(String name) {
        this.name = name;
        this.totalWorkedHours = 0;
        this.idleHours = 0;
        this.tasks = new ArrayList<>(); // Инициализация списка задач
    }

    // Методы доступа к полям класса
    public String getName() {
        return name;
    }

    public int getTotalWorkedHours() {
        return totalWorkedHours;
    }

    public int getIdleHours() {
        return idleHours;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    // Метод для добавления задачи сотруднику
    public void addTask(Task task) {
        tasks.add(task);
    }

    // Методы для увеличения общего количества отработанных и простаиваемых часов
    public void addWorkedHours(int hours) {
        this.totalWorkedHours += hours;
    }

    public void addIdleHours(int hours) {
        this.idleHours += hours;
    }
}
