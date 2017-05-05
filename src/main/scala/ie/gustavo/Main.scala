package ie.gustavo

object Main extends App {
  import Core._

  require(args.length == 1, "\n Usage: sbt \"run path to the input file\"")
  val path = args(0)
  val lines: List[String] = FileParser.lines(path)

  Invitation(lines).invited
    .foreach { case (name, id, distance) => println(s"$name | $id") }
}
