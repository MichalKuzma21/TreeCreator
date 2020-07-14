import java.io.File

import model.RowData
import org.apache.poi.ss.usermodel.Row
import org.apache.poi.xssf.usermodel.{XSSFSheet, XSSFWorkbook}

import scala.util.{Failure, Success, Try}

object XLSXReader extends WorkbookReader {

  private def sheet(path: String, sheetIndex: Int = 0): Try[XSSFSheet] = {
    Try({
      val workbook = new XSSFWorkbook(new File(path))
      val sheet = workbook.getSheetAt(sheetIndex)
      workbook.close()
      sheet
    })
  }

  override def readRows(pathToFile: String, ignoreHeader: Boolean, sheetIndex: Int = 0): List[RowData] = {
    sheet(pathToFile, sheetIndex) match {
      case Success(sheet) =>
        var rows = List[RowData]()
        val iterator = sheet.iterator()
        if (ignoreHeader)
          iterator.next()
        while (iterator.hasNext) {
          rows = rows :+ rowData(iterator.next())
        }
        rows
      case Failure(exception) =>
        println(exception.getMessage)
        List.empty
    }
  }

  private def rowData(row: Row): RowData = {
    val lastCell = row.getCell(row.getLastCellNum - 1) // moze to zabezpieczyc
    val id = lastCell.getNumericCellValue.toInt
    row.removeCell(lastCell)
    val iterator = row.cellIterator()
    var data = ""
    while (iterator.hasNext) {
      data = data + iterator.next()
    }
    RowData(data, id)
  }
}

