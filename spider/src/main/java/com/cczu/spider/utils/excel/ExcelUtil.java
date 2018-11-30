package com.cczu.spider.utils.excel;

import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//import org.apache.poi.hssf.util.Region;

/**
 * @author wujiantao
 * @date 2017/6/2.
 */
public class ExcelUtil {
    public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
    public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
    public static final String EMPTY = "";
    public static final String POINT = ".";
    public static SimpleDateFormat sdf =   new SimpleDateFormat("yyyy/MM/dd");

    /**
     * 获得path的后缀名
     * @param path
     * @return
     */
    public static String getPostfix(String path){
        if(path==null || EMPTY.equals(path.trim())){
            return EMPTY;
        }
        if(path.contains(POINT)){
            return path.substring(path.lastIndexOf(POINT)+1,path.length());
        }
        return EMPTY;
    }

    /**
     * 单元格格式
     * @param hssfCell
     * @return
     */
    @SuppressWarnings({ "static-access", "deprecation" })
    public static String getHValue(HSSFCell hssfCell){
        if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(hssfCell.getBooleanCellValue());
        } else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if(HSSFDateUtil.isCellDateFormatted(hssfCell)){
                Date date = HSSFDateUtil.getJavaDate(hssfCell.getNumericCellValue());
                cellValue = sdf.format(date);
            }else{
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(hssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
                if(strArr.equals("00")){
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            return String.valueOf(hssfCell.getStringCellValue());
        }
    }
    /**
     * 单元格格式
     * @param xssfCell
     * @return
     */
    public static String getXValue(XSSFCell xssfCell){
        if (xssfCell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            String cellValue = "";
            if(XSSFDateUtil.isCellDateFormatted(xssfCell)){
                Date date = XSSFDateUtil.getJavaDate(xssfCell.getNumericCellValue());
                cellValue = sdf.format(date);
            }else{
                DecimalFormat df = new DecimalFormat("#.##");
                cellValue = df.format(xssfCell.getNumericCellValue());
                String strArr = cellValue.substring(cellValue.lastIndexOf(POINT)+1,cellValue.length());
                if(strArr.equals("00")){
                    cellValue = cellValue.substring(0, cellValue.lastIndexOf(POINT));
                }
            }
            return cellValue;
        } else {
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }

    /**
     * 导出Excel的方法
     * @param title excel中的sheet名称
     * @param headers 表头
     * @param col_cellList 结果集
     * @param out 输出流
     * @param pattern 时间格式
     * @throws Exception
     */
    public static void exportExcel(String sheetName, String title, String[] headers, String[] columns, List<HashMap<String,String>> col_cellList, OutputStream out, String pattern, List<Integer> textColumns) throws Exception{
        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short)20);
        // 生成一个样式
//            HSSFCellStyle style = workbook.createCellStyle();
        HSSFCellStyle columnTopStyle = getColumnTopStyle(workbook);//获取列头样式对象
        HSSFCellStyle style = getStyle(workbook);					//单元格样式对象
        // 设置这些样式
        // style.setFillForegroundColor(HSSFColor.GOLD.index);
        // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        // HSSFFont font = workbook.createFont();
        // font.setColor(HSSFColor.VIOLET.index);
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        // style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        // style.setWrapText(true);
        // 声明一个画图的顶级管理器
        HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 产生表格标题行
        // 表头的样式
        HSSFCellStyle titleStyle = workbook.createCellStyle();// 创建样式对象
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        // 设置字体
        HSSFFont titleFont = workbook.createFont(); // 创建字体对象
        titleFont.setFontHeightInPoints((short) 15); // 设置字体大小
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
        titleFont.setFontName("黑体"); // 设置为黑体字
        titleStyle.setFont(titleFont);

        int firstRow = 0;
        if (title != null)
        {
            sheet.addMergedRegion(new Region(0,(short)0,0,(short)(headers.length-1)));//指定合并区域
            HSSFRow rowHeader = sheet.createRow(0);
            HSSFCell cellHeader = rowHeader.createCell((short)0);   //只能往第一格子写数据，然后应用样式，就可以水平垂直居中
            HSSFRichTextString textHeader = new HSSFRichTextString(title);
            cellHeader.setCellStyle(columnTopStyle);
            cellHeader.setCellValue(textHeader);

            firstRow = 1;
        }
        else
        {
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            HSSFFont font = workbook.createFont(); // 创建字体对象
            font.setFontHeightInPoints((short) 11); // 设置字体大小
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
            font.setFontName("黑体"); // 设置为黑体字
            style.setFont(font);
        }
        HSSFRow row = sheet.createRow(firstRow);
        for (int i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell((short)i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //便利数据集合
        if(col_cellList==null)
            throw new RuntimeException("没数据到各pia");
        for(int i=1;i<col_cellList.size()+1;i++){
            row=sheet.createRow(i+firstRow);
            for(int j=0;j<columns.length;j++){
                HashMap<String,String> col_cell=col_cellList.get(i-1);
                String cellValue=col_cell.get(columns[j]);
                HSSFCell cell = row.createCell(j);
                if(cellValue!= null){
                    if (textColumns != null && textColumns.contains(j))
                    {
                        HSSFRichTextString richString = new HSSFRichTextString(cellValue);
                        cell.setCellValue(richString);
                        cell.setCellType(Cell.CELL_TYPE_STRING);
                    }
                    else
                    {
                        Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                        Matcher matcher = p.matcher(cellValue);
                        if(matcher.matches()){
                            //是数字当作double处理
                            cell.setCellValue(Double.parseDouble(cellValue));
                        }else{
                            HSSFRichTextString richString = new HSSFRichTextString(cellValue);
                            cell.setCellValue(richString);
                        }
                    }
                }
            }
        }
        workbook.write(out);
    }

    /**
     * 列头单元格样式
     */
    public static HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /**
     * 列数据信息单元格样式
     */
    public static HSSFCellStyle getStyle(HSSFWorkbook workbook) {
        // 设置字体
        HSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        HSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    /**
     * 根据excel单元格类型获取excel单元格值
     * @param cell
     * @return
     */
    public static String getCellValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case XSSFCell.CELL_TYPE_NUMERIC:
                {
                    short format = cell.getCellStyle().getDataFormat();
                    if(format == 14 || format == 31 || format == 57 || format == 58)
                    { 	//excel中的时间格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellvalue = sdf.format(date);
                    }
                    else if(format == 22 || format == 176)
                    { 	//excel中的时间格式
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellvalue = sdf.format(date);
                    }
                    else
                    {
                        // 如果是纯数字
                        // 取得当前Cell的数值
                        cellvalue = NumberToTextConverter.toText(cell.getNumericCellValue());
                    }
                    break;
                }
                // 如果当前Cell的Type为STRIN
                case XSSFCell.CELL_TYPE_STRING:
                    // 取得当前的Cell字符串
                    cellvalue = cell.getStringCellValue().replaceAll("'", "'");
                    break;
                case  XSSFCell.CELL_TYPE_BLANK:
                    cellvalue = null;
                    break;
                // 默认的Cell值
                default:{
                    cellvalue = " ";
                }
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 根据excel单元格类型获取excel单元格值
     * @param cell
     * @return
     */
    public static String getDateCellValue(Cell cell) {
        String cellvalue = "";
        if (cell != null) {
            // 判断当前Cell的Type
            switch (cell.getCellType()) {
                // 如果当前Cell的Type为NUMERIC
                case XSSFCell.CELL_TYPE_NUMERIC:
                {
                    SimpleDateFormat sdf = null;
                    short format = cell.getCellStyle().getDataFormat();
                    if(format == 14 || format == 31 || format == 57 || format == 58)
                    {
                        sdf = new SimpleDateFormat("yyyy-MM-dd");
                    }
                    else if(format == 22 || format == 176)
                    {
                        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    }

                    if (sdf != null)
                    {
                        double value = cell.getNumericCellValue();
                        Date date = DateUtil.getJavaDate(value);
                        cellvalue = sdf.format(date);
                    }
                    break;
                }
                default:{
                    cellvalue = "";
                }
            }
        } else {
            cellvalue = "";
        }
        return cellvalue;
    }

    /**
     * 导出Excel的方法
     * @param title excel中的sheet名称
     * @param headers 表头
     * @param col_cellList 结果集
     * @param out 输出流
     * @param pattern 时间格式
     * @throws Exception
     */
    public static void exportXExcel(String sheetName, String title, String[] headers, String[] columns, List<HashMap<String,String>> col_cellList, OutputStream out, String pattern) throws Exception{
        // 声明一个工作薄
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 生成一个表格
        XSSFSheet sheet = workbook.createSheet(sheetName);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth((short)20);
        // 生成一个样式
//            HSSFCellStyle style = workbook.createCellStyle();
        XSSFCellStyle columnTopStyle = getXColumnTopStyle(workbook);//获取列头样式对象
        XSSFCellStyle style = getXStyle(workbook);					//单元格样式对象
        // 设置这些样式
        // style.setFillForegroundColor(HSSFColor.GOLD.index);
        // style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        // style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        // style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        // style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        // style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        // style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        // HSSFFont font = workbook.createFont();
        // font.setColor(HSSFColor.VIOLET.index);
        // font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        // style.setFont(font);
        // 指定当单元格内容显示不下时自动换行
        // style.setWrapText(true);
        // 声明一个画图的顶级管理器
//        XSSFPatriarch patriarch = sheet.createDrawingPatriarch();
        // 产生表格标题行
        // 表头的样式
        XSSFCellStyle titleStyle = workbook.createCellStyle();// 创建样式对象
        titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER_SELECTION);// 水平居中
        titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直居中
        // 设置字体
        XSSFFont titleFont = workbook.createFont(); // 创建字体对象
        titleFont.setFontHeightInPoints((short) 15); // 设置字体大小
        titleFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
        titleFont.setFontName("黑体"); // 设置为黑体字
        titleStyle.setFont(titleFont);

        int firstRow = 0;
        if (title != null)
        {
            sheet.addMergedRegion(new CellRangeAddress(0,(short)0,0,(short)(headers.length-1)));//指定合并区域
            XSSFRow rowHeader = sheet.createRow(0);
            XSSFCell cellHeader = rowHeader.createCell((short)0);   //只能往第一格子写数据，然后应用样式，就可以水平垂直居中
            HSSFRichTextString textHeader = new HSSFRichTextString(title);
            cellHeader.setCellStyle(columnTopStyle);
            cellHeader.setCellValue(textHeader);

            firstRow = 1;
        }
        else
        {
            style.setAlignment(HSSFCellStyle.ALIGN_LEFT);
            XSSFFont font = workbook.createFont(); // 创建字体对象
            font.setFontHeightInPoints((short) 11); // 设置字体大小
            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 设置粗体
            font.setFontName("黑体"); // 设置为黑体字
            style.setFont(font);
        }
        XSSFRow row = sheet.createRow(firstRow);
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = row.createCell((short)i);
            cell.setCellStyle(style);
            XSSFRichTextString text = new XSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        //便利数据集合
        if(col_cellList==null)
            throw new RuntimeException("没数据到各pia");
        for(int i=1;i<col_cellList.size()+1;i++){
            row=sheet.createRow(i+firstRow);
            for(int j=0;j<columns.length;j++){
                HashMap<String,String> col_cell=col_cellList.get(i-1);
                String cellValue=col_cell.get(columns[j]);
                XSSFCell cell = row.createCell(j);
                if(cellValue!= null){
                    Pattern p = Pattern.compile("^//d+(//.//d+)?$");
                    Matcher matcher = p.matcher(cellValue);
                    if(matcher.matches()){
                        //是数字当作double处理
                        cell.setCellValue(Double.parseDouble(cellValue));
                    }else{
                        XSSFRichTextString richString = new XSSFRichTextString(cellValue);
                        cell.setCellValue(richString);
                    }
                }
            }
        }
        workbook.write(out);
    }


    /**
     * 按照模版导出
     *
     * @param tempPath
     * @param destFilePath
     * @param col_cellList
     * @param response
     */
    public static void exportXExcel(String tempPath, String destFilePath,List<HashMap<String,String>> col_cellList,String fname,HttpServletResponse response){
        XLSTransformer former = new XLSTransformer();
        Map<String, List<HashMap<String,String>>> beanParams = new HashMap<>();
        beanParams.put("cellList",col_cellList);
        try {
            former.transformXLS(tempPath, beanParams, destFilePath);
            try {
                byte[] buffer = getBytes(destFilePath);
                response.setCharacterEncoding("UTF-8");//设置相应内容的编码格式
                fname = java.net.URLEncoder.encode(fname,"UTF-8");
                response.setHeader("Content-Disposition","attachment;filename=" + new String(fname.getBytes("UTF-8"),"GBK") + ".xlsx");
                response.setContentType("application/msexcel");//定义输出类型
                OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
                toClient.write(buffer);
                toClient.flush();
                toClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
    }

    /**
     * 列头单元格样式
     */
    public static XSSFCellStyle getXColumnTopStyle(XSSFWorkbook workbook) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体大小
        font.setFontHeightInPoints((short)11);
        //字体加粗
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;

    }

    /**
     * 列数据信息单元格样式
     */
    public static XSSFCellStyle getXStyle(XSSFWorkbook workbook) {
        // 设置字体
        XSSFFont font = workbook.createFont();
        //设置字体大小
        //font.setFontHeightInPoints((short)10);
        //字体加粗
        //font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //设置字体名字
        font.setFontName("Courier New");
        //设置样式;
        XSSFCellStyle style = workbook.createCellStyle();
        //设置底边框;
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        //设置底边框颜色;
        style.setBottomBorderColor(HSSFColor.BLACK.index);
        //设置左边框;
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        //设置左边框颜色;
        style.setLeftBorderColor(HSSFColor.BLACK.index);
        //设置右边框;
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        //设置右边框颜色;
        style.setRightBorderColor(HSSFColor.BLACK.index);
        //设置顶边框;
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        //设置顶边框颜色;
        style.setTopBorderColor(HSSFColor.BLACK.index);
        //在样式用应用设置的字体;
        style.setFont(font);
        //设置自动换行;
        style.setWrapText(false);
        //设置水平对齐的样式为居中对齐;
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //设置垂直对齐的样式为居中对齐;
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

        return style;
    }

    private static byte[] getBytes(String filePath){
        byte[] buffer = null;
        try {
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
            byte[] b = new byte[1000];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            fis.close();
            bos.close();
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer;
    }
}
/**
 * 自定义xssf日期工具类
 * @author lp
 *
 */
class XSSFDateUtil extends DateUtil{
    protected static int absoluteDay(Calendar cal, boolean use1904windowing) {
        return DateUtil.absoluteDay(cal, use1904windowing);
    }
}