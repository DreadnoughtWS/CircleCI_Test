package com.academy.alfagiftmini.presentation.homepage.components.viewmodel

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListUpdateCallback
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.rules.TestWatcher
import org.junit.runner.Description
import kotlin.coroutines.CoroutineContext

@OptIn(ExperimentalCoroutinesApi::class)
class MainDispatcherRule(
  private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
) : TestWatcher() {
  override fun starting(description: Description) {
    Dispatchers.setMain(testDispatcher)
  }

  override fun finished(description: Description) {
    Dispatchers.resetMain()
  }
}


class TestListCallback : ListUpdateCallback {
  override fun onChanged(position: Int, count: Int, payload: Any?) {}
  override fun onMoved(fromPosition: Int, toPosition: Int) {}
  override fun onInserted(position: Int, count: Int) {}
  override fun onRemoved(position: Int, count: Int) {}
}

class TestDiffCallback<T> : DiffUtil.ItemCallback<T>() {
  override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
    return oldItem == newItem
  }

  override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
    return oldItem == newItem
  }
}