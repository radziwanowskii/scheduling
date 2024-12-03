package org.scheduling;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Read_Tutor_Data
{
    ArrayList<Tutor> tutorList = new ArrayList<>();

    public Read_Tutor_Data(String filename) {
        try {

            int first_row = 0;
            FileInputStream file = new FileInputStream(filename);
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);

            //Iterate through each row one by one
            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext())
            {
                Row row = rowIterator.next();
                String name = " ";

                int maxTutorials = 10;
                int[] time = {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1};

                //exclude the first row with the column names
                if(first_row !=0) {
                    for (int j = 0; j <= 11; j++) {
                        //do it to get the null columns as well, when someone has the whole day free
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

                        switch (j) {
                            case 1:
                                name = cell.getStringCellValue();
                                break;

                            //read the max amount of tutorials in
                            case 5:
                                maxTutorials = (int) cell.getNumericCellValue();
                                break;
                            //in case of no busy times, excel field is empty and constraints are not applied (empty != string)
                            case 7:
                                if (cell.getCellType() == CellType.STRING) {
                                    String[] slots = cell.getStringCellValue().split(", ");

                                    for (int k = 0; k < slots.length; k++) {
                                        switch (slots[k]) {
                                            case "8:30-10:30":
                                                time[0] = 0;
                                                break;
                                            case "11:00-13:00":
                                                time[1] = 0;
                                                break;
                                            case "13:30-15:30":
                                                time[2] = 0;
                                                break;
                                            case "16:00-18:00":
                                                time[3] = 0;
                                                break;
                                        }
                                    }

                                }
                                break;

                            case 8:
                                if (cell.getCellType() == CellType.STRING) {
                                    String[] slots = cell.getStringCellValue().split(", ");
                                    for (int k = 0; k < slots.length; k++) {
                                        switch (slots[k]) {
                                            case "8:30-10:30":
                                                time[4] = 0;
                                                break;
                                            case "11:00-13:00":
                                                time[5] = 0;
                                                break;
                                            case "13:30-15:30":
                                                time[6] = 0;
                                                break;
                                            case "16:00-18:00":
                                                time[7] = 0;
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 9:
                                if (cell.getCellType() == CellType.STRING) {
                                    String[] slots = cell.getStringCellValue().split(", ");
                                    for (int k = 0; k < slots.length; k++) {
                                        switch (slots[k]) {
                                            case "8:30-10:30":
                                                time[8] = 0;
                                                break;
                                            case "11:00-13:00":
                                                time[9] = 0;
                                                break;
                                            case "13:30-15:30":
                                                time[10] = 0;
                                                break;
                                            case "16:00-18:00":
                                                time[11] = 0;
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 10:
                                if (cell.getCellType() == CellType.STRING) {
                                    String[] slots = cell.getStringCellValue().split(", ");
                                    for (int k = 0; k < slots.length; k++) {
                                        switch (slots[k]) {
                                            case "8:30-10:30":
                                                time[12] = 0;
                                                break;
                                            case "11:00-13:00":
                                                time[13] = 0;
                                                break;
                                            case "13:30-15:30":
                                                time[14] = 0;
                                                break;
                                            case "16:00-18:00":
                                                time[15] = 0;
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 11:
                                if (cell.getCellType() == CellType.STRING) {
                                    String[] slots = cell.getStringCellValue().split(", ");
                                    for (int k = 0; k < slots.length; k++) {
                                        switch (slots[k]) {
                                            case "8:30-10:30":
                                                time[16] = 0;
                                                break;
                                            case "11:00-13:00":
                                                time[17] = 0;
                                                break;
                                            case "13:30-15:30":
                                                time[18] = 0;
                                                break;
                                            case "16:00-18:00":
                                                time[19] = 0;
                                                break;
                                        }
                                    }
                                }
                                break;
                        }
                    }

                    Tutor tutor = new Tutor(name, maxTutorials, time);

                    tutorList.add(tutor);
                }
                first_row++;
            }
            file.close();
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public ArrayList<Tutor> returnList(){
        return tutorList;
    }

}
