import model.RowData

trait WorkbookReader {
  def readRows(pathToFile: String, ignoreHeader: Boolean, sheetIndex: Int): List[RowData]
}