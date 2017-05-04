package ie.gustavo

object Main extends App {
  import Core._

  val path = args(0)
  val lines: List[String] = FileParser.lines(path)

  val invitation = new Invitation(lines)
  invitation.invited.foreach { case (name, id, distance) => println(s"$name | $id") }
}
