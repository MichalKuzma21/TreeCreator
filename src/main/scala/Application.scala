import io.circe.syntax._

object Application extends App {

  val path = "./src/main/scala/resources/test1.xlsx"
  val rows = XLSXReader.readRows(path, ignoreHeader = true)
  print(TreeBuilder.tree(rows).asJson)
}