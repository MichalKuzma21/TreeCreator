package model

import io.circe.Encoder
import io.circe.generic.semiauto.deriveEncoder

final case class Node(id: Int, name: String, nodes: List[Node])

object Node {
  implicit val nodeEncoder: Encoder[Node] = deriveEncoder
}

