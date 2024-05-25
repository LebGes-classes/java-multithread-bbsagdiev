import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // Путь к входному файлу Excel
        String inputFilePath = "/Users/bulatsagdiev/Downloads/RoadMap.xlsx";
        // Путь к выходному файлу Excel
        String outputFilePath = "/Users/bulatsagdiev/Downloads/Output.xlsx";

        try {
            // Чтение данных о сотрудниках и задачах из входного файла Excel
            List<Employee> employees = ExcelReader.readEmployeesAndTasks(inputFilePath);

            // Создание экземпляра TaskProcessor и обработка задач сотрудников
            TaskProcessor taskProcessor = new TaskProcessor();
            taskProcessor.processTasks(employees, new ArrayList<>()); // Задачи теперь являются частью объектов Employee

            // Запись данных о сотрудниках в выходной файл Excel
            ExcelWriter.writeEmployeeData(outputFilePath, employees);

            // Вывод статистики о сотрудниках в консоль
            Statistics.printStatistics(employees);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
