import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TaskProcessor {

    // Максимальное количество часов в день, которые может работать сотрудник
    private static final int MAX_HOURS_PER_DAY = 8;

    // Метод для обработки задач сотрудников
    public void processTasks(List<Employee> employees, List<Task> tasks) {
        // Создаем пул потоков для выполнения задач
        ExecutorService executorService = Executors.newFixedThreadPool(employees.size());

        // Проходим по списку сотрудников
        for (Employee employee : employees) {
            // Запускаем выполнение задач для каждого сотрудника в отдельном потоке
            executorService.execute(() -> {
                int hoursWorkedToday = 0; // Количество отработанных часов сегодня

                // Проходим по списку задач сотрудника
                for (Task task : employee.getTasks()) {
                    int taskHours = task.getHoursRequired(); // Получаем количество часов, необходимых для выполнения задачи
                    // Пока остаются часы для работы над задачей и сотрудник может работать еще сегодня
                    while (taskHours > 0 && hoursWorkedToday < MAX_HOURS_PER_DAY) {
                        int hoursToWork = Math.min(taskHours, MAX_HOURS_PER_DAY - hoursWorkedToday); // Определяем сколько часов можно потратить на задачу сегодня
                        employee.addWorkedHours(hoursToWork); // Увеличиваем общее количество отработанных часов сотрудника
                        hoursWorkedToday += hoursToWork; // Обновляем количество отработанных часов сегодня
                        taskHours -= hoursToWork; // Уменьшаем оставшееся количество часов для выполнения задачи

                        // Если сотрудник отработал максимальное количество часов в день, завершаем цикл
                        if (hoursWorkedToday == MAX_HOURS_PER_DAY) {
                            break;
                        }
                    }
                }

                // Если сотрудник не отработал максимальное количество часов в день, добавляем простаиваемые часы
                if (hoursWorkedToday < MAX_HOURS_PER_DAY) {
                    employee.addIdleHours(MAX_HOURS_PER_DAY - hoursWorkedToday);
                }
            });
        }

        executorService.shutdown(); // Завершаем работу пула потоков после выполнения всех задач
        while (!executorService.isTerminated()) {
            // Ждем, пока все потоки завершат свою работу
        }
    }
}
