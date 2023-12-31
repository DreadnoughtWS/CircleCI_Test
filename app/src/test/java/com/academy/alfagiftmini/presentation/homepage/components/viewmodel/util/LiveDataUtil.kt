package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.paging.CombinedLoadStates
import androidx.paging.DifferCallback
import androidx.paging.NullPaddedList
import androidx.paging.PagingData
import androidx.paging.PagingDataDiffer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.TestCoroutineDispatcher
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValueTest(
  time: Long = 2,
  timeUnit: TimeUnit = TimeUnit.SECONDS,
  afterObserve: (T) -> Unit = {}
): T {
  var data: T? = null
  val latch = CountDownLatch(1)
  val observer = object : Observer<T> {
    override fun onChanged(o: T) {
      println(o)
      data = o
      latch.countDown()
      this@getOrAwaitValueTest.removeObserver(this)
    }

  }
  this.observeForever(observer)

  try {
    data?.let { afterObserve.invoke(it) }

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
      throw TimeoutException("LiveData value was never set.")
    }

  } finally {
    this.removeObserver(observer)
  }

  @Suppress("UNCHECKED_CAST")
  return data as T
}

suspend fun <T : Any> PagingData<T>.collectData(): List<T> {
  val dcb = object : DifferCallback {
    override fun onChanged(position: Int, count: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
  }
  val items = mutableListOf<T>()
  val dif = object : PagingDataDiffer<T>(dcb,TestCoroutineDispatcher()) {
    override suspend fun presentNewList(
      previousList: NullPaddedList<T>,
      newList: NullPaddedList<T>,
      lastAccessedIndex: Int,
      onListPresentable: () -> Unit
    ): Int? {
      for (idx in 0 until newList.size)
        items.add(newList.getFromStorage(idx))
      return null
    }
  }
  dif.collectFrom(this)
  return items
}
suspend fun <T : Any> PagingData<T>.collectDataForTest(testDispatcher: TestCoroutineDispatcher): List<T> {
  val dcb = object : DifferCallback {
    override fun onChanged(position: Int, count: Int) {}
    override fun onInserted(position: Int, count: Int) {}
    override fun onRemoved(position: Int, count: Int) {}
  }
  val items = mutableListOf<T>()
  val dif = object : PagingDataDiffer<T>(dcb, testDispatcher) {
    override suspend fun presentNewList(
      previousList: NullPaddedList<T>,
      newList: NullPaddedList<T>,
      lastAccessedIndex: Int,
      onListPresentable: () -> Unit
    ): Int? {
      for (idx in 0 until newList.size)
        items.add(newList.getFromStorage(idx))
      onListPresentable()
      return null
    }
  }
  dif.collectFrom(this)
  return items
}