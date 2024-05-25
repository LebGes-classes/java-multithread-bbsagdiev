import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelReader {

    // Метод для чтения данных о сотрудниках и задачах из Excel файла
    public static List<Employee> readEmployeesAndTasks(String filePath) throws IOException {
        Map<String, Employee> employeeMap = new HashMap<>(); // Создаем карту, чтобы связать сотрудников с их задачами

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) { // Используем XSSFWorkbook для чтения xlsx файлов

            Sheet sheet = workbook.getSheetAt(0); // Получаем первый лист из книги Excel

            for (Row row : sheet) { // Проходим по каждой строке в листе
                if (row.getRowNum() == 0) continue; // Пропускаем строку заголовков

                // Получаем ячейки с данными о задаче, начальной и конечной дате и имени сотрудника
                Cell taskNameCell = row.getCell(1);
                Cell startDateCell = row.getCell(2);
                Cell endDateCell = row.getCell(3);
                Cell employeeNameCell = row.getCell(4);

                if (taskNameCell == null || startDateCell == null || employeeNameCell == null) {
                    continue; // Пропускаем строки с отсутствующими основными данными
                }

                // Извлекаем значения из ячеек
                String taskName = taskNameCell.getStringCellValue();
                Date startDate = startDateCell.getDateCellValue();
                Date endDate = endDateCell != null ? endDateCell.getDateCellValue() : startDate; // Если конечная дата отсутствует, предполагаем, что это задача на один день
                String employeeName = employeeNameCell.getStringCellValue();

                if (startDate == null || endDate == null) {
                    continue; // Пропускаем строки с нулевыми датами
                }

                // Рассчитываем продолжительность задачи в часах
                long durationMillis = endDate.getTime() - startDate.getTime();
                int daysRequired = (int) (durationMillis / (1000 * 60 * 60 * 24)) + 1; // +1 для включения дня конечной даты
                int hoursRequired = daysRequired * 8; // Предполагаем, что сотрудник работает 8 часов в день

                // Создаем объект Task
                Task task = new Task(taskName, hoursRequired);

                // Получаем объект Employee из карты или создаем новый, если он не существует
                Employee employee = employeeMap.computeIfAbsent(employeeName, Employee::new);

                // Добавляем задачу к сотруднику
                employee.addTask(task);
            }
        }

        // Возвращаем список сотрудников и их задач
        return new ArrayList<>(employeeMap.values());
    }
}
