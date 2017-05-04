import org.scalatest.FlatSpec

class FlattenSuite extends FlatSpec {

  /**
    * Write some code, that will flatten an array of arbitrarily
    * nested arrays of integers into a flat array of integers.
    * e.g. [[1,2,[3]],4] -> [1,2,3,4].
    * @param ls an input list of any
    * @return a list of any
    */
  def flatten(ls: List[Any]): List[Any] = ls flatMap {
    case i: List[_] => flatten(i)
    case e => List(e)
  }

  it should "flatten the given example" in {
    val given1 = List(List(1,2,List(3)),4)
    val given2 = List(1, List(2, 3), List(List(List(List(4)), List(5)), List(6, 7)), 8)

    assert(flatten(given1) == List(1, 2, 3, 4))
    assert(flatten(given2) == List(1, 2, 3, 4, 5, 6, 7, 8))
  }
}
