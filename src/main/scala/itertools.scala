package itertools
import generator.Generator

class Chain[T](val iterables: Iterable[T]*) extends Iterable[T] {
	def iterator = {
		new ChainIterator(this)
	}
}
object Chain {
    def apply[T](iterables: Iterable[T]*) = {
        new Chain(iterables:_*)
    }
}
class ChainIterator[T](val chain: Chain[T]) extends Generator[T] {
    def generate() = {
        val iterableIterator = chain.iterables.iterator
        var thisIterator: Iterator[T] = null
        while (iterableIterator.hasNext) {
            thisIterator = iterableIterator.next().iterator
            while (thisIterator.hasNext) {
                produce(thisIterator.next())
            }
        }
    }
}

// Infinite Iterables

class Cycle[T](val iterable: Iterable[T]) extends Iterable[T] {
	def iterator = {
		new CycleIterator(this)
	}
}
object Cycle {
	def apply[T](iterable: Iterable[T]) = {
		new Cycle(iterable)
	}
}
class CycleIterator[T](val cycle: Cycle[T]) extends Generator[T] {
	def generate() = {
        var iterator: Iterator[T] = null
        while (true) {
            iterator = cycle.iterable.iterator
            while (iterator.hasNext) {
                produce(iterator.next())
            }
        }
	}
}

class Repeat[T](val item: T, val n: Int) extends Iterable[T] {
	def iterator = {
		new RepeatIterator(this)
	}
}
object Repeat {
	def apply[T](item: T, n: Int) = {
		new Repeat(item, n)
	}
}
class RepeatIterator[T](val repeat: Repeat[T]) extends Generator[T] {
	def generate() = {
		var count: Int = 0
        while (count < repeat.n) {
        	produce(repeat.item)
        	count += 1
        }
	}
}

class Count(val start: Int, val step: Int) extends Iterable[Int] {
	def iterator = {
		new CountIterator(this)
	}
}
object Count {
	def apply(start: Int, step: Int = 1) = {
		new Count(start, step)
	}
}
class CountIterator(val count: Count) extends Generator[Int] {
	def generate() = {
		var counter: Int = count.start
		produce(counter)
		while (true) {
			counter += count.step
			produce(counter)
		}
	}
}
