import model.RowData
import org.scalatest.FunSuite

class RowDataTest extends FunSuite {

  test("Root element") {
    assert(RowData("A", 1).containsChildrenOf(""))
  }

  test("Ending with letter") {
    assert(RowData("AA", 2).containsChildrenOf("A"))
  }

  test("Ending with digit") {
    assert(RowData("AA1", 2).containsChildrenOf("AA"))
  }

  test("Same name") {
    assert(RowData("AA", 2).containsChildrenOf("AA") === false)
  }

  test("Not children") {
    assert(RowData("AA", 2).containsChildrenOf("B") === false)
  }
}
