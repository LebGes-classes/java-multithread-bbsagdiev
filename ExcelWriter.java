import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter {

    // Метод для записи данных о сотрудниках в файл Excel
    public static void writeEmployeeData(String filePath, List<Employee> employees) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) { // Создаем новую книгу Excel
            Sheet sheet = workbook.createSheet("Employees"); // Создаем лист с названием "Employees"

            // Создаем заголовок для столбцов
            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("Employee Name");
            header.createCell(1).setCellValue("Total Worked Hours");
            header.createCell(2).setCellValue("Idle Hours");

            int rowIndex = 1; // Индекс строки, начиная с которой будут записываться данные о сотрудниках
            for (Employee employee : employees) { // Проходим по каждому сотруднику в списке
                Row row = sheet.createRow(rowIndex++);
                row.createCell(0).setCellValue(employee.getName()); // Записываем имя сотрудника в первый столбец
                row.createCell(1).setCellValue(employee.getTotalWorkedHours()); // Записываем общее количество отработанных часов во второй столбец
                row.createCell(2).setCellValue(employee.getIdleHours()); // Записываем количество простаиваемых часов в третий столбец
            }

            // Записываем данные из книги Excel в файл
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        }
    }
}
