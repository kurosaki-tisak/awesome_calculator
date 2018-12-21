package com.devtee.awesomecalculator.feature.numberpad

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.amshove.kluent.`should equal`
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner.Silent::class)
class NumberPadViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    lateinit var viewModel: NumberPadViewModel

    @Before
    fun `setup`() {
        MockitoAnnotations.initMocks(this)

        viewModel = NumberPadViewModel()
    }

    @Test
    fun `should cut zero when prev == 0 test`() {
        viewModel.itemPadClickListener("0")
        viewModel.itemPadClickListener("1")
        viewModel.itemPadClickListener("2")

        viewModel.getData().value `should equal` "12"
    }

    @Test
    fun `should not allow double zero when prev == 0 test`() {
        viewModel.itemPadClickListener("0")
        viewModel.itemPadClickListener("0")

        viewModel.getData().value `should equal` "0"
    }

    @Test
    fun `should not allow double dot test`() {
        viewModel.itemPadClickListener(".")
        viewModel.itemPadClickListener(".")

        viewModel.getData().value `should equal` "0."
    }

    @Test
    fun `should clear value to 0 test`() {
        viewModel.itemPadClickListener("1")
        viewModel.itemPadClickListener("2")
        viewModel.itemPadClickListener("1")
        viewModel.itemPadClickListener("C")

        viewModel.getData().value `should equal` "0"
    }
}