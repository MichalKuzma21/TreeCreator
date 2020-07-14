import model.{Node, RowData}
import org.scalatest.FunSuite

class TreeBuilderTest extends FunSuite {

  test("Empty list of rows") {
    assert(TreeBuilder.tree(List.empty) === List.empty)
  }

  test("One level hierarchy") {
    val rows = List(RowData("A", 1), RowData("B", 2), RowData("C", 3), RowData("D", 4))

    assert(TreeBuilder.tree(rows) ===
      List(
        Node(1, "A", List.empty),
        Node(2, "B", List.empty),
        Node(3, "C", List.empty),
        Node(4, "D", List.empty)
      ))
  }

  test("Four level hierarchy") {
    val rows =
      List(
        RowData("A", 1),
        RowData("AA", 2),
        RowData("AAA", 3),
        RowData("AAA1", 4),
        RowData("B", 5),
        RowData("BA", 6))

    assert(TreeBuilder.tree(rows) ===
      List(
        Node(1, "A", List(Node(2, "AA", List(Node(3, "AAA", List(Node(4, "AAA1", List.empty))))))),
        Node(5, "B", List(Node(6, "BA", List.empty)))
      ))
  }

  test("Ignore incorrect names") {
    val rows =
      List(
        RowData("A", 1),
        RowData("AA", 2),
        RowData("B", 3),
        RowData("BA", 4),
        RowData("CA", 5),
        RowData("D", 6))

    assert(TreeBuilder.tree(rows) ===
      List(
        Node(1, "A", List(Node(2, "AA", List.empty))),
        Node(3, "B", List(Node(4, "BA", List.empty))),
        Node(6, "D", List.empty)
      ))
  }

}
