import org.scalatest._
import itertools.Chain

class ChainTest extends FlatSpec {
  "A Chain" should "be instantiated from Iterables" in {
    val chain = Chain(Array(1,2,3), Array(4,5,6))
  }

  "A Chain" should "be an iterator" in {
    val chain: Iterator[Int] = Chain(Array(1,2,3), Array(4,5,6))
  }

  "A Chain" should "yield the correct values in the correct order" in {
    val chain = Chain(Array(1,2,3), Array(4,5,6))
    assert(chain.toArray === Array(1,2,3,4,5,6))
  }

  "A Chain" should "be capable of mixing iterable types" in {
    val chain = Chain(Array(1,2,3), Array(4,5,6), List(7,8,9))
    assert(chain.toArray === Array(1,2,3,4,5,6,7,8,9))
  }


}
