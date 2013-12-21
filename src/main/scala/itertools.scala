package itertools

class Chain[T](val iterables: Iterable[T]*) extends Iterator[T] {
	val iteratorIterator = (for (iterable <- iterables) yield iterable.iterator).iterator
	var currentIterator: Iterator[T] = _
	if (iteratorIterator.hasNext) {
		currentIterator = iteratorIterator.next()
	}

	private def update() = {
		while ((!currentIterator.hasNext) && iteratorIterator.hasNext) {
			currentIterator = iteratorIterator.next()
		}
	}

	def next() = {
		update()
		currentIterator.next()
	}

	def hasNext = {
		update()
		currentIterator.hasNext
	}

}


object Chain {
	def apply[T](iterables: Iterable[T]*) = {new Chain(iterables:_*)}
}


