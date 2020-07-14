import model.RowData
import org.scalatest.FunSuite

class XLSXReaderTest extends FunSuite {

  test("Read rows from test file") {
    val path = "./src/test/scala/resources/FileWithHeader.xlsx"
    val rows = XLSXReader.readRows(path, ignoreHeader = true)
    assert(rows === List(RowData("A", 1), RowData("AA", 2), RowData("B", 3), RowData("BB", 4)))
  }

  test("Ignoring header") {
    val pathToFileWithHeader = "./src/test/scala/resources/FileWithHeader.xlsx"
    val pathToFileWithoutHeader = "./src/test/scala/resources/FileWithoutHeader.xlsx"
    val deletedHeader = XLSXReader.readRows(pathToFileWithHeader, ignoreHeader = true)
    val noHeader = XLSXReader.readRows(pathToFileWithoutHeader, ignoreHeader = false)
    assert(deletedHeader === noHeader)
  }

}
