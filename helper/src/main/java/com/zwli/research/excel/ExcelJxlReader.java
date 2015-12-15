package com.zwli.research.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


//TODO: not finished
public class ExcelJxlReader {

    private static ExcelJxlReader instance;

    public static ExcelJxlReader getInstance() {
        if (instance == null) {
            synchronized (ExcelJxlReader.class) {
                if (instance == null) {
                    instance = new ExcelJxlReader();
                }
            }
        }
        return instance;
    }

    private File file;

    private ExcelJxlReader() {
    }

    public List<Map<String, Object>> read(int sheetNo, boolean hasTitle) throws Exception {
        if (file == null) {
            throw new NullPointerException("Please provide an excel file before operation.");
        }
        List<Map<String, Object>> dataModels = new ArrayList<Map<String, Object>>();
        Workbook workbook = Workbook.getWorkbook(file);
        try {
            Sheet sheet = workbook.getSheet(sheetNo);
            int start = hasTitle ? 1 : 0;
            for (int i = start; i < sheet.getRows() - 1; i++) {
                Map<String, Object> target = new HashMap<String, Object>();
                Cell[] cells = sheet.getRow(i);
                if (cells == null) {
                    continue;
                }
                System.out.println(i + "\t=> " + cells[0].getContents() + ", " + cells[13].getContents() + ", "
                        + cells[14].getContents());
                dataModels.add(target);
            }
        } finally {
            if (workbook != null) {
                workbook.close();
            }
        }
        return dataModels;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public static void main(String[] args) {
        String fn = "D:/_work/Akzo Noble/copy.xls";
        getInstance().setFile(new File(fn));
        int sheetNo = 1;
        boolean hasTitle = true;
        try {
            getInstance().read(sheetNo, hasTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
