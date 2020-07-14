package model

final case class Node(id: Int, name: String, nodes: List[Node])
