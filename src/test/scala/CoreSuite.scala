import ie.gustavo.Core.{Coordinate, CustomerParser, Ellipsoid}
import ie.gustavo.Core.CustomerParser.Customer
import org.scalatest.FlatSpec

class CoreSuite extends FlatSpec {

  "CustomerParser" should "convert json strings in customer objects" in {
    val s1 = """{"latitude": "52.986375", "user_id": 12, "name": "Christina McArdle", "longitude": "-6.043701"}"""
    val s2 = """{"latitude": "53", "user_id": 13, "name": "Olive Ahearn", "longitude": "-7"}"""
    val given: List[String] = List(s1, s2)

    val c1: Customer = Customer("52.986375", 12, "Christina McArdle", "-6.043701")
    val c2: Customer = Customer("53", 13, "Olive Ahearn", "-7")
    val expected = List(c1, c2)

    assert(CustomerParser.customers(given) == expected)
  }

  "Ellipsoid" should "return the distance in km to the intercom office" in {
    val within100Km = Coordinate(53.038056, -7.653889)
    val outside100km = Coordinate(51.92893, -10.27699)
    val intercomOffice = Coordinate(53.3381985, -6.2592576)

    assert(Ellipsoid.haversine(within100Km, intercomOffice) == 98.72968046311739)
    assert(Ellipsoid.haversine(outside100km, intercomOffice) == 313.0975108657996)
  }
}
