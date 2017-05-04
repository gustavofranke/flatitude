package ie.gustavo

object Core {
  object FileParser {
    val lines = (filePath: String) => io.Source
      .fromFile(filePath)
      .getLines
      .toList
  }

  object CustomerParser {
    case class Customer(latitude: String, user_id: Int, name: String, longitude: String)
    val customers = (cs: List[String]) => cs.map {
      import org.json4s._
      import org.json4s.jackson.JsonMethods._
      implicit val formats = DefaultFormats

      parse(_).extract[Customer]
    }
  }

  case class Coordinate(latitude: Double, longitude: Double)
  object Ellipsoid {
    /**
      * @see https://en.wikipedia.org/wiki/Great-circle_distance
      */
    val haversine = (coord1: Coordinate, coord2: Coordinate) => {
      import math._
      val dLat = (coord2.latitude - coord1.latitude).toRadians
      val dLon = (coord2.longitude - coord1.longitude).toRadians

      val a = pow(sin(dLat / 2), 2) + pow(sin(dLon / 2), 2) * cos(coord1.latitude.toRadians) * cos(coord2.latitude.toRadians)
      val c = 2 * asin(sqrt(a))
      6371 * c
    }
  }

  class Invitation(lines: List[String]) {
    val intercomLatitude = 53.3381985
    val intercomLongitude = -6.2592576
    val radiusThreshold = 100

    val invited = CustomerParser.customers(lines)
      .map(cust => (cust.name, cust.user_id, Ellipsoid.haversine(Coordinate(cust.latitude.toDouble, cust.longitude.toDouble), Coordinate(intercomLatitude, intercomLongitude))))
      .filter(_._3 <= radiusThreshold)
      .sortBy(_._2)
  }
}