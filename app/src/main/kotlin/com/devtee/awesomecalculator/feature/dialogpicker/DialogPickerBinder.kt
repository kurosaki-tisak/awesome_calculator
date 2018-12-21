package com.devtee.awesomecalculator.feature.dialogpicker

import androidx.fragment.app.DialogFragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.devtee.awesomecalculator.databinding.FragmentDialogPickerBinding
import com.devtee.awesomecalculator.di.utils.ViewModelFactory
import javax.inject.Inject

class DialogPickerBinder @Inject constructor(viewModelFactory: ViewModelFactory,
                                             fragment: DialogFragment,
                                             binding: FragmentDialogPickerBinding,
                                             list: ArrayList<PickerItem>,
                                             private val submit: (Pair<Int, PickerItem>) -> Unit?) {

    private var view: DialogPickerView
    private val viewModel = ViewModelProviders.of(fragment, viewModelFactory).get(DialogPickerViewModel::class.java)

    init {
        val itemClicked: (PickerItem?) -> Unit = { viewModel.onSubmit(it!!) }
        view = DialogPickerViewImpl(fragment, binding, itemClicked)

        viewModel.setupBoxList(list)

        binding.viewModel = viewModel
    }

    fun bindTo(owner: LifecycleOwner) {
        viewModel.submit.observe(owner, Observer { submit.invoke(it) })
        viewModel.listItemsLiveData.observe(owner, Observer { view.setItems(it) })
        viewModel.isDismissDialog.observe(owner, Observer { view.dismissDialog() })
    }

    fun setupPreselectedItem(preselected: String?) {
        viewModel.setupPreselectedItem(preselected)
    }
}