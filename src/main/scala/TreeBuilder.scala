import model.{Node, RowData}

object TreeBuilder {
  def tree(rows: List[RowData], parentName: String = ""): List[Node] = {
    rows
      .filter(_.containsChildrenOf(parentName))
      .map(rowData => {
        Node(rowData.id, rowData.name, tree(rows, rowData.name))
      })
  }
}
