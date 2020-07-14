package model

final case class RowData(name: String, id: Int) {
  def containsChildrenOf(parentName: String): Boolean = {
    name.matches(parentName + ".")
  }
}
