import java.util.List;

public class Statistics {

    // Метод для вывода статистики о сотрудниках в консоль
    public static void printStatistics(List<Employee> employees) {
        // Проходим по каждому сотруднику в списке
        for (Employee employee : employees) {
            System.out.println("Employee: " + employee.getName()); // Выводим имя сотрудника
            System.out.println("Total Worked Hours: " + employee.getTotalWorkedHours()); // Выводим общее количество отработанных часов
            System.out.println("Idle Hours: " + employee.getIdleHours()); // Выводим количество простаиваемых часов
            System.out.println("Tasks:"); // Выводим список задач сотрудника
            for (Task task : employee.getTasks()) {
                System.out.println("  - " + task.getName() + ": " + task.getHoursRequired() + " hours"); // Выводим каждую задачу с количеством часов, необходимых для ее выполнения
            }
            System.out.println(); // Добавляем пустую строку между информацией о каждом сотруднике
        }
    }
}
