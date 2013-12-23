import org.scalatest._
import itertools.{Chain, Cycle, Repeat, Count}

class ChainTest extends FlatSpec {
  	"A Chain" should "be instantiated from Iterables" in {
    	val chain = Chain(Array(1,2,3), Array(4,5,6))
  	}

  	it should "be an Iterable" in {
  	  	val chain: Iterable[Int] = Chain(Array(1,2,3), Array(4,5,6))
  	}

  	it should "yield the correct values in the correct order" in {
  	  	val chain = Chain(Array(1,2,3), Array(4,5,6))
  	  	assert(chain.toArray === Array(1,2,3,4,5,6))
  	}

  	it should "be capable of mixing iterable types" in {
  	  	val chain = Chain(Array(1,2,3), Array(4,5,6), List(7,8,9))
  	  	assert(chain.toArray === Array(1,2,3,4,5,6,7,8,9))
  	}

  	"A ChainIterator" should "throw NoSuchElementException when it has been exhausted" in {
   	 	val chain = Chain(Array(1,2,3), Array(4,5,6), List(7,8,9))
   	 	val chainIterator = chain.iterator
  	  	assert(chainIterator.toArray === Array(1,2,3,4,5,6,7,8,9))
  	  	intercept[NoSuchElementException] {
  	  		chainIterator.next()
  	  	}
  	}
}

// class ProductTest extends FlatSpec {
// 	"A CartesianProduct" should "be instantiated from Iterables" in {
// 		val product = CartesianProduct(Array(1,2,3), Array("A","B"))
// 	}

// 	it should "be an Iterable" in {
// 		val product: Product[Int, String]
// 	}
// }

class CycleTest extends FlatSpec {
	"A Cycle" should "be instantiated from an Iterable" in {
		val cycle = Cycle(Array(1,2,3))
	}

	it should "be an Iterable" in {
		val cycle: Iterable[Int] = Cycle(Array(1,2,3))
	}

	it should "yield the correct values in the correct order" in {
  	  	val cycle = Cycle(Array(1,2,3))
  	  	assert(cycle.take(4) === List(1,2,3,1))
  	}
}

class RepeatTest extends FlatSpec {
	"A Repeat" should "be instantiated from an Iterable" in {
		val repeat = Repeat("A",3)
	}

	it should "be an Iterable" in {
		val repeat: Iterable[String] = Repeat("A",3)
	}

	it should "yield the correct values in the correct order" in {
  	  	val repeat = Repeat("A",3)
  	  	assert(repeat.toArray === Array("A","A","A"))
  	}

  	"A RepeatIterator" should "throw NoSuchElementException when it has been exhausted" in {
  		val repeat = Repeat("A", 3)
  		val repeatIterator = repeat.iterator
  		for (i <- 0 until 3) {
  			repeatIterator.next()
  		}
  		intercept[NoSuchElementException] {
  			repeatIterator.next()
  		}
  	}

  	it should "return false for hasNext when it has been exhausted" in {
  		val repeat = Repeat("A", 3)
  		val repeatIterator = repeat.iterator
  		for (i <- 0 until 3) {
  			repeatIterator.next()
  		}
  		assert(!repeatIterator.hasNext)
  	}
}

class CountTest extends FlatSpec {
	"A Count" should "be instantiated from an Iterable" in {
		val count = Count(0, 2)
	}

	it should "be an Iterable" in {
		val count: Iterable[Int] = Count(0, 3)
	}

	it should "yield the correct values in the correct order" in {
		val count = Count(0, 3)
		assert(count.take(3) == List(0,3,6))
	}

}



